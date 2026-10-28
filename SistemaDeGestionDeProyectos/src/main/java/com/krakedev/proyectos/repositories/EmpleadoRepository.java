package com.krakedev.proyectos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.krakedev.proyectos.entidades.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer>{


}
