package com.example.chronometre;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chronometre.bddManager.DBHandler;

public class UpdateActivity extends AppCompatActivity {
    EditText chronoNameEdt;
    Chronometer chronoTimer;
    String chronoName;
    String time;
    Button editBtn;

    private DBHandler dbHandler;
    private Button boutonStart;
    private Button boutonStop;
    private Button boutonRestore;
    private CharSequence chronoTime;
    private long elapsedRealtime;
    Long timeWhenStopped;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_chrono);

        chronoNameEdt = findViewById(R.id.edit_name);
        chronoTimer = findViewById(R.id.chronometer);
        boutonStart = findViewById(R.id.start_chrono);
        boutonStop = findViewById(R.id.stop_chrono);
        boutonRestore = findViewById(R.id.restore_chrono);

        dbHandler = new DBHandler(UpdateActivity.this);

        chronoName = getIntent().getStringExtra("name");

        chronoNameEdt.setText(chronoName);
        editBtn = findViewById(R.id.edit_button);

        timeWhenStopped = 0L;

        boutonStart.setOnClickListener(v -> {
            chronoTimer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
            chronoTimer.start();
        });

        boutonStop.setOnClickListener(v -> {
            timeWhenStopped = chronoTimer.getBase() - SystemClock.elapsedRealtime();

            chronoTimer.stop();
        });

        boutonRestore.setOnClickListener(v -> {
            timeWhenStopped = 0L;
            chronoTimer.setBase(SystemClock.elapsedRealtime());
            chronoTimer.start();
        });

        editBtn.setOnClickListener(v -> {
            dbHandler.updateChrono(chronoName, chronoNameEdt.getText().toString(), chronoTimer.getText().toString());

            Toast.makeText(UpdateActivity.this, "Course Updated..", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(UpdateActivity.this, MainActivity.class);
            startActivity(i);
        });
    }
}