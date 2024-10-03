package com.example.samopumpaj;

import android.os.Bundle;
import android.view.View;
import android.view.WindowInsets;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
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

    @Override
    public void updateTitle(String title) {
        if (toolbarTitleTV != null) {
            toolbarTitleTV.setText(title);
        }
    }

    // Hides the system bars for fullscreen immersive experience
    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        int flags = View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        decorView.setSystemUiVisibility(flags);

        // Alternatively, for API 30 and above, use WindowInsets
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            decorView.setOnApplyWindowInsetsListener((v, insets) -> {
                v.setPadding(0, 0, 0, insets.getInsets(WindowInsets.Type.navigationBars()).bottom);
                return insets;
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Ensure the system UI stays hidden when returning to the app
        hideSystemUI();
    }

    // Method to handle clicks on training items
    public void onTrainingItemClick(int position) {
        // Handle the click event, and load ExerciseFragment with a specific title
        loadFragment(new ExerciseFragment(), "Exercise Details");
    }
}
