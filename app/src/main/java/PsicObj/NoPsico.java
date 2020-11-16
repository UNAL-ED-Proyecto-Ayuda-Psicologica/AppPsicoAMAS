package PsicObj;

import java.util.Date;

import DataStructures.Queue;

public class NoPsico extends User{
    String ClinicStory;
    Panic panic;

    public NoPsico(String nombre, String usuario, String contraseña, String correo) {
        super(nombre, usuario, contraseña, correo);
    }


    public void askForHelp(){

    }

    public Panic panicButton(Queue<Panic> panicQueue, String situation){
        if(this.panic==null){
            this.panic=new Panic(this, situation);
            panicQueue.enqueue(this.panic);
            return this.panic;
        }
        return null;
    }

    public Panic panicButton(Queue<Panic> panicQueue, String situation, int priority){
        if(this.panic==null){
            this.panic=new Panic(this, situation);
            panicQueue.enqueue(this.panic,priority);
            return this.panic;
        }
        return null;
    }

    public void askforDate(Psico doctor, Date date){
        doctor.pending.enqueue(new PsicoDate(doctor,this,date));//La confirmación de la cita es inmediata en este caso
    }

    public Panic getPanic() {
        return panic;
    }

    @Override
    public int compareTo(User o) {
        return (this.usuario.compareTo(o.usuario)+this.contraseña.compareTo(o.contraseña));
    }

    public void solvePanic() {
        this.panic=null;
    }
}
