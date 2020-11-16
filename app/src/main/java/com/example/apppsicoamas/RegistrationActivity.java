package com.example.apppsicoamas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.*;

import Pruebas.DataBase;
import PsicObj.NoPsico;
import PsicObj.Psico;

public class RegistrationActivity extends AppCompatActivity {

    private EditText nuevoUsuario;
    private EditText nuevaContraseña;
    private EditText nuevoCorreo;
    private Button registrarse;
    private CheckBox soyPsicologo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        nuevoUsuario = findViewById(R.id.etRegUsuario);
        nuevaContraseña = findViewById(R.id.etRegContraseña);
        nuevoCorreo = findViewById(R.id.etRegCorreo);
        registrarse = findViewById(R.id.bRegistrarse);
        soyPsicologo = findViewById(R.id.cbPsicologo);

    }

    public void nuevoRegistro(View view){
        String usuario = nuevoUsuario.getText().toString();
        String contraseña = nuevaContraseña.getText().toString();
        String correo = nuevoCorreo.getText().toString();

        if(validar(usuario, contraseña)){
            if(soyPsicologo.isChecked()){
                DataBase.agregarUsuario(new Psico("nombre génerico", usuario, contraseña,correo));
                /*String linea= null;<
                OutputStream os=this.getResources().openRawResource(R.raw.psico);
                //BufferedReader br = new BufferedReader(new InputStreamReader(is));
                try {

                } catch (IOException e) {
                    e.printStackTrace();
                }*/
            }
            else{
                DataBase.agregarUsuario(new NoPsico("nombre génerico", usuario, contraseña,correo));
            }

            Toast.makeText(RegistrationActivity.this, "Te acabas de registrar!",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    private boolean validar(String usuario, String contraseña){
        if(usuario.isEmpty() || contraseña.length() < 8){
            Toast.makeText(RegistrationActivity.this, "Ingresa una contraseña más larga",Toast.LENGTH_LONG).show();
            return false;
        } else if(DataBase.listanombredeusuarios.getIndex(usuario) >= 0 ){
            Toast.makeText(RegistrationActivity.this, "Este nombre de usuario está ocupado, ingresa uno distinto!",Toast.LENGTH_LONG).show();
            return false;
        } else{
            return true;
        }
    }


}