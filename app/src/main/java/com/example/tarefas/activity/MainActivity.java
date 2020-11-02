package com.example.tarefas.activity;


import android.content.Intent;
import android.os.Bundle;

import com.example.tarefas.R;
import com.example.tarefas.adapter.TarefaAdapter;
import com.example.tarefas.model.Tarefa;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TarefaAdapter tarefaAdapter;
    List<Tarefa> listaTarefas = new ArrayList<Tarefa>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /*

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getSupportActionBar().setDisplayShowTitleEnabled(true);

        //recyclerView = findViewById(R.id.recyclerView);



        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent adicionarActivity = new Intent(getApplicationContext(), AdicionarActivity.class);
                startActivity(adicionarActivity);
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    protected void onStart() {
        carregarListaTarefas();
        super.onStart();
    }

    public void carregarListaTarefas(){
        //Listar tarefas

        //Exibe Lista de Tarefas no RecyclerView
        Tarefa tarefa1 = new Tarefa();
        tarefa1.setNome("Ir ao mercado");
        listaTarefas.add(tarefa1);

        Tarefa tarefa2 = new Tarefa();
        tarefa2.setNome("Ir a Feira");
        listaTarefas.add(tarefa2);

        //Configurar RecyclerView
        tarefaAdapter = new TarefaAdapter(listaTarefas);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(tarefaAdapter);

    }*/


}