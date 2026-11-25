package com.puce.spotify.service;

import com.puce.spotify.model.Videojuego;
import com.puce.spotify.repository.VideojuegoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VideojuegoService {

    private VideojuegoRepository videojuegoRepository;

    public VideojuegoService(VideojuegoRepository videojuegoRepository){
        this.videojuegoRepository = videojuegoRepository;
    }

    //TRAER TODOS
    public List<Videojuego> traerTodos(){
        return videojuegoRepository.findAll();
    }

    //TRAER UNO
    public Optional<Videojuego> traerUno(int id){
        return videojuegoRepository.findById(id);
    }

    //GUARDAR
    public Videojuego guardar(Videojuego videojuego){
        return videojuegoRepository.save(videojuego);
    }

    //Especial
    public List<Videojuego> rangoPrecio(double min, double max){
        return videojuegoRepository.findAll()
                .stream().filter(
                        videojuego -> videojuego.getPrecio() >= min && videojuego.getPrecio() <= max
                ).collect(Collectors.toList());
    }
}