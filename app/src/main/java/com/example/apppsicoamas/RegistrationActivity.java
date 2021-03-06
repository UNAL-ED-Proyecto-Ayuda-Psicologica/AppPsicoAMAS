package com.example.apppsicoamas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import Pruebas.DataBase;
import PsicObj.NoPsico;
import PsicObj.Psico;

public class RegistrationActivity extends AppCompatActivity {

    private EditText nuevoNombre;
    private EditText nuevoUsuario;
    private EditText nuevaContraseña;
    private EditText nuevoCorreo;
    private Button registrarse;
    private CheckBox soyPsicologo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        nuevoNombre = findViewById(R.id.etEditName);
        nuevoUsuario = findViewById(R.id.etRegUsuario);
        nuevaContraseña = findViewById(R.id.etEditContraseña);
        nuevoCorreo = findViewById(R.id.etEditCorreo);
        registrarse = findViewById(R.id.bRegistrarse);
        soyPsicologo = findViewById(R.id.cbPsicologo);

    }

    public void nuevoRegistro(View view){
        String nombre = nuevoNombre.getText().toString();
        String usuario = nuevoUsuario.getText().toString();
        String contraseña = nuevaContraseña.getText().toString();
        String correo = nuevoCorreo.getText().toString();

        if(validar(usuario, contraseña)){
            long inicio = System.nanoTime();
            if(soyPsicologo.isChecked()){
                DataBase.agregarUsuario(new Psico(nombre, usuario, contraseña,correo));
            }
            else{
                DataBase.agregarUsuario(new NoPsico(nombre, usuario, contraseña,correo));
            }
            long fin = System.nanoTime();
            Toast.makeText(RegistrationActivity.this, "Te acabas de registrar! "+ ((fin - inicio) * 1.0e-9),Toast.LENGTH_LONG).show();
            Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    private boolean validar(String usuario, String contraseña){
        if(usuario.isEmpty() || contraseña.length() < 8){
            Toast.makeText(RegistrationActivity.this, "Ingresa una contraseña más larga",Toast.LENGTH_LONG).show();
            return false;
        } else if(DataBase.listanombredeusuarios.containsKey(usuario)){
            Toast.makeText(RegistrationActivity.this, "Este nombre de usuario está ocupado, ingresa uno distinto!",Toast.LENGTH_LONG).show();
            return false;
        } else{
            return true;
        }
    }


}