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
	    Pelicula p5 = new Pelicula("Cars", 117, "Rayo McQueen es una prometedora figura del automovilismo, que está a punto de conseguir su primera Copa Pistón, aun siendo un novato. Sin embargo, el día que se dirige hacia el circuito, tiene un accidente y acaba perdido en un pueblo llamado Radiador Springs, medio abandonado en el que se ve obligado a quedarse.", Genero.ANIMACION, Valoracion.CUATRO_ESTRELLAS, "/imagenes/cars.jpg");
	    Pelicula p6 = new Pelicula("Cars 2", 106, "Rayo McQueen y la grúa Mate viajan al extranjero para participar en el primer Campeonato Mundial en el que se decidirá cuál es el coche más rápido de la tierra. Mate se convertirá en un espía secreto y McQueen competirá contra los mejores coches. El campeonato los llevará a Japón, París, Londres y por último, a Italia. Sin embargo, estarán muy ocupados para poder disfrutar de los placeres de cada lugar.", Genero.ANIMACION, Valoracion.CUATRO_ESTRELLAS, "/imagenes/cars2.jpg");
	    Pelicula p7 = new Pelicula("Cars 3", 109, "Eclipsado por los coches jóvenes, el veterano Rayo McQueen se ha visto expulsado del deporte que tanto ama. Sin embargo, no se rendirá tan fácilmente. Con la ayuda de sus amigos, Rayo aprende trucos nuevos para vencer al arrogante Jackson Storm.", Genero.ANIMACION, Valoracion.CUATRO_ESTRELLAS, "/imagenes/cars3.jpg");
	    Pelicula p8 = new Pelicula("Shrek", 89, "Hace mucho tiempo, en una lejana ciénaga, vivía un ogro llamado Shrek. Un día, su preciada soledad se ve interrumpida por un montón de personajes de cuento de hadas que invaden su casa. Todos fueron desterrados de su reino por el malvado Lord Farquaad. Decidido a devolverles su reino y recuperar la soledad de su ciénaga, Shrek llega a un acuerdo con Lord Farquaad y va a rescatar a la princesa Fiona, la futura esposa del rey. Sin embargo, la princesa esconde un oscuro secreto.", Genero.FANTASIA, Valoracion.CINCO_ESTRELLAS, "/imagenes/shrek.jpg");
	    Pelicula p9 = new Pelicula("Harry Potter y la piedra filosofal", 152 , "El día en que cumple once años, Harry Potter descubre que es hijo de dos conocidos hechiceros, de los que ha heredado poderes mágicos. Deberá acudir entonces a una famosa escuela de magia y hechicería: Howards.", Genero.FANTASIA, Valoracion.CUATRO_ESTRELLAS, "/imagenes/harry.jpg");
	    Pelicula p10 = new Pelicula("El diario de Noa", 124, "Historia de amor entre Allie Hamilton y Noah Calhoun y recordada en una residencia de ancianos, décadas después de que sucediera. Basada en el libro de Nicholas Sparks.", Genero.ROMANCE, Valoracion.CUATRO_ESTRELLAS, "/imagenes/noa.jpg");
	    lPeliculas.add(p1);
	    lPeliculas.add(p2);
	    lPeliculas.add(p3);
	    lPeliculas.add(p4);
	    lPeliculas.add(p5);
	    lPeliculas.add(p6);
	    lPeliculas.add(p7);
	    lPeliculas.add(p8);
	    lPeliculas.add(p9);
	    lPeliculas.add(p10);
	}
}
