package com.example.deadwoodassambly.ui.adder;


import android.app.Application;
import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.deadwoodassambly.model.Assembly;
import com.example.deadwoodassambly.model.RemoteAssembly;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

public class AdderViewModel  extends AndroidViewModel {
    private FirebaseDatabase db;
    private DatabaseReference dbRef;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    public AdderViewModel(@NonNull Application application) {
        super(application);
        db=FirebaseDatabase.getInstance();
        dbRef=db.getReference("db");
        firebaseStorage=FirebaseStorage.getInstance();
        storageReference=firebaseStorage.getReference();
    }
    public void AddAssembly(Assembly assembly){
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        assembly.getImage().compress(Bitmap.CompressFormat.JPEG,100,baos);
        byte[] data=baos.toByteArray();
        String uid= UUID.randomUUID().toString();
        String uri="";
        UploadTask uploadTask=storageReference.child(uid).putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                String uri = "https://firebasestorage.googleapis.com" + taskSnapshot.getUploadSessionUri().getPath() + "/" + uid +
                        "?alt=media";
                String assemblyId=dbRef.push().getKey();
                RemoteAssembly remoteAssembly=new RemoteAssembly(assembly.getPlace(),assembly.getLatitude(),assembly.getLongitude(),
                        assembly.getDate(),assembly.getComment(),uri);
                dbRef.child(assemblyId).setValue(remoteAssembly);
            }
        });


    }
}
