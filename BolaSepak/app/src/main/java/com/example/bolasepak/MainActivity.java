package com.example.bolasepak;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SensorEventListener, TextWatcher {

    TextView tv_steps;
    SensorManager sensorManager;
    boolean running = false;
    ListView lst;
    EditText editText;
   ListMatch listMatch;
   Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lst = findViewById(R.id.lst);
        tv_steps = findViewById(R.id.tv_steps);
        editText = findViewById(R.id.edit);
        editText.addTextChangedListener(this);
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()){
            db = new Database(this);
            new fetchData().execute();
        }else{

        }
        sensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);

    }
    public String getURLWeather(String city){
        try {
            String cuaca;
            String content="";
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q="+ city +",uk&APPID=41d68abe906ec1411c059ae25e166a98");
            HttpURLConnection httpURLConnection = null;
            httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStreamReader inputStream = new InputStreamReader(httpURLConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStream);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content = content + line;
            }
            JSONObject result = new JSONObject(content);
            JSONArray JA = result.getJSONArray("weather");
            JSONObject JO = (JSONObject) JA.get(0);
            cuaca = (String) JO.get("main");
            bufferedReader.close();
            return cuaca;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public String getURLImg(String id){
        try {
            String img;
            String content="";
            URL url = new URL("https://www.thesportsdb.com/api/v1/json/1/lookupteam.php?id="+id);
            HttpURLConnection httpURLConnection = null;
            httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStreamReader inputStream = new InputStreamReader(httpURLConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStream);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content = content + line;
            }
            JSONObject result = new JSONObject(content);
            JSONArray JA = result.getJSONArray("teams");
            JSONObject JO = (JSONObject) JA.get(0);
            img = (String) JO.get("strTeamBadge");
            bufferedReader.close();
            return img;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }
    public class fetchData extends AsyncTask<Void, Void, ArrayList<EventDetail>> {
        String content = "";
        String[] idTeamA = new String[15];
        String[] idTeamB = new String[15];
        String[] nameTeamA = new String[15];
        String[] nameTeamB = new String[15];
        String[] location = new String[15];
        String[] date = new String[15];
        String[] time = new String[15];
        String[] idEvent = new String[15];
        String[] scoreTeamA = new String[15];
        String[] scoreTeamB = new String[15];
        String[] goalTeamA = new String[15];
        String[] goalTeamB = new String[15];
        String[] imgA = new String[15];
        String[] imgB = new String[15];
        Drawable[] logoA = new Drawable[15];
        Drawable[] logoB = new Drawable[15];
        String[] cuaca = new String[15];
        EventDetail pertandingan;
        ArrayList<EventDetail> mylist = new ArrayList<EventDetail>();
        Database db = new Database(getBaseContext());

        @Nullable
        @Override
        protected ArrayList<EventDetail> doInBackground(Void... voids) {
            try {
                URL url = new URL("https://www.thesportsdb.com/api/v1/json/1/eventsnextleague.php?id=4328");
                HttpURLConnection httpURLConnection = null;
                httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStreamReader inputStream = new InputStreamReader(httpURLConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStream);

                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    content = content + line;
                }

                JSONObject result = new JSONObject(content);
                JSONArray JA = result.getJSONArray("events");
                for (int i = 0; i < JA.length(); i++) {
                    JSONObject JO = (JSONObject) JA.get(i);
                    if(JO.get("strHomeTeam")=="null")
                        nameTeamA[i] = "";
                    else nameTeamA[i]=(String) JO.get("strHomeTeam");

                    if(JO.get("strAwayTeam")=="null") nameTeamB[i] = "";
                    else nameTeamB[i] = (String) JO.get("strAwayTeam");


                    if(JSONObject.NULL.equals(JO.get("strCity"))) location[i] = "";
                    else location[i] = (String) JO.get("strCity");

                    if(JO.get("dateEvent")=="null") date[i] = "";
                    else date[i] = (String) JO.get("dateEvent");

                    if(JO.get("strTime")=="null") time[i] = "";
                    else time[i] = (String) JO.get("strTime");

                    idTeamA[i] = (String) JO.get("idHomeTeam");
                    imgA[i] = getURLImg(idTeamA[i]);
                    idTeamB[i] = (String) JO.get("idAwayTeam");
                    imgB[i] = getURLImg(idTeamB[i]);
                    logoA[i] = LoadImageFromWebOperations(imgA[i]);
                    logoB[i] = LoadImageFromWebOperations(imgB[i]);
                    Log.d("data", content);
                    if((JO.getString("strCity")=="null")){
                        cuaca[i] = "";
                    }
                    else{
                        String city = (String) JO.get("strCity");
                        cuaca[i] = getURLWeather(city);
                    }
                    if(JSONObject.NULL.equals(JO.get("idEvent"))) idEvent[i] = "";
                    else idEvent[i] = (String) JO.get("idEvent");

                    if(JSONObject.NULL.equals(JO.get("intHomeScore"))) scoreTeamA[i]="";
                    else scoreTeamA[i] = (String) JO.get("intHomeScore");

                    if(JSONObject.NULL.equals(JO.get("intAwayScore"))) scoreTeamB[i]="";
                    else scoreTeamB[i] = (String) JO.get("intAwayScore");

                    if(JSONObject.NULL.equals(JO.get("strHomeGoalDetails"))) goalTeamA[i] ="";
                    else goalTeamA[i] = (String) JO.get("strHomeGoalDetails");

                    if(JSONObject.NULL.equals(JO.get("strAwayGoalDetails"))) goalTeamB[i] ="";
                    else goalTeamB[i] = (String) JO.get("strAwayGoalDetails");


                    pertandingan = new EventDetail(idEvent[i], nameTeamA[i], nameTeamB[i], scoreTeamA[i],
                            scoreTeamB[i], date[i], time[i], location[i], cuaca[i], goalTeamA[i], goalTeamB[i],
                            logoA[i], logoB[i], idTeamA[i], idTeamB[i]);
                    mylist.add(pertandingan);
                    db.addEvent(pertandingan);
                }
                bufferedReader.close();
                return mylist;

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<EventDetail> pertandingans) {
            if (pertandingans == null) {
                return;
            }
            super.onPostExecute(pertandingans);
            listMatch = new ListMatch(MainActivity.this, pertandingans);

            lst.setAdapter(listMatch);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        running = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(countSensor!=null){
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        }else{
            Toast.makeText(this, "Sensor not found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        running = false;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(running){
            String s = String.valueOf(event.values[0]);
            s.concat("steps today");
            tv_steps.setText(s);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        this.listMatch.getFilter().filter(s);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
