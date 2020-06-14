package com.material.components.activity.bottomnavigation;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.material.components.R;
import com.material.components.helper.BottomNavigationViewHelper;
import com.material.components.utils.Tools;
import com.material.components.utils.ViewAnimation;

public class BottomNavigationArticle extends AppCompatActivity {

    private BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation_article);
        initComponent();
    }

    private void initComponent() {
        navigation = findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                ViewAnimation.fadeOutIn(findViewById(R.id.nested_scroll_view));
                return true;
            }
        });

        (findViewById(R.id.bt_menu)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Tools.setSystemBarColor(this, android.R.color.white);
        Tools.setSystemBarLight(this);
    }
}
