package com.example.menu.helper.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.menu.model.Tarefa;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class TarefaDAO implements ITarefaDAO {

    private SQLiteDatabase escreve;
    private SQLiteDatabase ler;
    private DbHelper db;


    public TarefaDAO(Context context) {
        db = new DbHelper(context);
        escreve = db.getWritableDatabase();
        ler = db.getReadableDatabase();
    }

    @Override
    public boolean salvar(Tarefa tarefa) {
        ContentValues cv = new ContentValues();
        cv.put("nome", tarefa.getNomeTarefa());
        try{
            escreve.insert(DbHelper.TABELA_TAREFAS,null,cv);
            Log.i("INTO DB ","Tarefa salva com sucesso " );
        } catch (Exception e){
            Log.i("INTO DB ","Erro ao inserir a tarefa: " + e.getMessage() );
            return false;
        }


        return true;
    }

    @Override
    public boolean atualizar(Tarefa tarefa) {

        ContentValues cv = new ContentValues();
        cv.put("nome", tarefa.getNomeTarefa());

        try{
            String[] args = {tarefa.getId().toString()};
            escreve.update(
                    DbHelper.TABELA_TAREFAS,cv,"id = ?", args);
            Log.i("INTO DB ","Tarefa atualizada com sucesso " );
        } catch (Exception e){
            Log.i("INTO DB ","Erro ao atualizada a tarefa: " + e.getMessage() );
            return false;
        }

        return true;
    }

    @Override
    public boolean deletar(Tarefa tarefa) {
         try{
            String[] args = {tarefa.getId().toString()};
            escreve.delete(
                    DbHelper.TABELA_TAREFAS,"id = ?", args);
            Log.i("INTO DB ","Tarefa excluída com sucesso " );
        } catch (Exception e){
            Log.i("INTO DB ","Erro ao excluir a tarefa: " + e.getMessage() );
            return false;
        }

        return true;
    }

    @Override
    public List<Tarefa> listar() {
        List<Tarefa> lista = new ArrayList<Tarefa>();
        String sql = "SELECT id,nome FROM " + DbHelper.TABELA_TAREFAS + ";";
        Cursor c = ler.rawQuery(sql,null);

        while(c.moveToNext()){
            Tarefa tarefa = new Tarefa();
            Long id = c.getLong(c.getColumnIndex("id"));
            String nome = c.getString(c.getColumnIndex("nome"));
            tarefa.setId(id);
            tarefa.setNomeTarefa(nome);

            lista.add(tarefa);
        }

        return lista;
    }
}
