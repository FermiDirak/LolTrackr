package manuele.bryan.lolwinrate.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.net.URL;

import manuele.bryan.lolwinrate.Helpers.StringHelper;
import manuele.bryan.lolwinrate.R;

public class SetUpSummonerActivity extends Activity {

    EditText accountNameInput;
    Spinner regionSpinner;
    ImageButton infoButton;
    Button submitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up_summoner);

        accountNameInput = (EditText) findViewById(R.id.nameInput);
        regionSpinner = (Spinner) findViewById(R.id.regionSpinner);
        infoButton = (ImageButton) findViewById(R.id.setupInformationButton);
        submitButton = (Button) findViewById(R.id.submitButton);

        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infoPopup();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

    }

    private void infoPopup() {
        new AlertDialog.Builder(this)
                .setMessage("Summoner information is requested to provide personalized statistics")
                .show();
    }

    private void submit() {

        new VerifyUserTask().execute();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_set_up_summoner, menu);
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

    private class VerifyUserTask extends AsyncTask<String, String, Integer> {
        @Override
        protected Integer doInBackground(String... params) {
            String username = StringHelper.formatSummonerName(accountNameInput.getText().toString());
            String region = String.valueOf(regionSpinner.getSelectedItem()).toLowerCase();

            int code = 0;

            //TODO: change api's region
            String urlString = "https://na.api.pvp.net/api/lol/na/v1.4/summoner/by-name/" + username + "?api_key=cbbfab19-c674-40f6-acb4-4df0b39a82e4";

            try {
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                code = connection.getResponseCode();
                System.out.println("code: " + code);

            } catch (Exception e) {
                //TODO: Handle the lack of internet
                e.printStackTrace();
            }

            return code;
        }

        @Override
        protected void onPostExecute(Integer code) {
            super.onPostExecute(code);

            switch (code) {
                case 0:
                    Toast.makeText(getBaseContext(), "No connection", Toast.LENGTH_SHORT).show();
                    return;
                case 200:
                    //it works!!
                    return;
                case 404:
                    Toast.makeText(getBaseContext(), "Summoner name doesn't exist", Toast.LENGTH_SHORT).show();
                    return;
            }

        }
    }

}
