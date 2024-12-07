package domain;

public class Cliente extends Usuario {
	private String numTarjeta;

	public Cliente() {
		super();
		setNumTarjeta(numTarjeta);
	}

	public Cliente(String nombre, String apellido, String dni, String email, String contrasenia, String numTel,
			String numTarjeta) {
		super();
		this.setNumTarjeta(numTarjeta);

	}

	public String getNumTarjeta() {
		return numTarjeta;
	}

	public void setNumTarjeta(String numTarjeta) {
		this.numTarjeta = numTarjeta;
	}

}
