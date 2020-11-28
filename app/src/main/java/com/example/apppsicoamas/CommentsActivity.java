package com.example.apppsicoamas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import Pruebas.DataBase;
import Pruebas.Singleton;
import PsicObj.User;

public class CommentsActivity extends OnSessionActivity {
    private RecyclerView recycler;
    private AdapterComments adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        recycler = findViewById(R.id.recyclerComments);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        int postPosition = getIntent().getIntExtra("position",0);
        adapter = new AdapterComments(DataBase.posts.get(postPosition).getComments());
        recycler.setAdapter(adapter);
    }


    @Override
    public void update() {
        startActivity(new Intent(CommentsActivity.this,CommentsActivity.class));
    }

    @Override
    public User getCurrentUser() {
        return Singleton.getCurrentUserN();
    }
}