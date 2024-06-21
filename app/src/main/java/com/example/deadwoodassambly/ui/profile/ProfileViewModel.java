package com.example.deadwoodassambly.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileViewModel extends ViewModel {

    private FirebaseAuth firebaseAuth;
    private MutableLiveData<String> userEmailLiveData;

    public ProfileViewModel() {
        firebaseAuth = FirebaseAuth.getInstance();
        userEmailLiveData = new MutableLiveData<>();

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            userEmailLiveData.setValue(currentUser.getEmail());
        }
    }

    public LiveData<String> getUserEmailLiveData() {
        return userEmailLiveData;
    }
}