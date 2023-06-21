package com.example.halil_ibrahim_amasya;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

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

public class Amasya_Tarihce extends AppCompatActivity {

    TextView Histarihce;

    TextView HisBaslık;

    FirebaseStorage Hisstorage;

    ImageView Hisimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amasya_tarihce);

        FirebaseFirestore Hisdb = FirebaseFirestore.getInstance();
        Histarihce = findViewById(R.id.tarihce);
        HisBaslık = findViewById(R.id.Baslık);


        DocumentReference HisdocumentReference = Hisdb.collection("tarihce").document("tarihceId");
        HisdocumentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot HisdocumentSnapshot = task.getResult();
                    String Hiss = HisdocumentSnapshot.get("tarihce").toString();
                    Histarihce.setText(Hiss);

                    String Hiss2 = HisdocumentSnapshot.get("Baslık").toString();
                    HisBaslık.setText(Hiss2);

                }
            }
        });

        Hisstorage = FirebaseStorage.getInstance();
        StorageReference Hisreference = Hisstorage.getReference();
        StorageReference Hispath = Hisreference.child("/amasyaphoto.jpg");

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