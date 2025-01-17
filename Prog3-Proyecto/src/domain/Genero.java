package domain;

public enum Genero {
    ACCION, ANIMACION, CIENCIA_FICCION, COMEDIA, DRAMA, FANTASIA, MUSICAL, ROMANCE, SUSPENSE, TERROR;

    /**
     * Compara este género con otro género ignorando mayúsculas y minúsculas.
     * 
     * @param otroGenero El género a comparar.
     * @return True si los géneros son iguales, false en caso contrario.
     */
    public boolean equalsIgnoreCase(Genero otroGenero) {
        if (otroGenero == null) {
            return false;
        }
        return this.name().equalsIgnoreCase(otroGenero.name());
    }
}
