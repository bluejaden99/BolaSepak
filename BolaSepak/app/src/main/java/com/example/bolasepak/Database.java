package com.example.bolasepak;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;

public class Database extends SQLiteOpenHelper
{
    public Database(Context context){
        super(context,"event_database",null,1) ;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS match_events" +
                "(" +
                "idevent TEXT" + " , " +
                "idhome TEXT" + " , " +
                "idAway TEXT" + " , " +
                "hometeam TEXT" + " , " +
                "awayteam TEXT" + " , " +
                "homescore TEXT" + " , " +
                "awayscore TEXT" + " , " +
                "logohome TEXT" + " , " +
                "logoaway TEXT" + " , " +
                "goalhome TEXT" + " , " +
                "goalaway TEXT" + " , " +
                "matchdate TEXT" + " , " +
                "matchtime TEXT" + " , " +
                "matchweather TEXT"+ " , "+
                "matchlocation TEXT"+
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE events") ;
        onCreate(db) ;
    }
    public void addEvent(EventDetail event){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase() ;
        ContentValues contentValues = new ContentValues() ;
        contentValues.put("idevent",event.getIdEvent()) ;
        contentValues.put("idhome",event.getIdTeamHome()) ;
        contentValues.put("idaway",event.getIdTeamAway()) ;
        contentValues.put("hometeam",event.getTeamHome()) ;
        contentValues.put("awayteam",event.getTeamAway()) ;
        contentValues.put("homescore",event.getScoreHome()) ;
        contentValues.put("awayscore",event.getScoreAway()) ;
        contentValues.put("goalhome",event.getGoalHome()) ;
        contentValues.put("logohome", event.getLogoHome().toString());
        contentValues.put("logoaway", event.getLogoAway().toString());
        contentValues.put("goalaway",event.getGoalAway()) ;
        contentValues.put("matchdate",event.getMatchDate()) ;
        contentValues.put("matchtime",event.getMatchTime()) ;
        contentValues.put("matchweather", event.getMatchWeather());
        contentValues.put("matchlocation", event.getMatchLocation());

        sqLiteDatabase.insert("match_events", "nullable", contentValues) ;
        sqLiteDatabase.close();
    }

    public EventDetail getEvent(String idEvent){
        EventDetail events = new EventDetail() ;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase() ;
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM match_events WHERE idevent = " + idEvent,null) ;

        if (cursor.moveToFirst()){
            do{
                EventDetail event = new EventDetail() ;
                event.setIdEvent(cursor.getString(0));
                event.setIdTeamHome(cursor.getString(1));
                event.setIdTeamAway(cursor.getString(2));
                event.setTeamHome(cursor.getString(3));
                event.setTeamAway(cursor.getString(4));
                event.setScoreHome(cursor.getString(5));
                event.setScoreAway(cursor.getString(6));
                event.setGoalHome(cursor.getString(7));
                event.setGoalAway(cursor.getString(10));
                event.setMatchDate(cursor.getString(11));
                event.setMatchTime(cursor.getString(12));
                event.setMatchWeather(cursor.getString(13));
                event.setMatchLocation(cursor.getString(14));

                events = event ;

            }while (cursor.moveToNext()) ;
        }
        return events ;
    }

    public ArrayList<EventDetail> getAllEvents(){
        ArrayList<EventDetail> events = new ArrayList<EventDetail>();
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase() ;
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM match_events",null) ;

        if (cursor.moveToFirst()){
            do{
                EventDetail event = new EventDetail() ;
                event.setIdEvent(cursor.getString(0));
                event.setIdTeamHome(cursor.getString(1));
                event.setIdTeamAway(cursor.getString(2));
                event.setTeamHome(cursor.getString(3));
                event.setTeamAway(cursor.getString(4));
                event.setScoreHome(cursor.getString(5));
                event.setScoreAway(cursor.getString(6));
                event.setGoalHome(cursor.getString(7));
                event.setGoalAway(cursor.getString(10));
                event.setMatchDate(cursor.getString(11));
                event.setMatchTime(cursor.getString(12));
                event.setMatchWeather(cursor.getString(13));
                event.setMatchLocation(cursor.getString(14));

                events.add(event);

            }while (cursor.moveToNext()) ;
        }
        return events ;
    }

    public ArrayList<EventDetail> searchEvents(CharSequence chars){
        ArrayList<EventDetail> events = new ArrayList<EventDetail>();
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase() ;
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM match_events WHERE hometeam LIKE '%"
                + chars + "%' OR awayteam LIKE '%" + chars + "%'",null) ;

        if (cursor.moveToFirst()){
            do{
                EventDetail event = new EventDetail() ;
                event.setIdEvent(cursor.getString(0));
                event.setIdTeamHome(cursor.getString(1));
                event.setIdTeamAway(cursor.getString(2));
                event.setTeamHome(cursor.getString(3));
                event.setTeamAway(cursor.getString(4));
                event.setScoreHome(cursor.getString(5));
                event.setScoreAway(cursor.getString(6));
                event.setGoalHome(cursor.getString(7));
                event.setGoalAway(cursor.getString(10));
                event.setMatchDate(cursor.getString(11));
                event.setMatchTime(cursor.getString(12));
                event.setMatchWeather(cursor.getString(13));
                event.setMatchLocation(cursor.getString(14));

                events.add(event);

            }while (cursor.moveToNext()) ;
        }
        return events ;
    }

}

