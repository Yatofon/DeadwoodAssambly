package com.example.deadwoodassambly.ui.assembly;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deadwoodassambly.R;
import com.example.deadwoodassambly.databinding.FragmentAssemblyBinding;
import com.example.deadwoodassambly.databinding.FragmentLoggedInBinding;
import com.example.deadwoodassambly.ui.loginregister.LoggedInViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Tag;

public class AssemblyFragment extends Fragment {
    private AssemblyViewModel assemblyViewModel;
    private FragmentAssemblyBinding binding;
    private NavController navController;
//    FirebaseDatabase assemblyDatabase = FirebaseDatabase.getInstance();
//
//    DatabaseReference myRef = assemblyDatabase.getReference("message");
//
//    public void setMyRef(DatabaseReference myRef) {
//        myRef.setValue("Hello world!");
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                String value = snapshot.getValue(String.class);
//                String Tag = "tag";
//                Log.d(Tag, "Value is: " + value);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                String Tag = "tag";
//                Log.w(Tag, "Failed to read value.", error.toException());
//            }
//        });
//    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        assemblyViewModel =
                new ViewModelProvider(this).get(AssemblyViewModel.class);
        navController= Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_main);
        binding = FragmentAssemblyBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.navigation_adder);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}