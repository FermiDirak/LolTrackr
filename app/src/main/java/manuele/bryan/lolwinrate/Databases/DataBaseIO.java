package manuele.bryan.lolwinrate.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import manuele.bryan.lolwinrate.LolStatistics.StatisticsChampion;

public class DataBaseIO {
    Context context;

    public DataBaseIO(Context context) {
        this.context = context;
    }

    //______________________CHAMPIONS__________________________

    public List<StatisticsChampion> getChampions() {
        List<StatisticsChampion> statisticsChampions = new ArrayList<>();

        DataBase dataBase = new DataBase(context);
        SQLiteDatabase qdb = dataBase.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + DataBase.KEY_TABLE_CHAMPIONS;
        Cursor cursor = qdb.rawQuery(selectQuery, null);

        if (cursor != null) {
            cursor.moveToFirst();

            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToPosition(i);
                String champName = cursor.getString(
                        cursor.getColumnIndex(DataBase.KEY_CHAMPNAME));

                int wins = Integer.parseInt(
                        cursor.getString(cursor.getColumnIndex(DataBase.KEY_CHAMPWINS)));
                int losses = Integer.parseInt(
                        cursor.getString(cursor.getColumnIndex(DataBase.KEY_CHAMPLOSSES)));
                int matches = Integer.parseInt(
                        cursor.getString(cursor.getColumnIndex(DataBase.KEY_CHAMPMATCHES)));

                StatisticsChampion champ = new StatisticsChampion(champName,wins, losses, matches);
                statisticsChampions.add(champ);
            }

            cursor.close();
        }

        return statisticsChampions;
    }

    public void addChampions(List<StatisticsChampion> statisticsChampionList) {
        clearChampions();

        for (StatisticsChampion champ : statisticsChampionList) {
            addChampion(champ);
        }
    }

    public void addChampion(StatisticsChampion champ) {
        DataBase database = new DataBase(context);
        SQLiteDatabase qdb = database.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DataBase.KEY_CHAMPNAME, champ.champName);
        values.put(DataBase.KEY_CHAMPWINS, champ.wins);
        values.put(DataBase.KEY_CHAMPLOSSES, champ.losses);
        values.put(DataBase.KEY_CHAMPMATCHES, champ.matches);

        qdb.insert(DataBase.KEY_TABLE_CHAMPIONS, null, values);
    }

    public void clearChampions() {
        DataBase dataBase = new DataBase(context);
        SQLiteDatabase qdb = dataBase.getWritableDatabase();

        qdb.delete(DataBase.KEY_TABLE_CHAMPIONS, null, null);
    }

}
