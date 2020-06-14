package com.material.components.activity.form;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.material.components.R;

public class FormSignupCardStack extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_signup_card_stack);

        (findViewById(R.id.bt_create_account)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Create Account", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
