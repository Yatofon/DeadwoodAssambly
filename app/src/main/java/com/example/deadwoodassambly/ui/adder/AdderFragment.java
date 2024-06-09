package com.example.deadwoodassambly.ui.adder;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deadwoodassambly.R;
import com.example.deadwoodassambly.databinding.FragmentAdderBinding;
import com.example.deadwoodassambly.databinding.FragmentAssemblyBinding;
import com.example.deadwoodassambly.ui.assembly.AssemblyViewModel;


public class AdderFragment extends Fragment {
    private FragmentAdderBinding binding;

    private AdderViewModel adderViewModel;

    public static AdderFragment newInstance(String param1, String param2) {
        AdderFragment fragment = new AdderFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        adderViewModel =
                new ViewModelProvider(this).get(AdderViewModel.class);
        binding = FragmentAdderBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}