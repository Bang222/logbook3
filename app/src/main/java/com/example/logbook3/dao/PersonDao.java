package com.example.logbook3.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;


import com.example.logbook3.models.Person;

import java.util.List;

@Dao
public interface  PersonDao {
    @Insert
    long insertPerson(Person person);

    @Query("SELECT * FROM persons")
    List<Person> getAllPersons();
}
