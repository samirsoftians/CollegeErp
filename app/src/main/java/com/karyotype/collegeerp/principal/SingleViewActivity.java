package com.karyotype.collegeerp.principal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.karyotype.collegeerp.R;

/**
 * Created by Lenovo on 11/5/2017.
 */

public class SingleViewActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_view);

        // Get intent data
        Intent i = getIntent();

        // Selected image id
        int position = i.getExtras().getInt("id");
        ImageAdapter imageAdapter = new ImageAdapter(this);

        ImageView imageView = (ImageView) findViewById(R.id.SingleView);
        TextView textView=(TextView) findViewById(R.id.text_details);
        imageView.setImageResource(imageAdapter.mThumbIds[position]);
        textView.setText(imageAdapter.mThumbIds[position]);
    }
}
