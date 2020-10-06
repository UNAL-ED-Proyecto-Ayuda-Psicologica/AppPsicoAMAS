package PsicObj;

import java.util.Date;

public class NoPsico extends User implements Comparable<NoPsico>{
    String ClinicStory;

    public NoPsico(String nombre, String usuario, String contraseña) {
        super(nombre, usuario, contraseña);
    }

    @Override
    public int compareTo(NoPsico o) {
        return (this.usuario.compareTo(o.usuario)+this.contraseña.compareTo(o.contraseña));
        //Compare to
        //devuelve <0, entonces la cadena que llama al método es primero lexicográficamente
        //devuelve == 0 entonces las dos cadenas son lexicográficamente equivalentes
        //devuelve> 0, entonces el parámetro pasado al método compareTo es lexicográficamente el primero.
    }

    public void askForHelp(){

    }

    public void PanicButton(){

    }

    public void askforDate(Psico doctor, Date date){
        doctor.pending.enqueue(new PsicoDate(doctor,this,date));//La confirmación de la cita es inmediata en este caso
    }
}
