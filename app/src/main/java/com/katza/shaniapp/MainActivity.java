package com.katza.shaniapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {//implements View.OnClickListener
    Button btn1;
    Button btn2;
    Button submit;
    float britness = 1;
    TextView textHint;
    EditText editGuess;
    Switch switchVisability;
    SeekBar slowChangeBrightness;
    int secretNumber;
    boolean isChecked = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initViews();
        secretNumber = (int)(Math.random() * 10) + 1;
    }

    private void initViews() {
        textHint = findViewById(R.id.textHint);
        editGuess = findViewById(R.id.editGuess);
        submit = findViewById(R.id.submit);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        switchVisability = findViewById(R.id.switchButtonn);
        slowChangeBrightness = findViewById(R.id.visavilitySeekbar);

        submit.setOnClickListener(new View.OnClickListener() { // כפתור הגשה של נחש מספר
            @Override
            public void onClick(View v) {
                String input = editGuess.getText().toString();
                if (input != null && !input.isEmpty()) {
                    int guess = Integer.parseInt(input);  // ממיר את הטקסט למספר

                    if (guess < secretNumber) {
                        textHint.setText("מספר גבוה יותר!");
                    } else if (guess > secretNumber) {
                        textHint.setText("מספר נמוך יותר!");
                    } else {
                        textHint.setText("ניחשת נכון! 🎉");
                    }

                } else {
                    textHint.setText("עליך להכניס מספר");
                }
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() { // כפתור להגדלת בהירות התמונה
            @Override
            public void onClick(View v) {
                britness += 0.1;
                findViewById(R.id.dog).setAlpha(britness);
                Toast.makeText(MainActivity.this, "brightest", Toast.LENGTH_SHORT).show();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() { // כפתור להקטנת בהירות התמונה
            @Override

            public void onClick(View v) {
                britness -= 0.1;
                findViewById(R.id.dog).setAlpha(britness);
            }
        });
        // כפתור הבודק נכון או לא נכון האם הכפתור לחוץ או לא ושנה בהתאם מצב האם רואים או לא רואים את התמונה
        switchVisability.setOnCheckedChangeListener((switchButtonn, isChecked) -> {
            if (isChecked) {
                findViewById(R.id.dog).setVisibility(View.VISIBLE);
            } else {
                findViewById(R.id.dog).setVisibility(View.INVISIBLE);
            }
        });
        slowChangeBrightness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float slowChangeVis = progress / 100f;
                findViewById(R.id.dog).setAlpha(slowChangeVis);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "START", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "STOP", Toast.LENGTH_SHORT).show();
            }
        });

    }
}

    /* @Override
    public void onClick(View v) {
        if (v == btn1){
            britness += 0.1;
            findViewById(R.id.dog).setAlpha(britness);
        }
        else if (v == btn2){
            britness -= 0.1;
            findViewById(R.id.dog).setAlpha(britness);
        }
    }
     */
