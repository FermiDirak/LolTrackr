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

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import manuele.bryan.lolwinrate.Adapters.UserStatsPagerAdapter;
import manuele.bryan.lolwinrate.Helpers.ImageHelper;
import manuele.bryan.lolwinrate.Helpers.LolStatsApplication;
import manuele.bryan.lolwinrate.R;
import manuele.bryan.lolwinrate.UserStatistics.MatchHistory;
import manuele.bryan.lolwinrate.UserStatistics.UsersLeagueInfo;

public class UserStatsMainFragment extends Fragment {
    Context context;

    Typeface typeface;

    UserStatsPagerAdapter pagerAdapter;
    ViewPager viewPager;

    View userStatsPage0;

    View recentMatchView0;
    ImageView recentMatchIcon0;
    TextView recentMatchKills0;
    TextView recentMatchDeaths0;
    TextView recentMatchAssists0;
    TextView recentMatchCs0;
    View recentMatchWin0;

    View recentMatchView1;
    ImageView recentMatchIcon1;
    TextView recentMatchKills1;
    TextView recentMatchDeaths1;
    TextView recentMatchAssists1;
    TextView recentMatchCs1;
    View recentMatchWin1;

    View recentMatchView2;
    ImageView recentMatchIcon2;
    TextView recentMatchKills2;
    TextView recentMatchDeaths2;
    TextView recentMatchAssists2;
    TextView recentMatchCs2;
    View recentMatchWin2;

    View recentMatchView3;
    ImageView recentMatchIcon3;
    TextView recentMatchKills3;
    TextView recentMatchDeaths3;
    TextView recentMatchAssists3;
    TextView recentMatchCs3;
    View recentMatchWin3;

    LayoutInflater layoutInflater;
    boolean[] selectedRankQueue = {true, false, false};

    LinearLayout soloQueueView;
    LinearLayout team5v5View;
    LinearLayout team3v3View;

    View minimizedQueueView;
    View maximizedQueueView;

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

        typeface = Typeface.createFromAsset(context.getAssets(), "fonts/robotolight.ttf");

        userStatsPage0 = view.findViewById(R.id.usersStatsPage0);

        MatchHistory matchHistory = LolStatsApplication.matchHistory;


        recentMatchView0 = view.findViewById(R.id.recent0);
        recentMatchIcon0 = (ImageView) recentMatchView0.findViewById(R.id.recentItemIcon);
        recentMatchKills0 = (TextView) recentMatchView0.findViewById(R.id.recentItemKills);
        recentMatchDeaths0 = (TextView) recentMatchView0.findViewById(R.id.recentItemDeaths);
        recentMatchAssists0 = (TextView) recentMatchView0.findViewById(R.id.recentItemAssists);
        recentMatchCs0 = (TextView) recentMatchView0.findViewById(R.id.recentItemCsCounter);
        recentMatchWin0 = recentMatchView0.findViewById(R.id.recentItemWin);

        MatchHistory.Match match0 = matchHistory.matches.get(0);

        recentMatchKills0.setText(match0.kills + " ");
        recentMatchDeaths0.setText(match0.deaths + " ");
        recentMatchAssists0.setText(match0.assists + " ");
        recentMatchCs0.setText(match0.cs + " ");
        recentMatchWin0.setBackgroundColor(match0.winner ? getResources().getColor(R.color.wingreen) : getResources().getColor(R.color.losered));

        MatchHistory.Match match1 = matchHistory.matches.get(1);

        recentMatchView1 = view.findViewById(R.id.recent1);
        recentMatchIcon1 = (ImageView) recentMatchView1.findViewById(R.id.recentItemIcon);
        recentMatchKills1 = (TextView) recentMatchView1.findViewById(R.id.recentItemKills);
        recentMatchDeaths1 = (TextView) recentMatchView1.findViewById(R.id.recentItemDeaths);
        recentMatchAssists1 = (TextView) recentMatchView1.findViewById(R.id.recentItemAssists);
        recentMatchCs1 = (TextView) recentMatchView1.findViewById(R.id.recentItemCsCounter);
        recentMatchWin1 = recentMatchView1.findViewById(R.id.recentItemWin);

        MatchHistory.Match match2 = matchHistory.matches.get(2);

        recentMatchView2 = view.findViewById(R.id.recent2);
        recentMatchIcon2 = (ImageView) recentMatchView2.findViewById(R.id.recentItemIcon);
        recentMatchKills2 = (TextView) recentMatchView2.findViewById(R.id.recentItemKills);
        recentMatchDeaths2 = (TextView) recentMatchView2.findViewById(R.id.recentItemDeaths);
        recentMatchAssists2 = (TextView) recentMatchView2.findViewById(R.id.recentItemAssists);
        recentMatchCs2 = (TextView) recentMatchView2.findViewById(R.id.recentItemCsCounter);
        recentMatchWin2 = recentMatchView2.findViewById(R.id.recentItemWin);

        MatchHistory.Match match3 = matchHistory.matches.get(3);

        recentMatchView3 = view.findViewById(R.id.recent3);
        recentMatchIcon3 = (ImageView) recentMatchView3.findViewById(R.id.recentItemIcon);
        recentMatchKills3 = (TextView) recentMatchView3.findViewById(R.id.recentItemKills);
        recentMatchDeaths3 = (TextView) recentMatchView3.findViewById(R.id.recentItemDeaths);
        recentMatchAssists3 = (TextView) recentMatchView3.findViewById(R.id.recentItemAssists);
        recentMatchCs3 = (TextView) recentMatchView3.findViewById(R.id.recentItemCsCounter);
        recentMatchWin3 = recentMatchView3.findViewById(R.id.recentItemWin);

        layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        minimizedQueueView = layoutInflater.inflate(R.layout.user_rank_minimized, null);
        maximizedQueueView = layoutInflater.inflate(R.layout.user_rank_maximized, null);

        soloQueueView = (LinearLayout) view.findViewById(R.id.soloQueue);
        team5v5View = (LinearLayout) view.findViewById(R.id.team5v5);
        team3v3View = (LinearLayout) view.findViewById(R.id.team3v3);

        inflateRankedView(soloQueueView, UsersLeagueInfo.QUEUE_RANKED_SOLO_FIVES, selectedRankQueue[0]);
        inflateRankedView(team5v5View, UsersLeagueInfo.QUEUE_RANKED_TEAM_FIVES, selectedRankQueue[1]);
        inflateRankedView(team3v3View, UsersLeagueInfo.QUEUE_RANKED_TEAM_THREE, selectedRankQueue[2]);

        //TODO: remove this at a later time
        System.out.println("Team Name: " + LolStatsApplication.usersLeagueInfo.queuesList.get(UsersLeagueInfo.QUEUE_RANKED_TEAM_FIVES).teamOrPlayerName);

        soloQueueView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedRankQueue[0] = !selectedRankQueue[0];
                inflateRankedView(soloQueueView, UsersLeagueInfo.QUEUE_RANKED_SOLO_FIVES, selectedRankQueue[0]);
            }
        });

        team5v5View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedRankQueue[1] = !selectedRankQueue[1];
                inflateRankedView(team5v5View, UsersLeagueInfo.QUEUE_RANKED_TEAM_FIVES, selectedRankQueue[1]);
            }
        });

        team3v3View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedRankQueue[2] = !selectedRankQueue[2];
                inflateRankedView(team3v3View, UsersLeagueInfo.QUEUE_RANKED_TEAM_THREE, selectedRankQueue[2]);
            }
        });

//        favoritesView = view.findViewById(R.id.favoritesView);

//        PieChart rolesPieChart = (PieChart) favoritesView.findViewById(R.id.rolesPieChart);
//        rolesPieChart.setUsePercentValues(true);
//        rolesPieChart.setDescription("");
//        rolesPieChart.setDrawHoleEnabled(true);
//        rolesPieChart.setHoleColorTransparent(true);
//        rolesPieChart.setHoleRadius(58f);
//        rolesPieChart.setTransparentCircleRadius(61f);
//        rolesPieChart.setDrawCenterText(true);
//        rolesPieChart.setRotationAngle(0);
//        rolesPieChart.setRotationEnabled(true);
//        rolesPieChart.setCenterText("Favorite Roles");
//
//        setPieChartData(rolesPieChart);
//
//        rolesPieChart.animateY(1500, AnimationEasing.EasingOption.EaseInOutQuad);


        userStatsPage1 = view.findViewById(R.id.usersStatsPage1);
        userStatsPage2 = view.findViewById(R.id.usersStatsPage2);


        return view;
    }

    private void inflateRankedView(LinearLayout view, String rankQueue, boolean expand) {
        view.removeAllViews();

        UsersLeagueInfo.RankedQueue rankedQueueInfo = LolStatsApplication.usersLeagueInfo.queuesList.get(rankQueue);

        if (expand) {
            view.addView(layoutInflater.inflate(R.layout.user_rank_maximized, null));

            if (!LolStatsApplication.usersLeagueInfo.queuesList.containsKey(rankQueue)) {
                ((TextView) view.findViewById(R.id.userRankMaximizedQueueType)).setText(UsersLeagueInfo.getQueueTypePlainText(rankQueue));
                ((TextView) view.findViewById(R.id.userRankMaximizedTier)).setText("NONE");
                ((TextView) view.findViewById(R.id.userRankMaximizedLP)).setText(0 + "");
                ((TextView) view.findViewById(R.id.userRankMaximizedWins)).setText(0 + "");
                ((TextView) view.findViewById(R.id.userRankMaximizedLosses)).setText(0 + "");
                return;
            }

            ((TextView) view.findViewById(R.id.userRankMaximizedQueueType)).setText(UsersLeagueInfo.getQueueTypePlainText(rankQueue));
            ((ImageView) view.findViewById(R.id.userRankMaximizedIcon)).setImageResource(ImageHelper.getTierIcon(rankedQueueInfo.tier));
            ((TextView) view.findViewById(R.id.userRankMaximizedTier)).setText(rankedQueueInfo.tier + " " + rankedQueueInfo.division);
            ((TextView) view.findViewById(R.id.userRankMaximizedLP)).setText(rankedQueueInfo.leaguePoints + "");
            ((TextView) view.findViewById(R.id.userRankMaximizedWins)).setText(rankedQueueInfo.wins + "");
            ((TextView) view.findViewById(R.id.userRankMaximizedLosses)).setText(rankedQueueInfo.losses + "");

        } else {
            view.addView(layoutInflater.inflate(R.layout.user_rank_minimized, null));

            if (!LolStatsApplication.usersLeagueInfo.queuesList.containsKey(rankQueue)) {
                ((TextView) view.findViewById(R.id.userRankMinimizedRank)).setText("NONE");
                ((TextView) view.findViewById(R.id.userRankMinimizedQueueType)).setText(UsersLeagueInfo.getQueueTypePlainText(rankQueue));
                return;
            }

            ((ImageView) view.findViewById(R.id.userRankMinimizedIcon)).setImageResource(ImageHelper.getTierIcon(rankedQueueInfo.tier));
            ((TextView) view.findViewById(R.id.userRankMinimizedRank)).setText(rankedQueueInfo.tier + " " + rankedQueueInfo.division);
            ((TextView) view.findViewById(R.id.userRankMinimizedQueueType)).setText(UsersLeagueInfo.getQueueTypePlainText(rankQueue));

            //TODO: handle case where team name and username are the same

        }

    }

    private void setPieChartData(PieChart pieChart) {


        //TODO:refactor this into somewhere else so that it can be globally accessible
        ArrayList<Integer> colors = new ArrayList<>();

        for (int color : ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(color);
        }

        for (int color : ColorTemplate.JOYFUL_COLORS)
            colors.add(color);

        for (int color : ColorTemplate.COLORFUL_COLORS)
            colors.add(color);

        for (int color : ColorTemplate.LIBERTY_COLORS)
            colors.add(color);

        for (int color : ColorTemplate.PASTEL_COLORS)
            colors.add(color);

        pieChart.highlightValues(null);
        pieChart.invalidate();
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
