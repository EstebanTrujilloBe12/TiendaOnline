package com.example.tiendaonline.repository;

import com.example.tiendaonline.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByCategoriasNombre(String nombre);
}