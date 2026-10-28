package com.krakedev.proyectos.services;

import java.util.*;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import com.krakedev.proyectos.entidades.Usuario;
import com.krakedev.proyectos.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository usuarioRepository){
		this.usuarioRepository = usuarioRepository;
	}
	
	/**
     * Guarda un nuevo usuario.
     * Valida que el username no esté repetido.
     */
    public Usuario registrar(Usuario usuario) {
        if (usuario.getUsername() == null || usuario.getUsername().isBlank()) {
            throw new IllegalArgumentException("El username es obligatorio");
        }
        if (usuario.getPassword() == null || usuario.getPassword().isBlank()) {
            throw new IllegalArgumentException("La contraseña es obligatoria");
        }
        if (usuario.getRol() == null || usuario.getRol().isBlank()) {
            throw new IllegalArgumentException("El rol es obligatorio");
        }
        if (usuarioRepository.existsByUsername(usuario.getUsername())) {
            throw new IllegalArgumentException("El username ya está registrado");
        }
        
        //Se añade para la encriptacion
        String contrasenaEncriptada = BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt());
        usuario.setPassword(contrasenaEncriptada);
        
        return usuarioRepository.save(usuario);
    }

    /**
     * Autentica un usuario por username y valida contraseña.
     * Devuelve el Usuario si las credenciales son correctas.
     */

    public boolean autenticar(String username, String password) {
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findByUsername(username);
        if (usuarioEncontrado.isPresent()) {
            Usuario usuario = usuarioEncontrado.get();
            
            if (BCrypt.checkpw(password, usuario.getPassword())) {
                return true;
            }
        }
        
        return false;
    }
	
}
