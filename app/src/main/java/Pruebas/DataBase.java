package Pruebas;

import DataStructures.DoublyLinkedList;
import PsicObj.Psico;
import PsicObj.User;

public class DataBase {
    public static DoublyLinkedList<Psico> listadeusuarios = new DoublyLinkedList<>();

    public static void agregarUsuario(Psico psico){
        listadeusuarios.insert(psico);
    }

}
