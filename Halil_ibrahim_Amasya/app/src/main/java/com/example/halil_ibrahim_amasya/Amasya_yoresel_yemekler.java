package com.example.halil_ibrahim_amasya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.halil_ibrahim_amasya.Yemekler.Amasya_Coregi;
import com.example.halil_ibrahim_amasya.Yemekler.Bakla_Dolmasi;
import com.example.halil_ibrahim_amasya.Yemekler.Keskek;
import com.example.halil_ibrahim_amasya.Yemekler.Topuz_Kebabi;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Map;

public class Amasya_yoresel_yemekler extends AppCompatActivity {

    ListView HislistView;
    ArrayAdapter<String> Hisadapter;

    Intent Hisintent;

    FirebaseFirestore Hisdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amasya_yoresel_yemekler);

        Hisdb = FirebaseFirestore.getInstance();
        HislistView = (ListView)findViewById(R.id.yoresel_yemekler_listview);


        DocumentReference Hisreference = Hisdb.collection("Yemekler").document("YemeklerId");
        Hisreference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot Hisdocument = task.getResult();
                    Map<String, Object> HisAmasya_yemekler = Hisdocument.getData();
                    ArrayList<String> HisYemekler = (ArrayList<String>)HisAmasya_yemekler.get("YemeklerListe");

                    if (Hisdocument.exists()){
                        Hisadapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, HisYemekler);
                        HislistView.setAdapter(Hisadapter);

                        HislistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                switch (i) {
                                    case 0:
                                        Hisintent = new Intent(Amasya_yoresel_yemekler.this, Amasya_Coregi.class);
                                        startActivity(Hisintent);
                                        break;
                                    case 1:
                                        Hisintent = new Intent(Amasya_yoresel_yemekler.this, Topuz_Kebabi.class);
                                        startActivity(Hisintent);
                                        break;
                                    case 2:
                                        Hisintent = new Intent(Amasya_yoresel_yemekler.this, Bakla_Dolmasi.class);
                                        startActivity(Hisintent);
                                        break;
                                    case 3:
                                        Hisintent = new Intent(Amasya_yoresel_yemekler.this, Keskek.class);
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