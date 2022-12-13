package com.example.chronometre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chronometre.bddManager.DBHandler;

public class updateActivity extends AppCompatActivity {
    EditText chronoNameEdt;
    Chronometer chronoTimer;
    String chronoName;
    String time;
    Button editBtn;

    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_chrono);

        chronoNameEdt = findViewById(R.id.edit_name);
        chronoTimer = findViewById(R.id.chronometer);

        dbHandler = new DBHandler(updateActivity.this);

        chronoName = getIntent().getStringExtra("name");
        time = getIntent().getStringExtra("time");

        chronoNameEdt.setText(chronoName);
        chronoTimer.setText(time);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHandler.updateChrono(chronoName, chronoNameEdt.getText().toString(), chronoTimer.getText().toString());

                Toast.makeText(updateActivity.this, "Course Updated..", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(updateActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}