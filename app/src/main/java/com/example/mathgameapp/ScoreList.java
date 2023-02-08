package com.example.mathgameapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ScoreList extends ArrayAdapter<Score> {

    private Activity context;
    List<Score> scores;

    public ScoreList(Activity context, List<Score> scores) {
        super(context, R.layout.layout_score_list, scores);
        this.context = context;
        this.scores = scores;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_score_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewDegree = (TextView) listViewItem.findViewById(R.id.textViewDegree);

        Score score = scores.get(position);

        textViewName.setText(" Operation: " + score.getOperation());
        textViewDegree.setText(" Degree: "+ score.getDegree());

        return listViewItem;
    }
}
