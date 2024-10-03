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

import com.example.samopumpaj.DB.TrainingModel;
import com.example.samopumpaj.MainActivity;
import com.example.samopumpaj.R;
import com.example.samopumpaj.RVAdapters.TrainingRecycleViewAdapter;

import java.time.LocalDate;
import java.util.ArrayList;

public class TrainingFragment extends Fragment {

    private RecyclerView recyclerView;
    private TrainingRecycleViewAdapter trainingRecycleViewAdapter;
    private ArrayList<TrainingModel> trainingModels = new ArrayList<>();
    private FragmentTitleListener fragmentTitleListener;

    public TrainingFragment() {
        // Required empty public constructor
    }

    public static TrainingFragment newInstance(String param1, String param2) {
        TrainingFragment fragment = new TrainingFragment();
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
            fragmentTitleListener = (FragmentTitleListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement FragmentTitleListener");
        }
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
                position -> {
                    // Call the MainActivity method on item click
                    ((MainActivity) getActivity()).onTrainingItemClick(position);
                }
        );
        recyclerView.setAdapter(trainingRecycleViewAdapter);

        return view;
    }

    private void setUpTrainingModels() {
        String[] modelNames = getResources().getStringArray(R.array.training_list);
        for (String name : modelNames) {
            trainingModels.add(new TrainingModel(name, LocalDate.now().toString(), 0, 0)); //TODO
        }
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
}
