package com.example.miwok;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.zip.Inflater;


public class WordAdapter extends ArrayAdapter<word> {

    private int mcolorId;

    WordAdapter(Activity context ,ArrayList<word> words, int colorResourceId){
        super(context,0,words);
        mcolorId=colorResourceId;
    }

    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView==null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }
        word currentWord =getItem(position);

        TextView miwokTextView =(TextView) listItemView.findViewById(R.id.miwok_text_view);
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        ImageView imageView =(ImageView) listItemView.findViewById(R.id.image);

        miwokTextView.setText(currentWord.getmMiwokTranslation());
        defaultTextView.setText(currentWord.getDefaultTrasnlation());

        if(currentWord.hasImage()){
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageResource(currentWord.getmImageId());
        }
        else{
            imageView.setVisibility(View.GONE);
        }

        View textColor = listItemView.findViewById(R.id.back_color);
        int color = ContextCompat.getColor(getContext(),mcolorId);
        textColor.setBackgroundColor(color);

        return listItemView;

    }
}
