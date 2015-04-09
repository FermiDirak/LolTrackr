package manuele.bryan.lolwinrate.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import manuele.bryan.lolwinrate.Adapters.UserStatsPagerAdapter;
import manuele.bryan.lolwinrate.Databases.PreferencesDataBase;
import manuele.bryan.lolwinrate.Helpers.LolStatsApplication;
import manuele.bryan.lolwinrate.R;
import manuele.bryan.lolwinrate.UserStatistics.RankedStatsInfo;
import manuele.bryan.lolwinrate.UserStatistics.UserInfo;

public class UserStatsMainFragment extends Fragment {
    Context context;

    UserStatsPagerAdapter pagerAdapter;
    ViewPager viewPager;

    TextView levelStaticTextView;
    TextView levelTextView;
    TextView regionTextView;

    View userStatsPage0;

    TextView team3v3StaticTextView;
    ImageView team3v3RankedIconImageView;
    TextView team3v3RankTextView;
    TextView team3v3pointsTextView;
    TextView team3v3pointsStaticTextView;
    TextView team3v3WinsTextView;
    TextView team3v3WinsStaticTextView;
    TextView team3v3LossesTextView;
    TextView team3v3LossesStaticTextView;

    TextView solo5v5StaticTextView;
    ImageView solo5v5RankedIconImageView;
    TextView solo5v5RankTextView;
    TextView solo5v5pointsTextView;
    TextView solo5v5pointsStaticTextView;
    TextView solo5v5WinsTextView;
    TextView solo5v5WinsStaticTextView;
    TextView solo5v5LossesTextView;
    TextView solo5v5LossesStaticTextView;

    TextView team5v5StaticTextView;
    ImageView team5v5RankedIconImageView;
    TextView team5v5RankTextView;
    TextView team5v5pointsTextView;
    TextView team5v5pointsStaticTextView;
    TextView team5v5WinsTextView;
    TextView team5v5WinsStaticTextView;
    TextView team5v5LossesTextView;
    TextView team5v5LossesStaticTextView;

    View userStatsPage1;
    View userStatsPage2;


    public static UserStatsMainFragment newInstance() {
        UserStatsMainFragment fragment = new UserStatsMainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_stats_main, container, false);

        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/robotolight.ttf");

        levelStaticTextView = (TextView) view.findViewById(R.id.userStatsMainLevelStatic);
        levelTextView = (TextView) view.findViewById(R.id.userStatsMainLevel);
        regionTextView = (TextView) view.findViewById(R.id.userStatsRegion);

        levelStaticTextView.setTypeface(typeface);
        levelTextView.setTypeface(typeface);
        regionTextView.setTypeface(typeface);

        int level = LolStatsApplication.userInfo.summonerLevel;
        levelTextView.setText(level);

        PreferencesDataBase preferencesDataBase = new PreferencesDataBase(context);
        regionTextView.setText(preferencesDataBase.getUserRegion());

        //___________________________________USER_STATS_PAGE_0___________________________________

        userStatsPage0 = view.findViewById(R.id.usersStatsPage0);

        team3v3StaticTextView = (TextView) userStatsPage0.findViewById(R.id.page0Team3v3Static);
        team3v3RankedIconImageView = (ImageView) userStatsPage0.findViewById(R.id.page0Team3v3Icon);
        team3v3RankTextView = (TextView) userStatsPage0.findViewById(R.id.page0Team3v3Rank);
        team3v3pointsTextView = (TextView) userStatsPage0.findViewById(R.id.page0Team3v3Points);
        team3v3pointsStaticTextView = (TextView) userStatsPage0.findViewById(R.id.page0Team3v3PointsStatic);
        team3v3WinsTextView = (TextView) userStatsPage0.findViewById(R.id.page0Team3v3Wins);
        team3v3WinsStaticTextView = (TextView) userStatsPage0.findViewById(R.id.page0Team3v3WinsStatic);
        team3v3LossesTextView = (TextView) userStatsPage0.findViewById(R.id.page0Team3v3Losses);
        team3v3LossesStaticTextView = (TextView) userStatsPage0.findViewById(R.id.page0Team3v3LossesStatic);

        solo5v5StaticTextView = (TextView) userStatsPage0.findViewById(R.id.page0Solo5v5Static);
        solo5v5RankedIconImageView = (ImageView) userStatsPage0.findViewById(R.id.page0Solo5v5Icon);
        solo5v5RankTextView = (TextView) userStatsPage0.findViewById(R.id.page0Solo5v5Rank);
        solo5v5pointsTextView = (TextView) userStatsPage0.findViewById(R.id.page0Solo5v5Points);
        solo5v5pointsStaticTextView = (TextView) userStatsPage0.findViewById(R.id.page0Solo5v5PointsStatic);
        solo5v5WinsTextView = (TextView) userStatsPage0.findViewById(R.id.page0Solo5v5Wins);
        solo5v5WinsStaticTextView = (TextView) userStatsPage0.findViewById(R.id.page0Solo5v5WinsStatic);
        solo5v5LossesTextView = (TextView) userStatsPage0.findViewById(R.id.page0Solo5v5Losses);
        solo5v5LossesStaticTextView = (TextView) userStatsPage0.findViewById(R.id.page0Solo5v5LossesStatic);

        team5v5StaticTextView = (TextView) userStatsPage0.findViewById(R.id.page0Team5v5Static);
        team5v5RankedIconImageView = (ImageView) userStatsPage0.findViewById(R.id.page0Team5v5Icon);
        team5v5RankTextView = (TextView) userStatsPage0.findViewById(R.id.page0Team5v5Rank);
        team5v5pointsTextView = (TextView) userStatsPage0.findViewById(R.id.page0Team5v5Points);
        team5v5pointsStaticTextView = (TextView) userStatsPage0.findViewById(R.id.page0Team5v5PointsStatic);
        team5v5WinsTextView = (TextView) userStatsPage0.findViewById(R.id.page0Team5v5Wins);
        team5v5WinsStaticTextView = (TextView) userStatsPage0.findViewById(R.id.page0Team5v5WinsStatic);
        team5v5LossesTextView = (TextView) userStatsPage0.findViewById(R.id.page0Team5v5Losses);
        team5v5LossesStaticTextView = (TextView) userStatsPage0.findViewById(R.id.page0Team5v5LossesStatic);

        team3v3StaticTextView.setTypeface(typeface);
        team3v3RankTextView.setTypeface(typeface);
        team3v3pointsTextView.setTypeface(typeface);
        team3v3pointsStaticTextView.setTypeface(typeface);
        team3v3WinsTextView.setTypeface(typeface);
        team3v3WinsStaticTextView.setTypeface(typeface);
        team3v3LossesTextView.setTypeface(typeface);
        team3v3LossesStaticTextView.setTypeface(typeface);
        solo5v5StaticTextView.setTypeface(typeface);
        solo5v5RankTextView.setTypeface(typeface);
        solo5v5pointsTextView.setTypeface(typeface);
        solo5v5pointsStaticTextView.setTypeface(typeface);
        solo5v5WinsTextView.setTypeface(typeface);
        solo5v5WinsStaticTextView.setTypeface(typeface);
        solo5v5LossesTextView.setTypeface(typeface);
        solo5v5LossesStaticTextView.setTypeface(typeface);
        team5v5StaticTextView.setTypeface(typeface);
        team5v5RankTextView.setTypeface(typeface);
        team5v5pointsTextView.setTypeface(typeface);
        team5v5pointsStaticTextView.setTypeface(typeface);
        team5v5WinsTextView.setTypeface(typeface);
        team5v5WinsStaticTextView.setTypeface(typeface);
        team5v5LossesTextView.setTypeface(typeface);
        team5v5LossesStaticTextView.setTypeface(typeface);

        RankedStatsInfo rankedStatsInfo = LolStatsApplication.rankedStatsInfo;

        rankedStatsInfo.





        userStatsPage1 = view.findViewById(R.id.usersStatsPage1);
        userStatsPage2 = view.findViewById(R.id.usersStatsPage2);


        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager = (ViewPager) view.findViewById(R.id.userStatsViewPager);
        viewPager.setOffscreenPageLimit(5);
        pagerAdapter = new UserStatsPagerAdapter(viewPager);
        viewPager.setAdapter(pagerAdapter);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.context = activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

}
