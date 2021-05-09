package org.lut.quicklauncher;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button secondActivityBtn = (Button) findViewById(R.id.secondActivityBtn);
        Button googleBtn = (Button) findViewById(R.id.googleActivityBtn);

        // attempt to launch an activity within our app
        secondActivityBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), SecondActivity.class);
                //show how to pass information to another activity
                startIntent.putExtra("org.lut.quicklauncher.SOMETHING", "HELLO WORLD");
                startActivity(startIntent);
            }
        });

        // attempt to launch an activity outside out app (like google)
        googleBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String google = "https://www.google.com";
                Uri webAddress = Uri.parse(google);

                Intent gotoGoogle = new Intent(Intent.ACTION_VIEW, webAddress);
                if (gotoGoogle.resolveActivity(getPackageManager()) != null) {
                    startActivity(gotoGoogle);
                }
            }
        });

    }
}
