package com.example.bolasepak;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.provider.ContactsContract;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class EventDetailActivity extends AppCompatActivity {
    private String idEvent;
    private String teamHome;
    private String teamAway;
    private String scoreHome;
    private String scoreAway;
    private String matchDate;
    private String matchTime;
    private String matchLocation;
    private String matchWeather;
    private String goalHome;
    private String goalAway;
    private String logoHome;
    private String logoAway;
    private String idTeamHome;
    private String idTeamAway;
    private ArrayList<String> arrayHome;
    private ArrayList<String> arrayAway;
    Database db ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
/*
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()){
            InsertData("441613");
            db = new Database(this);
        }*/
        GetEventDetails() ;

        ImageView imageViewHomeTeam = (ImageView) findViewById(R.id.home_logo);
        TextView textViewHomeTeam = (TextView) findViewById(R.id.home_team);
        TextView textViewScoreHome = (TextView) findViewById(R.id.score_home);
        TextView textViewScoreAway = (TextView) findViewById(R.id.score_away);
        TextView textViewMatchDate = (TextView) findViewById(R.id.match_date);
        TextView textViewMatchWeather = (TextView) findViewById(R.id.match_weather);
        ImageView imageViewAwayTeam = (ImageView) findViewById(R.id.away_logo);
        TextView textViewAwayTeam = (TextView) findViewById(R.id.away_team);
        RecyclerView recyclerViewHome = (RecyclerView) findViewById(R.id.list_goalhome);
        RecyclerView recyclerViewAway = (RecyclerView) findViewById(R.id.list_goalaway);
        TextView textViewMatchLocation = (TextView) findViewById(R.id.match_location);
        TextView textViewMatchTime = (TextView) findViewById(R.id.match_weather);

        textViewHomeTeam.setText(teamHome) ;
        textViewAwayTeam.setText(teamAway) ;
        textViewMatchDate.setText(matchDate) ;
        textViewMatchWeather.setText(matchWeather) ;
        textViewScoreAway.setText(scoreAway);
        textViewScoreHome.setText(scoreHome);
        textViewMatchLocation.setText(matchLocation);
        textViewMatchTime.setText(matchTime);
        Picasso.get().load(logoHome).into(imageViewHomeTeam);
        Picasso.get().load(logoAway).into(imageViewAwayTeam);
        recyclerViewHome.setHasFixedSize(true) ;
        recyclerViewHome.setLayoutManager(new GridLayoutManager(this,1));
        EventAdapter adapterHome = new EventAdapter(this, arrayHome) ;
        recyclerViewHome.setAdapter(adapterHome) ;

        recyclerViewAway.setHasFixedSize(true) ;
        recyclerViewAway.setLayoutManager(new GridLayoutManager(this,1));
        EventAdapter adapterAway = new EventAdapter(this,arrayAway) ;
        recyclerViewAway.setAdapter(adapterAway) ;
    }

    public void GetEventDetails(){
//        String  idEvent = getIntent().getExtras().getString("id_event") ;
        Database sqLiteManager = new Database(this) ;
        EventDetail event = sqLiteManager.getEvent("441613") ;
        teamHome = event.getTeamHome() ;
        teamAway = event.getTeamAway() ;
        scoreHome = event.getScoreHome();
        scoreAway = event.getScoreAway();
        matchDate = event.getMatchDate();
        matchTime = event.getMatchTime();
        matchLocation = event.getMatchLocation();
        matchWeather = event.getMatchWeather();
        goalHome = event.getGoalHome();
        goalAway = event.getGoalAway();
        logoHome = event.getLogoHome().toString();
        logoAway = event.getLogoAway().toString();
        idTeamHome = event.getIdTeamHome();
        idTeamAway = event.getIdTeamAway();

        if (scoreHome.equals("null")){
            scoreHome = "" ;
        }
        if (scoreAway.equals("null")){
            scoreAway = "" ;
        }
        if (matchLocation.equals("null")){
            matchLocation= "";
        }
        if (matchWeather.equals("null")){
            matchWeather = "";
        }
        arrayHome = new ArrayList<String>();

        if (goalHome.equals("null")){
            goalHome = "" ;
            arrayHome.add("");
        }else{
            String[] splitArray = event.getGoalHome().split(";") ;
            arrayHome.addAll(Arrays.asList(splitArray));
        }
        arrayAway = new ArrayList<String>();

        if (goalAway.equals("null")){
            goalAway = "" ;
            arrayAway.add("");
        }else{
            String[] splitArray = event.getGoalAway().split(";") ;
            arrayAway.addAll(Arrays.asList(splitArray));
        }
        if (matchTime.equals("null")){
            matchTime = "";
        }
    }
/*
    public void InsertData(final String idEvent){
        String url = "https://www.thesportsdb.com/api/v1/json/1/lookupevent.php?id=" + idEvent;

        final RequestQueue requestQueue = Volley.newRequestQueue(this) ;
        final EventDetail events = new EventDetail();
        final String[] idTeamHome = {new String()};
        final String[] idTeamAway = {new String()};
        final String[] teamHome = {new String()};
        final String[] teamAway = {new String()};
        final String[] scoreHome = {new String()};
        final String[] scoreAway = {new String()};
        final String[] matchDate = {new String()};
        final String[] matchTime = {new String()};
        final String[] matchWeather = {new String()};
        final String[] matchLocation = {new String()};
        final String[] goalAway = {new String()};
        final String[] goalHome = {new String()};
        final String[] logoHome = {new String()};
        final String[] logoAway = {new String()};

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonObject = new JSONObject(response) ;
                    JSONArray jsonArray = jsonObject.getJSONArray("events") ;
                    for(int i = 0 ; i < jsonArray.length() ; i ++) {
                        JSONObject event = jsonArray.getJSONObject(i);
                        teamHome[0] = event.getString("strHomeTeam");
                        teamAway[0] = event.getString("strAwayTeam");
                        scoreHome[0] = event.getString("intHomeScore");
                        scoreAway[0] = event.getString("intAwayScore");
                        matchDate[0] = event.getString("dateEvent");
                        matchTime[0] = event.getString("strTime");
                        matchLocation[0] = event.getString("strCity");
                        goalHome[0] = event.getString("strHomeGoalDetails");
                        goalAway[0] = event.getString("strAwayGoalDetails");
                        idTeamHome[0] = event.getString("idHomeTeam");
                        idTeamAway[0] = event.getString("idAwayTeam");
                    }

                    String homeLogoUrl = "https://www.thesportsdb.com/api/v1/json/1/lookupteam.php?id=" + idTeamHome[0];

                    StringRequest stringRequestHome = new StringRequest(Request.Method.GET, homeLogoUrl, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObjectHome = new JSONObject(response);
                                JSONArray jsonArrayHome = jsonObjectHome.getJSONArray("teams");
                                for (int i = 0; i < jsonArrayHome.length(); i++) {
                                    JSONObject TeamHome = jsonArrayHome.getJSONObject(i);
                                    logoHome[0] = TeamHome.getString("strTeamBadge");
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    }) ;
                    requestQueue.add(stringRequestHome) ;

                    String awayLogoUrl = "https://www.thesportsdb.com/api/v1/json/1/lookupteam.php?id=" + idTeamAway[0];

                    StringRequest stringRequestAway = new StringRequest(Request.Method.GET, awayLogoUrl, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObjectAway = new JSONObject(response);
                                JSONArray jsonArrayAway = jsonObjectAway.getJSONArray("teams");
                                for (int i = 0; i < jsonArrayAway.length(); i++) {
                                    JSONObject TeamAway = jsonArrayAway.getJSONObject(i);
                                    logoAway[0] = TeamAway.getString("strTeamBadge");
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    }) ;
                    requestQueue.add(stringRequestAway) ;

                    if (!matchLocation[0].equals("null")) {
                        String apikey = "1ebf95bc86f31ee0cedc299bb072d385";
                        String weatherUrl = "api.openweathermap.org/data/2.5/weather?q=" + matchLocation[0] + "&appid=" + apikey;

                        StringRequest stringRequestW = new StringRequest(Request.Method.GET, weatherUrl, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObjectW = new JSONObject(response);
                                    JSONArray jsonArrayW = jsonObjectW.getJSONArray("coord");
                                    for (int i = 0; i < jsonArrayW.length(); i++) {
                                        JSONObject weatherM = jsonArrayW.getJSONObject(i);
                                        JSONObject matchW = weatherM.getJSONObject("weather");
                                        matchWeather[0] = matchW.getString("main");
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                            }
                        });
                        requestQueue.add(stringRequestW);
                    }
                    events.setTeamHome(teamHome[0]);
                    events.setMatchLocation(matchLocation[0]);
                    events.setMatchTime(matchTime[0]);
                    events.setMatchLocation(matchLocation[0]);
                    events.setMatchDate(matchDate[0]);
                    events.setGoalAway(goalAway[0]);
                    events.setGoalHome(goalHome[0]);
                    events.setScoreAway(scoreAway[0]);
                    events.setScoreHome(scoreHome[0]);
                    events.setTeamAway(teamAway[0]);
                    events.setIdTeamAway(idTeamAway[0]);
                    events.setIdTeamHome(idTeamHome[0]);
                    events.setMatchWeather(matchWeather[0]);
                    events.setIdEvent(idEvent);
                    events.setLogoHome(logoHome[0]);
                    events.setLogoAway(logoAway[0]);

                    db.addEvent(events);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) ;

        requestQueue.add(stringRequest) ;
    }*/


}
