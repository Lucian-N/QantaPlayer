package com.example.lucian_pc.qantaplayer;


import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FavoritesActivity extends AppCompatActivity {

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

        Collections.sort(songs, new Comparator<Song>() {
            public int compare(Song a, Song b) {
                return a.getSongName().compareTo(b.getSongName());
            }
        });

        // Return to main menu functionality
        TextView menu = findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                Intent favsIntent = new Intent(FavoritesActivity.this, MainActivity.class);
                startActivity(favsIntent);
            }
        });
    }

    public void getTracks() {

        ContentResolver songResolver = getContentResolver();
        Uri songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor songCursor = songResolver.query(songUri, null, null, null, null);

        // check for null references
        if (songCursor != null && songCursor.moveToFirst()) {
            //get columns
            int nameColumn = songCursor.getColumnIndex
                    (MediaStore.Audio.Media.TITLE);
            int artistColumn = songCursor.getColumnIndex
                    (MediaStore.Audio.Media.ARTIST);
            //add songs to list
            do {
                String thisTitle = songCursor.getString(nameColumn);
                String thisArtist = songCursor.getString(artistColumn);
                songs.add(new Song(thisTitle, thisArtist));
            }
            while (songCursor.moveToNext());
        }
    }

    //TODO Favorite songs implementation
}