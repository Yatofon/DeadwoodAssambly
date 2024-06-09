package com.example.deadwoodassambly.ui.adder;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deadwoodassambly.R;
import com.example.deadwoodassambly.databinding.FragmentAdderBinding;
import com.example.deadwoodassambly.databinding.FragmentAssemblyBinding;
import com.example.deadwoodassambly.ui.assembly.AssemblyViewModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;


public class AdderFragment extends Fragment {
    private FragmentAdderBinding binding;
    private AdderViewModel adderViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        adderViewModel =
                new ViewModelProvider(this).get(AdderViewModel.class);
        binding = FragmentAdderBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        if (!(ActivityCompat.checkSelfPermission(getActivity() ,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
            Task<Location> task = fusedLocationProviderClient.getLastLocation();
            task.addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    Geocoder geocoder = new Geocoder(getActivity());
                    try
                    {
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),
                                location.getLongitude(), 1);
                        binding.placeTextView.setText(addresses.get(0).getLocality());
                        binding.lantitudeTextView.setText(Double.toString(addresses.get(0).getLatitude()));
                        binding.lantitudeTextView.setText(Double.toString(addresses.get(0).getLongitude()));


                    }
                    catch (IOException e)
                    {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
        binding.saveDataToDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ActivityResultLauncher<String[]> locationPermissionRequest=
                registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> {
                    Boolean fileLocationGranted=result.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION,false);
                    Boolean coarseLocationGranted=result.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION,false);
                    if(fileLocationGranted!=null&&fileLocationGranted)
                    {

                    }
                    else if(coarseLocationGranted!=null&&coarseLocationGranted)
                    {

                    }
                    else {

                    }
                });
        locationPermissionRequest.launch(new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        });
    }
}