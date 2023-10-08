package com.example.logbook3.Addapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.logbook3.R;
import com.example.logbook3.models.Person;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<myViewHolder> {
    Context context;

    private List<Person> people;

    private int[] images = new int[]{R.drawable.image1, R.drawable.image2, R.drawable.image3};

    public MyAdapter(List<Person> people) {
        this.people = people;
    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new myViewHolder(view);
    }

    // duoc lap lai nhieu lan va rang buoc cai noi dung vao tung cai item myViewHolder position la vi tri so vong lap
    // lay hoder tu onCreateViewHolder
    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        Person person = people.get(position);
        holder.textViewName.setText(person.getName());
        holder.textViewDob.setText(person.getDob());
        holder.textViewEmail.setText(person.getEmail());
        holder.textViewName.setText(person.getName());
        holder.imageView.setImageResource(images[person.getImage()]);

    }

    // cai vong for nay cho cai ham onBindViewHolder like ( for position =0 position < name.length ; position++)
    @Override
    public int getItemCount() {
        return people.size();
    }
}
