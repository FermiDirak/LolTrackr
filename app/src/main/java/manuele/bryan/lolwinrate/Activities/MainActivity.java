package manuele.bryan.lolwinrate.Activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import manuele.bryan.lolwinrate.Databases.PreferencesDataBase;
import manuele.bryan.lolwinrate.Fragments.ChampionListFragment;
import manuele.bryan.lolwinrate.Fragments.NavigationDrawerFragment;
import manuele.bryan.lolwinrate.Fragments.QueryFragment;
import manuele.bryan.lolwinrate.R;


public class MainActivity extends ActionBarActivity
        implements QueryFragment.QueryFragmentListener,
        NavigationDrawerFragment.NavigationDrawerListener {

    private DrawerLayout drawerLayout;
    private NavigationDrawerFragment drawerFragment;
    private CharSequence drawerTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerTitle = getTitle();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        replaceFragment(ChampionListFragment.newInstance());

    }

    public void replaceFragment(Fragment newFragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, newFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed(){
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onQuerySubmit(int sortBy, int region, int filter, int sortOrder,
                              int queueType, int rankedTier, int time) {

    }


    @Override
    public void updateFragmentHolder() {
        PreferencesDataBase preferences = new PreferencesDataBase(getBaseContext());

        int lastOpenedTabPosition = preferences.getLastOpenedTab();

        drawerLayout.closeDrawer(findViewById(R.id.left_drawer));

        switch (lastOpenedTabPosition) {
            case 0:
                //user lookup
                return;
            case 1:
                //items
                return;
            case 2:
                //champion winrates
                return;
            case 3:
                //champion popularity
                return;
            case 4:
                //settings
                return;
            case 5:
                //about
                return;
        }

    }
}
