package com.material.components.activity.snackbartoast;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.material.components.R;
import com.material.components.utils.Tools;

public class ToastCustom extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast_custom);

        initToolbar();
        initComponent();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Toast Custom");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this);
    }

    private void initComponent() {
        ((AppCompatButton) findViewById(R.id.toast_floating)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastFloating();
            }
        });

        ((AppCompatButton) findViewById(R.id.toast_floating_dark)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastFloatingDark();
            }
        });


        ((AppCompatButton) findViewById(R.id.toast_floating_image)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastFloatingImage();
            }
        });

        ((AppCompatButton) findViewById(R.id.toast_icon_error)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastIconError();
            }
        });

        ((AppCompatButton) findViewById(R.id.toast_icon_success)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastIconSuccess();
            }
        });

        ((AppCompatButton) findViewById(R.id.toast_icon_info)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastIconInfo();
            }
        });
    }

    private void toastFloating() {
        final Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);

        //inflate view
        View custom_view = getLayoutInflater().inflate(R.layout.snackbar_toast_floating, null);
        (custom_view.findViewById(R.id.tv_undo)).setVisibility(View.GONE);
        (custom_view.findViewById(R.id.separator)).setVisibility(View.GONE);
        toast.setView(custom_view);
        toast.show();
    }

    private void toastFloatingDark() {
        final Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);

        //inflate view
        View custom_view = getLayoutInflater().inflate(R.layout.snackbar_toast_floating_dark, null);
        (custom_view.findViewById(R.id.tv_undo)).setVisibility(View.GONE);
        (custom_view.findViewById(R.id.separator)).setVisibility(View.GONE);
        toast.setView(custom_view);
        toast.show();
    }

    private void toastFloatingImage() {
        final Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);

        //inflate view
        View custom_view = getLayoutInflater().inflate(R.layout.snackbar_toast_floating_image, null);
        (custom_view.findViewById(R.id.tv_undo)).setVisibility(View.GONE);
        (custom_view.findViewById(R.id.separator)).setVisibility(View.GONE);
        toast.setView(custom_view);
        toast.show();
    }

    private void toastIconError() {
        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);

        //inflate view
        View custom_view = getLayoutInflater().inflate(R.layout.toast_icon_text, null);
        ((TextView) custom_view.findViewById(R.id.message)).setText("This is Error Message");
        ((ImageView) custom_view.findViewById(R.id.icon)).setImageResource(R.drawable.ic_close);
        ((CardView) custom_view.findViewById(R.id.parent_view)).setCardBackgroundColor(getResources().getColor(R.color.red_600));

        toast.setView(custom_view);
        toast.show();
    }

    private void toastIconSuccess() {
        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);

        //inflate view
        View custom_view = getLayoutInflater().inflate(R.layout.toast_icon_text, null);
        ((TextView) custom_view.findViewById(R.id.message)).setText("Success!");
        ((ImageView) custom_view.findViewById(R.id.icon)).setImageResource(R.drawable.ic_done);
        ((CardView) custom_view.findViewById(R.id.parent_view)).setCardBackgroundColor(getResources().getColor(R.color.green_500));

        toast.setView(custom_view);
        toast.show();
    }

    private void toastIconInfo() {
        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);

        //inflate view
        View custom_view = getLayoutInflater().inflate(R.layout.toast_icon_text, null);
        ((TextView) custom_view.findViewById(R.id.message)).setText("Some Info Text Here");
        ((ImageView) custom_view.findViewById(R.id.icon)).setImageResource(R.drawable.ic_error_outline);
        ((CardView) custom_view.findViewById(R.id.parent_view)).setCardBackgroundColor(getResources().getColor(R.color.blue_500));

        toast.setView(custom_view);
        toast.show();
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
