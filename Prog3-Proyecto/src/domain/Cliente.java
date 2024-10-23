package domain;

public class Cliente extends Usuario {
	private int numTarjeta;

	public Cliente() {
		super();
		setNumTarjeta(0);
	}

	public Cliente(String nombre, String apellido, String dni, String email, String contrasenia, int numTel,
			int numTarjeta) {
		super(nombre, apellido, dni, email, contrasenia, numTel, numTarjeta);
		this.setNumTarjeta(numTarjeta);

	}

	public int getNumTarjeta() {
		return numTarjeta;
	}

	public void setNumTarjeta(int numTarjeta) {
		this.numTarjeta = numTarjeta;
	}

}
