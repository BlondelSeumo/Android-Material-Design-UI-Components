package com.material.components.activity.settings;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.material.components.R;
import com.material.components.utils.Tools;

public class SettingProfileLight extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_profile_light);
        Tools.setSystemBarColor(this, android.R.color.white);
        Tools.setSystemBarLight(this);
    }
}
