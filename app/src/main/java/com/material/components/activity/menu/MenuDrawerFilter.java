package com.material.components.activity.menu;

import android.os.Bundle;
import android.os.Handler;
import com.google.android.material.snackbar.Snackbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.material.components.R;
import com.material.components.adapter.AdapterListNews;
import com.material.components.data.DataGenerator;
import com.material.components.model.News;
import com.material.components.utils.Tools;

import java.util.List;

public class MenuDrawerFilter extends AppCompatActivity {

    private View parent_view;

    private RecyclerView recyclerView;
    private AdapterListNews mAdapter;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_drawer_filter);
        parent_view = findViewById(android.R.id.content);

        initToolbar();
        initComponent();
    }

    private void initToolbar() {
        Tools.setSystemBarColor(this, R.color.red_600);
    }

    private void initComponent() {
        drawer = findViewById(R.id.drawer_layout);

        // open drawer at start
        drawer.openDrawer(GravityCompat.END);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        List<News> items = DataGenerator.getNewsData(this, 15);

        //set data and list adapter
        mAdapter = new AdapterListNews(this, items, R.layout.item_news_horizontal);
        recyclerView.setAdapter(mAdapter);

        // on item list clicked
        mAdapter.setOnItemClickListener(new AdapterListNews.OnItemClickListener() {
            @Override
            public void onItemClick(View view, News obj, int position) {
                Snackbar.make(parent_view, "Item " + obj.title + " clicked", Snackbar.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.btn_apply).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.closeDrawer(GravityCompat.END);
                recyclerView.setVisibility(View.INVISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }, 500);
            }
        });

        findViewById(R.id.btn_close_filter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.closeDrawer(GravityCompat.END);
            }
        });

        findViewById(R.id.bt_filter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.END);
            }
        });

        findViewById(R.id.bt_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}