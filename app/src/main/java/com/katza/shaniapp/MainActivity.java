package com.katza.shaniapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    int secretNumber;

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
        submit.setOnClickListener(new View.OnClickListener() {
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
        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                britness += 0.1;
                findViewById(R.id.dog).setAlpha(britness);
                Toast.makeText(MainActivity.this, "brightest", Toast.LENGTH_SHORT).show();
            }
        });
        btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                britness -= 0.1;
                findViewById(R.id.dog).setAlpha(britness);
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
