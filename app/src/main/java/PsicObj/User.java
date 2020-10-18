package PsicObj;

import androidx.annotation.Nullable;

import java.util.Date;

import DataStructures.List;
import DataStructures.SimplyLinkedList;
import DataStructures.Stack;

public abstract class User implements Comparable<User>{
    protected String nombre;
    protected String usuario;
    protected String contraseña;
    protected String correo;
    protected int edad;
    protected int telefono;
    public List<Publication> posts;
    public List<PsicoDate> dates;


    public User(String nombre, String usuario, String contraseña) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.posts = new SimplyLinkedList<>();
        this.dates = new SimplyLinkedList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUsuario(){
        return this.usuario;
    }

    public Publication writePost(Stack<Publication> stackP, String content, Date date) {
        Publication newPost = new Publication(content, this, date);
        stackP.push(newPost);
        this.posts.insert(newPost);
        return newPost;
    }

    public Publication modifyPost(int index, String content) {
        Publication post = posts.getK(index);
        post.setContent(content);
        return post;
    }

    public Publication modifyPost(Publication post, String content) throws Exception {
        if (post.user.equals(this)) post.setContent(content);
        else throw new Exception("Trying to modify a not owned post");
        return post;
    }

    public Publication commentPost(Publication post, String content, Date date) {
        post.addComment(content, this, date);
        return post;
    }

    public void deletePost(int index) {
        posts.deleteIndex(index);
    }

    public Publication upPost(Publication post) {
        post.giveAnUp();
        return post;
    }

    public Publication unupPost(Publication post) {
        post.removeUp();
        return post;
    }

    //public abstract int compareTo(Psico o);

    @Override
    public boolean equals(@Nullable Object obj) {
        User otroUser = (User) obj;

        if(this.usuario.equals(otroUser.usuario) && this.contraseña.equals(otroUser.contraseña)){
            return true;
        } else{
            return false;
        }
    }


}
