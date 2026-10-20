package com.krakedev.proyectos.controllers;

import java.util.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.krakedev.proyectos.entidades.Proyecto;
import com.krakedev.proyectos.services.ProyectoService;

@RestController
@RequestMapping("/api/proyectos")
public class ProyectoController {

	private final ProyectoService proyectoService;

    public ProyectoController(ProyectoService proyectoService) {
		this.proyectoService = proyectoService;
	}

	// POST /api/proyectos
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Proyecto proyecto) {
        try {
            Proyecto guardado = proyectoService.guardar(proyecto);
            return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear proyecto: " + e.getMessage());
        }
    }

    // GET /api/proyectos
    @GetMapping
    public ResponseEntity<?> listar() {
        try {
            List<Proyecto> lista = proyectoService.listar();
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al listar proyectos: " + e.getMessage());
        }
    }

    // GET /api/proyectos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable int id) {
        try {
            Proyecto p = proyectoService.buscarPorId(id);
            if (p == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Proyecto no encontrado con id: " + id);
            }
            return ResponseEntity.ok(p);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar proyecto: " + e.getMessage());
        }
    }

    // PUT /api/proyectos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable int id, @RequestBody Proyecto proyecto) {
        try {
            Proyecto actualizado = proyectoService.actualizar(id, proyecto);
            if (actualizado == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Proyecto no encontrado con id: " + id);
            }
            return ResponseEntity.ok(actualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar proyecto: " + e.getMessage());
        }
    }

    // DELETE /api/proyectos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        try {
            boolean eliminado = proyectoService.eliminar(id);
            if (!eliminado) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Proyecto no encontrado con id: " + id);
            }
            return ResponseEntity.ok("Proyecto eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar proyecto: " + e.getMessage());
        }
    }
}
