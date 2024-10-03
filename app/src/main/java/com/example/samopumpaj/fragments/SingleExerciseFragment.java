package com.example.samopumpaj.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samopumpaj.R;
import com.example.samopumpaj.RVAdapters.SingleExerciseRVAdapter;
import com.example.samopumpaj.WeightPickerActivity;

public class SingleExerciseFragment extends Fragment {

    private RecyclerView recyclerView;
    private SingleExerciseRVAdapter singleExerciseRecycleViewAdapter;
    private String[] exerciseValues = new String[3];

    public SingleExerciseFragment() {
        // Required empty public constructor
    }

    public static SingleExerciseFragment newInstance(String param1, String param2) {
        SingleExerciseFragment fragment = new SingleExerciseFragment();
        Bundle args = new Bundle();
        args.putString("param1", param1);
        args.putString("param2", param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_single_exercise, container, false);

        recyclerView = view.findViewById(R.id.singleExerciseRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        setUpExerciseModels();
        singleExerciseRecycleViewAdapter = new SingleExerciseRVAdapter(getContext(), exerciseValues);
        recyclerView.setAdapter(singleExerciseRecycleViewAdapter);

        // Find the ConstraintLayout and set the click listener
        View constraintLayout = view.findViewById(R.id.AddNewHighLayout);
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start ActivityWeight when the ConstraintLayout is clicked
                Intent intent = new Intent(getActivity(), WeightPickerActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void setUpExerciseModels() {

        exerciseValues = getResources().getStringArray(R.array.exercise_values);

    }
}
