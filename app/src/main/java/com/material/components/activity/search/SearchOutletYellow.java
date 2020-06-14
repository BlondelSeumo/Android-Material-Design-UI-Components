package com.material.components.activity.search;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.material.components.R;
import com.material.components.utils.Tools;

public class SearchOutletYellow extends AppCompatActivity {

    private ProgressBar progress_bar;
    private EditText et_search;
    private View lyt_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_outlet_yellow);

        initToolbar();
        initComponent();
    }

    private void initToolbar() {
        Tools.setSystemBarColor(this, R.color.yellow_600);
        Tools.setSystemBarLight(this);
    }

    private void initComponent() {
        lyt_content = findViewById(R.id.lyt_content);
        et_search = findViewById(R.id.et_search);
        progress_bar = findViewById(R.id.progress_bar);
        progress_bar.setVisibility(View.GONE);

        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    hideKeyboard();
                    searchAction();
                    return true;
                }
                return false;
            }
        });

    }

    public void outletTypeClick(View view) {
        if (view instanceof Button) {
            Button b = (Button) view;
            b.setSelected(!b.isSelected());
        }
    }

    private void searchAction() {
        progress_bar.setVisibility(View.VISIBLE);
        lyt_content.setVisibility(View.INVISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progress_bar.setVisibility(View.GONE);
                lyt_content.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(), "Search Submit", Toast.LENGTH_SHORT).show();
            }
        }, 1000);
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

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
