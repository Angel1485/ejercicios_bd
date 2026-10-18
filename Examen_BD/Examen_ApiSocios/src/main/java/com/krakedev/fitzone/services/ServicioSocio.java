package com.krakedev.fitzone.services;

import com.krakedev.fitzone.entidades.Socio;
import com.krakedev.fitzone.repository.SocioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioSocio {
	
	 private final SocioRepository socioRepository;

	    // Inyeccion por constructor
	    public ServicioSocio(SocioRepository socioRepository) {
	        this.socioRepository = socioRepository;
	    }

	    // Crea un socio validando que la edad sea >= 14.

	    public Socio crear(Socio s) {
	        if (s.getEdad() < 14) {
	            throw new IllegalArgumentException("La edad minima permitida es 14 años.");
	        }
	        return socioRepository.save(s);
	    }

	    //Lista los socios
	    public List<Socio> listar() {
	        return socioRepository.findAll();
	    }

	    //Buscar por estado
	    public List<Socio> buscarPorEstado(boolean activo) {
	        return socioRepository.findByActivo(activo);
	    }

}
