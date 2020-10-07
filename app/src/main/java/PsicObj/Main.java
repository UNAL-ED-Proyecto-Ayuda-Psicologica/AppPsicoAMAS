package PsicObj;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        int tipo = 1;//0 Psico y 1 NoPsico
        Login objLogin = new Login();


        if(tipo==0){
            Psico objUsuario = new Psico("Pepito","pepito1","pepito12");
            if(objLogin.logIn(objUsuario)==true){
                System.out.println("Iniciaste Sesion");
            }else{
                System.err.println("Usuario o contraseña incorrecto");
            }
        }else{
            NoPsico objUsuario = new NoPsico(null, "Juanit", "12345");
            if(objLogin.logIn(objUsuario)==true){
                System.out.println("Iniciaste Sesion");
            }else{
                System.err.println("Usuario o contraseña incorrecto");
            }
        }

    }

}



