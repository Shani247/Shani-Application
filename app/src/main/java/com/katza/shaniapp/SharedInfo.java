package com.katza.shaniapp;

import android.content.SharedPreferences;
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

public class SharedInfo extends AppCompatActivity {

    SharedPreferences sp;
    Button btnSave;
    EditText etFname, etLname;
    TextView tvDisplay;
    View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_shared_info);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initViews();

    }

    private void initViews() {
        btnSave = findViewById(R.id.btnSubmit);
        btnSave.setOnClickListener(new View.OnClickListener() { // כפתור להגדלת בהירות התמונה
            @Override
            public void onClick(View v) {
                if (btnSave == v) {
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("fname", etFname.getText().toString());
                    editor.putString("lname", etLname.getText().toString());
                    editor.commit();
                }
            }
        });
        etFname = findViewById(R.id.etFname);
        etLname = findViewById(R.id.etLname);
        tvDisplay = findViewById(R.id.tvDisplay);
        sp = getSharedPreferences("details1", 0);
        String strFname = sp.getString("fname", null);
        String strLname = sp.getString("lname", null);
        if (strFname != null && strLname != null) {
            tvDisplay.setText("Hey " + strFname + " " + strLname);
        }
    }
}

