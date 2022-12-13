package com.example.chronometre;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {
    private TextView name;
    private TextView timer;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        this.name = itemView.findViewById(R.id.TVName);
        this.timer = itemView.findViewById(R.id.TVTimer);
    }

    public TextView getName() {
        return name;
    }

    public TextView getTimer() {
        return timer;
    }
}