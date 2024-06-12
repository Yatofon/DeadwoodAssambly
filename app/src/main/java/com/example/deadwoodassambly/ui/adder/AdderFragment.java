package com.example.deadwoodassambly.ui.adder;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.example.deadwoodassambly.databinding.FragmentAdderBinding;
import com.example.deadwoodassambly.model.Assembly;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.Date;
import java.util.List;


public class AdderFragment extends Fragment {
    private FragmentAdderBinding binding;
    private AdderViewModel adderViewModel;
    private List<Address> addresses;
    private Date date;
    private Bitmap foto;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        adderViewModel =
                new ViewModelProvider(this).get(AdderViewModel.class);
        binding = FragmentAdderBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        if (!(ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
            Task<Location> task = fusedLocationProviderClient.getLastLocation();
            task.addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    Geocoder geocoder = new Geocoder(getActivity());
                    try {
                        addresses = geocoder.getFromLocation(location.getLatitude(),
                                location.getLongitude(), 1);
                        binding.placeTextView.setText(addresses.get(0).getLocality());
                        binding.lantitudeTextView.setText(Double.toString(Math.round(addresses.get(0).getLatitude() * 100) / 100.0));
                        binding.longitudeTextView.setText(Double.toString(Math.round(addresses.get(0).getLongitude() * 100) / 100.0));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            ActivityResultLauncher<Intent> startForResultCamera = registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if (result.getResultCode() == Activity.RESULT_OK) {
                                Intent intent = result.getData();
                                intent.putExtra("content", "camera");
                                Bundle extras = result.getData().getExtras();
                                foto = (Bitmap) extras.get("data");
                                binding.photoImageView.setImageBitmap(foto);
                            }
                        }
                    });
            binding.addPhotoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    try {
                        startForResultCamera.launch(takePictureIntent);
                    } catch (ActivityNotFoundException e) {
                    }
                }
            });
            binding.datePeaker.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                    date=new Date(year,month,dayOfMonth);
                }
            });
            binding.saveDataToDatabase.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String place = addresses.get(0).getLocality();
                    double latitude = addresses.get(0).getLatitude();
                    double longitude = addresses.get(0).getLongitude();
                    String comment=binding.commentEditText.getText().toString();
                    Assembly assembly=new Assembly(place,latitude,
                            longitude,date,comment,foto);
                    adderViewModel.AddAssembly(assembly);
                    getActivity().getSupportFragmentManager().popBackStack();
                }
            });
        }
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