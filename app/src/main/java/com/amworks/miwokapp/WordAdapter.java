package com.amworks.miwokapp;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    public WordAdapter(@NonNull Context context, ArrayList<Word> al) {
        super(context,0,al);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView=convertView;

        if(listItemView==null)
        {
            listItemView=LayoutInflater.from(getContext()).inflate(R.layout.view_layout,parent,false);
        }

        Word word=getItem(position);

        TextView textView1=listItemView.findViewById(R.id.miwok);
        textView1.setText(word.miwok);

        TextView textView2=listItemView.findViewById(R.id.english);
        textView2.setText(word.english);

        ImageView imageView=listItemView.findViewById(R.id.photo);
        imageView.setImageResource(word.resID);

        //can have value= VISIBLE/INVISIBLE/GONE
        imageView.setVisibility(View.VISIBLE);

        return listItemView;

    }

}
