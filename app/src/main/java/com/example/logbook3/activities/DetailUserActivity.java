package com.example.logbook3.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.logbook3.Addapter.MyAdapter;
import com.example.logbook3.R;
import com.example.logbook3.database.AppDatabase;
import com.example.logbook3.models.Person;

import java.util.ArrayList;
import java.util.List;

public class DetailUserActivity extends AppCompatActivity {
    private AppDatabase appDatabase;
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    ArrayList<String> test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);
        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "log_book_3")
                .allowMainThreadQueries() // For simplicity, don't use this in production
                .build();

        List<Person> people = appDatabase.personDao().getAllPersons();
        recyclerView = findViewById(R.id.recycler_view);
        MyAdapter myAdapter = new MyAdapter(people);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    public void onClickedBackDetailButton(View view) {
        Intent intent = new Intent(DetailUserActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
