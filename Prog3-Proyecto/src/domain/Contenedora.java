package domain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

public class Contenedora {
	private static ArrayList<Pelicula> lPeliculas = new ArrayList<>();
	private static ArrayList<Cliente> lClientes = new ArrayList<>();
	
	public static ArrayList<Pelicula> getLPeliculas(){
		return lPeliculas;
	}
	
	public static List<Cliente> getListaClientes() {
		return lClientes;
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
	
	public static List<Pelicula> buscarPeliculasPorGenero(Genero genero, Pelicula peliActual) {
	    
	    return lPeliculas.stream()
	                     .filter(p -> p.getGenero() == genero && p != peliActual)
	                     .collect(Collectors.toList());
	}
	
	public static void cargarPeliculas() {
	    File file = new File("ficheros/pelis.txt");  

	    List<String> lineas = new ArrayList<>();
	    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
	        String linea;
	        while ((linea = br.readLine()) != null) {
	            linea = linea.trim();
	            if (linea.endsWith(";")) {
	                linea = linea.substring(0, linea.length() - 1);
	            }
	            if (!linea.isEmpty()) {
	                lineas.add(linea);
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	        return;
	    }

	    cargarPeliculasRec(lineas, 0);
	}

	private static void cargarPeliculasRec(List<String> lineas, int indice) {
	    if (indice >= lineas.size()) {
	        return;
	    }

	    String linea = lineas.get(indice);
	    Pelicula p = parsearPelicula(linea);  
	    

	    lPeliculas.add(p);

	    cargarPeliculasRec(lineas, indice + 1);
	}

	// Método que se encarga de convertir una línea de texto en un objeto Pelicula
	private static Pelicula parsearPelicula(String linea) {
	    try {
	    
	        String[] partes = linea.split(";");

	        String strIndice = partes[0].trim();
	        int indice = Integer.parseInt(strIndice);

	        String strTitulo = limpiarComillas(partes[1]);

	        String strDuracion = partes[2].trim();
	        int duracion = Integer.parseInt(strDuracion);

	        String strSinopsis = limpiarComillas(partes[3]);

	        String strGenero = partes[4].trim();
	        String generoRaw = strGenero.substring(strGenero.indexOf('.') + 1); 
	        Genero genero = Genero.valueOf(generoRaw);

	        String strValoracion = partes[5].trim();  
	        String valRaw = strValoracion.substring(strValoracion.indexOf('.') + 1);
	        Valoracion valoracion = Valoracion.valueOf(valRaw);

	        String strRuta = limpiarComillas(partes[6]);

	        // Creamos la película
	        Pelicula peli = new Pelicula(indice, strTitulo, duracion, strSinopsis, genero, valoracion, strRuta);
	        return peli;

	    } catch (Exception e) {
	        return null;
	    }
	}

	// Función auxiliar que quita comillas iniciales y finales
	private static String limpiarComillas(String texto) {
	    String t = texto.trim();
	    if (t.startsWith("\"")) {
	        t = t.substring(1);
	    }
	    if (t.endsWith("\"")) {
	        t = t.substring(0, t.length() - 1);
	    }
	    return t.trim();
	}
	
}
