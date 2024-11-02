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
		Pelicula p1 = new Pelicula("Titulo1", 180, "Descripcion1", Genero.ACCION, Valoracion.DOS_ESTRELLAS, "introducir ruta");
		Pelicula p2 = new Pelicula("Titulo2", 150, "Descripcion2", Genero.DRAMA, Valoracion.CINCO_ESTRELLAS, "introducir ruta");
		Pelicula p3 = new Pelicula("Titulo3", 210, "Descripcion3", Genero.MUSICAL, Valoracion.CUATRO_ESTRELLAS, "introducir ruta");
		Pelicula p4 = new Pelicula("Titulo4", 120, "Descripcion4", Genero.CIENCIA_FICCION, Valoracion.UNA_ESTRELLA, "introducir ruta");
		lPeliculas.add(p1);
		lPeliculas.add(p2);
		lPeliculas.add(p3);
		lPeliculas.add(p4);
	}
}
