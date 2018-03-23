package com.example.lucian_pc.qantaplayer;

/**
 * Define custom object Song
 */

public class Song {

    // strings used to store song information
    private String mSongName;
    private String mArtistName;

    // Create new Song object
    public Song(String songName, String artistName) {
        mSongName = songName;
        mArtistName = artistName;
    }

    // Get song, artist as strings
    public String getSongName() {
        return mSongName;
    }
    public String getArtistName() {
        return mArtistName;
    }
}
