package com.krakedev.peliculas.services;
import java.util.*;
import org.springframework.stereotype.Service;
import com.krakedev.peliculas.entidades.Pelicula;
import com.krakedev.peliculas.repository.PeliculaRepository;

@Service
public class ServicioPelicula {

	private final PeliculaRepository repository;

    public ServicioPelicula(PeliculaRepository repository) {
		this.repository = repository;
	}

	// Crear
    public Pelicula crear(Pelicula pelicula) {
        return repository.save(pelicula);
    }

    // Listar todas
    public List<Pelicula> listar() {
        return repository.findAll();
    }

    // Buscar por ID
    public Pelicula buscarPorId(Long id) {
        Optional<Pelicula> resultado = repository.findById(id);
        return resultado.orElse(null);
    }

    // Actualizar
    public Pelicula actualizar(Long id, Pelicula pelicula) {
        Optional<Pelicula> existente = repository.findById(id);
        if (existente.isPresent()) {  //Metodo para saber si existe o no 
            Pelicula p = existente.get();
            p.setNombre(pelicula.getNombre());
            p.setDirector(pelicula.getDirector());
            p.setGenero(pelicula.getGenero());
            p.setDuracion(pelicula.getDuracion());
            p.setDisponible(pelicula.isDisponible());
            return repository.save(p);
        }
        return null;
    }

    // Eliminar  -- Nuevos metodos abreviados para eliminar
    public boolean eliminar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    // Buscar por genero
    public List<Pelicula> buscarPorGenero(String genero) {
        return repository.findByGenero(genero);
    }

    // Buscar por disponibilidad
    public List<Pelicula> buscarPorDisponible(boolean disponible) {
        return repository.findByDisponible(disponible);
    }
}
