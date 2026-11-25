package com.puce.spotify.service;

import com.puce.spotify.model.Cliente;
import com.puce.spotify.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    //TRAER TODOS
    public List<Cliente> traerTodos(){
        return clienteRepository.findAll();
    }

    //TRAER UNO
    public Optional<Cliente> traerUno(int id){
        return clienteRepository.findById(id);
    }

    //GUARDAR
    public Cliente guardar(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    //Especial
    public List<Cliente> buscarPorCiudad(String nombre){
        return clienteRepository.findAll()
                .stream().filter(
                        cliente -> cliente.getCiudad() != null &&
                                cliente.getCiudad().equalsIgnoreCase(nombre)
                ).collect(Collectors.toList());
    }
}