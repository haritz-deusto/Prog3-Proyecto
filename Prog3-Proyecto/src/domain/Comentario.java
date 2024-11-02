package domain;

import java.time.LocalDate;

public class Comentario extends Pelicula{
	protected Usuario usuario;
	protected String texto;
	protected LocalDate fecha;

	public Comentario(String titulo, int duracion, String descripcion, Genero genero, Valoracion estrellas,
			String rutaFoto, Usuario usuario, String texto, LocalDate fecha) {
		super(titulo, duracion, descripcion, genero, estrellas, rutaFoto);
		this.usuario = usuario;
		this.texto = texto;
		this.fecha = fecha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "Comentario [usuario=" + usuario + ", texto=" + texto + ", fecha=" + fecha + "]";
	}
}
