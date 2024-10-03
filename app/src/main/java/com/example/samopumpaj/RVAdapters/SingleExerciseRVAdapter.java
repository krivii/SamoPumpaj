package com.example.samopumpaj.RVAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samopumpaj.R;


public class SingleExerciseRVAdapter extends RecyclerView.Adapter<SingleExerciseRVAdapter.ViewHolder> {

    Context context;
    String [] exerciseValues;

    public SingleExerciseRVAdapter(Context context, String [] values) {
        this.context = context;
        this.exerciseValues = values;
    }

    @NonNull
    @Override
    public SingleExerciseRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_exercise_row, parent, false);
        return new SingleExerciseRVAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SingleExerciseRVAdapter.ViewHolder holder, int position) {
        holder.tvExerciseValue.setText(exerciseValues[position]);
        holder.tvTitle.setText("Title");
    }

    @Override
    public int getItemCount() {
        return exerciseValues.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvExerciseValue, tvTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvExerciseValue = itemView.findViewById(R.id.singleExerciseName_textView);
            tvTitle = itemView.findViewById(R.id.singleExerciseTitle_textView);
        }
    }
}