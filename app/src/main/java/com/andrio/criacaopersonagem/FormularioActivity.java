package com.andrio.criacaopersonagem;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FormularioActivity extends AppCompatActivity {

    private EditText etNome, etNivel, etRaca;
    private RadioGroup rgClasse;
    private RadioButton rbClasseEscolhida;
    private Button btnAdicionar;
    private Personagem personagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        etNome = findViewById(R.id.etNome);
        etNivel = findViewById(R.id.etNivel);
        etRaca = findViewById(R.id.etRaca);
        rgClasse = findViewById(R.id.rgClasse);
        btnAdicionar = findViewById(R.id.btnAdicionar);

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });

    }

    private void salvar(){
        String nome = etNome.getText().toString();
        String nivel = etNivel.getText().toString();
        String raca = etRaca.getText().toString();
        rbClasseEscolhida = findViewById(rgClasse.getCheckedRadioButtonId());

        if (nome.isEmpty()) {
            Toast.makeText(this,
                    "Você deve preencher o campo nome!",
                    Toast.LENGTH_LONG
            ).show();
        } else if (nivel.isEmpty()) {
            Toast.makeText(this, "Você deve preencher o campo nível!",
                    Toast.LENGTH_LONG
            ).show();
        } else if (raca.isEmpty()) {
            Toast.makeText(this, "Você deve preencher o campo raça!",
                    Toast.LENGTH_LONG
            ).show();
        } else if (rbClasseEscolhida == null) {
            Toast.makeText(this, "Você deve selecionar uma classe!",
                    Toast.LENGTH_LONG
            ).show();
        }else {
            Classe classe = Classe.valueOf(rbClasseEscolhida.getText().toString().toUpperCase());

            personagem = new Personagem(nome, nivel, raca, classe);

            PersonagemDAO.inserir(this, personagem);
            etNome.setText( "" );
            etNivel.setText( "" );
            etRaca.setText( "" );
            rgClasse.clearCheck();
            personagem = null;
        }
    }

}