package manuele.bryan.lolwinrate.Adapters;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v13.app.FragmentPagerAdapter;

import manuele.bryan.lolwinrate.Fragments.UserStatsPage0;
import manuele.bryan.lolwinrate.Fragments.UserStatsPage1;
import manuele.bryan.lolwinrate.Fragments.UserStatsPage2;

public class UserStatsPagerAdapter extends FragmentPagerAdapter {
    Context context;

    FragmentManager fragmentManager;

    public UserStatsPagerAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.fragmentManager = fragmentManager;
        this.context = context;
    }


    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return UserStatsPage0.newInstance();
            case 1:
                return UserStatsPage1.newInstance();
            case 2:
                return UserStatsPage2.newInstance();
            default:
                return UserStatsPage0.newInstance();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
