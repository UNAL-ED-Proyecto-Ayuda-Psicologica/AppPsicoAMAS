package com.example.apppsicoamas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

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
public class OnSessionActivityP extends OnSessionActivity /*AppCompatActivity*/ {
    private TextView userView;
    private TextView userSituation;

    /*
    private int commentsIndex;
    private int postsIndex;
    private TextView postView;
    private  TextView commentView;
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_session_p);
        userView=findViewById(R.id.tvPanic);
        userSituation=findViewById(R.id.tvSituation);

        if(!DataBase.botonesDePanico.isEmpty()) {
            long inicio = System.nanoTime();
            Panic panicO = DataBase.botonesDePanico.peek();
            long fin = System.nanoTime();

            Toast.makeText(this, "Reviso la cola " + ((fin - inicio) * 1.0e-9), Toast.LENGTH_LONG).show();
            String userViewString = panicO.getUser().getNombre() + " necesita tu ayuda:";
            String situationViewString = panicO.getSituation();
            userView.setText(userViewString);
            userSituation.setText(situationViewString);
        }else{
            userView.setText("Nadie ha oprimido el botón de pánico recientemente");
            userSituation.setText("---");
        }

        recycler = findViewById(R.id.recyclerPosts);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdapterPosts(DataBase.posts);
        recycler.setAdapter(adapter);
        adapter.setOnItemClickListener(new AdapterPosts.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position) {
                Intent i = new Intent(OnSessionActivityP.this, CommentsActivity.class);
                i.putExtra("position", position);
                startActivity(i);
            }

            @Override
            public void onCommentClickListener(int position) {
                makeComment(position,OnSessionActivityP.this);
            }
        });


    }

    public void attendPanic(View view){
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Este es tu paciente");
            final TextView textView = new TextView(this);
            textView.setText("El correo del paciente es: " + DataBase.botonesDePanico.peek().getUser().getCorreo() + "\nEstablece contacto con él apenas puedas");
            builder.setView(textView);

            builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    long inicio = System.nanoTime();
                    Singleton.getCurrentUserP().attendtoHelp(DataBase.botonesDePanico);
                    //Panic p = DataBase.botonesDePanico.dequeue();
                    long fin = System.nanoTime();

                    Toast.makeText(OnSessionActivityP.this, "Desencolo " + ((fin - inicio) * 1.0e-9), Toast.LENGTH_LONG).show();
                    //p.solve(Singleton.getCurrentUserP());
                    startActivity(new Intent(OnSessionActivityP.this,OnSessionActivityP.class));
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
            Toast.makeText(OnSessionActivityP.this,"Nadie está en crisis, por ahora.",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void update() {
        startActivity(new Intent(OnSessionActivityP.this,OnSessionActivityP.class));
    }

    @Override
    public User getCurrentUser() {
        return Singleton.getCurrentUserP();
    }
/*
    public void deletePost(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¿Estás seguro de que quieres borrar este post?");

        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DataBase.posts.delete(postsIndex);
                Toast.makeText(OnSessionActivityP.this,"Post borrado exitosamente",Toast.LENGTH_LONG).show();
                startActivity(new Intent(OnSessionActivityP.this,OnSessionActivityP.class));
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
                Toast.makeText(OnSessionActivityP.this,"Comentario borrado exitosamente",Toast.LENGTH_LONG).show();
                startActivity(new Intent(OnSessionActivityP.this,OnSessionActivityP.class));
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
*/
}