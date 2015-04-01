package manuele.bryan.lolwinrate.Adapters;

import android.support.v4.view.PagerAdapter;
import android.view.View;

import manuele.bryan.lolwinrate.R;

public class UserStatsPagerAdapter extends PagerAdapter {
    View parentView;

    public UserStatsPagerAdapter(View parentView) {
        this.parentView = parentView;
    }

    @Override
    public Object instantiateItem(View collection, int position) {
        int resId = 0;
        switch (position) {
            case 0:
                resId = R.id.usersStatsPage0;
                break;
            case 1:
                resId = R.id.usersStatsPage1;
                break;
            case 2:
                resId = R.id.usersStatsPage2;
                break;
        }

        return parentView.findViewById(resId);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((View) object);
    }
}
