package com.material.components.activity.player;

import android.os.CountDownTimer;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.material.components.R;
import com.material.components.utils.MusicUtils;
import com.material.components.utils.Tools;

public class PlayerVideoSimple extends AppCompatActivity {
    private ImageButton bt_play;
    private ImageView image;
    private TextView tv_duration;
    private View lyt_progress;
    private View lyt_volume;
    private AppCompatSeekBar seek_bar;

    private CountDownTimer countDownTimer;
    long millisInFuture = 30000; //30 seconds
    private MusicUtils musicUtils;

    private boolean state_play = false;
    private boolean show_action = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_video_simple);
        initToolbar();
        initComponent();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this);
    }


    private void initComponent() {
        musicUtils = new MusicUtils();
        bt_play = (ImageButton) findViewById(R.id.bt_play);
        image = (ImageView) findViewById(R.id.image);
        lyt_progress = (View) findViewById(R.id.lyt_progress);
        lyt_volume = (View) findViewById(R.id.lyt_volume);
        tv_duration = (TextView) findViewById(R.id.tv_duration);
        seek_bar = (AppCompatSeekBar) findViewById(R.id.seek_bar);

        seek_bar.setMax(30);

        bt_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleButtonPlay();
            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleActionView();
            }
        });
    }

    private void toggleActionView() {
        show_action = !show_action;
        if (show_action) {
            bt_play.setVisibility(View.VISIBLE);
            lyt_progress.setVisibility(View.VISIBLE);
            lyt_volume.setVisibility(View.VISIBLE);
        } else {
            bt_play.setVisibility(View.INVISIBLE);
            lyt_progress.setVisibility(View.INVISIBLE);
            lyt_volume.setVisibility(View.INVISIBLE);
        }
    }

    private void toggleButtonPlay() {
        state_play = !state_play;
        if (state_play) {
            bt_play.setImageResource(R.drawable.ic_pause);
            runCountDownTimer();
        } else {
            bt_play.setImageResource(R.drawable.ic_play_arrow);
            if (countDownTimer != null) countDownTimer.cancel();
        }
    }

    private void runCountDownTimer() {
        if (countDownTimer != null) countDownTimer.cancel();
        countDownTimer = new CountDownTimer(millisInFuture, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                millisInFuture = millisUntilFinished;
                tv_duration.setText(musicUtils.milliSecondsToTimer(millisUntilFinished));
                Long progress = (30000 - millisUntilFinished) / 1000;
                seek_bar.setProgress(progress.intValue());
            }

            @Override
            public void onFinish() {
                bt_play.setImageResource(R.drawable.ic_play_arrow);
                state_play = false;
                millisInFuture = 30000;
                seek_bar.setProgress(0);
            }
        }.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_basic, menu);
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

    @Override
    protected void onPause() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        super.onPause();
    }
}
