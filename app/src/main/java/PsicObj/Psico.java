package PsicObj;

import androidx.annotation.Nullable;

import DataStructures.Queue;

public class Psico extends User {
    String ProfesionalCard;
    String Speciality;
    String University;
    Queue<PsicoDate> pending;

    public Psico(String nombre, String usuario, String contraseña,String correo) {
        super(nombre, usuario, contraseña, correo);
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
    public int compareTo(User o) {
        return (this.usuario.compareTo(o.usuario)+this.contraseña.compareTo(o.contraseña));
    }
}
