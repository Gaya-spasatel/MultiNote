package com.peregudova.multinote;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.peregudova.multinote.requests.Note;

import java.util.List;
import java.util.Map;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.NotesViewHolder>{

    List<Note> notes;

    RVAdapter(Map<String, Note> notes){
        this.notes = (List<Note>) notes.values();
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        return new NotesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RVAdapter.NotesViewHolder holder, int position) {
        holder.owner.setText(notes.get(position).getOwner());
        holder.text.setText(notes.get(position).getText());
        holder.time.setText(notes.get(position).getLast_modified());
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
