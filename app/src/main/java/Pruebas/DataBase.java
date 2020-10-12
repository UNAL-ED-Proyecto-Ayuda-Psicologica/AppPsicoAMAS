package Pruebas;

import DataStructures.DoublyLinkedList;
import PsicObj.Psico;
import PsicObj.User;

public class DataBase {
    public static DoublyLinkedList<User> listadeusuarios = new DoublyLinkedList<>();
    public static DoublyLinkedList<String> listanombredeusuarios = new DoublyLinkedList<>();

    public static void agregarUsuario(User user){
        listadeusuarios.insert(user);
        listanombredeusuarios.insert(user.getUsuario());
    }

}
