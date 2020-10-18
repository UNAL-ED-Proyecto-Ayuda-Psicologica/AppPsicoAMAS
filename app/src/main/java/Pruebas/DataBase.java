package Pruebas;

import DataStructures.DoublyLinkedList;
import DataStructures.Queue;
import DataStructures.Stack;
import PsicObj.*;

public class DataBase {
    public static DoublyLinkedList<User> listadeusuarios = new DoublyLinkedList<>();
    public static DoublyLinkedList<String> listanombredeusuarios = new DoublyLinkedList<>();
    public static Queue<Panic> botonesDePanico = new Queue<>();
    public static Stack<Publication> posts = new Stack<>();
    public static Stack<Publication> auxPosts = new Stack<>();

    public static void agregarUsuario(User user){
        listadeusuarios.insert(user);
        listanombredeusuarios.insert(user.getUsuario());
    }
    public static void updateAll(){
        updatePostsStacks();
    }

    public static void updatePostsStacks(){
        while(!auxPosts.isEmpty()){
            posts.push(auxPosts.pop());
        }
    }

    public static void updateCommentStacks(Publication post){
        while(!post.getAuxcomments().isEmpty()){
            post.getComments().push(post.getAuxcomments().pop());
        }
    }

}
