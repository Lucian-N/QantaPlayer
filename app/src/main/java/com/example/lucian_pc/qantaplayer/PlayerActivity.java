package com.example.lucian_pc.qantaplayer;


import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PlayerActivity extends AppCompatActivity {

    private ArrayList<Song> songs;
    private ListView songView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_list);

        // define arraylist, adapter and views
        ArrayList<Song> songs = new ArrayList<>();
        SongAdapter songsAdapter = new SongAdapter(this, songs);
        ListView songView = findViewById(R.id.list);
        songView.setAdapter(songsAdapter);

        getTracks();

        Collections.sort(songs, new Comparator<Song>(){
            public int compare(Song a, Song b){
                return a.getSongName().compareTo(b.getSongName());
            }
        });
    }

    public void getTracks() {

        ContentResolver songResolver = getContentResolver();
        Uri songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor songCursor = songResolver.query(songUri, null, null, null, null);

        // check for null references
        if(songCursor!=null && songCursor.moveToFirst()){
            //get columns
            int nameColumn = songCursor.getColumnIndex
                    (MediaStore.Audio.Media.TITLE);
            int trackColumn = songCursor.getColumnIndex
                    (MediaStore.Audio.Media._ID);
            int artistColumn = songCursor.getColumnIndex
                    (MediaStore.Audio.Media.ARTIST);
            //add songs to list
            do {
                long thisTrack = songCursor.getLong(trackColumn);
                String thisTitle = songCursor.getString(nameColumn);
                String thisArtist = songCursor.getString(artistColumn);

                songs.add(new Song(thisTrack, thisTitle, thisArtist));
            }
            while (songCursor.moveToNext());
        }
    }
}