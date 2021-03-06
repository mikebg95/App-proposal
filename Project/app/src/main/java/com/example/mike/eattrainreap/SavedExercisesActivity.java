package com.example.mike.eattrainreap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class SavedExercisesActivity extends AppCompatActivity {

    // static variable for adapter
    public static ExerciseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_exercises);

        // create variables linked to listview
        ListView savedExercises = findViewById(R.id.savedExercises);

        // set listview to favorite exercises via adapter
        adapter = new ExerciseAdapter(this, R.layout.exercise_row, HomeActivity.favoriteExercises);
        savedExercises.setAdapter(adapter);

        // when clicked on exercise in listview
        savedExercises.setOnItemClickListener(new OnSavedExerciseClicked());
    }

    public class OnSavedExerciseClicked implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // get clicked exercise object
            Exercise currentExercise = (Exercise) parent.getItemAtPosition(position);

            // add exercise to and start intent for pop-up screen
            Intent intent = new Intent(getApplicationContext(), PopUpActivity.class);
            intent.putExtra("exercise", currentExercise);
            intent.putExtra("isFavorite", true);
            startActivity(intent);
        }
    }
}
