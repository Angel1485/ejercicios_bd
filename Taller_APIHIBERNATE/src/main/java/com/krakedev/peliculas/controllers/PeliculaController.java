package com.krakedev.peliculas.controllers;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.krakedev.peliculas.entidades.Pelicula;
import com.krakedev.peliculas.services.ServicioPelicula;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {

	private final ServicioPelicula servicio;
	
	public PeliculaController(ServicioPelicula servicio) {
		this.servicio = servicio;
	}
	
	// POST /peliculas
    @PostMapping("/insert")
    public ResponseEntity<?> crear(@RequestBody Pelicula pelicula) {
    	
    	try {
    		 Pelicula nueva = servicio.crear(pelicula);
			return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la pelicula");
		}

    }

    // GET /peliculas
    @GetMapping("/listar")
    public ResponseEntity<?> listar() {
    	
    	try {
			List<Pelicula> peliculas =  servicio.listar();
			return ResponseEntity.ok(peliculas);
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al listar las peliculas");
		}
    	
    }

    // GET /peliculas/{id}
    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
    	
    	try {
    		Pelicula p = servicio.buscarPorId(id);
			
			if(p == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La pelicula con el Id no existe: " + id);
			}
			return ResponseEntity.ok(p);
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al encontrar la pelicula");
		}
    	
    }

    // PUT /peliculas/{id}
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Pelicula peliculaActualizada) {
    	
    	try {
    		Pelicula actualizada = servicio.actualizar(id, peliculaActualizada);
			
			if(actualizada == null) {
			  return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La pelicula con el Id no existe: " + id);
			}
			return ResponseEntity.ok(actualizada);
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar la pelicula");
		}
    
    }

    // DELETE /peliculas/{id}
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
    	
    	try {
			boolean eliminar =  servicio.eliminar(id);
			
			if(!eliminar) { //Devuelve falso
			  return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La pelicula con el Id no existe: " + id);
			}
			return ResponseEntity.ok(eliminar);
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la pelicual");
		}
    	
    }

    // GET /peliculas/genero?genero=Accion
    @GetMapping("/genero")
    public ResponseEntity<?> buscarPorGenero(@RequestParam String genero) {
    	
    	try {
			List<Pelicula> pelicula = servicio.buscarPorGenero(genero);
			return ResponseEntity.ok(pelicula);
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al buscra por genero");
		}
    	
    }

    // GET /peliculas/disponible?disponible=true
    @GetMapping("/disponible")
    public ResponseEntity<?> buscarPorDisponible(@RequestParam boolean disponible) {
    	
    	try {
			List<Pelicula> pelicula = servicio.buscarPorDisponible(disponible);
			return ResponseEntity.ok(pelicula);
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al buscar por disponibilidad");
		}

    }
	
}
