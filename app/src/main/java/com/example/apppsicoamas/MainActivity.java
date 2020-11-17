package com.example.apppsicoamas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.*;

import Pruebas.DataBase;
import Pruebas.Singleton;
import PsicObj.NoPsico;
import PsicObj.Psico;
import PsicObj.User;

public class MainActivity extends AppCompatActivity {

    private EditText usuario;
    private EditText contraseña;
    private Button ingresar;
    private TextView bienvenida;
    private Button nuevoRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario = findViewById(R.id.etUsuario);
        contraseña = findViewById(R.id.etContraseña);
        ingresar = findViewById(R.id.bIngresar);
        bienvenida = findViewById(R.id.tvBienvenida);
        nuevoRegistro = findViewById(R.id.bNuevoRegistro);

        InputStream is=this.getResources().openRawResource(R.raw.nopsico);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        InputStream is2=this.getResources().openRawResource(R.raw.panico);
        BufferedReader br2 = new BufferedReader(new InputStreamReader(is2));
        InputStream is3=this.getResources().openRawResource(R.raw.psico);
        BufferedReader br3 = new BufferedReader(new InputStreamReader(is3));

        try {
            String linea = br.readLine();
            String linea2 = br2.readLine();
            while(linea != null){
                String[] parts = linea.split(";");
                NoPsico b = new NoPsico(parts[0], parts[2], parts[1],null);
                DataBase.agregarUsuario(b);
                b.panicButton(DataBase.botonesDePanico, linea2,2);
                linea=br.readLine();
                linea2 = br2.readLine();
            }
            String linea3 = br3.readLine();
            while(linea3 != null){
                String[] parts = linea3.split(";");
                Psico b = new Psico(parts[0], parts[2], parts[1],null);
                DataBase.agregarUsuario(b);
                linea3=br3.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ingresar(View view){
        String entradaUsuario = usuario.getText().toString();
        String entradaContraseña = contraseña.getText().toString();
        long inicio = System.nanoTime();
        if (entradaUsuario.isEmpty() || entradaContraseña.isEmpty()) {
            Toast.makeText(MainActivity.this, "Por favor digita usuario y contraseña", Toast.LENGTH_SHORT).show();
        } else {
            long fin = System.nanoTime();
            switch (validarUsuario(entradaUsuario,entradaContraseña)) {
                case 1:

                    Toast.makeText(MainActivity.this, "Iniciaste Sesión " + ((fin - inicio) * 1.0e-9), Toast.LENGTH_LONG).show();
                    startActivity(new Intent(MainActivity.this, OnSessionActivityN.class));
                    break;
                case 2:
                    Toast.makeText(MainActivity.this, "Iniciaste Sesión " + ((fin - inicio) * 1.0e-9), Toast.LENGTH_LONG).show();
                    startActivity(new Intent(MainActivity.this, OnSessionActivityP.class));
                    break;
                default:
                    System.out.println(((fin - inicio) * 1.0e-9));
                    Toast.makeText(MainActivity.this, "Usuario o contraseña incorrectos "+ ((fin - inicio) * 1.0e-9), Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }

    public int validarUsuario(String usuario, String contraseña) {
        Psico usuarioEntrante = new Psico("nombre génerico", usuario, contraseña,null);
        //boolean exists = DataBase.listadeusuarios.contains(usuarioEntrante);
        User user = DataBase.listadeusuarios.getElement(usuarioEntrante);
        if (user!=null /*&& usuarioEntrante.getUsuario().equals(usuario)*/ && user.validarContraseña(contraseña)) {
            if(user instanceof NoPsico){
                Singleton.setCurrentUserN((NoPsico) user);
                return 1;
            }else if(user instanceof  Psico){
                Singleton.setCurrentUserP((Psico) user);
                return 2;
            }

        }
        return 0;
    }

    public void registrarse(View view){
        startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
    }
}