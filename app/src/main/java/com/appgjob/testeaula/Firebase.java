package com.appgjob.testeaula;

import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Firebase {

    public List<Frases> lista_frases;

    public List<Frases> getLista(){

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("nivel_1").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        List<Frases> lista = new ArrayList<Frases>();
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot doc : task.getResult()){
                                Frases p = new Frases(
                                        doc.get("Orientação").toString(),
                                        doc.get("pergunta").toString(),
                                        doc.get("resposta_1").toString(),
                                        doc.get("resposta_2").toString(),
                                        doc.get("resposta_3").toString(),
                                        doc.get("resposta_4").toString()
                                );
                                lista.add(p);
                            }
                            /*ArrayAdapter<Frases> adapter = new ArrayAdapter<>(
                                    Listar.this,
                                    android.R.layout.simple_list_item_1, lista);*/
                            //listaV.setAdapter(adapter);
                            lista_frases = lista;
                        }else{
                            lista_frases = null;
                        }
                    }
                });
        return this.lista_frases;
    }

}
