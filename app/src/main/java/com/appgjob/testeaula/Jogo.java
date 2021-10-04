package com.appgjob.testeaula;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class Jogo extends AppCompatActivity {



    private TextView pergunta;
    private TextView orientacao;
    private CheckBox resposta1;
    private CheckBox resposta2;
    private CheckBox resposta3;
    private CheckBox resposta4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);

        pergunta = findViewById(R.id.tv_pergunta);
        resposta1 = findViewById(R.id.resposta1);
        resposta2 = findViewById(R.id.resposta2);
        resposta3 = findViewById(R.id.resposta3);
        resposta4 = findViewById(R.id.resposta4);

        Firebase fb = new Firebase();
        List<Frases> lista_frases ;
        lista_frases = fb.getLista();

        /*resposta1.setText( lista_frases.get(0).getResposta1());
        resposta2.setText(lista_frases.get(0).getResposta2());
        resposta3.setText(lista_frases.get(0).getResposta3());
        resposta4.setText(lista_frases.get(0).getResposta4());
        orientacao.setText(lista_frases.get(0).getOrientação());*/

        int prg = lista_frases.size();
        //pergunta.setText(prg);

        Log.d("MSG: ", prg+">>"+lista_frases);
        //resposta1.setText((CharSequence) );

    }

}