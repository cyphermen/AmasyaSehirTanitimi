package com.example.halil_ibrahim_amasya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ListView HislistView;
    ArrayAdapter<String> Hisadapter;

    Intent Hisintent;

    FirebaseFirestore Hisdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Hisdb = FirebaseFirestore.getInstance();
        HislistView = (ListView)findViewById(R.id.my_listView);


        DocumentReference Hisreference = Hisdb.collection("Menu").document("MenuId");
        Hisreference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot Hisdocument = task.getResult();
                    Map<String, Object> Hismenu = Hisdocument.getData();
                    ArrayList<String> HismenuArray = (ArrayList<String>)Hismenu.get("Menu");
                    if (Hisdocument.exists()) {

                        Hisadapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, HismenuArray);
                        HislistView.setAdapter(Hisadapter);


                        HislistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                switch (i) {
                                    case 0:
                                        Hisintent = new Intent(MainActivity.this, Amasya_Tarihce.class);
                                        startActivity(Hisintent);
                                        break;
                                    case 1:
                                        Hisintent = new Intent(MainActivity.this, Amasya_tarihi_yerler.class);
                                        startActivity(Hisintent);
                                        break;
                                    case 2:
                                        Hisintent = new Intent(MainActivity.this, Amasya_yoresel_yemekler.class);
                                        startActivity(Hisintent);
                                        break;
                                    case 3:
                                        Hisintent = new Intent(MainActivity.this, Amasya_Tatlilar.class);
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