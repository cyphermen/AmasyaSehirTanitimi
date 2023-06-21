package com.example.halil_ibrahim_amasya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.halil_ibrahim_amasya.Yerler.Amasya_Harsena_Kalesi;
import com.example.halil_ibrahim_amasya.Yerler.Amasya_Saat_Kulesi;
import com.example.halil_ibrahim_amasya.Yerler.Bayezid_Kulliyesi;
import com.example.halil_ibrahim_amasya.Yerler.Ferhat_Su_Kanali;
import com.example.halil_ibrahim_amasya.Yerler.Kral_Kaya_Mezarlari;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Map;

public class Amasya_tarihi_yerler extends AppCompatActivity {

    ListView HislistView;
    ArrayAdapter<String> Hisadapter;

    Intent Hisintent;

    FirebaseFirestore Hisdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amasya_tarihi_yerler);

        Hisdb = FirebaseFirestore.getInstance();
        HislistView = (ListView)findViewById(R.id.tarihi_yerler_listview);

        DocumentReference Hisreference = Hisdb.collection("tarihiYerler").document("tarihiYerlerId");
        Hisreference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot Hisdocument = task.getResult();
                    Map<String, Object> Histarihi_yerler = Hisdocument.getData();
                    ArrayList<String> HistarihiYerler = (ArrayList<String>)Histarihi_yerler.get("tarihiYerlerListe");

                    if (Hisdocument.exists()) {
                        Hisadapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, HistarihiYerler);
                        HislistView.setAdapter(Hisadapter);

                        HislistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                switch (i) {
                                    case 0:
                                        Hisintent = new Intent(Amasya_tarihi_yerler.this, Amasya_Harsena_Kalesi.class);
                                        startActivity(Hisintent);
                                        break;
                                    case 1:
                                        Hisintent = new Intent(Amasya_tarihi_yerler.this, Amasya_Saat_Kulesi.class);
                                        startActivity(Hisintent);
                                        break;
                                    case 2:
                                        Hisintent = new Intent(Amasya_tarihi_yerler.this, Ferhat_Su_Kanali.class);
                                        startActivity(Hisintent);
                                        break;
                                    case 3:
                                        Hisintent = new Intent(Amasya_tarihi_yerler.this, Bayezid_Kulliyesi.class);
                                        startActivity(Hisintent);
                                        break;
                                    case 4:
                                        Hisintent = new Intent(Amasya_tarihi_yerler.this, Kral_Kaya_Mezarlari.class);
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