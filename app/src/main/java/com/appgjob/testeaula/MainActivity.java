package com.appgjob.testeaula;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity implements Serializable   {

    List<Frases> lista_global ;
    private static final String TAG = "testando msg" ;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    //private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getBancoFirebase();

    }

    public void onCLickBd(View view){

        Map<String, Object> user = new HashMap<>();
        user.put("pergunta", "bebida favorita ?");
        user.put("resposta_1", "coca cola");
        user.put("resposta_2", "suco de uva");
        user.put("resposta_3", "café");
        user.put("resposta_4", "Pão");
        user.put("Orientação", "Algumas não são bebidas");

        db.collection("nivel_2")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + 002);
                        Toast.makeText(MainActivity.this, ""+Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId()), Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
        Toast.makeText(MainActivity.this,"Clicou no botão mandar", Toast.LENGTH_SHORT).show();
    }

    public void onClickListar(View view){
        Intent listar_tela = new Intent(MainActivity.this , Listar.class);
        startActivity(listar_tela);
    }


    public void onClickJogo(View view){
        Intent jogo = new Intent(MainActivity.this , Jogo.class);
        jogo.putExtra("formulario", (Serializable) lista_global);
        startActivity(jogo);

    }

    public  void getBancoFirebase(){

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
                            lista_global = lista;

                        }else{
                            Toast.makeText(MainActivity.this, "Erro na conexão!", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }


    public void onClickTelaInicioModo(View view){
        Intent telaInicioModo = new Intent(MainActivity.this , TelaInicioModo.class);
        startActivity(telaInicioModo);

    }

}