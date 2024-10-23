package domain;

public class Admin extends Usuario {

	public Admin() {
		super();
	}

	public Admin(String nombre, String apellido, String dni, String email, String contrasenia, int numTel,
			int numTarjeta) {
		super(nombre, apellido, dni, email, contrasenia, numTel, numTarjeta);
	}

}
