package com.katza.shaniapp;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CustomDialog extends BaseActivity {

    SharedPreferences sp;
    Dialog d;

    Button shlifaNetoonim;
    EditText etUserName, etPass;
    Button btnCustomLogin, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
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
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createLoginDialog();
            }

        });
        sp = getSharedPreferences("details1", 0);
        shlifaNetoonim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = etUserName.getText().toString();
                String pass = etPass.getText().toString();
                Toast.makeText(CustomDialog.this, "user - " + user + " pass - " + pass, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void createLoginDialog()
    {
        d = new Dialog(this);

        d.setContentView(R.layout.customdialog);

        d.setTitle("Login");

        d.setCancelable(true);

        etUserName = d.findViewById(R.id.etUserName);

        etPass = d.findViewById(R.id.etPassword);

        btnCustomLogin = d.findViewById(R.id.btnDialogLogin);

        btnCustomLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = etUserName.getText().toString();
                String pass = etPass.getText().toString();
                SharedPreferences.Editor editor = sp.edit();

                // שמירת הנתונים
                editor.putString("username", user);
                editor.putString("password", pass);

                // אישור השמירה
                editor.apply();

                // הודעה למשתמש
                Toast.makeText(CustomDialog.this, "הנתונים נשמרו", Toast.LENGTH_SHORT).show();
                d.dismiss(); // סוגר את הדיאלוג אחרי הלחיצה
            }
        });
        d.show();
    }
}

