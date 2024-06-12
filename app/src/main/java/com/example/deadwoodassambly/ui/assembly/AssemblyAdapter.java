package com.example.deadwoodassambly.ui.assembly;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deadwoodassambly.R;
import com.example.deadwoodassambly.model.RemoteAssembly;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AssemblyAdapter extends RecyclerView.Adapter<AssemblyAdapter.ViewHolder> {
    private final LayoutInflater inflater;
    private final List<RemoteAssembly> assemblieslist;

    public AssemblyAdapter(Context context, List<RemoteAssembly> assemblieslist) {
        this.inflater = LayoutInflater.from(context);
        this.assemblieslist = assemblieslist;
    }

    @NonNull
    @Override
    public AssemblyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.listassembliesitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RemoteAssembly remoteAssembly = assemblieslist.get(position);
        holder.placeView.setText(remoteAssembly.getPlace());
        holder.latitudeView.setText(Double.toString(remoteAssembly.getLatitude()));
        holder.longitudeView.setText(Double.toString(remoteAssembly.getLongitude()));
        Picasso.get().load(remoteAssembly.getImage()).into(holder.imageView);
        holder.commentView.setText(remoteAssembly.getComment());
        holder.dateView.setText(Integer.toString(remoteAssembly.getDate().getDate()) + "." +
                Integer.toString(remoteAssembly.getDate().getMonth()) + "." +
                (remoteAssembly.getDate().getYear()));
    }
    @Override
    public int getItemCount() {
        return assemblieslist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageView;
        final TextView placeView, latitudeView, longitudeView, dateView, commentView;

        ViewHolder(View view) {
            super(view);
            placeView = view.findViewById(R.id.rvPlace);
            latitudeView = view.findViewById(R.id.rvLatitude);
            longitudeView = view.findViewById(R.id.rvLongitude);
            dateView = view.findViewById(R.id.rvDate);
            commentView = view.findViewById(R.id.rvComment);
            imageView = view.findViewById(R.id.rvImage);
        }
    }
}
