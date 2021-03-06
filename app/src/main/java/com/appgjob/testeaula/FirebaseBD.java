package com.appgjob.testeaula;

import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FirebaseBD {


    public List<Frases> lista_retorno ;
    public int tamanho;

    public void processaLista() {
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
                            lista_retorno = lista;


                        }else{

                        }

                    }
                });
    }





}
