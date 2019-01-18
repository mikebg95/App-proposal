package com.example.mike.eattrainreap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class PopUpActivity extends Activity implements Serializable {

//   public static ArrayList<Exercise> favoriteExercises = new ArrayList<>();

   // variables for views
    TextView exName;
    TextView exMuscles;
    TextView exSecMuscles;
    TextView exEquipment;
    TextView exDescription;
    Button favorite;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        // get width and height of screen
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        // set width and height op pop-up screen to 80 percent of full screen
        getWindow().setLayout((int) (width * 0.8), (int) (height * 0.8));

        // link variables to views
        exName = findViewById(R.id.name);
        exMuscles = findViewById(R.id.muscle);
        exSecMuscles = findViewById(R.id.secondary_muscles);
        exEquipment = findViewById(R.id.equipment);
        exDescription = findViewById(R.id.description);
        favorite = findViewById(R.id.button);

        // get relevant exercise from intent
        Intent intent = getIntent();
        final Exercise exercise = (Exercise) intent.getSerializableExtra("exercise");
        final Boolean isFavorite = intent.getBooleanExtra("isFavorite", false);

        if (!isFavorite) {
            favorite.setText("Add to favorites");
        }
        else {
            favorite.setText("Delete from favorites");
        }

        // set layout views to relevant information
        exName.setText(exercise.getName());
        exDescription.setText(exercise.getDescription());
        exEquipment.setText(exercise.getEquipment());
        exMuscles.setText(exercise.getMuscles());
        exSecMuscles.setText(exercise.getSecondaryMuscles());

        // when button clicked, add exercise to arraylist of favorite exercises
        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isFavorite) {
                    // only add if not in arraylist
                    if (!HomeActivity.favoriteExercises.contains(exercise)) {
                        HomeActivity.favoriteExercises.add(exercise);
                        Toast.makeText(getApplicationContext(), "exercise added to favorites!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "exercise already added to favorites!", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
//                    int index = HomeActivity.favoriteExercises.indexOf(exercise);
//                    HomeActivity.favoriteExercises.remove(index);
                    Log.d("favorite exercises", HomeActivity.favoriteExercises.get(0).getName());
                    Log.d("exercise", exercise.getName());

                    Boolean inList = false;
                    for (int i = 0; i < HomeActivity.favoriteExercises.size(); i++) {
                        if (HomeActivity.favoriteExercises.get(i).getName().equals(exercise.getName())) {
                            inList = true;
                        }
                    }
                    if (inList) {
                        Log.d("favorite exercises before removal", Integer.toString(HomeActivity.favoriteExercises.size()));

                        for (int i = 0; i < HomeActivity.favoriteExercises.size(); i++) {
                            if (HomeActivity.favoriteExercises.get(i).getName().equals(exercise.getName())) {
                                HomeActivity.favoriteExercises.remove(i);
                            }
                        }

//                        HomeActivity.favoriteExercises.remove(exercise);
                        Toast.makeText(getApplicationContext(), "exercise removed from favorites!", Toast.LENGTH_SHORT).show();
                        SavedExercisesActivity.adapter.notifyDataSetChanged();
                        Log.d("favorite exercises after removal", Integer.toString(HomeActivity.favoriteExercises.size()));
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "exercise already removed from favorites!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}