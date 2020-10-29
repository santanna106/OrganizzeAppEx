package com.example.menu.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.menu.R;
import com.example.menu.helper.data.TarefaDAO;
import com.example.menu.model.Tarefa;
import com.google.android.material.textfield.TextInputEditText;

public class AdicionarActivity extends AppCompatActivity {

    private TextInputEditText editTarefa;
    private Tarefa tarefaAtual;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar);
        editTarefa = findViewById(R.id.editNomeTarefa);

        //Recuperar Tarefa
        tarefaAtual = (Tarefa) getIntent().getSerializableExtra("tarefaSelecionada");
        if(tarefaAtual != null){
            editTarefa.setText(tarefaAtual.getNomeTarefa());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_adicionar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        TarefaDAO dao = new TarefaDAO(getApplicationContext());
        switch (id) {
            case R.id.itemSalvar:
                if(tarefaAtual != null) {
                    String nomeTarefa = editTarefa.getText().toString();
                    if (!nomeTarefa.isEmpty()) {
                        Tarefa tarefa = new Tarefa();
                        tarefa.setNomeTarefa(nomeTarefa);
                        tarefa.setId(tarefaAtual.getId());

                        //atualizar
                        if(dao.atualizar(tarefa)){
                            Toast.makeText(getApplicationContext(),"Sucesso ao atualizar a tarefa",Toast.LENGTH_LONG);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(),"Erro ao atualizar a tarefa",Toast.LENGTH_LONG);
                        }
                    }

                } else {
                    String nomeTarefa = editTarefa.getText().toString();
                    if (!nomeTarefa.isEmpty()) {

                        Tarefa t = new Tarefa();
                        t.setNomeTarefa(nomeTarefa);
                        if(dao.salvar(t)){
                            Toast.makeText(getApplicationContext(),"Sucesso ao salvar a tarefa",Toast.LENGTH_LONG);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(),"Erro ao salvar a tarefa",Toast.LENGTH_LONG);
                        }

                        //Intent mainActivity = new Intent(this, MainActivity.class);
                        //startActivity(mainActivity);
                    }
                }


                break;


        }

      
        return super.onOptionsItemSelected(item);
    }
}