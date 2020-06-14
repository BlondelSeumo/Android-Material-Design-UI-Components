package com.material.components.activity.sliderimage;

import android.graphics.PorterDuff;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.material.components.R;
import com.material.components.adapter.AdapterSnapGeneric;
import com.material.components.data.DataGenerator;
import com.material.components.helper.StartSnapHelper;
import com.material.components.model.Image;
import com.material.components.utils.Tools;

import java.util.List;
public class SliderSnapFull extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider_snap_full);
        initToolbar();
        initComponent();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Snap Full");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this);
    }

    private void initComponent() {

        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress);
        progressBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        RecyclerView recyclerViewRated = findViewById(R.id.recyclerViewRated);
        recyclerViewRated.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // generate data
        List<Image> items = DataGenerator.getImageDate(this).subList(0, 5);
        final List<Image> items2 = DataGenerator.getImageDate(this).subList(0, 5);

        recyclerView.setAdapter(new AdapterSnapGeneric(this, items, R.layout.item_snap_full));
        recyclerView.setOnFlingListener(null);

        progressBar.setMax(items.size());
        progressBar.setProgress(1);
        StartSnapHelper startSnapHelper = new StartSnapHelper();
        startSnapHelper.attachToRecyclerView(recyclerView);
        startSnapHelper.setSnapPositionListener(new StartSnapHelper.SnapPositionListener() {
            @Override
            public void position(View view, int position) {
                progressBar.setProgress(position + 1);
            }
        });

        recyclerViewRated.setAdapter(new AdapterSnapGeneric(this, items2, R.layout.item_snap_full));
        recyclerViewRated.setOnFlingListener(null);

        bottomProgressDots(items2.size(), 0);
        StartSnapHelper startSnapHelper2 = new StartSnapHelper();
        startSnapHelper2.attachToRecyclerView(recyclerViewRated);
        startSnapHelper2.setSnapPositionListener(new StartSnapHelper.SnapPositionListener() {
            @Override
            public void position(View view, int position) {
                bottomProgressDots(items2.size(), position--);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_notif_setting, menu);
        return true;
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

    private void bottomProgressDots(int max, int current_index) {
        if (current_index < 0 || current_index > max - 1) return;
        LinearLayout dotsLayout = findViewById(R.id.layoutDots);
        ImageView[] dots = new ImageView[max];

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new ImageView(this);
            int width_height = 12;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(width_height, width_height));
            params.setMargins(5, 5, 5, 5);
            dots[i].setLayoutParams(params);
            dots[i].setImageResource(R.drawable.shape_circle);
            dots[i].setColorFilter(getResources().getColor(R.color.grey_20), PorterDuff.Mode.SRC_IN);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[current_index].setImageResource(R.drawable.shape_circle);
            dots[current_index].setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        }
    }
}
