package domain;

import java.time.LocalDate;

public class Comentario {
    private int idComentario;
    private String comentario;
    private LocalDate fecha;
    

    public Comentario(int idComentario, String comentario, LocalDate fecha) {
        this.idComentario = idComentario;
        this.comentario = comentario;
        this.fecha = fecha;
    }


	public int getIdComentario() {
		return idComentario;
	}


	public void setIdComentario(int idComentario) {
		this.idComentario = idComentario;
	}


	public String getComentario() {
		return comentario;
	}


	public void setComentario(String comentario) {
		this.comentario = comentario;
	}


	public LocalDate getFecha() {
		return fecha;
	}


	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}


	@Override
	public String toString() {
		return "Comentario [idComentario=" + idComentario + ", comentario=" + comentario + ", fecha=" + fecha + "]";
	}
    
 
}
