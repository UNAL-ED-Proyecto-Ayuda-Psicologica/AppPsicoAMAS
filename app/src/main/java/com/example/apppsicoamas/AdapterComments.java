package com.example.apppsicoamas;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import DataStructures.DynamicArray;
import PsicObj.Publication;

public class AdapterComments extends RecyclerView.Adapter<AdapterComments.ViewHolderComments> {
    private DynamicArray<Publication> arregloComentarios;

    private AdapterComments.OnItemClickListener listener;

    public interface OnItemClickListener{
        void onEditClickListener(int position);
        void onUpClickListener(int position);
    }

    public AdapterComments(DynamicArray<Publication> listaComentarios) {
        this.arregloComentarios = listaComentarios;
    }

    public void setOnItemClickListener(AdapterComments.OnItemClickListener listen){
        this.listener = listen;
    }

    @NonNull
    @Override
    public AdapterComments.ViewHolderComments onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_list,parent, false);
        return new AdapterComments.ViewHolderComments(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterComments.ViewHolderComments holder, int position) {
        holder.asignarDatos(arregloComentarios.get(position));
    }

    @Override
    public int getItemCount() {
        return arregloComentarios.length();
    }

    public class ViewHolderComments extends RecyclerView.ViewHolder {
        ImageButton up;
        ImageButton editar;
        TextView nombre;
        TextView usuario;
        TextView contenido;

        public ViewHolderComments(@NonNull View itemView) {
            super(itemView);
            up = itemView.findViewById(R.id.ibUpComment);
            nombre = itemView.findViewById(R.id.tvNombreCom);
            usuario = itemView.findViewById(R.id.tvUsarioCom);
            contenido = itemView.findViewById(R.id.tvContenidoCom);
            editar = itemView.findViewById(R.id.ibEditCom);
            up.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onUpClickListener(position);
                        }
                    }
                }
            });

            editar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onEditClickListener(position);
                        }
                    }
                }
            });
        }

        public void asignarDatos(Publication comment){
            nombre.setText(comment.getUser().getNombre());
            usuario.setText("@" + comment.getUser().getUsuario());
            contenido.setText(comment.getContent());

        }
    }
}
