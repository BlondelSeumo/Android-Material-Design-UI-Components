package com.material.components.activity.snackbartoast;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.material.components.R;
import com.material.components.utils.Tools;

public class SnackbarCustom extends AppCompatActivity {

    private View parent_view;
    private View parent_view_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snackbar_custom);
        parent_view = findViewById(android.R.id.content);
        parent_view_2 = findViewById(R.id.parent_view_2);

        initToolbar();
        initComponent();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Snackbar Custom");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this);
    }

    private void initComponent() {
        ((AppCompatButton) findViewById(R.id.snackbar_primary)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar snackbar = Snackbar.make(parent_view, "Snackbar Primary", Snackbar.LENGTH_SHORT);
                snackbar.getView().setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
                snackbar.show();
            }
        });
        ((AppCompatButton) findViewById(R.id.snackbar_action_accent)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackBarAccentAction();
            }
        });
        ((AppCompatButton) findViewById(R.id.snackbar_floating)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackBarFloating();
            }
        });

        ((AppCompatButton) findViewById(R.id.snackbar_floating_dark)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackBarFloatingDark();
            }
        });


        ((AppCompatButton) findViewById(R.id.snackbar_floating_image)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackBarFloatingImage();
            }
        });


        ((AppCompatButton) findViewById(R.id.snackbar_text_middle)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar mSnackBar = Snackbar.make(parent_view, "Snackbar Text Center", Snackbar.LENGTH_LONG);
                TextView mainTextView = (TextView) (mSnackBar.getView()).findViewById(R.id.snackbar_text);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    mainTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                } else {
                    mainTextView.setGravity(Gravity.CENTER_HORIZONTAL);
                }
                mainTextView.setGravity(Gravity.CENTER_HORIZONTAL);
                mSnackBar.show();
            }
        });

        ((AppCompatButton) findViewById(R.id.snackbar_icon_error)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackBarIconError();
            }
        });

        ((AppCompatButton) findViewById(R.id.snackbar_icon_success)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackBarIconSuccess();
            }
        });

        ((AppCompatButton) findViewById(R.id.snackbar_icon_info)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackBarIconInfo();
            }
        });
    }

    private void snackBarAccentAction() {
        Snackbar snackbar = Snackbar.make(parent_view, "Snackbar Primary Action", Snackbar.LENGTH_LONG)
                .setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Snackbar sb = Snackbar.make(parent_view, "UNDO CLICKED!", Snackbar.LENGTH_SHORT);
                        sb.getView().setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
                        sb.setActionTextColor(Color.WHITE);
                        sb.show();
                    }
                });
        snackbar.getView().setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
        snackbar.setActionTextColor(Color.WHITE);
        snackbar.show();
    }

    private void snackBarFloating() {
        final Snackbar snackbar = Snackbar.make(parent_view, "", Snackbar.LENGTH_LONG);
        //inflate view
        View custom_view = getLayoutInflater().inflate(R.layout.snackbar_toast_floating, null);

        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout snackBarView = (Snackbar.SnackbarLayout) snackbar.getView();
        snackBarView.setPadding(0, 0, 0, 0);
        (custom_view.findViewById(R.id.tv_undo)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
                Toast.makeText(getApplicationContext(), "UNDO Clicked!", Toast.LENGTH_SHORT).show();
            }
        });

        snackBarView.addView(custom_view, 0);
        snackbar.show();
    }

    private void snackBarFloatingDark() {
        final Snackbar snackbar = Snackbar.make(parent_view, "", Snackbar.LENGTH_LONG);
        //inflate view
        View custom_view = getLayoutInflater().inflate(R.layout.snackbar_toast_floating_dark, null);

        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout snackBarView = (Snackbar.SnackbarLayout) snackbar.getView();
        snackBarView.setPadding(0, 0, 0, 0);
        (custom_view.findViewById(R.id.tv_undo)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
                Toast.makeText(getApplicationContext(), "UNDO Clicked!", Toast.LENGTH_SHORT).show();
            }
        });

        snackBarView.addView(custom_view, 0);
        snackbar.show();
    }

    private void snackBarFloatingImage() {
        final Snackbar snackbar = Snackbar.make(parent_view, "", Snackbar.LENGTH_LONG);
        //inflate view
        View custom_view = getLayoutInflater().inflate(R.layout.snackbar_toast_floating_image, null);

        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout snackBarView = (Snackbar.SnackbarLayout) snackbar.getView();
        snackBarView.setPadding(0, 0, 0, 0);
        (custom_view.findViewById(R.id.tv_undo)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
                Toast.makeText(getApplicationContext(), "UNDO Clicked!", Toast.LENGTH_SHORT).show();
            }
        });

        snackBarView.addView(custom_view, 0);
        snackbar.show();
    }

    private void snackBarIconError() {
        final Snackbar snackbar = Snackbar.make(parent_view, "", Snackbar.LENGTH_SHORT);
        //inflate view
        View custom_view = getLayoutInflater().inflate(R.layout.snackbar_icon_text, null);

        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout snackBarView = (Snackbar.SnackbarLayout) snackbar.getView();
        snackBarView.setPadding(0, 0, 0, 0);

        ((TextView) custom_view.findViewById(R.id.message)).setText("This is Error Message");
        ((ImageView) custom_view.findViewById(R.id.icon)).setImageResource(R.drawable.ic_close);
        (custom_view.findViewById(R.id.parent_view)).setBackgroundColor(getResources().getColor(R.color.red_600));
        snackBarView.addView(custom_view, 0);
        snackbar.show();
    }

    private void snackBarIconSuccess() {
        final Snackbar snackbar = Snackbar.make(parent_view, "", Snackbar.LENGTH_SHORT);
        //inflate view
        View custom_view = getLayoutInflater().inflate(R.layout.snackbar_icon_text, null);

        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout snackBarView = (Snackbar.SnackbarLayout) snackbar.getView();
        snackBarView.setPadding(0, 0, 0, 0);

        ((TextView) custom_view.findViewById(R.id.message)).setText("Success!");
        ((ImageView) custom_view.findViewById(R.id.icon)).setImageResource(R.drawable.ic_done);
        (custom_view.findViewById(R.id.parent_view)).setBackgroundColor(getResources().getColor(R.color.green_500));
        snackBarView.addView(custom_view, 0);
        snackbar.show();
    }

    private void snackBarIconInfo() {
        final Snackbar snackbar = Snackbar.make(parent_view, "", Snackbar.LENGTH_SHORT);
        //inflate view
        View custom_view = getLayoutInflater().inflate(R.layout.snackbar_icon_text, null);

        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout snackBarView = (Snackbar.SnackbarLayout) snackbar.getView();
        snackBarView.setPadding(0, 0, 0, 0);

        ((TextView) custom_view.findViewById(R.id.message)).setText("Some Info Text Here");
        ((ImageView) custom_view.findViewById(R.id.icon)).setImageResource(R.drawable.ic_error_outline);
        (custom_view.findViewById(R.id.parent_view)).setBackgroundColor(getResources().getColor(R.color.blue_500));
        snackBarView.addView(custom_view, 0);
        snackbar.show();
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
