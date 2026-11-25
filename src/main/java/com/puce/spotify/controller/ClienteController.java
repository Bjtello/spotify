package com.puce.spotify.controller;

import com.puce.spotify.model.Cliente;
import com.puce.spotify.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    //TRAER TODOS
    @GetMapping
    public List<Cliente> verTodos(){
        return clienteService.traerTodos();
    }

    //TRAER UNO
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> traerUno(@PathVariable int id){
        return clienteService.traerUno(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //GUARDAR
    @PostMapping
    public ResponseEntity<Cliente> guardar(@RequestBody Cliente cliente){
        Cliente nuevoCliente = clienteService.guardar(cliente);
        return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
    }

    //Especial: GET /api/clientes/ciudad?nombre=Quito
    @GetMapping("/ciudad")
    public List<Cliente> buscarPorCiudad(@RequestParam String nombre){
        return clienteService.buscarPorCiudad(nombre);
    }
}