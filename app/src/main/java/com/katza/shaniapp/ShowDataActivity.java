package com.katza.shaniapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class ShowDataActivity extends BaseActivity {

    private TextView tvUsername, tvPassword, tvGender, tvAge;
    private Button btnEditData, btnCalculateAge;
    EditText etBirthYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show_data);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // אתחול TextViews
        tvUsername = findViewById(R.id.tvUsername);
        tvPassword = findViewById(R.id.tvPassword);
        tvGender   = findViewById(R.id.tvGender);
        tvAge      = findViewById(R.id.tvAge);
        etBirthYear = findViewById(R.id.etBirthYear);

        btnEditData = findViewById(R.id.btnEditData);
        btnCalculateAge = findViewById(R.id.btnCalculateAge);

        // כפתור לחשב גיל מהשנה שהמשתמש הכניס
        btnCalculateAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String birthYearText = etBirthYear.getText().toString();
                if (birthYearText.isEmpty()) {
                    Toast.makeText(ShowDataActivity.this, "אנא הכנס שנת לידה", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    int birthYear = Integer.parseInt(birthYearText);
                    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                    int age = currentYear - birthYear;


                    // החזרת הגיל ל-Activity שקרא למסך הזה
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("age", age);
                    setResult(RESULT_OK, resultIntent);
                    finish();



                } catch (NumberFormatException e) {
                    Toast.makeText(ShowDataActivity.this, "שנה לא חוקית", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // כפתור חזרה לעריכת פרטים
        btnEditData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // סוגר את המסך הנוכחי ומחזיר את ה-RESULT_OK אם הגיל חושב
            }
        });

        // קבלת הנתונים מה-Intent
        String user = getIntent().getStringExtra("username");
        String pass = getIntent().getStringExtra("password");
        Boolean gender = getIntent().getBooleanExtra("gender", true);
        int ageStr = getIntent().getIntExtra("age",56);

        // הצגת הנתונים
        tvUsername.setText("שם משתמש: " + user);
        tvPassword.setText("סיסמה: " + pass);
        tvGender.setText("מין: " + gender);
        tvAge.setText("גיל: "+ageStr);

    }
}
