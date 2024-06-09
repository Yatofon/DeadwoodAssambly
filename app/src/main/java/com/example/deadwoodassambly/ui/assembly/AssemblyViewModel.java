package com.example.deadwoodassambly.ui.assembly;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import java.io.Closeable;


public class AssemblyViewModel extends ViewModel {
    public AssemblyViewModel(@NonNull Closeable closeable) {
        super(closeable);

    }
}