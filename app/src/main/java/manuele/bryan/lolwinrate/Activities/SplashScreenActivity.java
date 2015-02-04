package manuele.bryan.lolwinrate.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import java.io.IOException;
import java.util.List;

import manuele.bryan.lolwinrate.LolStatistics.Champion;
import manuele.bryan.lolwinrate.Databases.DataBaseIO;
import manuele.bryan.lolwinrate.Databases.PreferencesDataBase;
import manuele.bryan.lolwinrate.LolStatistics.LeagueScrapper;
import manuele.bryan.lolwinrate.R;


public class SplashScreenActivity extends ActionBarActivity {
    final String PREFS = "prefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_splash_screen);

        SharedPreferences settings = getSharedPreferences(PREFS, 0);
        if (settings.getBoolean("my_first_time", true)) {
            //the app is being launched for first time
            PreferencesDataBase preferences = new PreferencesDataBase(getBaseContext());
            preferences.createDefaultSettings();

            settings.edit().putBoolean("my_first_time", false).apply();
        }

        new DownloadDataTask().execute("");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class DownloadDataTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {

            LeagueScrapper leagueScrapper = new LeagueScrapper(getBaseContext());
            List<Champion> champions = null;
            try {
                champions = leagueScrapper.createDataTable();
            } catch (IOException e) {
                //no internet connection!!
            }

            //TODO: no connectivity case
            if (champions == null) {

            }

            DataBaseIO dataBaseIO = new DataBaseIO(getBaseContext());
            dataBaseIO.addChampions(champions);

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
