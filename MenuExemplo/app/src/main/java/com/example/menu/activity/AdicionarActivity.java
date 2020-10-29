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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar);
        editTarefa = findViewById(R.id.editNomeTarefa);
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

        switch (id) {
            case R.id.itemSalvar:
                String nomeTarefa = editTarefa.getText().toString();
                if(!nomeTarefa.isEmpty()){
                    TarefaDAO dao = new TarefaDAO(getApplicationContext());
                    Tarefa t = new Tarefa();
                    t.setNomeTarefa(nomeTarefa);
                    dao.salvar(t);
                    finish();
                    //Intent mainActivity = new Intent(this, MainActivity.class);
                    //startActivity(mainActivity);
                }


                break;


        }

      
        return super.onOptionsItemSelected(item);
    }
}