package com.amworks.miwokapp;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static android.media.AudioManager.AUDIOFOCUS_LOSS;


public class numbersFragment extends Fragment {

    MediaPlayer mediaPlayer;

    AudioManager audioManager;

    AudioManager.OnAudioFocusChangeListener audioFocusChangeListener=new AudioManager.OnAudioFocusChangeListener()
    {
        @Override
        public void onAudioFocusChange(int focusChange) {

            if(focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT||focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            }
            else if(focusChange==AudioManager.AUDIOFOCUS_GAIN)
            {
                mediaPlayer.start();
            }else if(focusChange== AUDIOFOCUS_LOSS)
            {
                releaseMediaPlayer();
            }
        }
    };

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    public numbersFragment() {
        // Required empty public constructor
    }

    MediaPlayer.OnCompletionListener onCompletionListener=new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_numbers, container, false);

        audioManager=(AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> list=new ArrayList<>();
        list.add(new Word("one","lutti",R.drawable.number_one,R.raw.number_one));
        list.add(new Word("two","otiiko",R.drawable.number_two,R.raw.number_two));
        list.add(new Word("three","tolookosu",R.drawable.number_three,R.raw.number_three));
        list.add(new Word("four","oyyisa",R.drawable.number_four,R.raw.number_four));
        list.add(new Word("five","massokka",R.drawable.number_five,R.raw.number_five));
        list.add(new Word("six","temmokka",R.drawable.number_six,R.raw.number_six));
        list.add(new Word("seven","kenekaku",R.drawable.number_seven,R.raw.number_seven));
        list.add(new Word("eight","kawinta",R.drawable.number_eight,R.raw.number_eight));
        list.add(new Word("nine","wo'e",R.drawable.number_nine,R.raw.number_nine));

//        ArrayAdapter<Word> arrayAdapter=new ArrayAdapter<>(this,R.layout.view_layout,list);
//        ListView listView=findViewById(R.id.listView);
//        listView.setAdapter(arrayAdapter);
        WordAdapter arrayAdapter=new WordAdapter(getActivity(),list);
        ListView listView=rootView.findViewById(R.id.listView);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();

                int result = audioManager.requestAudioFocus(audioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    mediaPlayer = MediaPlayer.create(getActivity(), list.get(position).soundID);
                    mediaPlayer.start();

//                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                    @Override
//                    public void onCompletion(MediaPlayer mp) {
//                        releaseMediaPlayer();
//                    }
//                });

//                INSTEAD USING GLOBAL VARIABLE OF MEDIAPLAYER.ONCOMPLETIONlISTENER
                    mediaPlayer.setOnCompletionListener(onCompletionListener);

                }
                Word word=list.get(position); //for getting logs ; implemented in Word class
                Log.v("aaaa","currentWord:"+word);
            }
        });

        return rootView;
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;
            audioManager.abandonAudioFocus(audioFocusChangeListener);
        }
    }

}