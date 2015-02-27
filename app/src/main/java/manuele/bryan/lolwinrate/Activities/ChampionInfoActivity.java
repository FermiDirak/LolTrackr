package manuele.bryan.lolwinrate.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import manuele.bryan.lolwinrate.R;

public class ChampionInfoActivity extends ActionBarActivity {

    public static final String KEY_CHAMPNAME = "champname";
    public static final String KEY_WINRATE = "winrate";
    public static final String KEY_POPULARITY = "popularity";
    String champName = "";
    String winrate = "";
    String popularity = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champion_info);

        Intent intent = getIntent();
        champName = intent.getExtras().getString(KEY_CHAMPNAME);
        winrate = intent.getExtras().getString(KEY_WINRATE);
        popularity = intent.getExtras().getString(KEY_POPULARITY);

        TextView championNameTV = (TextView) findViewById(R.id.championName);
        TextView championTitleTV = (TextView) findViewById(R.id.championTitle);
        TextView winrateTV = (TextView) findViewById(R.id.winrate);
        TextView popularityTV = (TextView) findViewById(R.id.popularity);
        TextView bioTV = (TextView) findViewById(R.id.bioText);
        TextView allyTipsTV = (TextView) findViewById(R.id.allytips);
        TextView enemyTipsTV = (TextView) findViewById(R.id.enemytips);

        championNameTV.setText(champName);
        winrateTV.setText(winrate);
        popularityTV.setText(popularity);

        //TODO: access ddragon to get all the other info

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_champion_info, menu);
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
}
