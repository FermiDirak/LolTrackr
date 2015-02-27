package manuele.bryan.lolwinrate.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Window;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import manuele.bryan.lolwinrate.Databases.DataBaseIO;
import manuele.bryan.lolwinrate.Databases.PreferencesDataBase;
import manuele.bryan.lolwinrate.LolStatistics.StatisticsChampion;
import manuele.bryan.lolwinrate.LolStatistics.LeagueScrapper;
import manuele.bryan.lolwinrate.R;


public class SplashScreenActivity extends ActionBarActivity {
    final String PREFS = "prefs";

    TextView leagueStatisticsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_splash_screen);

        leagueStatisticsTextView = (TextView) findViewById(R.id.leagueStatisticsTextView);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/robotolight.ttf");
        leagueStatisticsTextView.setTypeface(typeface);

        SharedPreferences settings = getSharedPreferences(PREFS, 0);
        if (settings.getBoolean("my_first_time", true)) {
            //the app is being launched for first time
            PreferencesDataBase preferences = new PreferencesDataBase(getBaseContext());
            preferences.createDefaultSettings();

            settings.edit().putBoolean("my_first_time", false).apply();
        }

        new DownloadDataTask().execute("");

    }

    private class DownloadDataTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {

            LeagueScrapper leagueScrapper = new LeagueScrapper(getBaseContext());
            List<StatisticsChampion> statisticsChampions = null;
            try {
                statisticsChampions = leagueScrapper.createDataTable();
            } catch (IOException e) {
                //no internet connection!!
            }

            //TODO: no connectivity case
            if (statisticsChampions == null) {

            }

            DataBaseIO dataBaseIO = new DataBaseIO(getBaseContext());
            dataBaseIO.addChampions(statisticsChampions);

            return "";
        }

        @Override
        protected void onPostExecute(String string) {
            super.onPostExecute(string);

            Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
            startActivity(intent);

        }
    }

}
