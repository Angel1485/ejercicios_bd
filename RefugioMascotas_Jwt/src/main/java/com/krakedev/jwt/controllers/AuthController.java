package com.krakedev.jwt.controllers;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.krakedev.jwt.entidades.Usuario;
import com.krakedev.jwt.services.UsuarioService;
import com.krakedev.jwt.JwtUtil;
import com.krakedev.jwt.repositories.UsuarioRepository;

import java.util.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	private final UsuarioService usuarioService;
	private final UsuarioRepository usuarioRepository;

    // Inyección por constructor
    public AuthController(UsuarioService usuarioService, UsuarioRepository usuarioRepository ) {
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody Usuario usuario) {
        try {
            Usuario usuarioNuevo = usuarioService.guardar(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al registrar usuario: " + e.getMessage());
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credenciales) {
        String username = credenciales.get("username");
        String password = credenciales.get("password");
        
        boolean autenticado = usuarioService.autenticar(username, password);
        
        if (autenticado) {
			Usuario usuario = usuarioRepository.findByUsername(username).get();
            String token = JwtUtil.generarToken(usuario.getUsername(), usuario.getRol());
            return ResponseEntity.ok(Map.of("token", token));
            
        } else {
            
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario o contraseña incorrecta");
        }
    }
    
    @GetMapping("/perfil")
    public ResponseEntity<?> verPerfil(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Acceso Denegado: Debes proveer un token Bearer valido en la cabecera Authorization");
        }
        
        String token = authHeader.substring(7);
        DecodedJWT datosToken = JwtUtil.validarToker(token);
        
        if (datosToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Acceso Denegado: Token Invalido o Expirado");
        }
        
        String usuario = datosToken.getSubject();
        String rol = datosToken.getClaim("rol").asString();
        
      //Mensaje diferencia segun el rol
        String mensaje;
        switch (rol == null ? "" : rol.toUpperCase()) {
            case "ADMIN":
                mensaje = "Bienvenido Administrador. Tienes permisos totales para gestionar usuarios, rescates y adopciones.";
                break;
            case "USER":
                mensaje = "Bienvenido Voluntario del Refugio. Tienes permisos para gestionar rescates y adopciones.";
                break;
            default:
                mensaje = "Bienvenido. Tu rol no tiene permisos especiales asignados.";
        }
        
        return ResponseEntity.ok(Map.of(
            "Mensaje", mensaje,
            "Usuario", usuario,
            "Rol", rol,
            "estatus", "Autenticado Exitosamente"
        ));
    }

}
