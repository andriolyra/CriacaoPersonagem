package com.andrio.criacaopersonagem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class PersonagemDAO {

    public static void inserir(Context context, Personagem personagem){
        Conexao conn = new Conexao(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("nome", personagem.getNome() );
        valores.put("nivel", personagem.getNivel() );
        valores.put("raca", personagem.getRaca());
        valores.put("classe", personagem.getClasse().toString() );

        db.insert("personagens", null, valores);
    }

    public static void editar(Context context, Personagem personagem){
        Conexao conn = new Conexao(context);
        SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("nome", personagem.getNome() );
        valores.put("nivel", personagem.getNivel() );
        valores.put("raca", personagem.getRaca() );
        valores.put("classe", personagem.getClasse().toString() );

        db.update("personagens", valores ,
                " id = " + personagem.getId(), null  );
    }

    public static void excluir(Context context, int idPersonagem){
        SQLiteDatabase db = new Conexao(context).getWritableDatabase();

        db.delete("personagens",
                " id = " + idPersonagem, null  );
    }

    public static List<Personagem> getProdutos(Context context){
        Conexao conn = new Conexao(context);
        SQLiteDatabase db = conn.getReadableDatabase();
        List<Personagem> lista = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT id, nome, nivel, raca, classe FROM personagens ORDER BY nome",
                null);
        if( cursor != null && cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do{
                Personagem personagem = new Personagem();
                personagem.setId( cursor.getInt( 0 )  );
                personagem.setNome( cursor.getString( 1 )  );
                personagem.setNivel( cursor.getString( 2 )  );
                personagem.setRaca( cursor.getString( 3 )  );
                personagem.getClasse(Classe.valueOf(cursor.getString( 4 ))  );
                lista.add(personagem);
            }while ( cursor.moveToNext() );
        }
        return lista;
    }

}