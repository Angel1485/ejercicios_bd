package com.krakedev.proyectos.services;

import java.util.*;
import org.springframework.stereotype.*;
import com.krakedev.proyectos.entidades.*;
import com.krakedev.proyectos.repositories.*;

@Service
public class TareaService {

	private final TareaRepository tareaRepository;
    private final ProyectoRepository proyectoRepository;
    private final EmpleadoRepository empleadoRepository;

    public TareaService(TareaRepository tareaRepository, ProyectoRepository proyectoRepository,EmpleadoRepository empleadoRepository) {
		this.tareaRepository = tareaRepository;
		this.proyectoRepository = proyectoRepository;
		this.empleadoRepository = empleadoRepository;
	}

	public Tarea guardar(Tarea tarea) {
        // 1. Resolver proyecto desde la BD
        if (tarea.getProyecto() == null || tarea.getProyecto().getId() == 0) {
            throw new IllegalArgumentException("La tarea debe tener un proyecto asociado");
        }
        Proyecto proyecto = proyectoRepository.findById(tarea.getProyecto().getId())
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado con id: " + tarea.getProyecto().getId()));
        tarea.setProyecto(proyecto);

        // 2. Resolver empleados desde la BD
        List<Empleado> empleados = new ArrayList<>();
        if (tarea.getEmpleados() != null) {
            for (Empleado e : tarea.getEmpleados()) {
                Empleado emp = empleadoRepository.findById(e.getId())
                        .orElseThrow(() -> new RuntimeException("Empleado no encontrado con id: " + e.getId()));
                empleados.add(emp);
            }
        }
        tarea.setEmpleados(empleados);

        // 3. Persistir
        return tareaRepository.save(tarea);
    }
	
    public Tarea actualizar(int id, Tarea datos) {
        Tarea existente = buscarPorId(id);
        if (existente == null) return null;

        // 1. Campos simples
        existente.setDescripcion(datos.getDescripcion());
        existente.setFechaLimite(datos.getFechaLimite());
        existente.setCostoEstimado(datos.getCostoEstimado());

        // 2. Resolver y actualizar proyecto
        if (datos.getProyecto() != null && datos.getProyecto().getId() != 0) {
            Proyecto proyecto = proyectoRepository.findById(datos.getProyecto().getId())
                    .orElseThrow(() -> new RuntimeException("Proyecto no encontrado con id: " + datos.getProyecto().getId()));
            existente.setProyecto(proyecto);
        }

        // 3. Resolver y actualizar empleados
        if (datos.getEmpleados() != null) {
            List<Empleado> empleados= new ArrayList<>();
            for (Empleado e : datos.getEmpleados()) {
                Empleado emp = empleadoRepository.findById(e.getId())
                        .orElseThrow(() -> new RuntimeException("Empleado no encontrado con id: "+ e.getId()));
                empleados.add(emp);
            }
            existente.setEmpleados(empleados);
        }

        return tareaRepository.save(existente);
    }

    public List<Tarea> listar() {
        return tareaRepository.findAll();
    }

    public Tarea buscarPorId(int id) {
        Optional<Tarea> resultado = tareaRepository.findById(id);
        return resultado.orElse(null);
    }
    
    public boolean eliminar(int id) {
        if (!tareaRepository.existsById(id)) return false;
        tareaRepository.deleteById(id);
        return true;
    }
}
