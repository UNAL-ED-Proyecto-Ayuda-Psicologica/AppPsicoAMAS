package com.example.apppsicoamas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Comment;

import java.util.Date;

import Pruebas.DataBase;
import Pruebas.Singleton;
import PsicObj.NoPsico;
import PsicObj.Publication;
import PsicObj.User;

public class CommentsActivity extends OnSessionActivity {
    private RecyclerView recycler;
    private AdapterComments adapter;
    private TextView nombre;
    private TextView usuario;
    private ImageView foto;
    private TextView contenidoPost;
    private Publication currentPost;
    private int postPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        getSupportActionBar().setTitle("Detalles de la publicación");

        postPosition = getIntent().getIntExtra("position",0);
        currentPost = DataBase.posts.get(postPosition);

        nombre = findViewById(R.id.tvNombre);
        usuario = findViewById(R.id.tvNombredeusuario);
        foto = findViewById(R.id.ivFoto);
        contenidoPost = findViewById(R.id.tvContenidopost);

        nombre.setText(currentPost.getUser().getNombre());
        usuario.setText("@" + currentPost.getUser().getUsuario());
        contenidoPost.setText(currentPost.getContent());
        foto.setImageResource(R.drawable.avatar);

        recycler = findViewById(R.id.recyclerComments);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        adapter = new AdapterComments(DataBase.posts.get(postPosition).getComments());
        recycler.setAdapter(adapter);

        adapter.setOnItemClickListener(new AdapterComments.OnItemClickListener() {
            @Override
            public void onEditClickListener(int position) {
                editComment(position, CommentsActivity.this);
            }

            @Override
            public void onUpClickListener(int position) {
                toggleUpComment(position, CommentsActivity.this);
            }
        });
    }

    public void makeComment(View view){
        try {
            final int prevlenght = currentPost.getComments().length();
            AlertDialog.Builder builder = new AlertDialog.Builder(CommentsActivity.this);
            builder.setTitle("¿Qué opinas?");
            final String[] m_Text = {" "};
// Set up the input
            final EditText input = new EditText(CommentsActivity.this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);

// Set up the buttons
            builder.setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    m_Text[0] = input.getText().toString();
                    currentPost.addComment(m_Text[0], getCurrentUser(), new Date());
                    if (currentPost.getComments().length() > prevlenght)
                        Toast.makeText(CommentsActivity.this, "Mensaje enviado con exito", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(CommentsActivity.this, "Ups, pasó un error", Toast.LENGTH_LONG).show();
                    update();
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
            Toast.makeText(CommentsActivity.this,"Error",Toast.LENGTH_LONG);
        }

    }

    @Override
    public void update() {
        Intent i = new Intent(CommentsActivity.this,CommentsActivity.class);
        i.putExtra("position",this.postPosition);
        startActivity(i);
    }

    @Override
    public User getCurrentUser() {
        if(Singleton.getCurrentUserP()==null)
        return Singleton.getCurrentUserN();
        else return Singleton.getCurrentUserP();
    }

    @Override
    public void onBackPressed() {
        if(getCurrentUser() instanceof NoPsico){
            startActivity(new Intent(CommentsActivity.this,OnSessionActivityN.class));
        }else{
            startActivity(new Intent(CommentsActivity.this,OnSessionActivityP.class));
        }

        super.onBackPressed();
    }
    public void toggleUpComment(int position, final OnSessionActivity activity){
        Publication comment=DataBase.posts.get(postPosition).getComments().get(position);
        if(comment!=null) {
            comment = getCurrentUser().toggleUp(comment);
            update();
        }else{
            Toast.makeText(activity,"No hay ningun post",Toast.LENGTH_SHORT).show();
        }
    }

    public void editComment(int position, final OnSessionActivity activity){
        final Publication comment = DataBase.posts.get(postPosition).getComments().get(position);
        if(comment!=null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setTitle("Edición");
            final String[] m_Text = {" "};
            final String prevContent=comment.getContent();
// Set up the input
            final EditText input = new EditText(activity);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);
            input.setText(prevContent);


// Set up the buttons
            builder.setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    m_Text[0] = input.getText().toString();
                    try {
                        getCurrentUser().modifyPost(comment, m_Text[0]);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (!comment.getContent().equals(prevContent))
                        Toast.makeText(activity, "Post editado con exito", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(activity, "No hubo cambios", Toast.LENGTH_LONG).show();
                    update();

                }
            });
            builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            builder.show();
        }else{
            Toast.makeText(activity,"No hay ningun post",Toast.LENGTH_SHORT).show();
        }
    }
}
