package com.example.dktomaszenko.reproductordeaudio;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button play;
    SoundPool sp;
    int sonido_de_reproduccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play = (Button)findViewById(R.id.btn1);
        sp = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);

        sonido_de_reproduccion = sp.load(this, R.raw.short_sound, 1);
    }

    public void AudioSoundPool(View view){
        sp.play(sonido_de_reproduccion, 1, 1, 1, 0, 0);
    }

    public void AudioMediaPlayer(View view){
        MediaPlayer mp = MediaPlayer.create(this, R.raw.long_sound);
        mp.start();
    }
}
