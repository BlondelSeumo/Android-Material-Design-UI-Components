package com.material.components.activity.timeline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.material.components.R;
import com.material.components.utils.Tools;

public class TimelineImageFit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline_image_fit);
        initToolbar();


        Tools.displayImageOriginal(this, (ImageView) findViewById(R.id.images_1), R.drawable.image_12);
        Tools.displayImageOriginal(this, (ImageView) findViewById(R.id.images_2), R.drawable.image_11);
        Tools.displayImageOriginal(this, (ImageView) findViewById(R.id.images_3), R.drawable.image_13);
        Tools.displayImageOriginal(this, (ImageView) findViewById(R.id.images_4), R.drawable.image_15);
        Tools.displayImageOriginal(this, (ImageView) findViewById(R.id.images_5), R.drawable.image_12);
        Tools.displayImageOriginal(this, (ImageView) findViewById(R.id.images_6), R.drawable.image_26);
        Tools.displayImageOriginal(this, (ImageView) findViewById(R.id.images_7), R.drawable.image_27);
        Tools.displayImageOriginal(this, (ImageView) findViewById(R.id.images_8), R.drawable.image_8);
        Tools.displayImageOriginal(this, (ImageView) findViewById(R.id.images_9), R.drawable.image_9);
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this, R.color.grey_5);
        Tools.setSystemBarLight(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        Tools.changeMenuIconColor(menu, getResources().getColor(R.color.grey_60));
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
}
