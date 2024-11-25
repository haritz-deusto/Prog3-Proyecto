package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import domain.Admin;
import domain.Cliente;
import domain.Comentario;
import domain.Contenedora;
import domain.Genero;
import domain.Pelicula;
import domain.Valoracion;
import main.Main;
import domain.Contenedora;
import domain.Usuario;


public class BaseDeDatos {
	public BaseDeDatos() {
	   }
	
	   public static Connection initBD(String nomBD) {
		      Connection con = null;

		      try {
		         Class.forName("org.sqlite.JDBC");
		         con = DriverManager.getConnection("jdbc:sqlite:ficheros/" + nomBD);
		      } catch (ClassNotFoundException var3) {
		         var3.printStackTrace();
		      } catch (SQLException var4) {
		         var4.printStackTrace();
		      }

		      return con;
		   }
	   
	   public static void closeBD(Connection con) {
		      if (con != null) {
		         try {
		            con.close();
		         } catch (SQLException var2) {
		            var2.printStackTrace();
		         }
		      }

		   }
	   
	   /**
	 * @param con
	 */
	public static void crearTablas(Connection con) {
		      String sql = "CREATE TABLE IF NOT EXISTS Cliente (email String, nombre String, apellido String,  dni String, contrasenia String, nunTel String, numtarjeta String)";
		      String sql2 = "CREATE TABLE IF NOT EXISTS Pelicula (titulo String, genero Genero, estrellas Valoracion, duracion String, descripcion String rutafoto String)";
		      String sql3 = "CREATE TABLE IF NOT EXISTS Admin (email String, nombre String, apellido String,  dni String, contrasenia String, nunTel String, numtarjeta String)";

		      try {
		         Statement st = con.createStatement();
		         st.executeUpdate(sql);
		         st.executeUpdate(sql2);
		         st.executeUpdate(sql3);
					/*
					 * Iterator var6 = Contenedora.getLPeliculas().iterator();
					 * 
					 * while(var6.hasNext()) { Pelicula p = (Pelicula)var6.next(); String nombre =
					 * p.getTitulo().replace(" ", ""); st.executeUpdate(String.
					 * format("CREATE TABLE IF NOT EXISTS Asientos%s (c1 String, c2 String, c3 String, c4 String, c5 String, c6 String, c7 String, c8 String, c9 String, c10 String)"
					 * , nombre)); }
					 */

		         st.close();
		      } catch (SQLException var8) {
		         var8.printStackTrace();
		      }

		   }
	
	 public static void anadirPelicula(String titulo, Genero genero, Valoracion estrellas, String duracion, String descripcion, String rutafoto) {
	      String sql = String.format("INSERT INTO Pelicula VALUES('%s','%s','%s','%s','%s','%s')", titulo, genero.toString(), estrellas.toString(), duracion, descripcion, rutafoto);

	      try {
	         Connection con = initBD("baseDeDatos.db");
	         Statement st = con.createStatement();
	         st.executeUpdate(sql);
	         st.close();
	         closeBD(con);
	      } catch (SQLException var6) {
	         var6.printStackTrace();
	      }

	   }

	 public static void eliminarPelicula(String titulo) {
		    String sql = String.format("DELETE FROM Pelicula WHERE titulo='%s'", titulo);

		    try {
		        Connection con = initBD("baseDeDatos.db");
		        Statement st = con.createStatement();
		        st.executeUpdate(sql);
		        st.close();
		        closeBD(con);
		        System.out.println("Película eliminada con éxito: " + titulo);
		    } catch (SQLException e) {
		        e.printStackTrace();
		        System.err.println("Error al eliminar la película: " + titulo);
		    }
		}

	 
	   public static void borrarTodosLosClientes() {
	      String sql = "delete from Cliente";

	      try {
	         Connection con = initBD("baseDeDatos.db");
	         Statement st = con.createStatement();
	         st.execute(sql);
	         st.close();
	         closeBD(con);

	         
	      } catch (SQLException var3) {
	         var3.printStackTrace();
	      }

	   }
	   public static void borrarTodosLosAdmins() {
		      String sql = "delete from Admin";

		      try {
		         Connection con = initBD("baseDeDatos.db");
		         Statement st = con.createStatement();
		         st.execute(sql);
		         st.close();
		         closeBD(con);

		         
		      } catch (SQLException var3) {
		         var3.printStackTrace();
		      }

		   }
	   
	   public static void borrarTodasLasPeliculas() {
		      String sql = "delete from Pelicula";

		      try {
			     Connection con = initBD("baseDeDatos.db");
		         Statement st = con.createStatement();
		         st.execute(sql);
		         st.close();
		      } catch (SQLException var3) {
		         var3.printStackTrace();
		      }

		   }
	   public static List<Cliente> obtenerListaClientes() {
		    List<Cliente> listaClientesBD = new ArrayList<>();
		    String sql = "SELECT * FROM Cliente";

		    try {

		    	Connection con = initBD("baseDeDatos.db");
		    	Statement st = con.createStatement();
		    	ResultSet rs = st.executeQuery(sql);

		    	while (rs.next()) {
		            String email = rs.getString("email");
		            String nombre = rs.getString("nombre");
		            String apellido = rs.getString("apellido");
		            String dni = rs.getString("dni");
		            String contrasenia = rs.getString("contrasenia");
		            String nunTel = rs.getString("nunTel");
		            String numTarjeta = rs.getString("numtarjeta");

		            Cliente cliente = new Cliente();
		            cliente.setEmail(email);
		            cliente.setNombre(nombre);
		            cliente.setApellido(apellido);
		            cliente.setDni(dni);
		            cliente.setContrasenia(contrasenia);
		            cliente.setNumTel(Integer.parseInt(nunTel));
		            cliente.setNumTarjeta(Integer.parseInt(numTarjeta));

		            listaClientesBD.add(cliente);
		        }
		    	rs.close();
		    	st.close();
		    	con.close();
		    	
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } 

		    return listaClientesBD;
		}
	   public static List<Admin> obtenerListaAdmins() {
		    List<Admin> listaAdminsBD = new ArrayList<>();
		    String sql = "SELECT * FROM Admin";

		    try {
		    	Connection con = initBD("baseDeDatos.db");
		    	Statement st = con.createStatement();
		    	ResultSet rs = st.executeQuery(sql);
		    	
		        while (rs.next()) {
		            String email = rs.getString("email");
		            String nombre = rs.getString("nombre");
		            String apellido = rs.getString("apellido");
		            String dni = rs.getString("dni");
		            String contrasenia = rs.getString("contrasenia");
		            String nunTel = rs.getString("nunTel");
		            String numTarjeta = rs.getString("numtarjeta");

		            Admin admin = new Admin();
		            admin.setEmail(email);
		            admin.setNombre(nombre);
		            admin.setApellido(apellido);
		            admin.setDni(dni);
		            admin.setContrasenia(contrasenia);
		            admin.setNunTel(Integer.parseInt(nunTel));			//No tiene sentido numero de tarjeta y num tlf como veais
		            admin.setNumTarjeta(Integer.parseInt(numTarjeta));	//Modificarlo si quereis, esta puesto en la calse admin que ponga 0.

		            listaAdminsBD.add(admin);
		        }
		        rs.close();
		    	st.close();
		    	con.close();
		    	
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return listaAdminsBD;
		}
	   
	   public static List<Pelicula> obtenerListaPeliculas() {
		    List<Pelicula> listaPeliculasBD = new ArrayList<>();
		    String sql = "SELECT * FROM Pelicula";

		    try {
		    	Connection con = initBD("baseDeDatos.db");
		    	Statement st = con.createStatement();
		    	ResultSet rs = st.executeQuery(sql);

		        while (rs.next()) {

		        	String titulo = rs.getString("titulo");
		            String duracion = rs.getString("duracion");
		            String descripcion = rs.getString("descripcion");
		            String rutafoto = rs.getString("rutafoto");
		            String generoStr = rs.getString("genero");
		            
		            Genero genero = Genero.valueOf(generoStr.toUpperCase());
		            String estrellasStr = rs.getString("estrellas");
		            Valoracion estrellas = Valoracion.valueOf(estrellasStr.toUpperCase());

		            Pelicula pelicula = new Pelicula();
		            pelicula.setTitulo(titulo);
		            pelicula.setGenero(genero);
		            pelicula.setEstrellas(estrellas); 
		            pelicula.setDuracion(Integer.parseInt(duracion));
		            pelicula.setDescripcion(descripcion);
		            pelicula.setRutaFoto(rutafoto);

		            listaPeliculasBD.add(pelicula);
		        }
		        rs.close();
		    	st.close();
		    	con.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } 
		    return listaPeliculasBD;
		}
	   /*Consultas Utilies*/
	   
	   public static void anadirCliente(String email, String nombre, String apellido, String dni, String contrasenia, String nunTel, String numTarjeta) {
		    String sql = String.format("INSERT INTO Cliente VALUES('%s', '%s', '%s', '%s', '%s', '%s', '%s')", email, nombre, apellido, dni, contrasenia, nunTel, numTarjeta);

		    try {
		        Connection con = initBD("baseDeDatos.db");
		        Statement st = con.createStatement();
		        
		        st.executeUpdate(sql);
		        
		        Cliente nuevoCliente = new Cliente();
		        nuevoCliente.setEmail(email);
		        nuevoCliente.setNombre(nombre);
		        nuevoCliente.setApellido(apellido);
		        nuevoCliente.setDni(dni);
		        nuevoCliente.setContrasenia(contrasenia);
		        nuevoCliente.setNumTel(Integer.parseInt(nunTel));
		        nuevoCliente.setNumTarjeta(Integer.parseInt(numTarjeta));

//		        Contenedora.getListaClientes().add(nuevoCliente); Modificar clase contenedora que se carga con el metodo de obtenerlistaClientes
		        st.close();
		    	con.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}
	   
	   public static void actualizarCliente(String email, String nombre, String apellido, String dni, String contrasenia, String nunTel, String numTarjeta) {
		    // Actualizar en la base de datos
		    String sql = String.format("UPDATE Cliente SET nombre='%s', apellido='%s', dni='%s', contrasenia='%s', nunTel='%s', numtarjeta='%s' WHERE email='%s'", 
		                               nombre, apellido, dni, contrasenia, nunTel, numTarjeta, email);

		    try {
		        Connection con = initBD("baseDeDatos.db");
		        Statement st = con.createStatement();

		        List<Cliente> listaClientes = Contenedora.getListaClientes(); //Solucionar La carga en la lista contenedora
		        for (Cliente cliente : listaClientes) {
		            
		        	if (cliente.getEmail().equals(email)) {

		            	cliente.setNombre(nombre);
		                cliente.setApellido(apellido);
		                cliente.setDni(dni);
		                cliente.setContrasenia(contrasenia);
		                cliente.setNumTel(Integer.parseInt(nunTel));
		                cliente.setNumTarjeta(Integer.parseInt(numTarjeta));
		                break;
		            }
		        }
		        st.executeUpdate(sql);
		        st.close();
		    	con.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}
	   
	   public static void actualizarContraseniaCliente(String email, String nuevaContrasenia) {
		    String sql = String.format("UPDATE Cliente SET contrasenia='%s' WHERE email='%s'", nuevaContrasenia, email);

		    try {
		        Connection con = initBD("baseDeDatos.db");
		        Statement st = con.createStatement();
		        
		        st.executeUpdate(sql);
		        st.close();
		    	con.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}
	   
	   public static void eliminarCliente(String email) {
		    String sql = String.format("DELETE FROM Cliente WHERE email='%s'", email);

		    try {
		        Connection con = initBD("baseDeDatos.db");
		        Statement st = con.createStatement();
		       
		        st.executeUpdate(sql);
		        
		        List<Cliente> listaClientes = Contenedora.getListaClientes(); //Solucionar La carga en la lista contenedora
		        Iterator<Cliente> iterator = listaClientes.iterator();
		        while (iterator.hasNext()) {
		            Cliente cliente = iterator.next();
		            if (cliente.getEmail().equals(email)) {
		                iterator.remove(); 
		                break;
		            }
		        }
		        
		        st.close();
		    	con.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}
	   
	   public static Pelicula obtenerPeliculaPorTitulo(String titulo) {
		    Pelicula pelicula = null;
		    String sql = String.format("SELECT * FROM Pelicula WHERE titulo='%s'", titulo);

		    try {
		        Connection con = initBD("baseDeDatos.db");
		        Statement st = con.createStatement();
		        ResultSet rs = st.executeQuery(sql);

		        if (rs.next()) {
		            String duracion = rs.getString("duracion");
		            String descripcion = rs.getString("descripcion");
		            String rutafoto = rs.getString("rutafoto");
		            String generoStr = rs.getString("genero");

		            Genero genero = Genero.valueOf(generoStr.toUpperCase());
		            String estrellasStr = rs.getString("estrellas");
		            Valoracion estrellas = Valoracion.valueOf(estrellasStr.toUpperCase());

		            pelicula = new Pelicula();
		            pelicula.setTitulo(titulo);
		            pelicula.setGenero(genero);
		            pelicula.setEstrellas(estrellas); 
		            pelicula.setDuracion(Integer.parseInt(duracion));
		            pelicula.setDescripcion(descripcion);
		            pelicula.setRutaFoto(rutafoto);
		        }

		        rs.close();
		        st.close();
		        con.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return pelicula;
		}
	   
	   public static Cliente obtenerClientePorEmail(String email) {
		   List<Cliente> listaClientes = Contenedora.getListaClientes(); //Primero Buscamos si lo tenemos ya cargado en memoria
		    															// Si no buscamos en base de datos y se añade a la clase contenedora y si no existe null
		   for (Cliente cliente : listaClientes) {
		        if (cliente.getEmail().equals(email)) {
		            return cliente; 
		        }
		    }

		    Cliente cliente = null;
		    String sql = String.format("SELECT * FROM Cliente WHERE email='%s'", email);

		    try {
		        Connection con = initBD("baseDeDatos.db");
		        Statement st = con.createStatement();
		        ResultSet rs = st.executeQuery(sql);

		        if (rs.next()) {
		            String nombre = rs.getString("nombre");
		            String apellido = rs.getString("apellido");
		            String dni = rs.getString("dni");
		            String contrasenia = rs.getString("contrasenia");
		            String nunTel = rs.getString("nunTel");
		            String numTarjeta = rs.getString("numtarjeta");

		            cliente = new Cliente();
		            cliente.setEmail(email);
		            cliente.setNombre(nombre);
		            cliente.setApellido(apellido);
		            cliente.setDni(dni);
		            cliente.setContrasenia(contrasenia);
		            cliente.setNumTel(Integer.parseInt(nunTel));
		            cliente.setNumTarjeta(Integer.parseInt(numTarjeta));

		            Contenedora.getListaClientes().add(cliente);
		        }

		        rs.close();
		        st.close();
		        con.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return cliente;
		}
	   
	   public static List<Pelicula> obtenerPeliculasPorGenero(Genero genero) { //Misma solucion que antes 
		    List<Pelicula> listaPeliculastemp = new ArrayList<>();
		    List<Pelicula> listaPeliculasMemoria = Contenedora.getLPeliculas();
		    
		    for (Pelicula pelicula : listaPeliculasMemoria) {
		        if (pelicula.getGenero().equals(genero)) {
		            listaPeliculastemp.add(pelicula); 
		        }
		    }

		    if (listaPeliculastemp.isEmpty()) {
		        String sql = String.format("SELECT * FROM Pelicula WHERE genero='%s'", genero.toString());

		        try {
		            Connection con = initBD("baseDeDatos.db");
		            Statement st = con.createStatement();
		            ResultSet rs = st.executeQuery(sql);

		            while (rs.next()) {
		                String titulo = rs.getString("titulo");
		                String duracion = rs.getString("duracion");
		                String descripcion = rs.getString("descripcion");
		                String rutafoto = rs.getString("rutafoto");
		                String generoStr = rs.getString("genero");

		                Genero generoDb = Genero.valueOf(generoStr.toUpperCase());
		                String estrellasStr = rs.getString("estrellas");
		                Valoracion estrellas = Valoracion.valueOf(estrellasStr.toUpperCase());

		                Pelicula pelicula = new Pelicula();
		                pelicula.setTitulo(titulo);
		                pelicula.setGenero(generoDb);
		                pelicula.setEstrellas(estrellas);
		                pelicula.setDuracion(Integer.parseInt(duracion));
		                pelicula.setDescripcion(descripcion);
		                pelicula.setRutaFoto(rutafoto);

		                Contenedora.getLPeliculas().add(pelicula);
		                listaPeliculastemp.add(pelicula);
		            }

		            rs.close();
		            st.close();
		            con.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }

		    return listaPeliculastemp;
		}
	   
	   public static boolean existeClientePorEmailEnBD(String email) {
		    boolean existe = false;
		    String sql = String.format("SELECT 1 FROM Cliente WHERE email='%s'", email);

		    try {
		        Connection con = initBD("baseDeDatos.db");
		        Statement st = con.createStatement();
		        ResultSet rs = st.executeQuery(sql);

		        if (rs.next()) {
		            existe = true;
		        }

		        rs.close();
		        st.close();
		        con.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return existe;
		}
	   
	   public static List<Pelicula> obtenerPeliculasMejorValoradasPorGenero(int numeroEstrellas, Genero genero) {
		    List<Pelicula> listaPeliculastemp = new ArrayList<>();
		    List<Pelicula> listaPeliculasMemoria = Contenedora.getLPeliculas();
		    
		    //Memoria
		    for (Pelicula pelicula : listaPeliculasMemoria) {
		        if (pelicula.getGenero().equals(genero) && pelicula.getEstrellas().ordinal() > numeroEstrellas) {
		            listaPeliculastemp.add(pelicula);
		        }
		    }

		    //Base de Datos
		    if (listaPeliculastemp.isEmpty()) {
		        String sql = String.format("SELECT * FROM Pelicula WHERE genero='%s' AND CAST(estrellas AS INTEGER) > %d", genero.toString(), numeroEstrellas);

		        try {
		            Connection con = initBD("baseDeDatos.db");
		            Statement st = con.createStatement();
		            ResultSet rs = st.executeQuery(sql);
		            																	//Mismo que antes se busca en memoria
		            while (rs.next()) {													//Si no esta se busca en BD y añade a meoria y si no deuelve una arraList vacia
		                String titulo = rs.getString("titulo");
		                String duracion = rs.getString("duracion");
		                String descripcion = rs.getString("descripcion");
		                String rutafoto = rs.getString("rutafoto");
		                String generoStr = rs.getString("genero");

		                Genero generoDb = Genero.valueOf(generoStr.toUpperCase());
		                String estrellasStr = rs.getString("estrellas");
		                Valoracion estrellas = Valoracion.valueOf(estrellasStr.toUpperCase());

		                Pelicula pelicula = new Pelicula();
		                pelicula.setTitulo(titulo);
		                pelicula.setGenero(generoDb);
		                pelicula.setEstrellas(estrellas);
		                pelicula.setDuracion(Integer.parseInt(duracion));
		                pelicula.setDescripcion(descripcion);
		                pelicula.setRutaFoto(rutafoto);

		                Contenedora.getLPeliculas().add(pelicula);
		                listaPeliculastemp .add(pelicula);
		            }

		            rs.close();
		            st.close();
		            con.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }

		    return listaPeliculastemp;
		}
	   
	

}
