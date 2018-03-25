package com.example.lucian_pc.qantaplayer;

/**
 * Define custom object Song
 */

public class Song {

    // strings used to store song information
    private long mTrack;
    private String mSongName;
    private String mArtistName;

    // Create new Song object
    public Song(long trackNum ,String songName, String artistName) {
        mTrack = trackNum;
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
    public long getTrackNum() { return mTrack; }


}
