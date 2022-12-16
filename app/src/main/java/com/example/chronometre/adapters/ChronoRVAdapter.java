package com.example.chronometre.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chronometre.modal.ChronoModal;
import com.example.chronometre.R;
import com.example.chronometre.ViewHolder;
import com.example.chronometre.UpdateActivity;

import java.util.List;

public class ChronoRVAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final List<ChronoModal> liste;
    private final Context context;

    public ChronoRVAdapter(List<ChronoModal> liste, Context context) {
        this.liste = liste;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.liste_chrono, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChronoModal modal = liste.get(position);
        holder.getName().setText(modal.getChronoName());
        holder.getTimer().setText(modal.getChronoTimer());

        holder.itemView.setOnClickListener(v -> {
            Intent i = new Intent(context, UpdateActivity.class);
            i.putExtra("name", modal.getChronoName());
            i.putExtra("Timer", modal.getChronoTimer());
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return liste.size();
    }
}

