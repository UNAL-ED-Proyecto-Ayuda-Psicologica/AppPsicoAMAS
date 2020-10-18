package PsicObj;

import java.util.Date;

import DataStructures.Queue;

public class NoPsico extends User{
    String ClinicStory;

    public NoPsico(String nombre, String usuario, String contraseña) {
        super(nombre, usuario, contraseña);
    }


    public void askForHelp(){

    }

    public void PanicButton(Queue<Panic> panicQueue, String situation){
        panicQueue.enqueue(new Panic(this, situation));
    }

    public void askforDate(Psico doctor, Date date){
        doctor.pending.enqueue(new PsicoDate(doctor,this,date));//La confirmación de la cita es inmediata en este caso
    }

    @Override
    public int compareTo(User o) {
        return (this.usuario.compareTo(o.usuario)+this.contraseña.compareTo(o.contraseña));
    }
}
