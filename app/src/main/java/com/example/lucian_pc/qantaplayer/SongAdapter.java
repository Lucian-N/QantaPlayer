package com.example.lucian_pc.qantaplayer;

/**
 * Custom adapter code to view songs on a mobile device
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SongAdapter extends ArrayAdapter<Song> {

    public SongAdapter(Activity context, ArrayList<Song> qantaPlayer) {
        super(context, 0, qantaPlayer);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View songView = convertView;
        if (songView == null) {
            songView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        // Get the object located at this position in the list
        Song currentNumber = getItem(position);

        /* Find the TextView in the list_item.xml layout with the ID version_name
        * and set equal to defined variable */
        TextView artistTextView = songView.findViewById(R.id.artistName);
        artistTextView.setText(currentNumber.getArtistName());
        TextView songTextView = songView.findViewById(R.id.songName);
        songTextView.setText(currentNumber.getSongName());

        // Return the whole list item layout
        return songView;
    }
}
