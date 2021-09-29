package com.appgjob.testeaula;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {


    private static final String TAG = "testando msg" ;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    //private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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

}