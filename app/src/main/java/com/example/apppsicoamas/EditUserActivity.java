package com.example.apppsicoamas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import Pruebas.Singleton;
import PsicObj.Psico;
import PsicObj.User;

public class EditUserActivity extends AppCompatActivity {
    private EditText nuevoNombre;
    private EditText nuevaContraseña;
    private EditText nuevoCorreo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        this.nuevaContraseña = findViewById(R.id.etEditContraseña);
        this.nuevoNombre = findViewById(R.id.etEditName);
        this.nuevoCorreo = findViewById(R.id.etEditCorreo);

        this.nuevoNombre.setText(getCurrentUser().getNombre());
        this.nuevoCorreo.setText(getCurrentUser().getCorreo());


    }

    public User getCurrentUser(){
        if(Singleton.getCurrentUserP()==null)
            return Singleton.getCurrentUserN();
        else return Singleton.getCurrentUserP();
    }

    public void save(View view){
        getCurrentUser().setCorreo(this.nuevoCorreo.getText().toString());
        getCurrentUser().setNombre(this.nuevoNombre.getText().toString());
        if(this.nuevaContraseña.getText().toString().length()>=8) getCurrentUser().setContraseña(this.nuevaContraseña.getText().toString());
        cancel(view);
    }

    public void cancel(View view){
        if(getCurrentUser() instanceof Psico){
            startActivity(new Intent(EditUserActivity.this, OnSessionActivityP.class));
        }else startActivity(new Intent(EditUserActivity.this, OnSessionActivityN.class));
    }
}