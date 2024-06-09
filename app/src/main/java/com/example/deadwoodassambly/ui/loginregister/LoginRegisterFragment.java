package com.example.deadwoodassambly.ui.loginregister;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deadwoodassambly.R;
import com.example.deadwoodassambly.databinding.FragmentLoginRegisterBinding;


public class LoginRegisterFragment extends Fragment {

    private LoginRegisterViewModel loginRegisterViewModel;
    private FragmentLoginRegisterBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        loginRegisterViewModel =
                new ViewModelProvider(this).get(LoginRegisterViewModel.class);
        binding = FragmentLoginRegisterBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.fragmentLoginregisterRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginRegisterViewModel.register(binding.fragmentLoginregisterEmail.getText().toString(),
                        binding.fragmentLoginregisterPassword.getText().toString());
            }
        });
        binding.fragmentLoginregisterLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginRegisterViewModel.login(binding.fragmentLoginregisterEmail.getText().toString(),
                        binding.fragmentLoginregisterPassword.getText().toString());
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