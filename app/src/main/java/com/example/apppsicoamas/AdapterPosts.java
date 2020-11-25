package com.example.apppsicoamas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import DataStructures.DoublyLinkedList;
import DataStructures.DynamicArray;
import PsicObj.Publication;

public class AdapterPosts extends RecyclerView.Adapter<AdapterPosts.ViewHolderPosts> {
    DynamicArray<Publication> arregloPosts;

    public AdapterPosts(DynamicArray<Publication> listaPosts) {
        this.arregloPosts = listaPosts;
    }

    @NonNull
    @Override
    public ViewHolderPosts onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_list,null, false);
        return new ViewHolderPosts(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPosts holder, int position) {
        holder.asignarDatos(arregloPosts.get(position));
    }

    @Override
    public int getItemCount() {
        return arregloPosts.length();
    }

    public class ViewHolderPosts extends RecyclerView.ViewHolder {
        ImageView foto;
        ImageButton up;
        ImageButton comentar;
        TextView nombre;
        TextView usuario;
        TextView contenidoPost;

        public ViewHolderPosts(@NonNull View itemView) {
            super(itemView);
            foto = itemView.findViewById(R.id.ivFoto);
            up = itemView.findViewById(R.id.ibUp);
            comentar = itemView.findViewById(R.id.ibComentar);
            nombre = itemView.findViewById(R.id.tvNombre);
            usuario = itemView.findViewById(R.id.tvNombredeusuario);
            contenidoPost = itemView.findViewById(R.id.tvContenidopost);
        }

        public void asignarDatos(Publication post){
            nombre.setText(post.getUser().getNombre());
            usuario.setText("@" +post.getUser().getUsuario());
            contenidoPost.setText(post.getContent());

        }
    }
}
