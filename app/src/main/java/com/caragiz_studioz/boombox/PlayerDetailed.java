package com.caragiz_studioz.boombox;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.caragiz_studioz.boombox.helper.UIUpdateHelper;

/**
 * Created by caragiz on 01-09-2016.
 */
public class PlayerDetailed extends Activity {

    RelativeLayout relativeLayout;
    TextView trackName;
    TextView albumName;
    TextView artistName;
    ImageButton playButton;
    SeekBar seekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.player_detailed);

        relativeLayout = (RelativeLayout) findViewById(R.id.detailedPlayerLayout);
        trackName = (TextView) findViewById(R.id.trackName);
        albumName = (TextView) findViewById(R.id.albumName);
        artistName = (TextView) findViewById(R.id.artistName);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        UIUpdateHelper.addUpdateListenerFor(relativeLayout, trackName, albumName, artistName, seekBar);
    }
}
