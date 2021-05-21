package org.lut.simplenotepad;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static android.util.Log.d;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPrefs;
    EditText editText1;
    String fileName = "";
    int f_index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedPrefs = getSharedPreferences("org.lut.simplenotepad.PREFS", 0);
        int f = sharedPrefs.getInt("org.lut.simplenotepad.NOTE_INDEX", -1);

        if (f == -1) {
            f_index = (get_files() == 0) ? get_files() + 1 : get_files();
        } else if (f == 0) {
            f_index = get_files() + 1;
        } else {
            f_index = f;
        }

        d("kim", "file index: " + f_index);
        fileName = "Note" + f_index + ".txt";
        editText1 = (EditText) findViewById(R.id.EditText1);
        editText1.setText(Open(fileName));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d("kim", "saved file name is: " + fileName);
                save(fileName);
            }
        });

        FloatingActionButton createNoteBtn = findViewById(R.id.createNoteBtn);
        createNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f_index += 1;
                fileName = "Note" + f_index + ".txt";
                editText1.setText(Open(fileName));
                d("kim", "new note created as " + fileName);
            }
        });
    }

    public int get_files() {
        File directory;
        directory = getFilesDir();
        File[] files = directory.listFiles();

        if (files != null) {
            return files.length;
        }
        return 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void save(String filename) {
        try {
            OutputStreamWriter out = new OutputStreamWriter(openFileOutput(filename, 0));
            out.write(editText1.getText().toString());
            out.close();
            Toast.makeText(this, "Note saved as " + filename, Toast.LENGTH_SHORT).show();
        } catch (Throwable t) {
            Toast.makeText(this, "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public boolean FileExists(String filename) {
        File file = getBaseContext().getFileStreamPath(filename);
        return file.exists();
    }

    public String Open(String fileName) {
        String content = "";
        if (FileExists(fileName)) {
            try {
                InputStream in = openFileInput(fileName);
                if (in != null) {
                    InputStreamReader tmp = new InputStreamReader(in);
                    BufferedReader reader = new BufferedReader(tmp);
                    String str;
                    StringBuilder buf = new StringBuilder();
                    while ((str = reader.readLine()) != null) {
                        buf.append(str + "\n");
                    }
                    in.close();
                    content = buf.toString();
                }
            } catch (java.io.FileNotFoundException e) {
                Toast.makeText(this, "Note not exists!", Toast.LENGTH_SHORT).show();
            } catch (Throwable t) {
                Toast.makeText(this, "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
            }
        }
        return content;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent myIntent = new Intent(MainActivity.this, NoteSelect.class);
            MainActivity.this.startActivity(myIntent);
        }

        return super.onOptionsItemSelected(item);
    }
}
