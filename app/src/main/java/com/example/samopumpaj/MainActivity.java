package com.example.samopumpaj;

import static android.content.ContentValues.TAG;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.samopumpaj.DB.DataBaseHelper;
import com.example.samopumpaj.DB.WorkoutModel;
import com.example.samopumpaj.fragments.ExerciseFragment;
import com.example.samopumpaj.fragments.FragmentTitleListener;
import com.example.samopumpaj.fragments.WorkoutFragment;

public class MainActivity extends AppCompatActivity implements FragmentTitleListener {

    private TextView toolbarTitleTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbarTitleTV = findViewById(R.id.toolbarTV);

        // Load default fragment with the default title
        loadFragment(new WorkoutFragment(), "Samo pumpaj!");
    }

    // Method to load a fragment and update the title
    public void loadFragment(Fragment fragment, String title) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .addToBackStack(null)
                .commit();
        updateTitle(title);
    }

    // Method to handle clicks on training items
    public void onTrainingItemClick(int position) {
        // Load the ExerciseFragment when a training item is clicked
        loadFragment(new ExerciseFragment(), "Exercise Details");
    }

    @Override
    public void updateTitle(String title) {
        if (toolbarTitleTV != null) {
            toolbarTitleTV.setText(title);
        }
    }
}
