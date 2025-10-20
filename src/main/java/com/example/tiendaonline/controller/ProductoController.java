package com.example.tiendaonline.controller;

import com.example.tiendaonline.entity.Categoria;
import com.example.tiendaonline.entity.Producto;
import com.example.tiendaonline.repository.CategoriaRepository;
import com.example.tiendaonline.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    // POST /productos
    @PostMapping
    public Producto crearProducto(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

    // POST /productos/{id}/categorias
    @PostMapping("/{id}/categorias")
    public Producto asignarCategorias(@PathVariable Long id, @RequestBody List<String> nombresCategorias) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Set<Categoria> categorias = new HashSet<>();
        for (String nombre : nombresCategorias) {
            Categoria cat = categoriaRepository.findAll()
                    .stream()
                    .filter(c -> c.getNombre().equalsIgnoreCase(nombre))
                    .findFirst()
                    .orElseGet(() -> {
                        Categoria nueva = new Categoria();
                        nueva.setNombre(nombre);
                        return categoriaRepository.save(nueva);
                    });
            categorias.add(cat);
        }

        producto.setCategorias(categorias);
        return productoRepository.save(producto);
    }

    // GET /productos?categoria=Backend
    @GetMapping
    public List<Producto> listarPorCategoria(@RequestParam String categoria) {
        return productoRepository.findByCategoriasNombre(categoria);
    }
}

