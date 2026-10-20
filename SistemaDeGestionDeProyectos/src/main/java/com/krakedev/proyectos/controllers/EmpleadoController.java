package com.krakedev.proyectos.controllers;

import java.util.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.krakedev.proyectos.entidades.Empleado;
import com.krakedev.proyectos.services.EmpleadoService;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {
	
	private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
		this.empleadoService = empleadoService;
	}

	// POST /api/empleados
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Empleado empleado) {
        try {
            Empleado guardado = empleadoService.guardar(empleado);
            return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear empleado: " + e.getMessage());
        }
    }

    // GET /api/empleados
    @GetMapping
    public ResponseEntity<?> listar() {
        try {
            List<Empleado> lista = empleadoService.listar();
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al listar empleados: " + e.getMessage());
        }
    }

    // GET /api/empleados/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable int id) {
        try {
            Empleado emp = empleadoService.buscarPorId(id);
            if (emp == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Empleado no encontrado con id: " + id);
            }
            return ResponseEntity.ok(emp);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar empleado: " + e.getMessage());
        }
    }

    // PUT /api/empleados/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable int id, @RequestBody Empleado empleado) {
        try {
            Empleado actualizado = empleadoService.actualizar(id, empleado);
            if (actualizado == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Empleado no encontrado con id: " + id);
            }
            return ResponseEntity.ok(actualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar empleado: " + e.getMessage());
        }
    }

    // DELETE /api/empleados/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        try {
            boolean eliminado = empleadoService.eliminar(id);
            if (!eliminado) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Empleado no encontrado con id: " + id);
            }
            return ResponseEntity.ok("Empleado eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar empleado: " + e.getMessage());
        }
    }

}
