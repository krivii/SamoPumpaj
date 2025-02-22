package com.example.samopumpaj.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samopumpaj.DB.DataBaseHelper;
import com.example.samopumpaj.DB.TrainingModel;
import com.example.samopumpaj.DB.WorkoutModel;
import com.example.samopumpaj.MainActivity;
import com.example.samopumpaj.R;
import com.example.samopumpaj.RVAdapters.TrainingRecycleViewAdapter;
import com.example.samopumpaj.RVAdapters.WorkoutRecycleViewAdapter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TrainingFragment extends Fragment implements TrainingRecycleViewAdapter.OnItemClickListener{

    private RecyclerView recyclerView;
    private TrainingRecycleViewAdapter trainingRecycleViewAdapter;
    private List<TrainingModel> trainingModels;
    private FragmentTitleListener fragmentTitleListener;
    private int workoutId;

    public TrainingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_training, container, false);

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.trainingRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        setUpTrainingModels();


        // Pass the click listener
        trainingRecycleViewAdapter = new TrainingRecycleViewAdapter(
                getContext(),
                trainingModels,
    this
        );
        recyclerView.setAdapter(trainingRecycleViewAdapter);

        return view;
    }

    @Override
    public void onItemClick(int position) {

        TrainingModel selectedTraining = trainingModels.get(position);

        ExerciseFragment exerciseFragment = new ExerciseFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("trainingId", selectedTraining.getId());

        exerciseFragment.setArguments(bundle);
        onDestroyView();


        ((MainActivity) requireActivity()).loadFragment(exerciseFragment, "Exercises");
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // Retrieve the workout data from the arguments
            workoutId = getArguments().getInt("workoutId");
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // Attach the FragmentTitleListener interface to the activity
        if (context instanceof FragmentTitleListener) {
            fragmentTitleListener = (FragmentTitleListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement FragmentTitleListener");
        }
    }



    private void setUpTrainingModels() {
        DataBaseHelper dbHelper = new DataBaseHelper(getContext());
        trainingModels = dbHelper.getAllTrainingsByWorkoutId(workoutId);

    }

    @Override
    public void onPause() {
        super.onPause();
        // Clear the training models when the fragment is paused
        trainingModels.clear();
        if (trainingRecycleViewAdapter != null) {
            trainingRecycleViewAdapter.notifyDataSetChanged(); // Notify adapter of data change
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragmentTitleListener = null; // Clear the reference when the fragment is detached
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        for (int i = 0; i < trainingModels.size(); i++) {
            trainingModels.set(i, null);
        }
    }
}
