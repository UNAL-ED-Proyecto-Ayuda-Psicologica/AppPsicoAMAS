package com.example.apppsicoamas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.style.TtsSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import DataStructures.Stack;
import Pruebas.DataBase;
import Pruebas.Singleton;
import PsicObj.*;
public class OnSessionActivityP extends AppCompatActivity {
    private TextView userView;
    private TextView userSituation;
    private int commentsIndex;
    private int postsIndex;
    private TextView postView;
    private  TextView commentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        postsIndex=DataBase.posts.length()-1;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_session_p);
        userView=findViewById(R.id.tvPanic);
        userSituation=findViewById(R.id.tvSituation);
        postView=findViewById(R.id.postText);
        commentView=findViewById(R.id.commentText);
        setPosts(true);

        if(!DataBase.botonesDePanico.isEmpty()) {
            Panic panicO = DataBase.botonesDePanico.peek();
            String userViewString = panicO.getUser().getNombre() + " necesita tu ayuda:";
            String situationViewString = panicO.getSituation();
            userView.setText(userViewString);
            userSituation.setText(situationViewString);
        }else{
            userView.setText("Nadie ha oprimido el botón de pánico recientemente");
            userSituation.setText("---");
        }


    }

    public void setPosts(boolean firstTime){
        try {
            Publication post = DataBase.posts.get(postsIndex);
            if (!DataBase.posts.isEmpty()) {
                postView.setText(post.getUser().getNombre() + "(" + post.getUser().getUsuario() + ") dice:" + post.getContent());
                if(firstTime) commentsIndex=post.getComments().length()-1;
                Publication comment = post.getComments().get(commentsIndex);
                if (!post.getComments().isEmpty()) {
                    commentView.setText(comment.getUser().getNombre() + "(" + comment.getUser().getUsuario() + ") dice:" + comment.getContent());
                } else commentView.setText("(Este post no tiene comentarios)");
            } else {
                commentView.setText("");
                postView.setText("No hay publicaciones recientes");
            }
        }catch (NullPointerException e){
            Toast.makeText(OnSessionActivityP.this,"No hay elementos que ver",Toast.LENGTH_LONG).show();
        }
    }

    public void nextComment(View view){
        if(this.commentsIndex > 0){
            this.commentsIndex--;
            this.setPosts(false);
        }else{
            Toast.makeText(OnSessionActivityP.this,"No hay más elementos que ver",Toast.LENGTH_LONG).show();
        }
    }
    public void prevComment(View view){
        if(this.commentsIndex < DataBase.posts.get(postsIndex).getComments().length()-1){
            this.commentsIndex++;
            this.setPosts(false);
        }else{
            Toast.makeText(OnSessionActivityP.this,"No hay más elementos que ver",Toast.LENGTH_LONG).show();
        }

    }
    public void prevPost(View view){
        if(this.postsIndex < DataBase.posts.length()-1){
            this.postsIndex++;
            this.setPosts(false);
        }else{
            Toast.makeText(OnSessionActivityP.this,"No hay más elementos que ver",Toast.LENGTH_LONG).show();
        }

    }
    public void nextPost(View view){
        if(this.postsIndex > 0){
            this.postsIndex--;
            this.setPosts(false);
        }else{
            Toast.makeText(OnSessionActivityP.this,"No hay más elementos que ver",Toast.LENGTH_LONG).show();
        }

    }


    public void signOff(View view){
        Singleton.setCurrentUserP(null);
        startActivity(new Intent(OnSessionActivityP.this,MainActivity.class));
    }

    public void makeComment(View view){
        try {
            final Publication post = DataBase.posts.get(postsIndex);
            final int prevlenght = post.getComments().length();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("¿Qué opinas?");
            final String[] m_Text = {" "};
            final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);

            builder.setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    m_Text[0] = input.getText().toString();
                    post.addComment(m_Text[0], Singleton.getCurrentUserN(), new Date());
                    if (post.getComments().length() > prevlenght)
                        Toast.makeText(OnSessionActivityP.this, "Mensaje enviado con exito", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(OnSessionActivityP.this, "Ups, pasó un error", Toast.LENGTH_LONG).show();
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

    public void attendPanic(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Este es tu paciente");
        final TextView textView= new TextView(this);
        textView.setText("El correo del paciente es: "+DataBase.botonesDePanico.peek().getUser().getCorreo()+"\nEstablece contacto con él apenas puedas");
        builder.setView(textView);

        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Panic p=DataBase.botonesDePanico.dequeue();
                p.solve(Singleton.getCurrentUserP());
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

}