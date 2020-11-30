package com.example.apppsicoamas;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;

import Pruebas.DataBase;
import Pruebas.Singleton;
import PsicObj.Publication;
import PsicObj.User;

public abstract class OnSessionActivity extends AppCompatActivity {

    protected RecyclerView recycler;
    protected AdapterPosts adapter;

    public abstract void update();
    public abstract User getCurrentUser();


 /*   @SuppressLint("SetTextI18n")
    public void setPosts(boolean firstTime){
        try{
            Publication post = DataBase.posts.get(postsIndex);
            if (!DataBase.posts.isEmpty()) {
                postView.setText(post.getUser().getNombre()+"(" +post.getUser().getUsuario() +") dice:" +
                        " \n"+post.getContent()+"\n [Este post tiene "+post.getnUps()+" ups y fue publicado el "+post.getPublishDate().toString()+"]");
                if(firstTime) commentsIndex=post.getComments().length()-1;
                Publication comment = post.getComments().get(commentsIndex);
                if (!post.getComments().isEmpty()) {
                    commentView.setText(comment.getUser().getNombre()+"(" +comment.getUser().getUsuario() +") dice:"+comment.getContent());
                } else commentView.setText("(Este post no tiene comentarios)");
            } else {
                commentView.setText("");
                postView.setText("No hay publicaciones recientes");
            }
        }catch (NullPointerException e){
            Toast.makeText(OnSessionActivity.this,"No hay elementos que ver",Toast.LENGTH_LONG).show();
        }
    }*/


    public void signOff(View view){
        Singleton.setCurrentUserN(null);
        Singleton.setCurrentUserP(null);
        startActivity(new Intent(OnSessionActivity.this,MainActivity.class));
    }


    public void deleteAccount(View view){
        DataBase.listadeusuarios.remove(getCurrentUser());
        DataBase.listanombredeusuarios.remove(getCurrentUser().getUsuario());
        Toast.makeText(OnSessionActivity.this, "Has eliminado tu cuenta con éxito!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(OnSessionActivity.this, MainActivity.class));
    }

/*    public void toggleUpPost(View view){
        Publication post=DataBase.posts.get(postsIndex);
        if(post!=null) {
            post = getCurrentUser().toggleUp(post);
            setPosts(false);
        }else{
            Toast.makeText(OnSessionActivity.this,"No hay ningun post",Toast.LENGTH_SHORT).show();
        }
    }

    public void toggleUpComment(View view){
        Publication comment=DataBase.posts.get(postsIndex).getComments().get(commentsIndex);
        if(comment!=null){
            comment=getCurrentUser().toggleUp(comment);
            setPosts(false);
        }else{
            Toast.makeText(OnSessionActivity.this,"No hay ningun comentario",Toast.LENGTH_SHORT).show();
        }
    }*/


}
