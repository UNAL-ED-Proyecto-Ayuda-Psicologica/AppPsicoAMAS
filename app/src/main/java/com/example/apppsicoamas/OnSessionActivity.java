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

import java.util.Date;

import Pruebas.DataBase;
import Pruebas.Singleton;
import PsicObj.Publication;
import PsicObj.User;

public abstract class OnSessionActivity extends AppCompatActivity {
    protected int commentsIndex;
    protected int postsIndex;
    protected TextView postView;
    protected  TextView commentView;

    public abstract void update();
    public abstract User getCurrentUser();

    @SuppressLint("SetTextI18n")
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
    }

    public void nextComment(View view){
        if(this.commentsIndex > 0){
            this.commentsIndex--;
            this.setPosts(false);
        }else{
            Toast.makeText(OnSessionActivity.this,"No hay más elementos que ver",Toast.LENGTH_LONG).show();
        }
    }
    public void prevComment(View view){
        if(this.commentsIndex < DataBase.posts.get(postsIndex).getComments().length()-1){
            this.commentsIndex++;
            this.setPosts(false);
        }else{
            Toast.makeText(OnSessionActivity.this,"No hay más elementos que ver",Toast.LENGTH_LONG).show();
        }

    }
    public void prevPost(View view){
        if(this.postsIndex < DataBase.posts.length()-1){
            this.postsIndex++;
            this.setPosts(true);
        }else{
            Toast.makeText(OnSessionActivity.this,"No hay más elementos que ver",Toast.LENGTH_LONG).show();
        }

    }
    public void nextPost(View view){
        if(this.postsIndex > 0){
            this.postsIndex--;
            this.setPosts(true);
        }else{
            Toast.makeText(OnSessionActivity.this,"No hay más elementos que ver",Toast.LENGTH_LONG).show();
        }

    }
    public void signOff(View view){
        Singleton.setCurrentUserN(null);
        Singleton.setCurrentUserP(null);
        startActivity(new Intent(OnSessionActivity.this,MainActivity.class));
    }

    public void makeComment(View view){
        try {
            final Publication post = DataBase.posts.get(postsIndex);
            final int prevlenght = post.getComments().length();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("¿Qué opinas?");
            final String[] m_Text = {" "};
// Set up the input
            final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);

// Set up the buttons
            builder.setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    m_Text[0] = input.getText().toString();
                    post.addComment(m_Text[0], getCurrentUser(), new Date());
                    if (post.getComments().length() > prevlenght)
                        Toast.makeText(OnSessionActivity.this, "Mensaje enviado con exito", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(OnSessionActivity.this, "Ups, pasó un error", Toast.LENGTH_LONG).show();
                    update();
                    //startActivity(new Intent(OnSessionActivity.this, this.getClass()));

                }
            });
            builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            builder.show();
        }catch (NullPointerException e){
            Toast.makeText(this,"Error",Toast.LENGTH_LONG);
        }
    }

    public void deletePost(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¿Estás seguro de que quieres borrar este post?");

        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DataBase.posts.delete(postsIndex);
                Toast.makeText(OnSessionActivity.this,"Post borrado exitosamente",Toast.LENGTH_LONG).show();
                update();
                //startActivity(new Intent(OnSessionActivity.this, this.getClass()));
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();

    }

    public void deleteComment(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¿Estás seguro de que quieres borrar este post?");

        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Publication post=DataBase.posts.get(postsIndex);
                post.getComments().delete(commentsIndex);
                Toast.makeText(OnSessionActivity.this,"Comentario borrado exitosamente",Toast.LENGTH_LONG).show();
                update();
                //startActivity(new Intent(OnSessionActivity.this,this.getClass()));
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();

    }


    public void deleteAccount(View view){
        DataBase.listadeusuarios.delete(getCurrentUser());
        DataBase.listanombredeusuarios.delete(getCurrentUser().getUsuario());
        Toast.makeText(OnSessionActivity.this, "Has eliminado tu cuenta con éxito!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(OnSessionActivity.this, MainActivity.class));
    }

    public void toggleUpPost(View view){
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
    }


}
