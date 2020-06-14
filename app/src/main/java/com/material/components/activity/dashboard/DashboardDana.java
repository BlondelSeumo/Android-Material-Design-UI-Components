package com.material.components.activity.dashboard;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.material.components.R;
import com.material.components.utils.Tools;

public class DashboardDana extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_dana);
        initToolbar();
    }

    private void initToolbar() {
        Tools.setSystemBarColor(this, R.color.blue_500);
    }
}
