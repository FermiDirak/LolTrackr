package manuele.bryan.lolwinrate.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.IOException;
import java.util.List;

import manuele.bryan.lolwinrate.Databases.DataBaseIO;
import manuele.bryan.lolwinrate.Databases.PreferencesDataBase;
import manuele.bryan.lolwinrate.LolStatistics.LeagueScrapper;
import manuele.bryan.lolwinrate.LolStatistics.StatisticsChampion;
import manuele.bryan.lolwinrate.R;


public class SplashScreenActivity extends ActionBarActivity {
    final String PREFS = "prefs";

    RelativeLayout splashLayout;
    ImageView splashImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_splash_screen);

        splashLayout = (RelativeLayout) findViewById(R.id.splashLayout);
        splashImage = (ImageView) findViewById(R.id.splashImage);

        SharedPreferences settings = getSharedPreferences(PREFS, 0);
        if (settings.getBoolean("my_first_time", true)) {
            //the app is being launched for first time
            PreferencesDataBase preferences = new PreferencesDataBase(getBaseContext());
            preferences.createDefaultSettings();

            settings.edit().putBoolean("my_first_time", false).apply();
        }

        PreferencesDataBase preferences = new PreferencesDataBase(getBaseContext());
        if (preferences.getUsername().equals(PreferencesDataBase.DEFAULT_USER_NAME)) {
            Intent intent = new Intent(SplashScreenActivity.this, SetUpSummonerActivity.class);
            startActivity(intent);
        } else {
            startAnimations();
            new DownloadDataTask().execute("");
        }
    }

    private void startAnimations() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        animation.reset();
        splashLayout.clearAnimation();
        splashLayout.startAnimation(animation);

        animation = AnimationUtils.loadAnimation(this, R.anim.translateup);
        animation.reset();
        splashImage.clearAnimation();
        splashImage.startAnimation(animation);
    }

    private class DownloadDataTask extends AsyncTask<String, String, Integer> {
        //0 means no connection, 1 means connection

        protected Integer leagueScrape() {
            LeagueScrapper leagueScrapper = new LeagueScrapper(getBaseContext());
            List<StatisticsChampion> statisticsChampions = null;
            try {
                statisticsChampions = leagueScrapper.createDataTable();

            } catch (IOException e) {
                //no internet connection!!

                System.out.println("no internet connection");
                return 0;
            }

            if (statisticsChampions == null) {
                return 0;
            }

            DataBaseIO dataBaseIO = new DataBaseIO(getBaseContext());
            dataBaseIO.addChampions(statisticsChampions);

            return 1;
        }

        protected Integer updateUser() {
            PreferencesDataBase preferences = new PreferencesDataBase(getBaseContext());

            String username = preferences.getUsername();
            String region = preferences.getRegion();

            return 1;
        }

        @Override
        protected Integer doInBackground(String... params) {
            if ( leagueScrape() == 1) {
                return updateUser();
            }

            return 0;
        }

        @Override
        protected void onPostExecute(Integer code) {
            super.onPostExecute(code);

            switch (code) {
                case 0:
                    Intent intent1 = new Intent(SplashScreenActivity.this, NoConnectivityActivity.class);
                    startActivity(intent1);
                    return;
                case 1:
                    Intent intent2 = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(intent2);
            }

            finish();
        }
    }

}
