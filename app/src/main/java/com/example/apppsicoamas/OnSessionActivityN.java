package com.example.apppsicoamas;

import androidx.appcompat.app.*;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import Pruebas.DataBase;
import Pruebas.Singleton;
import PsicObj.*;

public class OnSessionActivityN extends AppCompatActivity {
    private EditText writingPost;

    private int commentsIndex;
    private int postsIndex;
    private TextView postView;
    private  TextView commentView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        postsIndex=DataBase.posts.length()-1;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_session);
        writingPost=findViewById(R.id.writingPost);

        postView=findViewById(R.id.postText);
        commentView=findViewById(R.id.commentText);
        setPosts(true);
        Panic p=Singleton.getCurrentUserN().getPanic();
        if(p!=null){
            if (p.isSolved()) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Un psicólogo puede responder a tu ayuda");
                final String[] m_Text = {" "};
// Set up the input
                final TextView output = new TextView(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                output.setText("Su correo es: " + p.getPsico().getCorreo());
                builder.setView(output);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        }

    }
    public void setPosts(boolean firstTime){
        try{
            Publication post = DataBase.posts.get(postsIndex);
            if (!DataBase.posts.isEmpty()) {
                postView.setText(post.getUser().getNombre()+"(" +post.getUser().getUsuario() +") dice:"+post.getContent());
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
            Toast.makeText(OnSessionActivityN.this,"No hay elementos que ver",Toast.LENGTH_LONG).show();
        }
    }


    public void nextComment(View view){
        if(this.commentsIndex > 0){
            this.commentsIndex--;
            this.setPosts(false);
        }else{
            Toast.makeText(OnSessionActivityN.this,"No hay más elementos que ver",Toast.LENGTH_LONG).show();
        }
    }
    public void prevComment(View view){
        if(this.commentsIndex < DataBase.posts.get(postsIndex).getComments().length()-1){
            this.commentsIndex++;
            this.setPosts(false);
        }else{
            Toast.makeText(OnSessionActivityN.this,"No hay más elementos que ver",Toast.LENGTH_LONG).show();
        }

    }
    public void prevPost(View view){
        if(this.postsIndex < DataBase.posts.length()-1){
            this.postsIndex++;
            this.setPosts(false);
        }else{
            Toast.makeText(OnSessionActivityN.this,"No hay más elementos que ver",Toast.LENGTH_LONG).show();
        }

    }
    public void nextPost(View view){
        if(this.postsIndex > 0){
            this.postsIndex--;
            this.setPosts(false);
        }else{
            Toast.makeText(OnSessionActivityN.this,"No hay más elementos que ver",Toast.LENGTH_LONG).show();
        }

    }


    public void panicButton(View view){
        final int prevlenght=DataBase.botonesDePanico.length();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¿Cúal es tu situación?");
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
                Singleton.getCurrentUserN().PanicButton(DataBase.botonesDePanico, m_Text[0]);
                if(DataBase.botonesDePanico.length()>prevlenght) Toast.makeText(OnSessionActivityN.this,"Mensaje enviado con exito",Toast.LENGTH_LONG).show();
                else Toast.makeText(OnSessionActivityN.this,"Ups, pasó un error, tal vez ya enviaste tu mensaje",Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

    }

    public void makePost(View view){
        int prev = DataBase.posts.length();
        Singleton.getCurrentUserN().writePost(DataBase.posts,writingPost.getText().toString(),new Date());


        if(DataBase.posts.length()>prev){
            startActivity(new Intent(OnSessionActivityN.this,OnSessionActivityN.class));
            Toast.makeText(OnSessionActivityN.this,"Publicado con éxito",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(OnSessionActivityN.this,"Error al publicar",Toast.LENGTH_LONG).show();
        }

    }

    public void signOff(View view){
        Singleton.setCurrentUserN(null);
        startActivity(new Intent(OnSessionActivityN.this,MainActivity.class));
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
                    post.addComment(m_Text[0], Singleton.getCurrentUserN(), new Date());
                    if (post.getComments().length() > prevlenght)
                        Toast.makeText(OnSessionActivityN.this, "Mensaje enviado con exito", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(OnSessionActivityN.this, "Ups, pasó un error", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(OnSessionActivityN.this, OnSessionActivityN.class));
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

    public void deleteAccount(View view){
        DataBase.listadeusuarios.delete(Singleton.getCurrentUserN());
        DataBase.listanombredeusuarios.delete(Singleton.getCurrentUserN().getUsuario());
        Toast.makeText(OnSessionActivityN.this, "Has eliminado tu cuenta con éxito!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(OnSessionActivityN.this,MainActivity.class));
    }

    public void deletePost(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¿Estás seguro de que quieres borrar este post?");

        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DataBase.posts.delete(postsIndex);
                Toast.makeText(OnSessionActivityN.this, "Post borrado exitosamente", Toast.LENGTH_LONG).show();
                startActivity(new Intent(OnSessionActivityN.this, OnSessionActivityN.class));
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

    public void deleteComment(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¿Estás seguro de que quieres borrar este post?");

        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Publication post = DataBase.posts.get(postsIndex);
                post.getComments().delete(postsIndex);
                Toast.makeText(OnSessionActivityN.this, "Comentario borrado exitosamente", Toast.LENGTH_LONG).show();
                startActivity(new Intent(OnSessionActivityN.this, OnSessionActivityN.class));
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
}