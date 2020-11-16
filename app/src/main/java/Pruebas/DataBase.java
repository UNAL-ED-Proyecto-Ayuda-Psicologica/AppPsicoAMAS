package Pruebas;

import DataStructures.AVLTree;
import DataStructures.DoublyLinkedList;
import DataStructures.DynamicArray;
import DataStructures.Queue;
import DataStructures.Stack;
import PsicObj.*;

public class DataBase {
    public static AVLTree<User> listadeusuarios = new AVLTree<>();
    public static AVLTree<String> listanombredeusuarios = new AVLTree<>();
    public static Queue<Panic> botonesDePanico = new Queue<>();
    public static DynamicArray<Publication> posts = new DynamicArray<>();
    //public static Stack<Publication> auxPosts = new Stack<>();

    public static void agregarUsuario(User user){
        listadeusuarios.insert(user);
        listanombredeusuarios.insert(user.getUsuario());
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
