package com.krakedev.proyectos.services;

import java.util.*;
import org.springframework.stereotype.*;
import com.krakedev.proyectos.entidades.Empleado;
import com.krakedev.proyectos.repositories.EmpleadoRepository;

@Service
public class EmpleadoService {
	
	private final EmpleadoRepository empleadoRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository) {
		super();
		this.empleadoRepository = empleadoRepository;
	}

	public Empleado guardar(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    public List<Empleado> listar() {
        return empleadoRepository.findAll();
    }

    public Empleado buscarPorId(int id) {
        Optional<Empleado> resultado = empleadoRepository.findById(id);
        return resultado.orElse(null);
    }

    public Empleado actualizar(int id, Empleado datos) {
        Empleado existente = buscarPorId(id);
        if (existente == null) return null;

        existente.setNombre(datos.getNombre());
        existente.setCargo(datos.getCargo());
        return empleadoRepository.save(existente);
    }

    public boolean eliminar(int id) {
        if (!empleadoRepository.existsById(id)) return false;
        empleadoRepository.deleteById(id);
        return true;
    }

}
