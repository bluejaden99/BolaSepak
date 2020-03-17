package com.example.bolasepak;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder>
{

    Context context ;
    ArrayList<String> listGoal ;

    public EventAdapter(Context context, ArrayList<String> listGoal) {
        this.context = context;
        this.listGoal = listGoal;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_goal,viewGroup,false) ;
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder myHolder, int i) {
        String name = listGoal.get(i) ;
        myHolder.goal_text.setText(name) ;
    }

    @Override
    public int getItemCount() {
        return listGoal.size();
    }

    class EventViewHolder extends RecyclerView.ViewHolder{
        public TextView goal_text ;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            goal_text = itemView.findViewById(R.id.goal_text) ;
        }
    }
}
