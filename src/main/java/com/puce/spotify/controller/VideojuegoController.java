package com.puce.spotify.controller;

import com.puce.spotify.model.Videojuego;
import com.puce.spotify.service.VideojuegoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/videojuegos")
public class VideojuegoController {

    private VideojuegoService videojuegoService;

    public VideojuegoController(VideojuegoService videojuegoService){
        this.videojuegoService = videojuegoService;
    }

    //TRAER TODOS
    @GetMapping
    public List<Videojuego> traerTodos(){
        return videojuegoService.traerTodos();
    }

    //GUARDAR
    @PostMapping
    public Videojuego guardar(@RequestBody Videojuego videojuego){
        return videojuegoService.guardar(videojuego);
    }

    //Especial: GET /api/videojuegos/precio?min=20&max=60
    @GetMapping("/precio")
    public List<Videojuego> rangoPrecio(@RequestParam double min, @RequestParam double max){
        return videojuegoService.rangoPrecio(min, max);
    }
}
