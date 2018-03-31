package com.example.lucian_pc.qantaplayer;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
* Icons made by https://www.flaticon.com/authors/naseer-ahmed
*/

public class PlayerActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<Song> songs;
    private ListView songView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        // define arraylist, adapter and views
        songs = new ArrayList<>();
        SongAdapter songsAdapter = new SongAdapter(this, songs);
        songView = findViewById(R.id.list);
        songView.setAdapter(songsAdapter);

        getTracks();
        Collections.sort(songs, new Comparator<Song>(){
            public int compare(Song a, Song b){
                return a.getSongName().compareTo(b.getSongName());
            }
        });
        // Return to main menu functionality
        Button menu = findViewById(R.id.menu);
        menu.setOnClickListener(this);
    }
    public void getTracks() {

        ContentResolver songResolver = getContentResolver();
        Uri songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor songCursor = songResolver.query(songUri, null, null, null, null);

        // sanity check, loop through media to generate strings for array adapter
        if (songCursor != null && songCursor.moveToFirst()) {

            int songNameColumn = songCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int artistNameColumn = songCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);

            for (songCursor.moveToFirst(); !songCursor.isAfterLast(); songCursor.moveToNext()) {
                String thisTitle = songCursor.getString(songNameColumn);
                String thisArtist = songCursor.getString(artistNameColumn);
                songs.add(new Song(thisTitle, thisArtist));
            }
        }
    }
    //TODO Now playing functionality

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.menu:
                Intent menuIntent = new Intent(this, MainActivity.class);
                startActivity(menuIntent);
                break;
            default:
                break;
        }
    }
}