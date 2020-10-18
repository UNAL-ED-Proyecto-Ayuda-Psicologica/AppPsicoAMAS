package Pruebas;


import PsicObj.*;

public class Singleton {
    private static final Singleton INSTANCE = new Singleton();
    private static Psico currentUserP;
    private  static NoPsico currentUserN;
    // El constructor privado no permite que se genere un constructor por defecto.
    // (con mismo modificador de acceso que la definición de la clase)
    private Singleton() {}

    public static Singleton getInstance() {
        return INSTANCE;
    }

    public static Psico getCurrentUserP() {
        return currentUserP;
    }

    public static void setCurrentUserP(Psico currentUserP) {
        Singleton.currentUserP = currentUserP;
    }

    public static NoPsico getCurrentUserN() {
        return currentUserN;
    }

    public static void setCurrentUserN(NoPsico currentUserN) {
        Singleton.currentUserN = currentUserN;
    }
}