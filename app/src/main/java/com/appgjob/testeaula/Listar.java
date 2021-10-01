package com.appgjob.testeaula;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class Listar extends AppCompatActivity {



    private ListView listaV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        listaV = findViewById(R.id.lista);

        listaV = findViewById(R.id.lista);

    }
    //teste

    public void onClickListar(View view){

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

                                        doc.get("Orientação").toString(),
                                        doc.get("pergunta").toString(),
                                        doc.get("resposta_1").toString(),
                                        doc.get("resposta_2").toString(),
                                        doc.get("resposta_3").toString(),
                                        doc.get("resposta_4").toString()

                                );
                                lista.add(p);
                            }

                            ArrayAdapter<Frases> adapter = new ArrayAdapter<>(
                                    Listar.this,
                                    android.R.layout.simple_list_item_1, lista);

                            listaV.setAdapter(adapter);
                        }
                    }
                });

    }

    public void onClickVoltar(View view){
        finish();
    }
}