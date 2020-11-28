package com.example.apppsicoamas;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.Date;

import DataStructures.DoublyLinkedList;
import DataStructures.DynamicArray;
import Pruebas.DataBase;
import Pruebas.Singleton;
import PsicObj.Publication;

public class AdapterPosts extends RecyclerView.Adapter<AdapterPosts.ViewHolderPosts> {
    private DynamicArray<Publication> arregloPosts;
    private OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClickListener(int position);
        void onCommentClickListener(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listen){
        this.listener = listen;
    }

    public AdapterPosts(DynamicArray<Publication> listaPosts) {
        this.arregloPosts = listaPosts;
    }

    @NonNull
    @Override
    public ViewHolderPosts onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_list,parent, false);
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
        public ImageView foto;
        public ImageButton up;
        public ImageButton comentar;
        public TextView nombre;
        public TextView usuario;
        public TextView contenidoPost;

        public ViewHolderPosts(@NonNull View itemView) {
            super(itemView);
            foto = itemView.findViewById(R.id.ivFoto);
            up = itemView.findViewById(R.id.ibUp);
            comentar = itemView.findViewById(R.id.ibComentar);
            nombre = itemView.findViewById(R.id.tvNombre);
            usuario = itemView.findViewById(R.id.tvNombredeusuario);
            contenidoPost = itemView.findViewById(R.id.tvContenidopost);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClickListener(position);
                        }
                    }
                }
            });
            comentar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onCommentClickListener(position);
                        }
                    }
                }
            });
        }

        public void asignarDatos(Publication post){
            nombre.setText(post.getUser().getNombre());
            usuario.setText("@" +post.getUser().getUsuario());
            contenidoPost.setText(post.getContent());
        }
    }
}
