package com.example.tiendaonline.controller;

import com.example.tiendaonline.entity.Cliente;
import com.example.tiendaonline.repository.ClienteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // POST → crear cliente con dirección
    @PostMapping
    public Cliente crearCliente(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // GET → listar todos los clientes (con su dirección)
    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }
}
