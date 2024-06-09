package com.example.deadwoodassambly.ui.adder;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.deadwoodassambly.model.Assembly;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdderViewModel  extends AndroidViewModel {
    private FirebaseDatabase db;
    private DatabaseReference dbRef;
    public AdderViewModel(@NonNull Application application) {
        super(application);
        db=FirebaseDatabase.getInstance();
        dbRef=db.getReference("assemblies");
    }
    public void AddAssembly(Assembly assembly){
        String assemblyId=dbRef.push().getKey();
        dbRef.child(assemblyId).setValue(assembly);
    }
}
