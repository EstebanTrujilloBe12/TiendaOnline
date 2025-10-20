package com.example.tiendaonline.dto;

import java.util.List;

public class PedidoDTO {
    private Long id;
    private String estado;
    private Long clienteId;
    private List<ItemPedidoDTO> items;
    public PedidoDTO() {}
    // getters/setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
    public List<ItemPedidoDTO> getItems() { return items; }
    public void setItems(List<ItemPedidoDTO> items) { this.items = items; }
}
