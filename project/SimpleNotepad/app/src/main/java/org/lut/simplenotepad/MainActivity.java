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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.util.Log.d;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPrefs;
    EditText editText1;
    String fileName = "";
    String contents = "";
    int f_index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedPrefs = getSharedPreferences("org.lut.simplenotepad.PREFS", 0);
        int f = sharedPrefs.getInt("org.lut.simplenotepad.NOTE_INDEX", -1);
        String f_name = sharedPrefs.getString("org.lut.simplenotepad.FILE_NAME", null);

        d("kim", "MainActivity/onCreate(), f: " + f);
        if (f == -1) {
            //f_index = (get_files() == 0) ? get_files() + 1 : get_files();
            f_index = 1;
            fileName = f_index + ".";
        } else if (f == 0) {
            f_index = get_maxIndex() + 1;
            fileName = f_index + ".";
        } else if (f == 1) {
            f_index = -1;
            fileName = f_name;
            contents = Open(fileName);
        } else {
            f_index = get_maxIndex();
            fileName = get_filename(f_index);
            contents = Open(fileName);
        }

        editText1 = (EditText) findViewById(R.id.EditText1);
        editText1.setText(contents);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d("kim", "MainActivity/onCreate(), saved file name is: " + fileName);
                save(fileName);
            }
        });

        FloatingActionButton createNoteBtn = findViewById(R.id.createNoteBtn);
        createNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contents = "";
                f_index = get_maxIndex() + 1;
                fileName = f_index + ".";
                editText1.setText(contents);
                d("kim", "MainActivity/onCreate(), new note created as " + fileName);
            }
        });
    }

    public String get_filename(int idx){
        File directory;
        directory = getFilesDir();
        File[] files = directory.listFiles();

        for (int i=0; i<files.length; i++) {
            String f_name = files[i].getName();
            String sub = f_name.substring(0, f_name.indexOf("."));
            if ( sub.equals(String.valueOf(idx))) {
                return f_name;
            }
        }
        return "";
    }

    public int get_maxIndex() {
        File directory;
        directory = getFilesDir();
        File[] files = directory.listFiles();

        int max_ID = 0;
        if (files == null) {
            return 0;
        }

        for (int i=0; i<files.length; i++) {
            String f_name = files[i].getName();
            String sub = f_name.substring(0, f_name.indexOf("."));
            //d("kim", "sub is: " + sub);
            int tmp = Integer.parseInt(sub);
            max_ID = Math.max(tmp, max_ID);
        }
        return max_ID;
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

    public void save(String file_name) {
        String currentDate = new SimpleDateFormat("dd_MM_yyyy", Locale.getDefault()).format(new Date());
        String filename;
        if ( (file_name.substring(file_name.length()-1)).equals(".") ) {
            filename = file_name + currentDate;
        } else {
            filename = file_name;
        }

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
