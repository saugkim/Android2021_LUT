package org.lut.simplenotepad;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
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
                    // int position = getAdapterPosition()+1;
                    // d("kim","RecyclerView clicked Item Position: "+ position);
                     int idx = getAdapterPosition();
                     Note note = mNotes.get(idx);

                     SharedPreferences sharedPref = view.getContext().getSharedPreferences("org.lut.simplenotepad.PREFS", 0);
                     SharedPreferences.Editor editor = sharedPref.edit();
                     editor.putString("org.lut.simplenotepad.FILE_NAME", note.getTitle());
                     editor.putInt("org.lut.simplenotepad.NOTE_INDEX", 1);
                     editor.apply();

                     Intent intentToMain = new Intent(view.getContext(), MainActivity.class);
                     view.getContext().startActivity(intentToMain);
                }
            });

            view.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(final View v) {
                    d("kim", "NoteAdapter/MyViewHolder, long click?");

                    final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setTitle("Confirm REMOVE action");
                    builder.setMessage(" YES to remove selected note, NO to cancel");

                    builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            int idx = getAdapterPosition();
                            Note note = mNotes.get(idx);
                            String f_name = note.getTitle();
                            mNotes.remove(idx);

                            notifyDataSetChanged();
                            notifyItemRemoved(idx);
                            notifyItemRangeChanged(idx, mNotes.size());

                            File dir = v.getContext().getFilesDir();
                            File filepath = new File(dir, f_name);
                            filepath.delete();

                            SharedPreferences sharedPref = v.getContext().getSharedPreferences("org.lut.simplenotepad.PREFS", 0);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putInt("org.lut.simplenotepad.NOTE_INDEX", 2);
                            editor.apply();
                        }
                    });

                    builder.setNegativeButton("NO", null);
                    AlertDialog dialog = builder.create();
                    dialog.show();

                    return false;
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
        String title = note.getTitle().substring( note.getTitle().lastIndexOf(".")+1 );
        holder.title.setText(title);
        holder.content.setText(note.getContent());
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }
}