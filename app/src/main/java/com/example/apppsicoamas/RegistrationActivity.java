package com.example.apppsicoamas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Pruebas.DataBase;
import PsicObj.Psico;

public class RegistrationActivity extends AppCompatActivity {

    private EditText nuevoUsuario;
    private EditText nuevaContraseña;
    private Button registrarse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        nuevoUsuario = findViewById(R.id.etRegUsuario);
        nuevaContraseña = findViewById(R.id.etRegContraseña);
        registrarse = findViewById(R.id.bRegistrarse);

    }

    public void nuevoRegistro(View view){
        String usuario = nuevoUsuario.getText().toString();
        String contraseña = nuevaContraseña.getText().toString();

        if(validar(usuario, contraseña)){
            DataBase.agregarUsuario(new Psico("nombre génerico", usuario, contraseña));
            Toast.makeText(RegistrationActivity.this, "Te acabas de registrar!",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
            startActivity(intent);
            System.out.println(DataBase.listadeusuarios.toString());
        }
    }

    private boolean validar(String usuario, String contraseña){
        if(usuario.isEmpty() || contraseña.length() < 5){
            Toast.makeText(RegistrationActivity.this, "Ingresa una contraseña más larga",Toast.LENGTH_LONG).show();
            return false;
        } else {
            return true;
        }
    }


}