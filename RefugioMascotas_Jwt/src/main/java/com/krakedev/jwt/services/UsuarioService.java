package com.krakedev.jwt.services;

import com.krakedev.jwt.entidades.Usuario;
import com.krakedev.jwt.repositories.UsuarioRepository;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UsuarioService {
	
	 private final UsuarioRepository usuarioRepository;

	    // Inyección de dependencias por constructor
	    public UsuarioService(UsuarioRepository usuarioRepository) {
	        this.usuarioRepository = usuarioRepository;
	    }

	    /**
	     * Guarda un nuevo usuario.
	     * Valida que el username no esté repetido.
	     */
	    public Usuario guardar(Usuario usuario) {
	        if (usuario.getUsername() == null || usuario.getUsername().isBlank()) {
	            throw new IllegalArgumentException("El username es obligatorio");
	        }
	        if (usuario.getPassword() == null || usuario.getPassword().isBlank()) {
	            throw new IllegalArgumentException("La contraseña es obligatoria");
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
//	    public Usuario autenticar(String username, String password) {
//	        Optional<Usuario> opt = usuarioRepository.findByUsername(username);
	//
//	        if (opt.isEmpty()) {
//	            throw new IllegalArgumentException("Credenciales inválidas");
//	        }
	//
//	        Usuario usuario = opt.get();
//	        if (!usuario.getPassword().equals(password)) {
//	            throw new IllegalArgumentException("Credenciales inválidas");
//	        }
	//
//	        return usuario;
//	    }

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
	 
	 // Autenticar usuario por boolean
	    public boolean autenticarToF(String username, String password) {
	        Optional<Usuario> usuarioOpt = usuarioRepository.findByUsername(username);
	        
	        if (usuarioOpt.isPresent()) {
	            Usuario usuario = usuarioOpt.get();
	            
	            if (usuario.getPassword().equals(password)) {
	                return true;
	            }
	        }
	        return false;
	    }


	    /**
	     * Busca un usuario por username (método auxiliar).
	     */
	    public Optional<Usuario> buscarPorUsername(String username) {
	        return usuarioRepository.findByUsername(username);
	    }

}
