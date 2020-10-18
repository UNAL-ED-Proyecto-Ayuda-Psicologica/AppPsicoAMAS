package com.example.apppsicoamas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.*;

import DataStructures.DoublyLinkedList;
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

        InputStream is=this.getResources().openRawResource(R.raw.psico);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        try {
            String linea = br.readLine();
            while(linea != null){
                String[] parts = linea.split(";");
                Psico b = new Psico(parts[0], parts[2], parts[1]);
                DataBase.agregarUsuario(b );
                linea=br.readLine();
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

        Psico usuarioEntrante = new Psico("nombre génerico", usuario, contraseña);
        int index = DataBase.listadeusuarios.getIndex(usuarioEntrante);
        User user = DataBase.listadeusuarios.getK(index);
        if (index >= 0 /*&& DataBase.listadeusuarios.getK(index).equals(usuarioEntrante)*/) {
            if(user instanceof NoPsico){
                Singleton.setCurrentUserN((NoPsico) user);
                return 1;
            }else if(user instanceof  Psico){
                Singleton.setCurrentUserP((Psico) user);
                return 2;
            }

        }
        return 0;
        //Formato lectura archivo de texto
        /*try {
            FileReader fr = null;
            fr = new FileReader("libros.txt");
            BufferedReader br = new BufferedReader(fr);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        //Fin formato

    }

    public void registrarse(View view){
        startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
    }
}