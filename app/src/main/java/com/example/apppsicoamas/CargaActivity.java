package com.example.apppsicoamas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import Pruebas.DataBase;
import PsicObj.NoPsico;
import PsicObj.Psico;

public class CargaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga);

        DataBase.agregarUsuario(new Psico("Luz unu","luzalejandra","veramorales", "laveramo@unal.edu.co"));
        //DataBase.agregarUsuario(new Psico("Concepcion","zzzzz","MSQSDAK123", "cpadillas@unal.edu.co"));
        //String morado="crisdavid";
        //int plus=morado.length()/5;
        //Toast.makeText(MainActivity.this, "hash "+ plus, Toast.LENGTH_LONG).show();


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
            long inicio = System.nanoTime();
            while(linea3 != null){
                String[] parts = linea3.split(";");
                Psico b = new Psico(parts[0], parts[2], parts[1],null);
                DataBase.agregarUsuario(b);
                linea3=br3.readLine();
            }
            long fin = System.nanoTime();
            Toast.makeText(CargaActivity.this, "Terminé en "+ ((fin - inicio) * 1.0e-9), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void iniciar (View view){
        startActivity(new Intent(CargaActivity.this, MainActivity.class));
    }
}