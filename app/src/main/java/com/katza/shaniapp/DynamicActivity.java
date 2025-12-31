package com.katza.shaniapp;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DynamicActivity extends BaseActivity {

    LinearLayout lin, lin1, sideLin;
    ImageView im, in;
    ScrollView scroll;
    HorizontalScrollView sideScroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dynamic);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lin = findViewById(R.id.main);

        scroll = new ScrollView(this);
        LinearLayout.LayoutParams inLayoutParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        scroll.setLayoutParams(inLayoutParam);
        lin1 = new LinearLayout(this);
        lin1.setLayoutParams(inLayoutParam);
        lin1.setOrientation(LinearLayout.VERTICAL);

        sideScroll = new HorizontalScrollView(this);
        LinearLayout.LayoutParams outLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        sideScroll.setLayoutParams(outLayoutParams);
        sideLin = new LinearLayout(this);
        sideLin.setLayoutParams(outLayoutParams);
        sideLin.setOrientation(LinearLayout.HORIZONTAL);


        for (int i = 0; i <= 100; i++)
        {
            im = new ImageView(this);
            in = new ImageView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams (100, 100);
            im.setLayoutParams(layoutParams);
            int imageKey = getResources().getIdentifier("img" + i%3, "drawable", getPackageName());
            im.setImageResource(imageKey);
            in.setImageResource(imageKey);
            lin1.addView(im);
            sideLin.addView(in);
        }
        scroll.addView(lin1);
        sideScroll.addView(sideLin);
        lin.addView(sideScroll);
        lin.addView(scroll);
    }

}