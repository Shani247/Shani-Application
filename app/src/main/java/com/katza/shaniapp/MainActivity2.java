package com.katza.shaniapp;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends BaseActivity {

    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        tv = (TextView) findViewById(R.id.tv);

        //registerForContextMenu(tv);


    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        super.onOptionsItemSelected(item);

        int id = item.getItemId();

        if (id == R.id.action_login) {
            Toast.makeText(this, "You selected login", Toast.LENGTH_SHORT).show();
        }
        else if (R.id.action_register == id) {
            Toast.makeText(this, "You selected register", Toast.LENGTH_SHORT).show();
        }
        else if (R.id.action_exit == id) {
            Toast.makeText(this, "You selected exit", Toast.LENGTH_SHORT).show();
        }

        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_2, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.firstOption) {
            Toast.makeText(this, "You selected first line",
                    Toast.LENGTH_LONG).show();
            return true;

        } else if (item.getItemId() == R.id.seconedOption) {
            Toast.makeText(this, "You selected second line",
                    Toast.LENGTH_LONG).show();
            return true;
        }

        return false;
    }

     */

}