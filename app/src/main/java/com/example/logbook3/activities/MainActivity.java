package com.example.logbook3.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.logbook3.R;
import com.example.logbook3.database.AppDatabase;
import com.example.logbook3.models.Person;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    RadioButton radioButton1, radioButton2, radioButton3;
    EditText editName, editDayOfBirth, editEmail;
    TextView viewName, viewDayOfBirth, viewEmail;

    String name, email, DOB;
    int image;

    private AppDatabase appDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //radio
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);
        //edit
        editName = findViewById(R.id.editTextName);
        editDayOfBirth = findViewById(R.id.editTextDOB);
        editEmail = findViewById(R.id.editTextEmail);
        //view
        viewName = findViewById(R.id.textViewName);
        viewDayOfBirth = findViewById(R.id.textViewDOB);
        viewEmail = findViewById(R.id.textViewEmail);
        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "log_book_3").allowMainThreadQueries().build();
        editDayOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.contactdatabase, menu);
        return true;
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDayOfMonth) {
                        // Handle the date selection
                        String selectedDate = selectedDayOfMonth + "/" + (selectedMonth + 1) + "/" + selectedYear;
                        editDayOfBirth.setText(selectedDate);
                    }
                }, year, month, dayOfMonth);

        // Show the date picker dialog
        datePickerDialog.show();
    }

    public void clickedRadioButton1(View view) {
        radioButton1.setChecked(true);
        radioButton2.setChecked(false);
        radioButton3.setChecked(false);
    }

    public void clickedRadioButton2(View view) {
        radioButton1.setChecked(false);
        radioButton2.setChecked(true);
        radioButton3.setChecked(false);
    }

    public void clickedRadioButton3(View view) {
        radioButton1.setChecked(false);
        radioButton2.setChecked(false);
        radioButton3.setChecked(true);
    }

    public void clickViewDetail(View view) {
        Intent intent = new Intent(MainActivity.this, DetailUserActivity.class);
        startActivity(intent);
    }

    // Define function getInputs() to get values from inputs
    private void getInputs() {

        name = editName.getText().toString();
        DOB = editDayOfBirth.getText().toString();
        email = editEmail.getText().toString();
        if (radioButton1.isChecked() == true) {
            image = 0;
        }
        if (radioButton2.isChecked() == true) {
            image = 1;
        }
        if (radioButton3.isChecked() == true) {
            image = 2;
        }
        Person person = new Person(name,DOB,email,image);
        person.name = name;
        person.dob = DOB;
        person.email = email;
        person.image = image;
//        // Calls the insertDetails method we created
        long personId = appDatabase.personDao().insertPerson(person);
    }
    public void resetForm () {
        editName.setText("");
        editDayOfBirth.setText("");
        editEmail.setText("");
        name = "";
        email = "";
        DOB = "";
        radioButton1.setChecked(false);
        radioButton2.setChecked(false);
        radioButton3.setChecked(false);
    }
    public void displayNextAlert() {
        new AlertDialog.Builder(this)
                .setTitle("Success To Contact is saved")
                .setMessage(
                        "New contact saved"
                )
                .setNeutralButton("OKE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        resetForm();
                    }
                })
                .show();
    }
    public void Validate() {
        if(editName.getText().toString().trim().isEmpty()){
            Toast.makeText(
                            MainActivity.this,
                            "Please input the Name",
                            Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        if(editDayOfBirth.getText().toString().trim().isEmpty()){
            Toast.makeText(
                            MainActivity.this,
                            "Please input the Day Of Birth",
                            Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        if(editEmail.getText().toString().trim().isEmpty()){
            Toast.makeText(
                            MainActivity.this,
                            "Please input the Email",
                            Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        if (radioButton1.isChecked() == false && radioButton2.isChecked() == false && radioButton3.isChecked() == false) {
            Toast.makeText(
                            MainActivity.this,
                            "Please choose the images",
                            Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        submit();
    }
    public void clickSaveDetail(View view) {
        Validate();
    }

    public void submit() {
        getInputs();
        displayNextAlert();
    }
}