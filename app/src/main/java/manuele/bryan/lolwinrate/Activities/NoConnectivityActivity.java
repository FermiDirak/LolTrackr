package manuele.bryan.lolwinrate.Activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import manuele.bryan.lolwinrate.R;


public class NoConnectivityActivity extends ActionBarActivity {

    RelativeLayout noConnectivityLayout;
    TextView retryText1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_connectivity);

        noConnectivityLayout = (RelativeLayout) findViewById(R.id.noConnectivityLayout);
        retryText1 = (TextView) findViewById(R.id.retryText1);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/robotolight.ttf");
        retryText1.setTypeface(typeface);

        noConnectivityLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NoConnectivityActivity.this, SplashScreenActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_no_connectivity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
