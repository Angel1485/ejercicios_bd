package com.krakedev.proyectos.entidades;

import java.util.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "empleados")
public class Empleado {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String cargo;
    
   // @Column(nullable = false, length = 50)
    @Column(length = 50)
    private String departamento;
    
    @ManyToMany(mappedBy = "empleados")
    @JsonIgnore
    private List<Tarea> tareas;

	public Empleado() {
	}

	public Empleado(String nombre, String cargo, String departamento, List<Tarea> tareas) {
		this.nombre = nombre;
		this.cargo = cargo;
		this.departamento = departamento;
		this.tareas = tareas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public List<Tarea> getTareas() {
		return tareas;
	}

	public void setTareas(List<Tarea> tareas) {
		this.tareas = tareas;
	}

	public String getDepartamneto() {
		return departamento;
	}

	public void setDepartamneto(String departamneto) {
		this.departamento = departamneto;
	}

}
