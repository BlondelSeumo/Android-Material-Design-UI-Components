package com.material.components.activity.sliderimage;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.material.components.R;
import com.material.components.adapter.AdapterSnapGeneric;
import com.material.components.data.DataGenerator;
import com.material.components.helper.StartSnapHelper;
import com.material.components.model.Image;
import com.material.components.utils.Tools;

import java.util.List;

public class SliderSnapNews extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider_snap_news);
        Tools.setSystemBarColor(this);
        initComponent();
    }

    private void initComponent() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // generate data
        List<Image> items = DataGenerator.getImageDate(this).subList(0, 8);

        recyclerView.setAdapter(new AdapterSnapGeneric(this, items, R.layout.item_snap_news));
        recyclerView.setOnFlingListener(null);
        new StartSnapHelper().attachToRecyclerView(recyclerView);

        (findViewById(R.id.bt_menu)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
