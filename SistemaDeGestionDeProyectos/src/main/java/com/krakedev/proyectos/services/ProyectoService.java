package com.krakedev.proyectos.services;

import java.util.*;

import org.springframework.stereotype.*;
import com.krakedev.proyectos.entidades.Proyecto;
import com.krakedev.proyectos.repositories.*;

@Service
public class ProyectoService {
	
    private final ProyectoRepository proyectoRepository;

    public ProyectoService(ProyectoRepository proyectoRepository) {
		this.proyectoRepository = proyectoRepository;
	}

	public Proyecto guardar(Proyecto proyecto) {
        return proyectoRepository.save(proyecto);
    }

    public List<Proyecto> listar() {
        return proyectoRepository.findAll();
    }

    public Proyecto buscarPorId(int id) {
        Optional<Proyecto> resultado = proyectoRepository.findById(id);
        return resultado.orElse(null);
    }

    public Proyecto actualizar(int id, Proyecto datos) {
        Proyecto existente = buscarPorId(id);
        if (existente == null) return null;

        existente.setNombre(datos.getNombre());
        existente.setDescripcion(datos.getDescripcion());
        existente.setFechaInicio(datos.getFechaInicio());
        return proyectoRepository.save(existente);
    }

    public boolean eliminar(int id) {
        if (!proyectoRepository.existsById(id)) return false;
        proyectoRepository.deleteById(id);
        return true;
    }

}
