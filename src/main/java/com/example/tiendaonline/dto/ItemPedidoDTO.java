package com.example.tiendaonline.dto;

import java.math.BigDecimal;

public class ItemPedidoDTO {
    private Long productoId;
    private Integer cantidad;
    private BigDecimal precioUnitario; // opcional en request (se calcula server-side)

    public ItemPedidoDTO() {}
    public Long getProductoId() { return productoId; }
    public void setProductoId(Long productoId) { this.productoId = productoId; }
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    public BigDecimal getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(BigDecimal precioUnitario) { this.precioUnitario = precioUnitario; }
}
