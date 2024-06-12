package com.example.deadwoodassambly.ui.assembly;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deadwoodassambly.R;
import com.example.deadwoodassambly.databinding.FragmentAssemblyBinding;
import com.example.deadwoodassambly.model.RemoteAssembly;

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
        assemblyViewModel.getAssemblies().observe(getViewLifecycleOwner(),value->{
            AssemblyAdapter adapter=new AssemblyAdapter(getActivity(),value);
            binding.rvAssembly.setAdapter(adapter);
            ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT)
            {
                @Override
                public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                    return false;
                }

                @Override
                public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                    RemoteAssembly remoteAssembly= value.get(viewHolder.getAdapterPosition());
                    assemblyViewModel.Remove(remoteAssembly);
                    adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                }
            };
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
            itemTouchHelper.attachToRecyclerView(binding.rvAssembly);
        });

        return root;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}