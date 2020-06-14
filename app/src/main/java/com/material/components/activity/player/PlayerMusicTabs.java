package com.material.components.activity.player;

import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.material.components.R;
import com.material.components.adapter.AdapterListMusicSong;
import com.material.components.fragment.FragmentMusicAlbum;
import com.material.components.fragment.FragmentMusicSong;
import com.material.components.utils.MusicUtils;
import com.material.components.utils.Tools;

import java.util.ArrayList;
import java.util.List;

public class PlayerMusicTabs extends AppCompatActivity {

    public View parent_view;

    private ViewPager view_pager;
    private TabLayout tab_layout;

    private ImageButton bt_play;
    private ProgressBar song_progressbar;
    private AdapterListMusicSong mAdapter;

    // Media Player
    private MediaPlayer mp;
    // Handler to update UI timer, progress bar etc,.
    private Handler mHandler = new Handler();

    //private SongsManager songManager;
    private MusicUtils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_music_tabs);
        parent_view = findViewById(R.id.parent_view);

        initToolbar();
        initComponent();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Music Player");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this);
    }

    private void initComponent() {
        view_pager = (ViewPager) findViewById(R.id.view_pager);
        setupViewPager(view_pager);

        tab_layout = (TabLayout) findViewById(R.id.tab_layout);
        tab_layout.setupWithViewPager(view_pager);

        bt_play = (ImageButton) findViewById(R.id.bt_play);
        song_progressbar = (ProgressBar) findViewById(R.id.song_progressbar);

        // set Progress bar values
        song_progressbar.setProgress(0);
        song_progressbar.setMax(MusicUtils.MAX_PROGRESS);

        // Media Player
        mp = new MediaPlayer();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                // Changing button image to play button
                bt_play.setImageResource(R.drawable.ic_play_arrow);
            }
        });

        try {
            mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
            AssetFileDescriptor afd = getAssets().openFd("short_music.mp3");
            mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            afd.close();
            mp.prepare();
        } catch (Exception e) {
            Snackbar.make(parent_view, "Cannot load audio file", Snackbar.LENGTH_SHORT).show();
        }

        utils = new MusicUtils();

        buttonPlayerAction();
    }

    /**
     * Play button click event plays a song and changes button to pause image
     * pauses a song and changes button to play image
     */
    private void buttonPlayerAction() {
        bt_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // check for already playing
                if (mp.isPlaying()) {
                    mp.pause();
                    // Changing button image to play button
                    bt_play.setImageResource(R.drawable.ic_play_arrow);
                } else {
                    // Resume song
                    mp.start();
                    // Changing button image to pause button
                    bt_play.setImageResource(R.drawable.ic_pause);
                    mHandler.post(mUpdateTimeTask);
                }

            }
        });
    }

    public void controlClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.bt_expand: {
                Snackbar.make(parent_view, "Expand", Snackbar.LENGTH_SHORT).show();
                break;
            }
        }
    }

    /**
     * Background Runnable thread
     */
    private Runnable mUpdateTimeTask = new Runnable() {
        public void run() {
            updateTimerAndSeekbar();
            // Running this thread after 10 milliseconds
            if (mp.isPlaying()) {
                mHandler.postDelayed(this, 100);
            }
        }
    };

    private void updateTimerAndSeekbar() {
        long totalDuration = mp.getDuration();
        long currentDuration = mp.getCurrentPosition();

        // Updating progress bar
        int progress = (int) (utils.getProgressSeekBar(currentDuration, totalDuration));
        song_progressbar.setProgress(progress);
    }

    // stop player when destroy
    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(mUpdateTimeTask);
        mp.release();
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(FragmentMusicSong.newInstance(), "SONGS");
        adapter.addFragment(FragmentMusicAlbum.newInstance(), "ALBUMS");
        adapter.addFragment(FragmentMusicAlbum.newInstance(), "ARTISTS");
        adapter.addFragment(FragmentMusicSong.newInstance(), "PLAYLIST");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_setting, menu);
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

    private class SectionsPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public SectionsPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}

