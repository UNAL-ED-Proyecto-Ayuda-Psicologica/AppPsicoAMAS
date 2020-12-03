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

import java.util.Date;

import Pruebas.DataBase;
import Pruebas.Singleton;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        int postPosition = getIntent().getIntExtra("position",0);
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
        startActivity(new Intent(CommentsActivity.this,CommentsActivity.class));
    }

    @Override
    public User getCurrentUser() {
        return Singleton.getCurrentUserN();
    }
}