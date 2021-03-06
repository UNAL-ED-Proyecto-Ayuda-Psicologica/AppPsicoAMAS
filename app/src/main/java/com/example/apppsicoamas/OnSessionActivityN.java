package com.example.apppsicoamas;

import androidx.appcompat.app.*;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

import DataStructures.DoublyLinkedList;
import Pruebas.DataBase;
import Pruebas.Singleton;
import PsicObj.*;

public class OnSessionActivityN extends OnSessionActivity /*AppCompatActivity*/ {
    private EditText writingPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_session);
        getSupportActionBar().setTitle("Inicio");
        writingPost=findViewById(R.id.writingPost);

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
        recycler = findViewById(R.id.recyclerPosts);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdapterPosts(DataBase.posts);
        recycler.setAdapter(adapter);
        adapter.setOnItemClickListener(new AdapterPosts.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position) {
                Intent i = new Intent(OnSessionActivityN.this, CommentsActivity.class);
                i.putExtra("position", position);
                startActivity(i);
            }

            @Override
            public void onCommentClickListener(int position) {
                    makeComment(position,OnSessionActivityN.this);
            }

            @Override
            public void onEditClickListener(int position) {
                editPost(position, OnSessionActivityN.this);
            }

            @Override
            public void onUpClickListener(int position) {
                toggleUpPost(position, OnSessionActivityN.this);
            }


        });
    }

    public void panicButton(View view){
        final int prevlenght=DataBase.botonesDePanico.length();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¿Cúal es tu situación?");
        final String[] m_Text = {" "};
        final LinearLayout layout = new LinearLayout(this);
        final RadioGroup rgroup=new RadioGroup(this);
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setHint("Agrega un comentario de tu situación");

        final RadioButton prio1= new RadioButton(this);
        final RadioButton prio2= new RadioButton(this);
        final RadioButton prio3= new RadioButton(this);

        layout.setOrientation(LinearLayout.VERTICAL);

        prio1.setText("Estás pensando en suicidarte y hacerte daño, o ya lo has intentado");
        prio2.setText("Estas alejado/ausente de tus seres queridos, te sientes cansado(a), deprimido(a), no puedes dormir bien o estás ansioso");
        prio3.setText("No te sientes en crisis pero crees que necesitas ayuda psicológica o psiquiátrica");

        rgroup.addView(prio1);
        rgroup.addView(prio2);
        rgroup.addView(prio3);

        layout.addView(rgroup);
        layout.addView(input);
        builder.setView(layout);


// Set up the buttons
        builder.setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int prio = 0;
                if(prio1.isChecked()) prio = 1;
                else if(prio2.isChecked()) prio = 2;
                else if(prio3.isChecked()) prio = 3;
                m_Text[0] = input.getText().toString();
                Singleton.getCurrentUserN().panicButton(DataBase.botonesDePanico, m_Text[0],prio);
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

    @Override
    public void update() {
        startActivity(new Intent(OnSessionActivityN.this,OnSessionActivityN.class));
    }

    @Override
    public User getCurrentUser() {
        return Singleton.getCurrentUserN();
    }

  /*  public void deletePost(View view) {
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
                post.getComments().delete(commentsIndex);
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
    }*/
}