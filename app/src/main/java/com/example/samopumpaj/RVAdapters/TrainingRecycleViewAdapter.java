package com.example.samopumpaj.RVAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.samopumpaj.DB.TrainingModel;
import com.example.samopumpaj.R;
import java.util.List;

public class TrainingRecycleViewAdapter extends RecyclerView.Adapter<TrainingRecycleViewAdapter.ViewHolder> {

    Context context;
    private List<TrainingModel> trainingModels;
    private OnItemClickListener onItemClickListener; // Add listener interface

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public TrainingRecycleViewAdapter(Context context, List<TrainingModel>  trainingModels, OnItemClickListener listener) {
        this.context = context;
        this.trainingModels = trainingModels;
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public TrainingRecycleViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.training_row, parent, false);
        return new TrainingRecycleViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainingRecycleViewAdapter.ViewHolder holder, int position) {
        TrainingModel trainingModel = trainingModels.get(position);
        holder.tvTrainingName.setText(trainingModel.getName());
        holder.tvDate.setText(trainingModel.getLastVisit().toString());
        holder.tvVisits.setText(trainingModel.getNumberOfVisits());


        // Set click listener on the entire item view
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return trainingModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTrainingName, tvDate, tvVisits;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTrainingName = itemView.findViewById(R.id.trainingName_textView);
            tvDate = itemView.findViewById(R.id.trainingDate_textView);
            tvVisits = itemView.findViewById(R.id.trainingNbVisits_textView);
        }
    }
}
