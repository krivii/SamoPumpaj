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

import com.example.samopumpaj.DB.ExerciseModel;
import com.example.samopumpaj.RVAdapters.ExerciseRecycleViewAdapter;
import com.example.samopumpaj.R;

import java.time.LocalDate;
import java.util.ArrayList;

public class ExerciseFragment extends Fragment {

    private RecyclerView recyclerView;
    private ExerciseRecycleViewAdapter exerciseRecycleViewAdapter;
    private ArrayList<ExerciseModel> exerciseModels = new ArrayList<>();
    private FragmentTitleListener titleListener;

    public ExerciseFragment() {
        // Required empty public constructor
    }

    public static ExerciseFragment newInstance(String param1, String param2) {
        ExerciseFragment fragment = new ExerciseFragment();
        Bundle args = new Bundle();
        args.putString("param1", param1);
        args.putString("param2", param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // Attach the FragmentTitleListener interface to the activity
        if (context instanceof FragmentTitleListener) {
            titleListener = (FragmentTitleListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement FragmentTitleListener");
        }
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise, container, false);

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.exerciseRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        setUpExerciseModels();
        exerciseRecycleViewAdapter = new ExerciseRecycleViewAdapter(getContext(), exerciseModels);
        recyclerView.setAdapter(exerciseRecycleViewAdapter);

        // Update the title
        if (titleListener != null) {
            titleListener.updateTitle("Exercises");
        }

        return view;
    }

    private void setUpExerciseModels() {
        String[] modelNames = getResources().getStringArray(R.array.exercise_list);
//        for (String name : modelNames) {
//            exerciseModels.add(new ExerciseModel(name, "Biceps",
//                    "https://www.youtube.com/watch?v=dQw4w9WgXcQ", LocalDate.now().toString(), 1, 2,1));
//        }
    }
}
