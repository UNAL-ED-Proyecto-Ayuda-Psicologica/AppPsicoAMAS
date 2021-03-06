package com.example.apppsicoamas;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
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
    public void makeComment(int position, final OnSessionActivity activity){
        try {
            final int tposition = position;
            final Publication post = DataBase.posts.get(position);
            final int prevlenght = post.getComments().length();
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setTitle("¿Qué opinas?");
            final String[] m_Text = {" "};
// Set up the input
            final EditText input = new EditText(activity);
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
                        Toast.makeText(activity, "Mensaje enviado con exito", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(activity, "Ups, pasó un error", Toast.LENGTH_LONG).show();
                    update();
                    Intent i = new Intent(activity, CommentsActivity.class);
                    i.putExtra("position", tposition);
                    startActivity(i);

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
            Toast.makeText(activity,"Error",Toast.LENGTH_LONG);
        }
    }

    public void signOff(){
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("¿Estás seguro de que quieres cerrar sesión?");

            builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Singleton.setCurrentUserN(null);
                    Singleton.setCurrentUserP(null);
                    startActivity(new Intent(OnSessionActivity.this,MainActivity.class));
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
            Toast.makeText(OnSessionActivity.this,"Error",Toast.LENGTH_LONG).show();
        }

    }


    public void deleteAccount(){
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("¿Estás seguro de que quieres eliminar tu cuenta?");

            builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    DataBase.listadeusuarios.delete(getCurrentUser().getUsuario());
                    DataBase.listanombredeusuarios.delete(getCurrentUser().getUsuario());
                    Toast.makeText(OnSessionActivity.this, "Has eliminado tu cuenta con éxito!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(OnSessionActivity.this, MainActivity.class));
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
            Toast.makeText(OnSessionActivity.this,"Error",Toast.LENGTH_LONG).show();
        }
    }

   public void toggleUpPost(int postsIndex, final OnSessionActivity activity){
        Publication post=DataBase.posts.get(postsIndex);
        if(post!=null) {
            post = getCurrentUser().toggleUp(post);
            update();
        }else{
            Toast.makeText(activity,"No hay ningun post",Toast.LENGTH_SHORT).show();
        }
    }

    public void editPost(int postsIndex, final OnSessionActivity activity){
        final Publication post = DataBase.posts.get(postsIndex);
        if(post!=null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setTitle("Edición");
            final String[] m_Text = {" "};
            final String prevContent=post.getContent();
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
                        getCurrentUser().modifyPost(post, m_Text[0]);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (!post.getContent().equals(prevContent))
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
/*
    public void toggleUpComment(View view){
        Publication comment=DataBase.posts.get(postsIndex).getComments().get(commentsIndex);
        if(comment!=null){
            comment=getCurrentUser().toggleUp(comment);
            setPosts(false);
        }else{
            Toast.makeText(OnSessionActivity.this,"No hay ningun comentario",Toast.LENGTH_SHORT).show();
        }
    }*/

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.overflow, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.itCerrarSesion){
            signOff();
        } else if (id == R.id.itEliminarCuenta) {
            deleteAccount();
        } else if (id == R.id.itEditarUsuario){
            startActivity(new Intent(OnSessionActivity.this, EditUserActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

}
