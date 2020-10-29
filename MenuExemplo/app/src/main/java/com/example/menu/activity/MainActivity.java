package com.example.menu.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.menu.R;
import com.example.menu.helper.data.DbHelper;
import com.example.menu.helper.data.TarefaDAO;
import com.example.menu.helper.ui.RecyclerItemClickListener;
import com.example.menu.model.Tarefa;
import com.example.menu.ui.TarefaAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TarefaAdapter tarefaAdapter;
    private List<Tarefa> listaTarefas = new ArrayList<Tarefa>();

    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.fab);

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(),
                        recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Tarefa tarefaSelecionada = listaTarefas.get(position);
                        Intent intent = new Intent(MainActivity.this,AdicionarActivity.class);
                        intent.putExtra("tarefaSelecionada",tarefaSelecionada);

                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        Tarefa tarefaSelecionada = listaTarefas.get(position);
                        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);

                        //Configurar titulo e mensagem
                        dialog.setTitle("Confirmar exclusão");
                        dialog.setMessage("Deseja excluir a tarefa: " + tarefaSelecionada.getNomeTarefa() + " ?") ;

                        dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());
                                if(tarefaDAO.deletar(tarefaSelecionada)){
                                    Toast.makeText(getApplicationContext(),"Sucesso ao deletar a tarefa",Toast.LENGTH_LONG);
                                    carregarListaTarefas();
                                } else {
                                    Toast.makeText(getApplicationContext(),"Erro ao deletar a tarefa",Toast.LENGTH_LONG);
                                }
                            }
                        });

                        dialog.setNegativeButton("Não", null);
                        dialog.create();
                        dialog.show();

                    }

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    }
                }));
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
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onStart() {
        carregarListaTarefas();
        super.onStart();
    }

    public void carregarListaTarefas(){
        //Listar tarefas

        //Exibe Lista de Tarefas no RecyclerView
        TarefaDAO dao = new TarefaDAO(getApplicationContext());
        listaTarefas = dao.listar();

        //Configurar RecyclerView
        tarefaAdapter = new TarefaAdapter(listaTarefas);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(tarefaAdapter);

    }

}