package com.krakedev.fitzone.controller;

import com.krakedev.fitzone.entidades.Socio;
import com.krakedev.fitzone.services.ServicioSocio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/socios")
public class SocioController {
	
	private final ServicioSocio servicioSocio;

    public SocioController(ServicioSocio servicioSocio) {
        this.servicioSocio = servicioSocio;
    }

    // POST /socios/insert
    @PostMapping("/insert")
    public ResponseEntity<?> crear(@RequestBody Socio socio) {
        try {
            Socio nuevo = servicioSocio.crear(socio);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el socio");
        }
    }

    // GET /socios/listar
    @GetMapping("/listar")
    public ResponseEntity<?> listar() {
        try {
            List<Socio> socios = servicioSocio.listar();
            return ResponseEntity.ok(socios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al listar los socios");
        }
    }

    // GET /socios/estado?activo=true
    @GetMapping("/estado")
    public ResponseEntity<?> buscarPorEstado(@RequestParam boolean activo) {
        try {
            List<Socio> socios = servicioSocio.buscarPorEstado(activo);
            return ResponseEntity.ok(socios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al filtrar los socios");
        }
    }

}
