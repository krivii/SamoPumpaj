package com.example.samopumpaj.RVAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samopumpaj.DB.WorkoutModel;
import com.example.samopumpaj.R;

import java.util.ArrayList;

public class WorkoutRecycleViewAdapter extends RecyclerView.Adapter<WorkoutRecycleViewAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<WorkoutModel> workoutModels;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public WorkoutRecycleViewAdapter(Context context, ArrayList<WorkoutModel> workoutModels, OnItemClickListener listener) {
        this.context = context;
        this.workoutModels = workoutModels;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.workout_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the WorkoutModel for the current position
        WorkoutModel workout = workoutModels.get(position);

        // Set data into the TextViews
        holder.tvWorkoutName.setText(workout.getName());
        holder.tvVisits.setText(String.valueOf(workout.getNumberOfVisits()));

        // Handle item click
        holder.itemView.setOnClickListener(v -> listener.onItemClick(position));
    }

    @Override
    public int getItemCount() {
        return workoutModels.size();  // Return the size of the ArrayList
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvWorkoutName, tvDate, tvVisits;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvWorkoutName = itemView.findViewById(R.id.workoutName_textView);
            tvDate = itemView.findViewById(R.id.date_textView);
            tvVisits = itemView.findViewById(R.id.nbVisits_textView);
        }
    }
}
