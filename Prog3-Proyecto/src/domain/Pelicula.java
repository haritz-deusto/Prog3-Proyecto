package domain;

public class Pelicula {
	private String titulo;
	private int duracion;
	private String descripcion;
	protected Genero genero;
	protected Valoracion estrellas;
	
	public Pelicula(String titulo, int duracion, String descripcion, Genero genero, Valoracion estrellas) {
		super();
		this.titulo = titulo;
		this.duracion = duracion;
		this.descripcion = descripcion;
		this.genero = genero;
		this.estrellas = estrellas;
	}
	public Pelicula() {
		super();
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public Valoracion getEstrellas() {
		return estrellas;
	}

	public void setEstrellas(Valoracion estrellas) {
		this.estrellas = estrellas;
	}
	
	@Override
	public String toString() {
		return "Pelicula [titulo=" + titulo + ", duracion=" + duracion + ", descripcion=" + descripcion + ", genero=" + genero + ", estrellas=" + estrellas + "]";
	}

}
