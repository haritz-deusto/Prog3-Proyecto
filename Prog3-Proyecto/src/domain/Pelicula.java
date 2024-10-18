package domain;

public class Pelicula {
	private String titulo;
	private int duracion;
	private String descripcion;
	public Pelicula(String titulo, int duracion, String descripcion) {
		super();
		this.titulo = titulo;
		this.duracion = duracion;
		this.descripcion = descripcion;
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
	@Override
	public String toString() {
		return "Pelicula [titulo=" + titulo + ", duracion=" + duracion + ", descripcion=" + descripcion + "]";
	}

}
