package com.material.components.activity.form;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.material.components.R;
import com.material.components.utils.Tools;

public class FormAddProject extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_add_project);
        Tools.setSystemBarColor(this, android.R.color.white);
        Tools.setSystemBarLight(this);

        (findViewById(R.id.bt_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
