package com.example.logbook3.Addapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.logbook3.R;

public class myViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView textViewName,textViewEmail,textViewDob;
    public myViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewName = itemView.findViewById(R.id.dataName);
        textViewDob = itemView.findViewById(R.id.dataDob);
        textViewEmail = itemView.findViewById(R.id.dataEmail);
        imageView = itemView.findViewById(R.id.imageDetail);
    }
}
