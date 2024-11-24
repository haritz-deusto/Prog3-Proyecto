package domain;

public class Admin extends Usuario {

	public Admin() {
		super();
	}

	public Admin(String nombre, String apellido, String dni, String email, String contrasenia, int numTel,
			int numTarjeta) {
		super(nombre, apellido, dni, email, contrasenia, numTel, numTarjeta);
	}

	public int setNumTarjeta(int int1) {
		
		return 0;		
	}

	public int setNunTel(int int1) {
		return 0;		
	}

}
