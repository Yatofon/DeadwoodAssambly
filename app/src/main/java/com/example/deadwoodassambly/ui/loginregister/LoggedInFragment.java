package com.example.deadwoodassambly.ui.loginregister;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deadwoodassambly.R;
import com.example.deadwoodassambly.databinding.FragmentLoggedInBinding;

public class LoggedInFragment extends Fragment {
    private LoggedInViewModel loggedInViewModel;
    private FragmentLoggedInBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        loggedInViewModel =
                new ViewModelProvider(this).get(LoggedInViewModel.class);
        binding = FragmentLoggedInBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}