package com.forgamers.mobile.accelerator.game;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SupportGameList extends ArrayAdapter<Game>  implements Filterable {
    private Activity context;

    ArrayList<Game> games;

    public SupportGameList(Activity context, ArrayList<Game> games) {
        super(context, R.layout.row_item, games);
        this.context = context;
        this.games = games;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        LayoutInflater inflater = context.getLayoutInflater();

        Game innsertGame = getItem(position);

        if(convertView==null)
            row = inflater.inflate(R.layout.row_item, null, true);
        TextView textViewGameName = (TextView) row.findViewById(R.id.textViewGameName);
        TextView textViewCountry = (TextView) row.findViewById(R.id.textViewCountry);
        ImageView imageFlag = (ImageView) row.findViewById(R.id.imageViewFlag);

        try {
            if(innsertGame != null)
            {
                textViewGameName.setText(innsertGame.getGameName());
                textViewCountry.setText(innsertGame.getCountryName());
                imageFlag.setImageResource(innsertGame.getImageid());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return  row;
    }
}
