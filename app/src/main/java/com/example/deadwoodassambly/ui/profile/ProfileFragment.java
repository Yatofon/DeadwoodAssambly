package com.example.deadwoodassambly.ui.profile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deadwoodassambly.R;
import com.example.deadwoodassambly.databinding.FragmentAssemblyBinding;
import com.example.deadwoodassambly.databinding.FragmentProfileBinding;
import com.example.deadwoodassambly.ui.assembly.AssemblyViewModel;


public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;


    private ProfileViewModel profileViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}