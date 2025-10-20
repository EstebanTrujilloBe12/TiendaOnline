package com.example.tiendaonline.service;

import com.example.tiendaonline.entity.*;
import com.example.tiendaonline.repository.*;
import com.example.tiendaonline.dto.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProductoRepository productoRepository;

    public PedidoService(PedidoRepository pedidoRepository,
                         ClienteRepository clienteRepository,
                         ProductoRepository productoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.productoRepository = productoRepository;
    }

    @Transactional
    public Pedido crearPedidoDesdeDTO(PedidoDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setEstado(dto.getEstado());
        pedido.setFecha(java.time.LocalDateTime.now());

        // validar ítems y evitar duplicados
        Set<Long> productosEnPedido = new HashSet<>();
        BigDecimal total = BigDecimal.ZERO;

        if (dto.getItems() != null) {
            for (ItemPedidoDTO itemDto : dto.getItems()) {
                if (!productosEnPedido.add(itemDto.getProductoId())) {
                    throw new RuntimeException("Producto duplicado en el mismo pedido: " + itemDto.getProductoId());
                }
                Producto producto = productoRepository.findById(itemDto.getProductoId())
                        .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + itemDto.getProductoId()));

                if (producto.getStock() < itemDto.getCantidad()) {
                    throw new RuntimeException("Stock insuficiente para producto " + producto.getNombre());
                }

                // decrementa stock
                producto.setStock(producto.getStock() - itemDto.getCantidad());
                productoRepository.save(producto);

                ItemPedido item = new ItemPedido();
                item.setProducto(producto);
                item.setCantidad(itemDto.getCantidad());
                item.setPrecioUnitario(producto.getPrecio());
                pedido.addItem(item);

                total = total.add(producto.getPrecio().multiply(BigDecimal.valueOf(itemDto.getCantidad())));
            }
        }

        pedido.setTotal(total);
        return pedidoRepository.save(pedido);
    }

    @Transactional
    public Pedido cambiarEstado(Long pedidoId, String nuevoEstado) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        // ejemplo de regla: si estaba NUEVO puedes cambiar a CANCELADO y devolver stock (opcional)
        String anterior = pedido.getEstado();
        pedido.setEstado(nuevoEstado);
        // si cancela, revertir stock (ejemplo simple)
        if ("CANCELADO".equalsIgnoreCase(nuevoEstado) && !"CANCELADO".equalsIgnoreCase(anterior)) {
            for (ItemPedido ip : pedido.getItems()) {
                Producto p = ip.getProducto();
                p.setStock(p.getStock() + ip.getCantidad());
                productoRepository.save(p);
            }
        }
        return pedidoRepository.save(pedido);
    }
}

