package com.example.halil_ibrahim_amasya.Yerler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.halil_ibrahim_amasya.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class Kral_Kaya_Mezarlari extends AppCompatActivity {

    TextView HisAcıklama;

    TextView HisBaslık;

    FirebaseStorage Hİsstorage;

    ImageView Hisimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kral_kaya_mezarlari);

        FirebaseFirestore Hisdb = FirebaseFirestore.getInstance();
        HisAcıklama = findViewById(R.id.Acıklama);
        HisBaslık = findViewById(R.id.Baslık);

        DocumentReference Hisreference2 = Hisdb.collection("tarihiYerler").document("tarihiYerlerId");
        Hisreference2.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot Hisdocument = task.getResult();
                    Map<String, Object> Hismenu = Hisdocument.getData();
                    ArrayList<String> HismenuArray = (ArrayList<String>) Hismenu.get("tarihiYerlerListe");
                    if (Hisdocument.exists()) {
                        HisBaslık.setText(HismenuArray.get(4));
                    }
                }
            }
        });

        DocumentReference HisdocumentReference = Hisdb.collection("tarihiYerler").document("tarihiYerlerId");
        HisdocumentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot HisdocumentSnapshot = task.getResult();
                    String Hiss = HisdocumentSnapshot.get("mezar").toString();
                    HisAcıklama.setText(Hiss);
                }
            }
        });

        Hİsstorage = FirebaseStorage.getInstance();
        StorageReference Hisreference = Hİsstorage.getReference();
        StorageReference Hispath = Hisreference.child("yerler/kral_kaya_mezarlari.jpg");

        try {
            final File HisgeciciDosya = File.createTempFile("gecici","jpg");
            Hispath.getFile(HisgeciciDosya).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap Hisbitmap = BitmapFactory.decodeFile(HisgeciciDosya.getAbsolutePath());
                    Hisimg = findViewById(R.id.Photo);
                    Hisimg.setImageBitmap(Hisbitmap);
                }
            });
        }
        catch (IOException e){

        }
    }
}