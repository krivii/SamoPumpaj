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

public class WorkoutFragment extends Fragment implements WorkoutRecycleViewAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private WorkoutRecycleViewAdapter workoutRecycleViewAdapter;
    private ArrayList<WorkoutModel> workoutModels;
    private String[] modelNames;



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
        setUpWorkoutModels1();
        modelNames = getResources().getStringArray(R.array.workout_list);
        workoutRecycleViewAdapter = new WorkoutRecycleViewAdapter(getContext(), workoutModels, this);
        recyclerView.setAdapter(workoutRecycleViewAdapter);

        return view;
    }

    private void setUpWorkoutModels() {
        String[] modelNames = getResources().getStringArray(R.array.workout_list);
        //for (String name : modelNames) {
         //  workoutModels.add(new WorkoutModel(name, LocalDate.now().toString(), 10));
        //}
    }

    private void setUpWorkoutModels1() {

        DataBaseHelper dbHelper = new DataBaseHelper(getContext());
        workoutModels = new ArrayList<>();

        // Creating 3 WorkoutModel rows
        workoutModels.add(new WorkoutModel(1, "Full Body Workout"));
        workoutModels.add(new WorkoutModel(2, "Cardio Blast"));
        workoutModels.add(new WorkoutModel(3, "Strength Training"));

        for (WorkoutModel workoutModel : workoutModels) {
            boolean succ = dbHelper.addWorkout(workoutModel);
            Toast.makeText(getContext(), "Success=" + succ, Toast.LENGTH_SHORT).show() ;
        }
    }

    @Override
    public void onItemClick(int position) {
        // Reset the workout models
        for (int i = 0; i < modelNames.length; i++) {
            modelNames[i] = null;
        }

        // Switch to TrainingFragment
        TrainingFragment trainingFragment = new TrainingFragment();
        ((MainActivity) requireActivity()).loadFragment(trainingFragment, "Trainings"); // Update the title as needed
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        for (int i = 0; i < modelNames.length; i++) {
            modelNames[i] = null;
        }
    }
}
