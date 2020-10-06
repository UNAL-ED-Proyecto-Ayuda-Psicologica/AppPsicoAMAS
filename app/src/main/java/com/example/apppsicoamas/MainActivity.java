package com.example.apppsicoamas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import java.io.*;
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Formato lectura archivo de texto
        /*try {
            FileReader fr = null;
            fr = new FileReader("libros.txt");
            BufferedReader br = new BufferedReader(fr);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        //Fin formato

        System.out.println("Hola mundo");

    }
}