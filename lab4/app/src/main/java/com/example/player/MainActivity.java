package com.example.player;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //open audio player
        Button audioButt = findViewById(R.id.audioButton);
        audioButt.setOnClickListener(view -> {
            startActivity(new Intent(this, AudioActivity.class));
        });

        //open video player
        Button videoButt = findViewById(R.id.videoButton);
        videoButt.setOnClickListener(view -> {
            startActivity(new Intent(this, VideoActivity.class));
        });

        //open internet video player
        Button internetVideoButt = findViewById(R.id.internetVideoButton);
        internetVideoButt.setOnClickListener(view -> {
            startActivity(new Intent(this, InternetVideoActivity.class));
        });
    }
}
