package com.katza.shaniapp;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
public abstract class BaseActivity extends AppCompatActivity {
    // מכריזים על מחלקה בשם BaseActivity שהיא abstract.
    // abstract אומר שמחלקה זו **לא ניתנת להופעלה ישירות**, אלא משמשת "תבנית" למחלקות אחרות.
    // היא יורשת מ-AppCompatActivity, כלומר כל התכונות של Activity רגיל זמינות לה.

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // פונקציה שנקראת אוטומטית על ידי Android כשצריך ליצור את התפריט העליון (Options Menu) של ה-Activity.

        getMenuInflater().inflate(R.menu.menu_main, menu);
        // "מפציץ" (inflate) את קובץ ה-XML של התפריט (menu_main.xml) לתוך האובייקט Menu.
        // כלומר, התפריט מוגדר בקובץ XML ונטען כאן.

        return true;
        // מחזיר true כדי לאותת ל-Android שהתפריט נוצר וניתן להציגו.
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // פונקציה שנקראת אוטומטית כשמשתמש לוחץ על פריט בתפריט.

        int id = item.getItemId();
        // שולף את ה-ID של הפריט שנבחר (לפי מה שהגדרת ב-XML).

        if (id == R.id.action_login) {
            // אם המשתמש לחץ על פריט עם ID בשם action_login
            Intent intent = new Intent(this, CustomDialog.class);
            startActivity(intent);
            //finish();
            return true;
            // מחזיר true כדי לאותת ל-Android שהאירוע טופל.
        }
        else if (id == R.id.action_register) {
            // אם המשתמש לחץ על פריט עם ID בשם action_register
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            //finish();
            return true;
        }

        else if (id == R.id.action_profile) {
            Intent intent = new Intent(this, SharedInfo.class);
            startActivity(intent);
            //finish();
            return true;
        }

        else if (id == R.id.action_settings) {
            Intent intent = new Intent(this, DynamicActivity.class);
            startActivity(intent);
            //finish();
            return true;
        }
        else if (id == R.id.action_exit) {
            // אם המשתמש לחץ על פריט עם ID בשם action_exit
            finishAffinity();
            // סוגר את כל ה-Activities של האפליקציה (effectively "Exit App")
            return true;
        }

        return super.onOptionsItemSelected(item);
        // אם הפריט לא טופל כאן, מעביר את האירוע למחלקת האב (AppCompatActivity) כדי לטפל בו.
    }
}
