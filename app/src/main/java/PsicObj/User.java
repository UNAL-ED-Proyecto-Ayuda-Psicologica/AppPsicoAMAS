package PsicObj;

import androidx.annotation.Nullable;

import java.util.Date;

import DataStructures.List;

public class User {
    String nombre;
    String usuario;
    String contraseña;
    String correo;
    int edad;
    int telefono;
    List<Publication> posts;
    List<PsicoDate> dates;

    public User(String nombre, String usuario, String contraseña) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    public Publication writePost(String content, Date date) {
        Publication newPost = new Publication(content, this, date);
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


}
