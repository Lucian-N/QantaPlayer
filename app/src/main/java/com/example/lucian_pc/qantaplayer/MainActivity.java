package com.example.lucian_pc.qantaplayer;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

        // Implement button functionality
        Button songs = findViewById(R.id.libraries);
        Button favorites = findViewById(R.id.favorites);
        songs.setOnClickListener(this);
        favorites.setOnClickListener(this);

        //TODO Options
        //TODO Now playing functionality
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.libraries:
                Intent libraryIntent = new Intent(this, PlayerActivity.class);
                startActivity(libraryIntent);
                break;
            case R.id.favorites:
                Intent favoriteIntent = new Intent(this, FavoritesActivity.class);
                startActivity(favoriteIntent);
                break;
            default:
                break;
        }
    }
}