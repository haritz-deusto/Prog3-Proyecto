package domain;

public abstract class Usuario {
	
	private String nombre;
	private String apellido;
	private String dni;
	private String email;
	private String contrasenia;
	private String numTel;
	private String numTarjeta;
	private String tipoUsuario;
	
	public Usuario() {
		super();
	}
	public Usuario(String nombre, String apellido, String dni, String email, String contrasenia, String numTel, String numTarjeta, String tipoUsuario) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.email = email;
		this.contrasenia = contrasenia;
		this.numTel = numTel;
		this.numTarjeta = numTarjeta;
		this.tipoUsuario = tipoUsuario;
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
	public String getNumTel() {
		return numTel;
	}
	public String getNumTarjeta() {
		return numTarjeta;
	}
	public void setNumTarjeta(String numTarjeta) {
		this.numTarjeta = numTarjeta;
	}
	public String getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}
	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni + ", email=" + email
				+ ", contrasenia=" + contrasenia + ", numTel=" + numTel + ", numTarjeta=" + numTarjeta
				+ ", tipoUsuario=" + tipoUsuario + "]";
	}
	
	
	
	

}
