package PsicObj;

public class Psico extends User implements Comparable<Psico> {

    public Psico(String nombre, String usuario, String contraseña) {
        super(nombre, usuario, contraseña);
    }

    @Override
    public int compareTo(Psico o) {
        return (this.usuario.compareTo(o.usuario)+this.contraseña.compareTo(o.contraseña));
        //Compare to
        //devuelve <0, entonces la cadena que llama al método es primero lexicográficamente
        //devuelve == 0 entonces las dos cadenas son lexicográficamente equivalentes
        //devuelve> 0, entonces el parámetro pasado al método compareTo es lexicográficamente el primero.
    }
}
