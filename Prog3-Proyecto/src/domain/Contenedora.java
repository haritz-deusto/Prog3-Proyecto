package domain;

import java.util.ArrayList;

public class Contenedora {
	private static ArrayList<Pelicula> lPeliculas = new ArrayList<>();
	
	public static ArrayList<Pelicula> getLPeliculas(){
		return lPeliculas;
	}
	
	public static Pelicula buscarPelicula(String rutaFoto) {
		boolean enc = false;
		int pos = 0;
		Pelicula p = null;
		while(!!enc && pos < lPeliculas.size()) {
			p = lPeliculas.get(pos);
			if(p.getRutaFoto().equals(rutaFoto)) {
				enc = true;
			}else {
				pos++;
			}
		}
		return p;
	}
	
	
	public static void cargarPeliculas() {
	    Pelicula p1 = new Pelicula("Venom 3", 109, "Eddie y Venom están a la fuga. Perseguidos por sus sendos mundos y cada vez más cercados, el dúo se ve abocado a tomar una decisión devastadora que hará que caiga el telón sobre el último baile de Venom y Eddie.", Genero.CIENCIA_FICCION, Valoracion.DOS_ESTRELLAS, "/imagenes/venom3.jpg");
	    Pelicula p2 = new Pelicula("Joker 2", 128, "Tras crear el caos, Arthur Fleck ha sido internado en Arkham a la espera de juicio por sus crímenes como Joker. Mientras lidia con su doble identidad, Arthur no sólo se topa con el amor verdadero, sino que también descubre la música que siempre ha estado dentro de él.", Genero.MUSICAL, Valoracion.CINCO_ESTRELLAS, "/imagenes/joker2.jpg");
	    Pelicula p3 = new Pelicula("Minions 4", 95, "Margo, Edith y Agnes adoran a su padre y se portan bien entre ellas, con su nuevo hermanito y con Lucy, a la que llaman mamá. Dru y Gru desarrollan un fuerte vínculo fraternal. Poppy es moralmente ambigua, como todos los villanos, pero trabaja en equipo y acaba siendo amiga de Gru y Margo.", Genero.COMEDIA, Valoracion.CUATRO_ESTRELLAS, "/imagenes/minions4.jpg");
	    Pelicula p4 = new Pelicula("Del Reves 2", 96, "Ahora que es adolescente, Riley experimenta nuevos sentimientos como Ansiedad y Envidia, que se imponen a los viejos mientras ella duda sobre si abandonar a sus antiguas amigas por otras de la escuela secundaria.", Genero.DRAMA, Valoracion.UNA_ESTRELLA, "/imagenes/insideout2.jpg");
	    lPeliculas.add(p1);
	    lPeliculas.add(p2);
	    lPeliculas.add(p3);
	    lPeliculas.add(p4);
	}
}
