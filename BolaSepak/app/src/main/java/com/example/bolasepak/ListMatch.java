package com.example.bolasepak;

import android.content.Context;
import android.util.EventLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListMatch extends BaseAdapter implements Filterable {

   private ArrayList<EventDetail> arrayPertandingan, temp;
   private Context context;
   CustomFilter customFilter;

   ListMatch(MainActivity context, ArrayList<EventDetail> arrayPertandingan) {
        this.context=context;
        this.arrayPertandingan = arrayPertandingan;
        this.temp = arrayPertandingan;
    }

    @Override
    public int getCount() {
        return arrayPertandingan.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayPertandingan.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
       LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       View row = layoutInflater.inflate(R.layout.match, null);

       TextView nameTeamA = row.findViewById(R.id.nameTeamA);
       TextView nameTeamB = row.findViewById(R.id.nameTeamB);
       TextView location = row.findViewById(R.id.location);
       TextView date = row.findViewById(R.id.date);
       TextView time = row.findViewById(R.id.time);
       ImageView imgTeamA = row.findViewById(R.id.imgteamA);
       ImageView imgTeamB = row.findViewById(R.id.imgteamB);
       TextView cuaca = row.findViewById(R.id.cuaca);

       nameTeamA.setText(arrayPertandingan.get(position).getTeamHome());
       nameTeamB.setText(arrayPertandingan.get(position).getTeamAway());
       location.setText(arrayPertandingan.get(position).getMatchLocation());
       date.setText(arrayPertandingan.get(position).getMatchDate());
       time.setText(arrayPertandingan.get(position).getMatchTime());
       imgTeamA.setImageDrawable(arrayPertandingan.get(position).getLogoHome());
       imgTeamB.setImageDrawable(arrayPertandingan.get(position).getLogoAway());
       cuaca.setText(arrayPertandingan.get(position).getMatchWeather());
        return row;
    }

    @Override
    public Filter getFilter() {
        if(customFilter==null){
            customFilter = new CustomFilter();
        }
        return customFilter;
    }

    class CustomFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if(constraint!=null && constraint.length()>0) {
                constraint = constraint.toString().toUpperCase();

                ArrayList<EventDetail> filters = new ArrayList<>();
                for (int i = 0; i < temp.size(); i++) {
                    if (temp.get(i).getTeamHome().toUpperCase().contains(constraint) || temp.get(i).getTeamAway().toUpperCase().contains(constraint)) {
                        EventDetail pertandingan = new EventDetail(temp.get(i).getIdEvent(), temp.get(i).getTeamHome(), temp.get(i).getTeamAway(),
                                temp.get(i).getScoreHome(), temp.get(i).getScoreAway(), temp.get(i).getMatchDate(), temp.get(i).getMatchTime(), temp.get(i).getMatchLocation(),
                                temp.get(i).getMatchWeather(), temp.get(i).getGoalHome(), temp.get(i).getGoalAway(), temp.get(i).getLogoHome(), temp.get(i).getLogoAway(),
                                temp.get(i).getIdTeamHome(), temp.get(i).getIdTeamAway());
                        filters.add(pertandingan);
                    }
                }
                results.count = filters.size();
                results.values = filters;
            }
            else {
                results.count = temp.size();
                results.values  = temp;
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            arrayPertandingan = (ArrayList<EventDetail>)results.values;
            notifyDataSetChanged();
        }
    }
}
