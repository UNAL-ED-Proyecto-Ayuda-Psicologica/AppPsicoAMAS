package PsicObj;

import androidx.annotation.Nullable;

import DataStructures.Queue;

public class Psico extends User implements Comparable<Psico> {
    String ProfesionalCard;
    String Speciality;
    String University;
    Queue<PsicoDate> pending;

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


    public void attendtoHelp(){

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Psico{");
        sb.append("nombre='").append(nombre).append('\'');
        sb.append(", usuario='").append(usuario).append('\'');
        sb.append(", contraseña='").append(contraseña).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        User otroPsico = (Psico) obj;

        if(this.usuario.equals(otroPsico.usuario) && this.contraseña.equals(otroPsico.contraseña)){
            return true;
        }else{
            return false;
        }
    }
}
