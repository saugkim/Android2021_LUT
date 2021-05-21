package org.lut.simplenotepad;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static android.util.Log.d;
import static androidx.core.content.ContextCompat.startActivity;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder> {

    private List<Note> mNotes;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView content;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            content = (TextView) view.findViewById(R.id.content);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                     int position = getAdapterPosition()+1;
                     d("kim","RecyclerView clicked Item Position: "+ position);

                     SharedPreferences sharedPref = view.getContext().getSharedPreferences("org.lut.simplenotepad.PREFS", 0);
                     SharedPreferences.Editor editor = sharedPref.edit();
                     editor.putInt("org.lut.simplenotepad.NOTE_INDEX", position);
                     editor.apply();

                     Intent intentToMain = new Intent(view.getContext(), MainActivity.class);
                     view.getContext().startActivity(intentToMain);
                }
            });
        }
    }

    public NoteAdapter(List<Note> notes) {
        this.mNotes = notes;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View noteView = inflater.inflate(R.layout.notelist, parent, false);

        return new MyViewHolder(noteView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Note note = mNotes.get(position);
        String title = note.getTitle().substring(0, note.getTitle().length()-4);
        holder.title.setText(title);
        holder.content.setText(note.getContent());
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }
}