package org.lut.simplenotepad;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static android.util.Log.d;

public class NoteSelectActivity extends AppCompatActivity {

    public ArrayList<Note> notes;
    private NoteAdapter nAdapter;
    private RecyclerView rvNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_select);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rvNotes = (RecyclerView) findViewById(R.id.rvNotes);

        notes = getNotes();
        d("kim", "NoteSelect, number of notes: "+ notes.size());

        nAdapter = new NoteAdapter(notes);
        RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(getApplicationContext());
        rvNotes.setLayoutManager(mLayoutManager);
        rvNotes.setItemAnimator(new DefaultItemAnimator());
        rvNotes.setAdapter(nAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMain();
            }
        });
    }

    private void goToMain() {

        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("org.lut.simplenotepad.PREFS", 0);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("org.lut.simplenotepad.NOTE_INDEX", 0);
        editor.apply();

        Intent intentToMain = new Intent(NoteSelectActivity.this, MainActivity.class);
        startActivity(intentToMain);
    }

    private ArrayList<Note> getNotes() {
        ArrayList<Note> notes_ = new ArrayList<Note>();

        File directory;
        directory = getFilesDir();
        File[] files = directory.listFiles();

        if (files == null) {
            return notes_;
        }

        for (int i = 0; i < files.length; i++) {
            String filename = files[i].getName();
            String contents = Open(filename);
            d("kim", "NoteSelect, inside loop filename: " + filename + ", file content: " + contents);
            Note note = new Note(filename, contents);
            notes_.add(note);
            /*
            d("kim", "inside for loop " + f);
            theFile = "Note" + f + ".txt";
            String contents = Open(theFile);
            d("kim", "inside for loop file content: " + contents);
            Note note = new Note(theFile, Open(theFile));
            notes_.add(note);
            */
        }
        return notes_;
    }

    public String Open(String fileName) {
        String content = "";
        try {
            InputStream in = openFileInput(fileName);
            if ( in != null) {
                InputStreamReader tmp = new InputStreamReader( in );
                BufferedReader reader = new BufferedReader(tmp);
                String str;
                StringBuilder buf = new StringBuilder();
                while ((str = reader.readLine()) != null) {
                    buf.append(str + "\n");
                } in .close();

                content = buf.toString();
            }
        } catch (java.io.FileNotFoundException e) {} catch (Throwable t) {
            Toast.makeText(this, "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
        }

        return content;
    }
}
