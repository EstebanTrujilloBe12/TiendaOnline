package com.example.tiendaonline.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fecha;

    @Column(nullable = false)
    private String estado; // NUEVO, PAGADO, ENVIADO

    @Column(nullable = false)
    private BigDecimal total;

    // ✅ Relación con Cliente (Muchos pedidos → un cliente)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    @JsonBackReference(value = "cliente-pedidos") // evita bucle con Cliente
    private Cliente cliente;

    // ✅ Relación con ItemPedido (1:N)
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> items = new ArrayList<>();

    public Pedido() {}

    // ✅ Métodos para manejar los items
    public void addItem(ItemPedido it) {
        items.add(it);
        it.setPedido(this);
    }

    public void removeItem(ItemPedido it) {
        items.remove(it);
        it.setPedido(null);
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemPedido> getItems() {
        return items;
    }

    public void setItems(List<ItemPedido> items) {
        this.items = items;
    }
}

