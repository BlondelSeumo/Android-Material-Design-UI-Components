package com.material.components.activity.bottomnavigation;

import android.graphics.PorterDuff;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.material.components.R;
import com.material.components.utils.Tools;

public class BottomNavigationSmall extends AppCompatActivity {

    private ImageView[] menu_nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation_small);
        initToolbar();
        initComponent();
    }

    private void initToolbar() {

        Tools.setSystemBarColor(this, R.color.grey_3);
        Tools.setSystemBarLight(this);
    }

    private void initComponent() {
        menu_nav = new ImageView[5];
        menu_nav[0] = findViewById(R.id.menu_nav_1);
        menu_nav[1] = findViewById(R.id.menu_nav_2);
        menu_nav[2] = findViewById(R.id.menu_nav_3);
        menu_nav[3] = findViewById(R.id.menu_nav_4);
        menu_nav[4] = findViewById(R.id.menu_nav_5);

        // display image
        Tools.displayImageOriginal(this, (ImageView) findViewById(R.id.image_1), R.drawable.image_18);
        Tools.displayImageOriginal(this, (ImageView) findViewById(R.id.image_2), R.drawable.image_30);
        Tools.displayImageOriginal(this, (ImageView) findViewById(R.id.image_3), R.drawable.image_21);
        Tools.displayImageOriginal(this, (ImageView) findViewById(R.id.image_4), R.drawable.image_23);
        Tools.displayImageOriginal(this, (ImageView) findViewById(R.id.image_5), R.drawable.image_19);
    }

    public void onNavigationClick(View view) {
        for (ImageView iv : menu_nav) {
            iv.setColorFilter(getResources().getColor(R.color.grey_20), PorterDuff.Mode.SRC_IN);
            if (iv.getId() == view.getId()) {
                iv.setColorFilter(getResources().getColor(R.color.light_green_500), PorterDuff.Mode.SRC_IN);
            }
        }
    }
}
