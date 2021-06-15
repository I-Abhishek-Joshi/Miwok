package com.example.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager ;

    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if((focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) && mMediaPlayer != null){
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);
                    }
                    else if(focusChange == AudioManager.AUDIOFOCUS_GAIN){
                        mMediaPlayer.start();
                    }
                    else if(focusChange == AudioManager.AUDIOFOCUS_LOSS){
                        releaseMediaPlayer();
                    }
                }
            };

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        final ArrayList<word> words = new ArrayList<word>();

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        words.add(new word("One" , "Lutti",R.drawable.number_one, R.raw.number_one));
        words.add(new word("Two","Otiiko",R.drawable.number_two,R.raw.number_two));
        words.add(new word("Three","Tolookuso",R.drawable.number_three,R.raw.number_three));
        words.add(new word("Four","Oyyisa",R.drawable.number_four,R.raw.number_four));
        words.add(new word("Five","Massokka",R.drawable.number_five,R.raw.number_five));
        words.add(new word("Six","Temmokka",R.drawable.number_six,R.raw.number_six));
        words.add(new word("Seven","Kenekaku",R.drawable.number_seven,R.raw.number_seven));
        words.add(new word("Eight","Kawinta",R.drawable.number_eight,R.raw.number_eight));
        words.add(new word("Nine","wo'e",R.drawable.number_nine,R.raw.number_nine));
        words.add(new word("Ten","na'aacha",R.drawable.number_ten,R.raw.number_ten));

        ListView listview = (ListView) findViewById(R.id.list);
        WordAdapter adapter = new WordAdapter(this,words,R.color.category_numbers);
        listview.setAdapter(adapter);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                releaseMediaPlayer();
                word myword = words.get(i);

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, mAudioManager.STREAM_MUSIC, mAudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    mMediaPlayer = MediaPlayer.create(NumbersActivity.this, myword.getmAudioResourceId());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            releaseMediaPlayer();
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer(){
        if(mMediaPlayer !=null){
            mMediaPlayer.release();
            mMediaPlayer=null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}

