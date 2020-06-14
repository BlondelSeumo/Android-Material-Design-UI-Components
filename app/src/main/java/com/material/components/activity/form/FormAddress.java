package com.material.components.activity.form;

import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.material.components.R;
import com.material.components.utils.Tools;

public class FormAddress extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_address);
        initToolbar();
        initComponent();
    }

    private void initComponent() {
        (findViewById(R.id.et_state)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showStateDialog(v);
            }
        });

        (findViewById(R.id.et_country)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCountryDialog(v);
            }
        });

        (findViewById(R.id.bt_submit)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Submit", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void showStateDialog(final View v) {
        final String[] array = new String[]{
                "Arizona", "California", "Florida", "Massachusetts", "New York", "Washington"
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("State");
        builder.setSingleChoiceItems(array, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ((EditText) v).setText(array[i]);
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    private void showCountryDialog(final View v) {
        final String[] array = new String[]{
                "United State", "Germany", "United Kingdom", "Australia"
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Country");
        builder.setSingleChoiceItems(array, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ((EditText) v).setText(array[i]);
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }
}
