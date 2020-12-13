package Pruebas;

import DataStructures.AVLTree;
import DataStructures.DoublyLinkedList;
import DataStructures.DynamicArray;
import DataStructures.PriorityQueue;
import DataStructures.*;
import PsicObj.*;

public class DataBase {
    public static StringHashMap<User> listadeusuarios = new StringHashMap<>();
    public static StringHashMap<String> listanombredeusuarios = new StringHashMap<>();
    public static Queue<Panic> botonesDePanico = new PriorityQueue<>();
    public static DynamicArray<Publication> posts = new DynamicArray<>();


    //public static Stack<Publication> auxPosts = new Stack<>();

    public static void agregarUsuario(User user){
        //listadeusuarios.insert(user);
        listadeusuarios.put(user.getUsuario(),user);
        listanombredeusuarios.put(user.getUsuario(),user.getUsuario());
    }
    /*
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
*/
}
