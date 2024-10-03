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
    private final String [] workoutModels;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public WorkoutRecycleViewAdapter(Context context, String [] workoutModels, OnItemClickListener listener) {
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
        holder.tvWworkoutName.setText(workoutModels[position]);
        holder.tvDate.setText("24.08.2024");
        holder.tvVisits.setText(String.valueOf(5));

        holder.itemView.setOnClickListener(v -> listener.onItemClick(position));
    }

    @Override
    public int getItemCount() {
        return workoutModels.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvWworkoutName, tvDate, tvVisits;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvWworkoutName = itemView.findViewById(R.id.workoutName_textView);
            tvDate = itemView.findViewById(R.id.date_textView);
            tvVisits = itemView.findViewById(R.id.nbVisits_textView);
        }
    }
}
