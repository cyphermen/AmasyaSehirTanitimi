package com.example.halil_ibrahim_amasya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.halil_ibrahim_amasya.Tatlilar.Dene_Hasudasi;
import com.example.halil_ibrahim_amasya.Tatlilar.Elma_Tatlisi;
import com.example.halil_ibrahim_amasya.Tatlilar.Yufka_Tatlisi;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Map;

public class Amasya_Tatlilar extends AppCompatActivity {

    ListView HislistView;
    ArrayAdapter<String> Hisadapter;

    Intent Hisintent;

    FirebaseFirestore Hisdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amasya_tatlilar);

        Hisdb = FirebaseFirestore.getInstance();
        HislistView = (ListView)findViewById(R.id.tatlilar_listview);

        DocumentReference Hisreference = Hisdb.collection("tatlılar").document("tatlılarId");
        Hisreference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot Hisdocument = task.getResult();
                    Map<String, Object> HisAmasya_tatlilar = Hisdocument.getData();
                    ArrayList<String> Histatlılar = (ArrayList<String>) HisAmasya_tatlilar.get("tatlılarListe");

                    if (Hisdocument.exists()) {
                        Hisadapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, Histatlılar);
                        HislistView.setAdapter(Hisadapter);

                        HislistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                switch (i) {
                                    case 0:
                                        Hisintent = new Intent(Amasya_Tatlilar.this, Dene_Hasudasi.class);
                                        startActivity(Hisintent);
                                        break;
                                    case 1:
                                        Hisintent = new Intent(Amasya_Tatlilar.this, Elma_Tatlisi.class);
                                        startActivity(Hisintent);
                                        break;
                                    case 2:
                                        Hisintent = new Intent(Amasya_Tatlilar.this, Yufka_Tatlisi.class);
                                        startActivity(Hisintent);
                                        break;
                                }
                            }
                        });
                    }
                }
            }
        });

    }
}