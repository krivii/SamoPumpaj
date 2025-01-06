package com.example.samopumpaj.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samopumpaj.DB.DataBaseHelper;
import com.example.samopumpaj.DB.WorkoutModel;
import com.example.samopumpaj.MainActivity;
import com.example.samopumpaj.R;
import com.example.samopumpaj.RVAdapters.WorkoutRecycleViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class WorkoutFragment extends Fragment implements WorkoutRecycleViewAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private WorkoutRecycleViewAdapter workoutRecycleViewAdapter;
    private List<WorkoutModel> workoutModels;

    public WorkoutFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workout, container, false);

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.workoutRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        setUpWorkoutModels();
        workoutRecycleViewAdapter = new WorkoutRecycleViewAdapter(getContext(), workoutModels, this);
        recyclerView.setAdapter(workoutRecycleViewAdapter);

        return view;
    }

    private void setUpWorkoutModels() {

        DataBaseHelper dbHelper = new DataBaseHelper(getContext());
        workoutModels = dbHelper.getAllWorkouts();
    }

    @Override
    public void onItemClick(int position) {

        // Get the selected WorkoutModel
        WorkoutModel selectedWorkout = workoutModels.get(position);

        // Create a new instance of TrainingFragment
        TrainingFragment trainingFragment = new TrainingFragment();

        // Create a Bundle to pass the WorkoutModel data
        Bundle bundle = new Bundle();
        bundle.putInt("workoutId", selectedWorkout.getId());

        // Set the Bundle as arguments for the TrainingFragment
        trainingFragment.setArguments(bundle);
        onDestroyView();

        // Switch to TrainingFragment
        ((MainActivity) requireActivity()).loadFragment(trainingFragment, "Trainings"); // Update the title as needed
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        for (int i = 0; i < workoutModels.size(); i++) {
            workoutModels.set(i, null);
        }
    }
}
