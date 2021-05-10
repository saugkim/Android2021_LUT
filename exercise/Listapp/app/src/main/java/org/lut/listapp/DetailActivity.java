package org.lut.listapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent in = getIntent();
        int idx = in.getIntExtra("org.lut.listapp.ITEM_INDEX", -1);

        if (idx > -1) {
            ImageView imageView = (ImageView) findViewById(R.id.imageView2);
            int pic = getImageID(idx);
            scaleImg(imageView, pic);
        }
    }

    private int getImageID(int index) {
        switch (index) {
            case 0:
                return R.drawable.peach;
            case 1:
                return R.drawable.tomato;
            case 2:
                return R.drawable.squash;
            default:
                return -1;
        }
    }

    private void scaleImg(ImageView imgView, int pic) {
        Display screen = getWindowManager().getDefaultDisplay();
        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), pic, options);

        int imgWidth = options.outWidth;
        int screenWidth = screen.getWidth();

        if (imgWidth > screenWidth) {
            int ratio = Math.round( (float)imgWidth / (float)screenWidth );
            options.inSampleSize = ratio;
            options.inJustDecodeBounds = false;
        }

        options.inJustDecodeBounds = false;
        Bitmap scaledImg = BitmapFactory.decodeResource(getResources(), pic, options);
        imgView.setImageBitmap(scaledImg);
    }
}
