package com.katza.shaniapp;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class CustomDialog extends BaseActivity {

    SharedPreferences sp;
    Dialog d;

    Button shlifaNetoonim, btnLogin;
    EditText etUserName, etPass, etAge, etBirthYear;
    Button btnCustomLogin;
    RadioGroup rgGender;

    // ActivityResultLauncher אם נרצה בעתיד
    private ActivityResultLauncher<Intent> ageLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data!=null)
                        {

                            int age = data.getIntExtra("age",111);
                            etAge.setText(String.valueOf(age));
                            Toast.makeText(CustomDialog.this, "הגיל שחושב: " + age, Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(CustomDialog.this, "אנא הכנס/י שנת לידה", Toast.LENGTH_SHORT).show();
                    }

                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_dialog);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
    }

    private void initViews() {
        shlifaNetoonim = findViewById(R.id.shlifaNetoonim);
        btnLogin = findViewById(R.id.btnLogin);

        sp = getSharedPreferences("details1", 0);

        btnLogin.setOnClickListener(v -> createLoginDialog());

        shlifaNetoonim.setOnClickListener(v -> {
            if (d != null && etUserName != null && etPass != null && rgGender != null) {
                String user = etUserName.getText().toString();
                String pass = etPass.getText().toString();

                String gender = "";
                int selectedId = rgGender.getCheckedRadioButtonId();
                if (selectedId != -1) {
                    RadioButton selected = d.findViewById(selectedId);
                    gender = selected.getText().toString();
                }

                String ageStr = etAge.getText().toString();

                Toast.makeText(CustomDialog.this,
                        "user: " + user + "\npass: " + pass + "\ngender: " + gender + "\nage: " + ageStr,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void createLoginDialog() {
        d = new Dialog(this);
        d.setContentView(R.layout.customdialog);
        d.setTitle("Login");
        d.setCancelable(true);

        etUserName = d.findViewById(R.id.etUserName);
        etPass = d.findViewById(R.id.etPassword);
        rgGender = d.findViewById(R.id.rgGender);
        etAge = d.findViewById(R.id.etAge);
        etBirthYear = d.findViewById(R.id.etBirthYear);
        btnCustomLogin = d.findViewById(R.id.btnDialogLogin);

        //Result #2
        Button btnCalculateAge = d.findViewById(R.id.btnCalculateAge);
        btnCalculateAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomDialog.this,ShowDataActivity.class);
                ageLauncher.launch(intent);
            }
        });

        //NO Result #1
        btnCustomLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = etUserName.getText().toString();
                String pass = etPass.getText().toString();

                String gender = "";
                int selectedId = rgGender.getCheckedRadioButtonId();
                if (selectedId != -1) {
                    RadioButton selected = d.findViewById(selectedId);
                    gender = selected.getText().toString();
                }


                // שמירת הנתונים ב-SharedPreferences
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("username", user);
                editor.putString("password", pass);
                editor.putBoolean("gender", false);
                editor.putInt("age", 12);
                editor.apply();

                // שליחה ל-ShowData
                Intent intent = new Intent(CustomDialog.this, ShowDataActivity.class);
                intent.putExtra("username", user);
                intent.putExtra("password", pass);
                intent.putExtra("gender", false);
                intent.putExtra("age", 12);
                startActivity(intent);

                Toast.makeText(CustomDialog.this, "הנתונים נשמרו", Toast.LENGTH_SHORT).show();
                d.dismiss();
            }
        });

        d.show();
    }
}
