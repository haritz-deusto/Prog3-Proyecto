package domain;

public class Admin extends Usuario {

	public Admin() {
		super();
	}

	public Admin(String nombre, String apellido, String dni, String email, String contrasenia, String numTel, String numTarjeta, String tipoUsuario) {
		super();
		tipoUsuario  = "ADMIN";

	}

	public int setNumTarjeta(int int1) {
		
		return 0;		
	}

	public int setNunTel(int int1) {
		return 0;		
	}

}
