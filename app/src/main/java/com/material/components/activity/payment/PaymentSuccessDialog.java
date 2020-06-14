package com.material.components.activity.payment;

import android.os.Bundle;
import android.os.Handler;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.material.components.R;
import com.material.components.fragment.DialogPaymentSuccessFragment;
import com.material.components.utils.Tools;


public class PaymentSuccessDialog extends AppCompatActivity {

    private AppCompatButton show_dialog;
    private ProgressBar progress_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_success_dialog);

        show_dialog = (AppCompatButton) findViewById(R.id.show_dialog);
        progress_bar = (ProgressBar) findViewById(R.id.progress_bar);

        show_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitAction();
            }
        });

        submitAction();
        initToolbar();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Payment Success");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this);
    }

    private void submitAction() {
        progress_bar.setVisibility(View.VISIBLE);
        show_dialog.setAlpha(0f);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showDialogPaymentSuccess();
                progress_bar.setVisibility(View.GONE);
                show_dialog.setAlpha(1f);
            }
        }, 1000);
    }

    private void showDialogPaymentSuccess() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        DialogPaymentSuccessFragment newFragment = new DialogPaymentSuccessFragment();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.add(android.R.id.content, newFragment).addToBackStack(null).commit();
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
