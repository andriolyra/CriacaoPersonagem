package com.andrio.criacaopersonagem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnAdd;
    private ListView lvPersonagens;
    private List<Personagem> listPersonagems;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvPersonagens = findViewById(R.id.lvPersonagens);
        btnAdd = findViewById(R.id.btnAdd);

        lvPersonagens.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                deletar(position);
                return true;
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,
                        FormularioActivity.class);
                i.putExtra("acao", "inserir");
                startActivity(i);
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        carregarPersonagens();
    }

    private void carregarPersonagens() {
        listPersonagems = PersonagemDAO.getProdutos(this);

        if (listPersonagems.isEmpty()) {
            lvPersonagens.setEnabled(false);
            String[] listaVazia = {"Nada Cadastrado"};
            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaVazia);
        } else {
            lvPersonagens.setEnabled(true);
            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listPersonagems);
        }
        lvPersonagens.setAdapter(adapter);
    }


    private void deletar(int posicao) {
        Personagem personagem = listPersonagems.get(posicao);
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Excluir");
        alerta.setIcon(android.R.drawable.ic_dialog_alert);
        alerta.setMessage("Confirma que quer excluir: " + personagem.getNome() + "? ");
        alerta.setNeutralButton("Não", null);
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                PersonagemDAO.excluir(MainActivity.this, personagem.getId());
                carregarPersonagens();
            }
        });
        alerta.show();

    }
}