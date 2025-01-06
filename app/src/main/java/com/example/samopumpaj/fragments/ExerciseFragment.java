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
import com.example.samopumpaj.DB.ExerciseModel;
import com.example.samopumpaj.MainActivity;
import com.example.samopumpaj.RVAdapters.ExerciseRecycleViewAdapter;
import com.example.samopumpaj.R;
import com.example.samopumpaj.RVAdapters.TrainingRecycleViewAdapter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExerciseFragment extends Fragment {

    private RecyclerView recyclerView;
    private ExerciseRecycleViewAdapter exerciseRecycleViewAdapter;
    private List<ExerciseModel> exerciseModels;
    //private FragmentTitleListener titleListener;
    private int trainingId;

    public ExerciseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise, container, false);

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.exerciseRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        setUpExerciseModels();
        exerciseRecycleViewAdapter =  new ExerciseRecycleViewAdapter(
                getContext(),
                exerciseModels,
                position -> {
                    // Call the MainActivity method on item click
                    //((MainActivity) getActivity()).onExerciseItemClick(position);
                }
        );

        recyclerView.setAdapter(exerciseRecycleViewAdapter);

        return view;
    }

    public static ExerciseFragment newInstance(String param1, String param2) {
        ExerciseFragment fragment = new ExerciseFragment();
        Bundle args = new Bundle();
        args.putString("param1", param1);
        args.putString("param2", param2);
        fragment.setArguments(args);
        return fragment;
    }


    /*@Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // Attach the FragmentTitleListener interface to the activity
        if (context instanceof FragmentTitleListener) {
            titleListener = (FragmentTitleListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement FragmentTitleListener");
        }
    }*/

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // Retrieve the workout data from the arguments
            trainingId = getArguments().getInt("trainingId");
        }
    }



    private void setUpExerciseModels() {
        DataBaseHelper dbHelper = new DataBaseHelper(getContext());
        exerciseModels = dbHelper.getAllExercisesByTrainingId(trainingId); //TODO: treba nafilat trainingid!
    }

    @Override
    public void onPause() {
        super.onPause();
        // Clear the training models when the fragment is paused
        exerciseModels.clear();
        if (exerciseRecycleViewAdapter != null) {
            exerciseRecycleViewAdapter.notifyDataSetChanged(); // Notify adapter of data change
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        for (int i = 0; i < exerciseModels.size(); i++) {
            exerciseModels.set(i, null);
        }
    }
}
