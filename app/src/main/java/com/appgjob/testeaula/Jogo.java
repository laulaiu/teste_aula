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

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Jogo extends AppCompatActivity  {

    private int controlador = 1;
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

        getFormulario();

        Timer cronometro = new Timer();
        TimerTask tarefa = new TimerTask() {
            @Override
            public void run() {
                tempo.setText(segundos+"");
                segundos += 1;
            }
        };
        int milissegundos = 1000;
        cronometro.schedule(tarefa, milissegundos);

    }




    public void getFormulario() {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("nivel_1")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            List<Frases> lista = new ArrayList<Frases>();

                            for(QueryDocumentSnapshot doc : task.getResult()){
                                Frases p = new Frases(
                                        doc.get("pergunta").toString(),
                                        doc.get("resposta_1").toString(),
                                        doc.get("resposta_2").toString(),
                                        doc.get("resposta_3").toString(),
                                        doc.get("resposta_4").toString(),
                                        doc.get("Orientação").toString()
                                );
                                lista.add(p);
                            }
                            pergunta.setText(lista.get(controlador).getPergunta());
                            resposta1.setText(lista.get(controlador).getResposta1());
                            resposta2.setText(lista.get(controlador).getResposta2());
                            resposta3.setText(lista.get(controlador).getResposta3());
                            resposta4.setText(lista.get(controlador).getResposta4());
                            orientacao = lista.get(controlador).getOrientacao();
                        }else{
                            Toast.makeText(Jogo.this, "Erro na conexão!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}