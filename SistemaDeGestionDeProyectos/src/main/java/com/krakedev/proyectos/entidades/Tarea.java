package com.krakedev.proyectos.entidades;

import java.time.LocalDate;
import java.util.*;
import jakarta.persistence.*;

@Entity
@Table(name = "tareas")
public class Tarea {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 200)
    private String descripcion;

    @Column(nullable = false)
    private LocalDate fechaLimite;

    @Column(nullable = false)
    private double costoEstimado;

    // Relacion Muchos a Uno con Proyecto
    @ManyToOne
    @JoinColumn(name = "proyecto_id", nullable = false)
    private Proyecto proyecto;

    // Relacion Muchos a Muchos con Empleado (tabla intermedia)
    @ManyToMany
    @JoinTable(
        name = "tarea_empleados",
        joinColumns = @JoinColumn(name = "tarea_id"),
        inverseJoinColumns = @JoinColumn(name = "empleado_id")
    )
    private List<Empleado> empleados;

	public Tarea() {
	}

	public Tarea(String descripcion, LocalDate fechaLimite, double costoEstimado, Proyecto proyecto, List<Empleado> empleados) {
		this.descripcion = descripcion;
		this.fechaLimite = fechaLimite;
		this.costoEstimado = costoEstimado;
		this.proyecto = proyecto;
		this.empleados = empleados;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public LocalDate getFechaLimite() {
		return fechaLimite;
	}

	public void setFechaLimite(LocalDate fechaLimite) {
		this.fechaLimite = fechaLimite;
	}

	public double getCostoEstimado() {
		return costoEstimado;
	}

	public void setCostoEstimado(double costoEstimado) {
		this.costoEstimado = costoEstimado;
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	public List<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}

}
