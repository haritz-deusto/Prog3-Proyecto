package domain;

public class Pelicula {
	private String titulo;
	private int duracion;
	private String descripcion;
	protected Genero genero;
	protected Valoracion estrellas;
	private String rutaFoto;
	
	public Pelicula(String titulo, int duracion, String descripcion, Genero genero, Valoracion estrellas, String rutaFoto) {
		super();
		this.titulo = titulo;
		this.duracion = duracion;
		this.descripcion = descripcion;
		this.genero = genero;
		this.estrellas = estrellas;
		this.rutaFoto = rutaFoto;
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
	public String getRutaFoto() {
		return rutaFoto;
	}
	public void setRutaFoto(String rutaFoto) {
		this.rutaFoto = rutaFoto;
	}
	@Override
	public String toString() {
		return "Pelicula [titulo=" + titulo + ", duracion=" + duracion + ", descripcion=" + descripcion + ", genero="
				+ genero + ", estrellas=" + estrellas + ", rutaFoto=" + rutaFoto + "]";
	}
	
	
	


}
