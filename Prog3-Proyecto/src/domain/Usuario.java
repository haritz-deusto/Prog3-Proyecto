package domain;

public class Usuario {
	
	private String nombre;
	private String apellido;
	private String dni;
	private String email;
	private String contrasenia;
	private int numTel;
	private int numTarjeta;
	
	public Usuario() {
		super();
	}
	public Usuario(String nombre, String apellido, String dni, String email, String contrasenia, int numTel, int numTarjeta) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.email = email;
		this.contrasenia = contrasenia;
		this.numTel = numTel;
		this.numTarjeta = numTarjeta;
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
	
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	public int getNumTel() {
		return numTel;
	}
	public void setNumTel(int numTel) {
		this.numTel = numTel;
	}
	public int getNumTarjeta() {
		return numTarjeta;
	}
	public void setNumTarjeta(int numTarjeta) {
		this.numTarjeta = numTarjeta;
	}
	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni + ", email=" + email
				+ ", contrasenia=" + contrasenia + ", numTel=" + numTel + ", numTarjeta=" + numTarjeta + "]";
	}
	
	

}
