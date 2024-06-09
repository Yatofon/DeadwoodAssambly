package com.example.deadwoodassambly.ui.assembly;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.Manifest;
import com.example.deadwoodassambly.R;
import com.example.deadwoodassambly.databinding.FragmentAssemblyBinding;

public class AssemblyFragment extends Fragment {
    private AssemblyViewModel assemblyViewModel;
    private FragmentAssemblyBinding binding;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        assemblyViewModel =
                new ViewModelProvider(this).get(AssemblyViewModel.class);
        navController= Navigation.findNavController(getActivity(), R.id.nav_host_fragment_activity_main);
        binding = FragmentAssemblyBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.floatingActionButton.setOnClickListener(v -> navController.navigate(R.id.navigation_adder));
        return root;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}