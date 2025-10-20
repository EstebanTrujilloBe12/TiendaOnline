package com.example.tiendaonline.dto;

import java.math.BigDecimal;
import java.util.Set;

public class ProductoDTO {
    private Long id;
    private String nombre;
    private BigDecimal precio;
    private Integer stock;
    private Set<String> categorias;
    public ProductoDTO() {}
    // getters/setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }
    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
    public Set<String> getCategorias() { return categorias; }
    public void setCategorias(Set<String> categorias) { this.categorias = categorias; }
}
