package com.krakedev.fitzone.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "socios_gym")
public class Socio {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombres;

    @Column(nullable = false)
    private String apellidos;

    @Column(nullable = false)
    private int edad;

    @Column(nullable = false)
    private boolean activo;

    // Constructor vacio 
    public Socio() {
    }

    // Constructor con parametros
    public Socio(String nombres, String apellidos, int edad, boolean activo) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
        this.activo = activo;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@Override
	public String toString() {
		return "Socio [id=" + id + ", nombres=" + nombres + ", apellidos=" + apellidos + ", edad=" + edad + ", activo="
				+ activo + "]";
	}

}
