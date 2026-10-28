package com.krakedev.proyectos.controllers;

import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.krakedev.proyectos.entidades.Usuario;
import com.krakedev.proyectos.repositories.UsuarioRepository;
import com.krakedev.proyectos.security.JwtUtil;
import com.krakedev.proyectos.services.TokenBlackListService;
import com.krakedev.proyectos.services.UsuarioService;


import java.util.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	private final UsuarioService usuarioService;
	private final UsuarioRepository usuarioRepository;
	private final TokenBlackListService tokenBlackListService;

    // Inyección por constructor
    public AuthController(UsuarioService usuarioService, UsuarioRepository usuarioRepository, TokenBlackListService tokenBlackListService ) {
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
        this.tokenBlackListService = tokenBlackListService;
    }
    
    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody Usuario usuario) {
        try {
            Usuario usuarioNuevo = usuarioService.registrar(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioNuevo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al registrar usuario: " + e.getMessage());
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
        String username = usuario.getUsername();
        String password = usuario.getPassword();
        
        boolean autenticado = usuarioService.autenticar(username, password);
        
        if (autenticado) {
            Usuario usuarioEncontrado = usuarioRepository.findByUsername(username).get();
            String token = JwtUtil.generarToken(usuarioEncontrado.getUsername(), usuarioEncontrado.getRol());  //Se añade el Jwtutil
            return ResponseEntity.ok(Map.of("token", token));
            
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario o contraseña incorrecta");
        }
    }
    
  // -------------------------------  Metodo anterior sin refactorizacion ---------------------------------------
//    @GetMapping("/perfil")      
//    public ResponseEntity<?> verPerfil(@RequestHeader(value = "Authorization", required = false) String authHeader) {
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                    .body("Acceso Denegado: Debes proveer un token Bearer valido en la cabecera Authorization");
//        }
//        
//        String token = authHeader.substring(7);
//        DecodedJWT datosToken = JwtUtil.validarToker(token);
//        
//        if (datosToken == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Acceso Denegado: Token Invalido o Expirado");
//        }
//        
//        String usuario = datosToken.getSubject();
//        String rol = datosToken.getClaim("rol").asString();
//        
//      //Mensaje diferencia segun el rol
//        String mensaje;
//        switch (rol == null ? "" : rol.toUpperCase()) {
//            case "ADMIN":
//                mensaje = "Bienvenido Administrador. Tienes permisos totales para gestionar usuarios, rescates y adopciones.";
//                break;
//            case "USER":
//                mensaje = "Bienvenido Voluntario del Refugio. Tienes permisos para gestionar rescates y adopciones.";
//                break;
//            default:
//                mensaje = "Bienvenido. Tu rol no tiene permisos especiales asignados.";
//        }
//        
//        return ResponseEntity.ok(Map.of(
//            "Mensaje", mensaje,
//            "Usuario", usuario,
//            "Rol", rol,
//            "estatus", "Autenticado Exitosamente"
//        ));
//    }
    
    
    //Metodo Refactorizado de Perfil
    @GetMapping("/perfil")
    public ResponseEntity<?> verPerfil() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String usuario = auth.getName();
        String rol = auth.getAuthorities().iterator().next().getAuthority();

        return ResponseEntity.ok(Map.of("Mensaje", "Bienvenido al sistema protegido por Spring Security", 
						        		"Usuario", usuario, 
						        		"rol_detectado", rol, 
						        		"status", "Autenticado Exitosamente"));
    }
    
    //Para el logout
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            tokenBlackListService.invalidarToken(token);
            return ResponseEntity.ok(Map.of("Mensaje", "Sesión Cerrada Exitosamente. Token Invalido"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token no proporcionado ");
        }
    }
    
    @GetMapping("/dashboard")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> adminDashboard() {

        String usuario = SecurityContextHolder.getContext().getAuthentication().getName();

        return ResponseEntity
                .ok(Map.of("Mensaje", "Bienvenido al panel secreto de administradores", "ADMIN", usuario));
    }
}
