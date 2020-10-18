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

import DataStructures.Stack;
import Pruebas.DataBase;
import Pruebas.Singleton;
import PsicObj.*;

public class OnSessionActivityN extends AppCompatActivity {
    private EditText writingPost;
    /*
    private Stack<Publication> auxPosts=new Stack<>();
    private Stack<Publication> auxComments=new Stack<>();
    private Stack<Publication> posts=new Stack<>();
    private Stack<Publication> comments=new Stack<>();
    */
    private TextView postView;
    private  TextView commentView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_session);
        writingPost=findViewById(R.id.writingPost);

        postView=findViewById(R.id.postText);
        commentView=findViewById(R.id.commentText);
        setPosts();

    }
    public void setPosts(){
        DataBase.auxPosts=new Stack<>();
        try {
            if (!DataBase.posts.isEmpty()) {
                postView.setText(DataBase.posts.peek().getContent());
                if (!DataBase.posts.peek().getComments().isEmpty()) {
                    commentView.setText(DataBase.posts.peek().getComments().peek().getContent());
                } else commentView.setText("(Este post no tiene comentarios)");
            } else {
                commentView.setText("");
                postView.setText("No hay publicaciones recientes");
            }
        }catch (NullPointerException e){
            postView.setText("No hay elementos");
        }
    }

    public void nextComment(View view){
        try{
            DataBase.posts.peek().getAuxcomments().push(DataBase.posts.peek().getComments().pop());
            commentView.setText(DataBase.posts.peek().getComments().peek().getContent());
        }catch (NullPointerException e){
            Toast.makeText(OnSessionActivityN.this,"No hay más comentarios",Toast.LENGTH_LONG).show();
        }

    }
    public void prevComment(View view){
        try {
            DataBase.posts.peek().getComments().push(DataBase.posts.peek().getAuxcomments().pop());
            commentView.setText(DataBase.posts.peek().getComments().peek().getContent());
        }catch (NullPointerException e){
            Toast.makeText(OnSessionActivityN.this,"No hay más comentarios",Toast.LENGTH_LONG).show();
        }

    }
    public void prevPost(View view){
        try {
            DataBase.posts.push(DataBase.auxPosts.pop());
            postView.setText(DataBase.posts.peek().getContent());
        }catch (NullPointerException e){
            Toast.makeText(OnSessionActivityN.this,"No hay más publicaciones",Toast.LENGTH_LONG).show();
        }

    }
    public void nextPost(View view){
        try {
            DataBase.auxPosts.push(DataBase.posts.pop());
            postView.setText(DataBase.posts.peek().getContent());
        }catch (NullPointerException e){
            Toast.makeText(OnSessionActivityN.this,"No hay más publicaciones",Toast.LENGTH_LONG).show();
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
                else Toast.makeText(OnSessionActivityN.this,"Ups, pasó un error",Toast.LENGTH_LONG).show();
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
        DataBase.updatePostsStacks();
        Singleton.setCurrentUserN(null);
        startActivity(new Intent(OnSessionActivityN.this,MainActivity.class));
    }

    public void makeComment(View view){
        DataBase.updateCommentStacks(DataBase.posts.peek());
        final Publication post=DataBase.posts.peek();
        final int prevlenght=post.getComments().length();
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
                post.addComment(m_Text[0],Singleton.getCurrentUserN(),new Date());
                if(DataBase.posts.peek().getComments().length()>prevlenght) Toast.makeText(OnSessionActivityN.this,"Mensaje enviado con exito",Toast.LENGTH_LONG).show();
                else Toast.makeText(OnSessionActivityN.this,"Ups, pasó un error",Toast.LENGTH_LONG).show();
                startActivity(new Intent(OnSessionActivityN.this,OnSessionActivityN.class));
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