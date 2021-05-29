package com.peregudova.multinote;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.peregudova.multinote.requests.Note;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.NotesViewHolder>{
    private final LayoutInflater inflater;
    ArrayList<Note> notes;

    RVAdapter(Context context, Map<String, Note> notes){
        this.notes = new ArrayList<>();
        this.notes.addAll(notes.values());
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.card, parent, false);
        return new NotesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RVAdapter.NotesViewHolder holder, int position) {
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 29.05.2021 открытие фрагмента с отдельной заметкой 
            }
        });
        holder.owner.setText(notes.get(position).getOwner());
        if(notes.get(position).getText().length()>50) {
            holder.text.setText(notes.get(position).getText().substring(0, 50));
        } else{
            holder.text.setText(notes.get(position).getText());
        }
        holder.time.setText(notes.get(position).getLast_modified());
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull @NotNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public static class NotesViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView text;
        TextView owner;
        TextView time;
        NotesViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            text = itemView.findViewById(R.id.note_text);
            owner = itemView.findViewById(R.id.note_owner);
            time = itemView.findViewById(R.id.note_time);
        }
    }
}
