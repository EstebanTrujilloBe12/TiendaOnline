package com.example.tiendaonline.repository;

import com.example.tiendaonline.entity.Pedido;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    @EntityGraph(attributePaths = {"items", "items.producto"})
    Optional<Pedido> findById(Long id);
}
