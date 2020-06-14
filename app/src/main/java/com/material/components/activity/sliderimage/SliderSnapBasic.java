package com.material.components.activity.sliderimage;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.material.components.R;
import com.material.components.adapter.AdapterSnapGeneric;
import com.material.components.data.DataGenerator;
import com.material.components.helper.EndSnapHelper;
import com.material.components.helper.StartSnapHelper;
import com.material.components.model.Image;
import com.material.components.utils.Tools;

import java.util.List;


public class SliderSnapBasic extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider_snap_basic);
        initToolbar();
        initComponent();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Snap Basic");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this);
    }

    private void initComponent() {
        RecyclerView recyclerCenter = findViewById(R.id.recyclerCenter);
        RecyclerView recyclerStart = findViewById(R.id.recyclerStart);
        RecyclerView recyclerEnd = findViewById(R.id.recyclerEnd);

        recyclerCenter.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerStart.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        layoutManager.setReverseLayout(true);
        recyclerEnd.setLayoutManager(layoutManager);

        // generate data
        List<Image> items = DataGenerator.getImageDate(this);

        //set data and list adapter
        recyclerCenter.setAdapter(new AdapterSnapGeneric(this, items, R.layout.item_snap_basic));
        new LinearSnapHelper().attachToRecyclerView(recyclerCenter);

        recyclerStart.setAdapter(new AdapterSnapGeneric(this, items, R.layout.item_snap_basic));
        recyclerStart.setOnFlingListener(null);
        new StartSnapHelper().attachToRecyclerView(recyclerStart);

        recyclerEnd.setAdapter(new AdapterSnapGeneric(this, items, R.layout.item_snap_basic));
        recyclerEnd.setOnFlingListener(null);
        new EndSnapHelper().attachToRecyclerView(recyclerEnd);
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
}
