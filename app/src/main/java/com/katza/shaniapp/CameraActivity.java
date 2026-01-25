package com.katza.shaniapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class CameraActivity extends AppCompatActivity {

    ImageView imageView;
    Button btnCamera;
    ActivityResultLauncher<Intent> cameraLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_camera);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initview();

        // אתחול של ActivityResultLauncher שמפעיל Intent ומקבל תוצאה
        cameraLauncher =
                registerForActivityResult(

                        // קובע שה-Launcher מיועד להפעלת Activity חיצוני
                        // ולקבלת תוצאה ממנו (כמו מצלמה)
                        new ActivityResultContracts.StartActivityForResult(),

                        // פונקציה שתופעל לאחר סיום פעילות המצלמה
                        result -> {

                            // בדיקה האם הפעולה הסתיימה בהצלחה
                            if (result.getResultCode() == RESULT_OK) {

                                // קבלת ה-Intent שחזר מהמצלמה
                                Intent data = result.getData();

                                // בדיקה שה-Intent והנתונים הנוספים אינם ריקים
                                if (data != null && data.getExtras() != null) {

                                    // שליפת התמונה שצולמה בפורמט Bitmap
                                    // (תמונה קטנה - thumbnail)
                                    Bitmap bitmap =
                                            (Bitmap) data.getExtras().get("data");

                                    // הצגת התמונה בתוך ImageView על המסך
                                    imageView.setImageBitmap(bitmap);
                                }
                            }
                        }
                );
    }
    private void initview()
    {
        imageView = findViewById(R.id.imageView);
        btnCamera = findViewById(R.id.btnCamera);

        btnCamera.setOnClickListener(v -> {
            Intent intent =
                    new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            cameraLauncher.launch(intent);
        });
    }
}