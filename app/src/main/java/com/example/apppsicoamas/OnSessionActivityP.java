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
    /*
    private Stack<Publication> auxPosts;
    private Stack<Publication> auxComments;
    private Stack<Publication> posts;
    private Stack<Publication> comments;
    */
    private TextView postView;
    private  TextView commentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_session_p);
        userView=findViewById(R.id.tvPanic);
        userSituation=findViewById(R.id.tvSituation);
        postView=findViewById(R.id.postText);
        commentView=findViewById(R.id.commentText);
        setPosts();

        if(!DataBase.botonesDePanico.isEmpty()) {
            Panic panicO = DataBase.botonesDePanico.peek();
            String userViewString = panicO.getUser().getUsuario() + " necesita tu ayuda:";
            String situationViewString = panicO.getSituation();
            userView.setText(userViewString);
            userSituation.setText(situationViewString);
        }else{
            userView.setText("Nadie ha oprimido el botón de pánico recientemente");
            userSituation.setText("---");
        }


    }

    public void setPosts(){
        DataBase.auxPosts=new Stack<>();
        DataBase.posts=DataBase.posts;
        if(!DataBase.posts.isEmpty()){
            postView.setText(DataBase.posts.peek().getContent());
            if(!DataBase.posts.peek().getComments().isEmpty()){
                commentView.setText(DataBase.posts.peek().getComments().peek().getContent());
            }else commentView.setText("(Este post no tiene comentarios)");
        }else{
            commentView.setText("");
            postView.setText("No hay publicaciones recientes");
        }
    }

    public void nextComment(View view){
        try{
            DataBase.posts.peek().getAuxcomments().push(DataBase.posts.peek().getComments().pop());
            commentView.setText(DataBase.posts.peek().getComments().peek().getContent());
        }catch (NullPointerException e){
            Toast.makeText(OnSessionActivityP.this,"No hay más comentarios",Toast.LENGTH_LONG).show();
        }

    }
    public void prevComment(View view){
        try {
            DataBase.posts.peek().getComments().push(DataBase.posts.peek().getAuxcomments().pop());
            commentView.setText(DataBase.posts.peek().getComments().peek().getContent());
        }catch (NullPointerException e){
            Toast.makeText(OnSessionActivityP.this,"No hay más comentarios",Toast.LENGTH_LONG).show();
        }

    }
    public void prevPost(View view){
        try {
            DataBase.posts.push(DataBase.auxPosts.pop());
            postView.setText(DataBase.posts.peek().getContent());
        }catch (NullPointerException e){
            Toast.makeText(OnSessionActivityP.this,"No hay más publicaciones",Toast.LENGTH_LONG).show();
        }

    }
    public void nextPost(View view){
        try {
            DataBase.auxPosts.push(DataBase.posts.pop());
            postView.setText(DataBase.posts.peek().getContent());
        }catch (NullPointerException e){
            Toast.makeText(OnSessionActivityP.this,"No hay más publicaciones",Toast.LENGTH_LONG).show();
        }

    }

    public void signOff(View view){
        DataBase.updatePostsStacks();
        Singleton.setCurrentUserP(null);
        startActivity(new Intent(OnSessionActivityP.this,MainActivity.class));
    }

    public void makeComment(View view){
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
                if(DataBase.posts.peek().getComments().length()>prevlenght) Toast.makeText(OnSessionActivityP.this,"Mensaje enviado con exito",Toast.LENGTH_LONG).show();
                else Toast.makeText(OnSessionActivityP.this,"Ups, pasó un error",Toast.LENGTH_LONG).show();
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