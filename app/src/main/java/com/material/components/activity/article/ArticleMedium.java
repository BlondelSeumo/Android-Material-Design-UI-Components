package com.material.components.activity.article;

import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.core.widget.NestedScrollView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.material.components.R;
import com.material.components.utils.Tools;
import com.material.components.utils.ViewAnimation;

public class ArticleMedium extends AppCompatActivity {

    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_medium);
        iniComponent();
        initToolbar();
    }

    boolean hide = false;

    private void iniComponent() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        ((NestedScrollView) findViewById(R.id.nested_scroll_view)).setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY >= oldScrollY) { // down
                    if (hide) return;
                    ViewAnimation.hideFab(fab);
                    hide = true;
                } else {
                    if (!hide) return;
                    ViewAnimation.showFab(fab);
                    hide = false;
                }
            }
        });
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this, R.color.grey_5);
        Tools.setSystemBarLight(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_article_share_save, menu);
        Tools.changeMenuIconColor(menu, getResources().getColor(R.color.grey_60));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle() + " clicked", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
