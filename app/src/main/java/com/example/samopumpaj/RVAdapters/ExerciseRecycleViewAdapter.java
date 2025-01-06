package com.example.samopumpaj.RVAdapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.samopumpaj.DB.ExerciseModel;
import com.example.samopumpaj.R;
import com.example.samopumpaj.fragments.SingleExerciseFragment;

import java.util.List;

public class ExerciseRecycleViewAdapter extends RecyclerView.Adapter<ExerciseRecycleViewAdapter.ViewHolder> {

    Context context;
    private List<ExerciseModel> exerciseModels;
    private TrainingRecycleViewAdapter.OnItemClickListener onItemClickListener;

    public ExerciseRecycleViewAdapter(Context context, List<ExerciseModel> exerciseModels, TrainingRecycleViewAdapter.OnItemClickListener listener) {
        this.context = context;
        this.exerciseModels = exerciseModels;
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public ExerciseRecycleViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.exercise_row, parent, false);
        return new ExerciseRecycleViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseRecycleViewAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tvExerciseName.setText(exerciseModels.get(position).getName());
        holder.tvDate.setText(exerciseModels.get(position).getLastUpdate().toString());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cast the context to AppCompatActivity to access FragmentManager
                AppCompatActivity activity = (AppCompatActivity) v.getContext();

                // Create an instance of the fragment
                Fragment singleFragment = new SingleExerciseFragment();

                // Optionally, pass data to the fragment
                Bundle bundle = new Bundle();
                bundle.putString("exerciseName", exerciseModels.get(position).getName());
                singleFragment.setArguments(bundle);

                // Begin a fragment transaction
                FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.singleExerciseFL, singleFragment); // Replace with your container ID
                fragmentTransaction.addToBackStack(null); // Add to back stack to enable back navigation
                fragmentTransaction.commit();
            }
        });
    }


    @Override
    public int getItemCount() {
        return exerciseModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvExerciseName, tvDate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvExerciseName = itemView.findViewById(R.id.exerciseName_textView);
            tvDate = itemView.findViewById(R.id.exerciseDate_textView);
        }
    }
}
