package com.krakedev.peliculas.entidades;
import jakarta.persistence.*;

@Entity
@Table(name = "peliculas")
public class Pelicula {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(nullable = false)
	    private String nombre;

	    @Column(nullable = false)
	    private String director;

	    @Column(nullable = false)
	    private String genero;

	    @Column(nullable = false)
	    private int duracion;

	    @Column(nullable = false)
	    private boolean disponible;

	    // Constructor vacio
	    public Pelicula() {
	    }

	    // Constructor con parametros
	    public Pelicula(Long id, String nombre, String director, String genero, int duracion, boolean disponible) {
	        this.id = id;
	        this.nombre = nombre;
	        this.director = director;
	        this.genero = genero;
	        this.duracion = duracion;
	        this.disponible = disponible;
	    }
	    
	 // Getters y Setters

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getDirector() {
			return director;
		}

		public void setDirector(String director) {
			this.director = director;
		}

		public String getGenero() {
			return genero;
		}

		public void setGenero(String genero) {
			this.genero = genero;
		}

		public int getDuracion() {
			return duracion;
		}

		public void setDuracion(int duracion) {
			this.duracion = duracion;
		}

		public boolean isDisponible() {
			return disponible;
		}

		public void setDisponible(boolean disponible) {
			this.disponible = disponible;
		}

		@Override
		public String toString() {
			return "Pelicula [id=" + id + ", nombre=" + nombre + ", director=" + director + ", genero=" + genero
					+ ", duracion=" + duracion + ", disponible=" + disponible + "]";
		}
	  
}
