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
        String username = "";
        String region = "";

        username = accountNameInput.getText().toString();
        region = String.valueOf(regionSpinner.getSelectedItem()).toLowerCase();

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


    //TODO: create a database of user statistics
    private class VerifyUserTask extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {

            return "";
        }

        @Override
        protected void onPostExecute(String string) {
            super.onPostExecute(string);

            

        }
    }

}
