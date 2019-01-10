package com.example.mike.eattrainreap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ExercisesActivity extends AppCompatActivity {

    Button findExercise;
    Button savedExercises;
    Button previousExercises;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        findExercise = findViewById(R.id.findExercise);
        savedExercises = findViewById(R.id.savedExercises);
        previousExercises = findViewById(R.id.previousExercises);

        findExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(ExercisesActivity.this, FindExerciseActivity.class);
                startActivity(intent);
            }
        });

        savedExercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(ExercisesActivity.this, SavedExercisesActivity.class);
                startActivity(intent);
            }
        });

        previousExercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(ExercisesActivity.this, PreviousExercisesActivity.class);
                startActivity(intent);
            }
        });
    }
}
