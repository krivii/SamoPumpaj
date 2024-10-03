package com.example.samopumpaj.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samopumpaj.DB.WorkoutModel;
import com.example.samopumpaj.MainActivity;
import com.example.samopumpaj.R;
import com.example.samopumpaj.RVAdapters.WorkoutRecycleViewAdapter;

import java.time.LocalDate;
import java.util.ArrayList;

public class WorkoutFragment extends Fragment implements WorkoutRecycleViewAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private WorkoutRecycleViewAdapter workoutRecycleViewAdapter;
    private ArrayList<WorkoutModel> workoutModels;


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

        // Set up WorkoutModels
        setUpWorkoutModels();
        String[] modelNames = getResources().getStringArray(R.array.workout_list);
        workoutRecycleViewAdapter = new WorkoutRecycleViewAdapter(getContext(), modelNames, this);
        recyclerView.setAdapter(workoutRecycleViewAdapter);

        return view;
    }

    private void setUpWorkoutModels() {
        String[] modelNames = getResources().getStringArray(R.array.workout_list);
        //for (String name : modelNames) {
         //  workoutModels.add(new WorkoutModel(name, LocalDate.now().toString(), 10));
        //}
    }

    @Override
    public void onItemClick(int position) {
        // Reset the workout models
        workoutModels.clear();

        // Switch to TrainingFragment
        TrainingFragment trainingFragment = new TrainingFragment();
        ((MainActivity) requireActivity()).loadFragment(trainingFragment, "Trainings"); // Update the title as needed
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        workoutModels.clear(); // Clear models when the fragment is destroyed
    }
}
