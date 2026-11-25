package com.puce.spotify.controller;

import com.puce.spotify.model.Cancion;
import com.puce.spotify.service.CancionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/canciones")
public class CancionController {

    private CancionService cancionService;

    public CancionController(CancionService cancionService){
        this.cancionService = cancionService;
    }

    //TRAER TODOS
    @GetMapping
    public List<Cancion> verTodos(){
        return cancionService.traerTodos();
    }

    //TRAER UNO
    @GetMapping("/{id}")
    public ResponseEntity<Cancion> traerUno(@PathVariable int id){
        return cancionService.traerUno(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //GUARDAR
    @PostMapping
    public ResponseEntity<Cancion> guardar(@RequestBody Cancion cancion){
        Cancion nuevaCancion = cancionService.guardar(cancion);
        return new ResponseEntity<>(nuevaCancion, HttpStatus.CREATED);
    }

    //EDITAR
    @PutMapping("/{id}")
    public ResponseEntity<Cancion> editar(@PathVariable int id, @RequestBody Cancion cancion){
        return cancionService.editar(id, cancion)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id){
        if (cancionService.eliminar(id)){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    //Especial: GET /api/canciones/conteo?genero=rock
    @GetMapping("/conteo")
    public long contarPorGenero(@RequestParam String genero){
        return cancionService.contarPorGenero(genero);
    }
}