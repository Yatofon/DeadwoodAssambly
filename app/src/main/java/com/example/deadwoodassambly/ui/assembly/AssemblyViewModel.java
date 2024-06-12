package com.example.deadwoodassambly.ui.assembly;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.deadwoodassambly.model.RemoteAssembly;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class AssemblyViewModel extends AndroidViewModel {
    private MutableLiveData<List<RemoteAssembly>> assemblies;
    private FirebaseDatabase db;
    private DatabaseReference dbRef;

    public AssemblyViewModel(@NonNull Application application) {
        super(application);
        assemblies=new MutableLiveData<>();
        db=FirebaseDatabase.getInstance();
        dbRef=db.getReference("db");
    }
    public void Remove(RemoteAssembly remoteAssemblyRemove){
        Query query=this.db.getReference("db");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot appleSnapshot: snapshot.getChildren()) {
                    RemoteAssembly remoteAssembly=appleSnapshot.getValue(RemoteAssembly.class);
                    if(remoteAssembly.equals(remoteAssemblyRemove)) {
                        appleSnapshot.getRef().setValue(null);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public MutableLiveData<List<RemoteAssembly>> getAssemblies(){

        Query query=this.db.getReference("db");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<RemoteAssembly> list=new ArrayList<>();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    RemoteAssembly remoteAssembly=dataSnapshot.getValue(RemoteAssembly.class);
                    list.add(remoteAssembly);
                }
                assemblies.setValue(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return assemblies;
    }

}