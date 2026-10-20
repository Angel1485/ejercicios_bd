package com.krakedev.proyectos.controllers;

import java.util.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.krakedev.proyectos.entidades.Tarea;
import com.krakedev.proyectos.services.TareaService;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {
	
	private final TareaService tareaService;

    public TareaController(TareaService tareaService) {
		this.tareaService = tareaService;
	}

	// POST /api/tareas — recibe tarea con proyecto.id y empleados[].id
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Tarea tarea) {
        try {
            Tarea guardada = tareaService.guardar(tarea);
            return ResponseEntity.status(HttpStatus.CREATED).body(guardada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear tarea: " + e.getMessage());
        }
    }

    // GET /api/tareas
    @GetMapping
    public ResponseEntity<?> listar() {
        try {
            List<Tarea> lista = tareaService.listar();
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al listar tareas: " + e.getMessage());
        }
    }

    // GET /api/tareas/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable int id) {
        try {
            Tarea t = tareaService.buscarPorId(id);
            if (t == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Tarea no encontrada con id: " + id);
            }
            return ResponseEntity.ok(t);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar tarea: " + e.getMessage());
        }
    }

    // PUT /api/tareas/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable int id, @RequestBody Tarea tarea) {
        try {
            Tarea actualizada = tareaService.actualizar(id, tarea);
            if (actualizada == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Tarea no encontrada con id: " + id);
            }
            return ResponseEntity.ok(actualizada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar tarea: " + e.getMessage());
        }
    }

    // DELETE /api/tareas/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        try {
            boolean eliminado = tareaService.eliminar(id);
            if (!eliminado) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Tarea no encontrada con id: " + id);
            }
            return ResponseEntity.ok("Tarea eliminada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar tarea: " + e.getMessage());
        }
    }

}
