package com.appgjob.testeaula;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


//Leonardo: Conexão com o banco Firebase, Get os dados do banco, e insere na tela
//de formulario, timer onde faz a contagem regresiva caso o tempo chegue ao seu limite
// o jogador ira perder ponto.
// Pontos com contagem de 10 em 10 criei uma função onde ira calcular todos os pontos do
//jogadores.

public class Jogo extends AppCompatActivity implements Serializable {

    private long intervalo = 1000;
    private int controlador = 0;
    private int segundos = 0;
    private TextView pergunta;
    private TextView tempo;
    private String orientacao;
    private CheckBox resposta1;
    private CheckBox resposta2;
    private CheckBox resposta3;
    private CheckBox resposta4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);


        tempo = findViewById(R.id.tempo);
        pergunta = findViewById(R.id.tv_pergunta);
        resposta1 = findViewById(R.id.resposta1);
        resposta2 = findViewById(R.id.resposta2);
        resposta3 = findViewById(R.id.resposta3);
        resposta4 = findViewById(R.id.resposta4);


        funcao();
        FirebaseBD fb = new FirebaseBD();
        List<Frases> lista = fb.lista_retorno;

        pergunta.setText(lista.get(controlador).getPergunta());
        resposta1.setText(lista.get(controlador).getResposta1());
        resposta2.setText(lista.get(controlador).getResposta2());
        resposta3.setText(lista.get(controlador).getResposta3());
        resposta4.setText(lista.get(controlador).getResposta4());
        orientacao = lista.get(controlador).getOrientacao();

        //getFormulario();

    }


    public void funcao () {
        System.out.println("inicio");
        Timer timer = null;
        if (timer == null) {
            timer = new Timer();
            TimerTask tarefa = new TimerTask() {
                public void run() {
                    try {
                        tempo.setText(segundos+"");
                        segundos += 1;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            timer.scheduleAtFixedRate(tarefa, intervalo, intervalo);
        }
    }



    public void getFormulario() {


        List<Frases> lista  = (List<Frases>) getIntent().getExtras().getSerializable("formulario");

        Toast.makeText(Jogo.this, "A:"+lista.size(), Toast.LENGTH_SHORT).show();


        pergunta.setText(lista.get(controlador).getPergunta());
        resposta1.setText(lista.get(controlador).getResposta1());
        resposta2.setText(lista.get(controlador).getResposta2());
        resposta3.setText(lista.get(controlador).getResposta3());
        resposta4.setText(lista.get(controlador).getResposta4());
        orientacao = lista.get(controlador).getOrientacao();
    }




}