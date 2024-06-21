package com.example.deadwoodassambly.ui.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.Map;
import com.yandex.mapkit.mapview.MapView;



public class MapsFragment extends Fragment {
    private MapsViewModel mapsViewModel;
    private MapView mapView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapsViewModel = new ViewModelProvider(this).get(MapsViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MapKitFactory.setApiKey("35547aea-62bc-4084-bd67-fb75df9e37a3\n");
        MapKitFactory.initialize(requireContext());
        mapView = new MapView(requireContext());
        return mapView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Map map = mapView.getMap();
        map.move(new CameraPosition(new Point(54.716372, 20.504181), 11.0f, 0.0f, 0.0f));
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
        MapKitFactory.getInstance().onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
        MapKitFactory.getInstance().onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mapView.onStop();
        MapKitFactory.getInstance().onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onStop();
        MapKitFactory.getInstance().onStop();
    }
}
