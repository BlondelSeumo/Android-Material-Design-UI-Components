package com.material.components.activity.bottomnavigation;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;

import com.material.components.R;
import com.material.components.utils.Tools;

public class BottomNavigationMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation_main);
        initToolbar();
        initComponent();
    }

    private void initToolbar() {
        Tools.setSystemBarColor(this, R.color.blue_900);
    }

    private void initComponent() {
        // display image
        Tools.displayImageOriginal(this, (ImageView) findViewById(R.id.image_1), R.drawable.image_18);
        Tools.displayImageOriginal(this, (ImageView) findViewById(R.id.image_2), R.drawable.image_30);
        Tools.displayImageOriginal(this, (ImageView) findViewById(R.id.image_3), R.drawable.image_21);
        Tools.displayImageOriginal(this, (ImageView) findViewById(R.id.image_4), R.drawable.image_23);
        Tools.displayImageOriginal(this, (ImageView) findViewById(R.id.image_5), R.drawable.image_19);
    }
}