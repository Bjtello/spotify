package com.puce.spotify.service;

import com.puce.spotify.model.Cancion;
import com.puce.spotify.repository.CancionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CancionService {

    private CancionRepository cancionRepository;

    public CancionService(CancionRepository cancionRepository){
        this.cancionRepository = cancionRepository;
    }

    //TRAER TODOS
    public List<Cancion> traerTodos(){
        return cancionRepository.findAll();
    }

    //TRAER UNO
    public Optional<Cancion> traerUno(int id){
        return cancionRepository.findById(id);
    }

    //GUARDAR
    public Cancion guardar(Cancion cancion){
        return cancionRepository.save(cancion);
    }

    //EDITAR
    public Optional<Cancion> editar(int id, Cancion cancionActualizada){
        return cancionRepository.findById(id)
                .map(cancionExiste -> {
                    cancionExiste.setTitulo(cancionActualizada.getTitulo());
                    cancionExiste.setArtista(cancionActualizada.getArtista());
                    cancionExiste.setAlbum(cancionActualizada.getAlbum());
                    cancionExiste.setGenero(cancionActualizada.getGenero());
                    cancionExiste.setDuracionSegundos(cancionActualizada.getDuracionSegundos());

                    return cancionRepository.save(cancionExiste);
                });
    }

    //ELIMINAR
    public boolean eliminar(int id){
        if (cancionRepository.existsById(id)){
            cancionRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    //ESPECIAL
    public long contarPorGenero(String genero){
        return cancionRepository.findAll()
                .stream().filter(
                        cancion -> cancion.getGenero() != null &&
                                cancion.getGenero().equalsIgnoreCase(genero)
                ).count();
    }
}