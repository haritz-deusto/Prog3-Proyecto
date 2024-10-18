package domain;

public class Admin {
	private String nombre;
	private String apellido;
	private String dni;
	private String email;
	private int numTel;
	
	public Admin(String nombre, String apellido, String dni, String email, int numTel) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.email = email;
		this.numTel = numTel;
	}

	public Admin() {
		super();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getNumTel() {
		return numTel;
	}

	public void setNumTel(int numTel) {
		this.numTel = numTel;
	}

	@Override
	public String toString() {
		return "Admin [nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni + ", email=" + email + ", numTel="
				+ numTel + "]";
	}
	
}
