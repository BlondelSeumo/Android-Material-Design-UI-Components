package com.material.components.activity.profile;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.material.components.R;
import com.material.components.utils.Tools;

public class ProfileCardOverlap extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_card_overlap);
        initToolbar();

        // display image
        Tools.displayImageOriginal(this, (ImageView) findViewById(R.id.image_1), R.drawable.image_18);
        Tools.displayImageOriginal(this, (ImageView) findViewById(R.id.image_2), R.drawable.image_30);
        Tools.displayImageOriginal(this, (ImageView) findViewById(R.id.image_3), R.drawable.image_21);
        Tools.displayImageOriginal(this, (ImageView) findViewById(R.id.image_4), R.drawable.image_23);
        Tools.displayImageOriginal(this, (ImageView) findViewById(R.id.image_5), R.drawable.image_19);
        Tools.displayImageOriginal(this, (ImageView) findViewById(R.id.image_6), R.drawable.image_11);
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this, R.color.indigo_500);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle() + " clicked", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}

