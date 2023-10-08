package com.example.logbook3.database;
import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.logbook3.dao.PersonDao;
import com.example.logbook3.models.Person;

@Database(entities = {Person.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PersonDao personDao();
}
