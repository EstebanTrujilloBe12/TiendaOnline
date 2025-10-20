package com.example.tiendaonline.controller;

import com.example.tiendaonline.dto.PedidoDTO;
import com.example.tiendaonline.entity.Pedido;
import com.example.tiendaonline.service.PedidoService;
import com.example.tiendaonline.repository.PedidoRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;
    private final PedidoRepository pedidoRepository;

    public PedidoController(PedidoService pedidoService, PedidoRepository pedidoRepository) {
        this.pedidoService = pedidoService;
        this.pedidoRepository = pedidoRepository;
    }

    @PostMapping
    public Pedido crearPedido(@RequestBody PedidoDTO dto) {
        return pedidoService.crearPedidoDesdeDTO(dto);
    }

    @GetMapping("/{id}")
    public Pedido obtenerPedido(@PathVariable Long id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
    }

    @PutMapping("/{id}/estado")
    public Pedido cambiarEstado(@PathVariable Long id, @RequestParam String valor) {
        return pedidoService.cambiarEstado(id, valor);
    }
}


