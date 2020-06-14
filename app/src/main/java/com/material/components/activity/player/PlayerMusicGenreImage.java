package com.material.components.activity.player;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.material.components.R;

public class PlayerMusicGenreImage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_music_genre_image);
    }

    public void genreClick(View view) {
        if (view instanceof Button) {
            Button b = (Button) view;
            b.setSelected(!b.isSelected());
        }
    }
}
