package manuele.bryan.lolwinrate.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.animation.AnimationEasing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.PercentFormatter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import manuele.bryan.lolwinrate.Adapters.UserStatsPagerAdapter;
import manuele.bryan.lolwinrate.Databases.JsonIO;
import manuele.bryan.lolwinrate.Helpers.ImageHelper;
import manuele.bryan.lolwinrate.Helpers.LolStatsApplication;
import manuele.bryan.lolwinrate.Helpers.RiotAPIConstants;
import manuele.bryan.lolwinrate.Helpers.RiotAPIConstantsHelper;
import manuele.bryan.lolwinrate.R;
import manuele.bryan.lolwinrate.UserStatistics.MatchHistory;
import manuele.bryan.lolwinrate.UserStatistics.RankedStatsInfo;
import manuele.bryan.lolwinrate.UserStatistics.UsersLeagueInfo;

public class UserStatsMainFragment extends Fragment {
    Context context;

    MatchHistory matchHistory;
    Typeface typeface;

    UserStatsPagerAdapter pagerAdapter;
    ViewPager viewPager;

    //____PAGE_0____
    View userStatsPage0;

    View recentMatchView0;
    View recentMatchView1;
    View recentMatchView2;
    View recentMatchView3;

    LayoutInflater layoutInflater;
    boolean[] selectedRankQueue = {true, false, false};

    LinearLayout soloQueueView;
    LinearLayout team5v5View;
    LinearLayout team3v3View;

    View minimizedQueueView;
    View maximizedQueueView;

    View favoritesView;

    //____PAGE_1____
    View userStatsPage1;

    //____PAGE_2____
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

        //____RECENT_MATCH_INFLATION____
        matchHistory = LolStatsApplication.matchHistory;
        recentMatchView0 = view.findViewById(R.id.recent0);
        recentMatchView1 = view.findViewById(R.id.recent1);
        recentMatchView2 = view.findViewById(R.id.recent2);
        recentMatchView3 = view.findViewById(R.id.recent3);
        try {
            inflateRecentMatchView(recentMatchView0, 0);
            inflateRecentMatchView(recentMatchView1, 1);
            inflateRecentMatchView(recentMatchView2, 2);
            inflateRecentMatchView(recentMatchView3, 3);

        } catch (IOException e) {
            e.printStackTrace();
        }

        //____LEAGUE_INFO_INFLATION____
        layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        minimizedQueueView = layoutInflater.inflate(R.layout.user_rank_minimized, null);
        maximizedQueueView = layoutInflater.inflate(R.layout.user_rank_maximized, null);

        soloQueueView = (LinearLayout) view.findViewById(R.id.soloQueue);
        team5v5View = (LinearLayout) view.findViewById(R.id.team5v5);
        team3v3View = (LinearLayout) view.findViewById(R.id.team3v3);

        inflateRankedView(soloQueueView, UsersLeagueInfo.QUEUE_RANKED_SOLO_FIVES, selectedRankQueue[0]);
        inflateRankedView(team5v5View, UsersLeagueInfo.QUEUE_RANKED_TEAM_FIVES, selectedRankQueue[1]);
        inflateRankedView(team3v3View, UsersLeagueInfo.QUEUE_RANKED_TEAM_THREE, selectedRankQueue[2]);

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

        //____FAVORITES_VIEW_INFLATION____
        favoritesView = view.findViewById(R.id.favoritesView);

        PieChart rolesPieChart = (PieChart) favoritesView.findViewById(R.id.rolesPieChart);
        setupRolePieChart(rolesPieChart);

        rolesPieChart.animateY(1500, AnimationEasing.EasingOption.EaseInOutQuad);


        userStatsPage1 = view.findViewById(R.id.usersStatsPage1);
        userStatsPage2 = view.findViewById(R.id.usersStatsPage2);


        return view;
    }

    private void inflateRecentMatchView(View recentMatchView, int i) throws IOException {
        ImageView recentMatchIcon = (ImageView) recentMatchView.findViewById(R.id.recentItemIcon);
        TextView recentMatchKills = (TextView) recentMatchView.findViewById(R.id.recentItemKills);
        TextView recentMatchDeaths = (TextView) recentMatchView.findViewById(R.id.recentItemDeaths);
        TextView recentMatchAssists = (TextView) recentMatchView.findViewById(R.id.recentItemAssists);
        TextView recentMatchCs = (TextView) recentMatchView.findViewById(R.id.recentItemCsCounter);
        View recentMatchWin = recentMatchView.findViewById(R.id.recentItemWin);

        TextView recentMatchKillsStatic = (TextView) recentMatchView.findViewById(R.id.recentItemKillsStatic);
        TextView recentMatchDeathsStatic = (TextView) recentMatchView.findViewById(R.id.recentItemDeathsStatic);
        TextView recentMatchAssistsStatic = (TextView) recentMatchView.findViewById(R.id.recentItemAssistsStatic);
        TextView recentMatchCsStatic = (TextView) recentMatchView.findViewById(R.id.recentItemCsCounterStatic);

        recentMatchKillsStatic.setTypeface(typeface);
        recentMatchDeathsStatic.setTypeface(typeface);
        recentMatchAssistsStatic.setTypeface(typeface);
        recentMatchCsStatic.setTypeface(typeface);

        recentMatchKills.setTypeface(typeface);
        recentMatchDeaths.setTypeface(typeface);
        recentMatchAssists.setTypeface(typeface);
        recentMatchCs.setTypeface(typeface);

        MatchHistory.Match match = matchHistory.matches.get(i);
        recentMatchIcon.setImageDrawable(Drawable.createFromStream(context.getAssets().open("images/champiconshq/" + RiotAPIConstantsHelper.championDictionary.get(match.championId) + ".png"), null));
        recentMatchKills.setText(match.kills + " ");
        recentMatchDeaths.setText(match.deaths + " ");
        recentMatchAssists.setText(match.assists + " ");
        recentMatchCs.setText(match.cs + " ");
        recentMatchWin.setBackgroundColor(match.winner ? getResources().getColor(R.color.wingreen) : getResources().getColor(R.color.losered));
    }

    private void inflateRankedView(LinearLayout view, String rankQueue, boolean expand) {
        view.removeAllViews();

        UsersLeagueInfo.RankedQueue rankedQueueInfo = LolStatsApplication.usersLeagueInfo.queuesList.get(rankQueue);

        if (expand) {
            view.addView(layoutInflater.inflate(R.layout.user_rank_maximized, null));

            if (!LolStatsApplication.usersLeagueInfo.queuesList.containsKey(rankQueue)) {
                ((TextView) view.findViewById(R.id.userRankMaximizedQueueType)).setText(RiotAPIConstantsHelper.getQueueTypePlainText(rankQueue));
                ((TextView) view.findViewById(R.id.userRankMaximizedTier)).setText("NONE");
                ((TextView) view.findViewById(R.id.userRankMaximizedLP)).setText(0 + "");
                ((TextView) view.findViewById(R.id.userRankMaximizedWins)).setText(0 + "");
                ((TextView) view.findViewById(R.id.userRankMaximizedLosses)).setText(0 + "");
                return;
            }

            ((TextView) view.findViewById(R.id.userRankMaximizedQueueType)).setText(RiotAPIConstantsHelper.getQueueTypePlainText(rankQueue));
            ((ImageView) view.findViewById(R.id.userRankMaximizedIcon)).setImageResource(ImageHelper.getTierIcon(rankedQueueInfo.tier));
            ((TextView) view.findViewById(R.id.userRankMaximizedTier)).setText(rankedQueueInfo.tier + " " + rankedQueueInfo.division);
            ((TextView) view.findViewById(R.id.userRankMaximizedLP)).setText(rankedQueueInfo.leaguePoints + "");
            ((TextView) view.findViewById(R.id.userRankMaximizedWins)).setText(rankedQueueInfo.wins + "");
            ((TextView) view.findViewById(R.id.userRankMaximizedLosses)).setText(rankedQueueInfo.losses + "");

        } else {
            view.addView(layoutInflater.inflate(R.layout.user_rank_minimized, null));

            if (!LolStatsApplication.usersLeagueInfo.queuesList.containsKey(rankQueue)) {
                ((TextView) view.findViewById(R.id.userRankMinimizedRank)).setText("NONE");
                ((TextView) view.findViewById(R.id.userRankMinimizedQueueType)).setText(RiotAPIConstantsHelper.getQueueTypePlainText(rankQueue));
                return;
            }

            ((ImageView) view.findViewById(R.id.userRankMinimizedIcon)).setImageResource(ImageHelper.getTierIcon(rankedQueueInfo.tier));
            ((TextView) view.findViewById(R.id.userRankMinimizedRank)).setText(rankedQueueInfo.tier + " " + rankedQueueInfo.division);
            ((TextView) view.findViewById(R.id.userRankMinimizedQueueType)).setText(RiotAPIConstantsHelper.getQueueTypePlainText(rankQueue));

            //TODO: handle case where team name and username are the same

        }

    }

    private void setupRolePieChart(PieChart pieChart) {
        pieChart.setUsePercentValues(true);
        pieChart.setDescription("");
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColorTransparent(true);
        pieChart.setHoleRadius(58f);
        pieChart.setTransparentCircleRadius(61f);
        pieChart.setDrawCenterText(true);
        pieChart.setRotationAngle(0);
        pieChart.setRotationEnabled(true);
        pieChart.setCenterText("Favorite Roles");
        pieChart.getLegend().setEnabled(false);



        HashMap<String, Integer> roleData = aggregatePlayerTagData();
        ArrayList<String> xTitles = new ArrayList<>(roleData.keySet());

        ArrayList<Entry> entries = new ArrayList<>();
        for (int i = 0; i < xTitles.size(); i++) {
            String role = xTitles.get(i);
            entries.add(new Entry(roleData.get(role), i));
        }

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        dataSet.setColors(LolStatsApplication.colors);

        PieData data = new PieData(xTitles, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        data.setValueTypeface(typeface);

        pieChart.setData(data);

        pieChart.highlightValues(null);
        pieChart.invalidate();
    }
    
    private HashMap<String, Integer> aggregatePlayerTagData() {
        int fighterCounter = 0;
        int assassinCounter = 0;
        int mageCounter = 0;
        int marksmanCounter = 0;
        int supportCounter = 0;
        int tankCounter = 0;

        //corresponds a champion to its respective amount of matches played in ranked
        // champId : playCount
        ArrayList<int[]> recordedMatches = new ArrayList<>();

        HashMap<Integer, RankedStatsInfo.ChampionStats> championStats = LolStatsApplication.rankedStatsInfo.championList;
        ArrayList<Integer> keys = new ArrayList<>(championStats.keySet());
        for (int i = 0; i < keys.size(); i++) {
            int key = keys.get(i);
            int gamesPlayed = championStats.get(keys.get(i)).totalSessionsPlayed;

            recordedMatches.add(new int[] {key, gamesPlayed});
        }

        ArrayList<ArrayList<String>> compiledTags = JsonIO.parseChampionBriefStaticJson(getActivity().getAssets());
        for (int i = 0; i < compiledTags.size(); i++) {
            ArrayList<String> champTags = compiledTags.get(i);

            for (String tag : champTags) {
                switch(tag) {
                    case RiotAPIConstants.TAG_FIGHTER:
                        fighterCounter += 1;
                        break;
                    case RiotAPIConstants.TAG_ASSASSIN:
                        assassinCounter += 1;
                        break;
                    case RiotAPIConstants.TAG_MAGE:
                        mageCounter += 1;
                        break;
                    case RiotAPIConstants.TAG_MARKSMAN:
                        marksmanCounter += 1;
                        break;
                    case RiotAPIConstants.TAG_SUPPORT:
                        supportCounter += 1;
                        break;
                    case RiotAPIConstants.TAG_TANK:
                        tankCounter += 1;
                        break;

                }
            }
        }
        
        HashMap<String, Integer> playerRoleData = new HashMap<>();
        playerRoleData.put(RiotAPIConstants.TAG_FIGHTER, fighterCounter);
        playerRoleData.put(RiotAPIConstants.TAG_ASSASSIN, assassinCounter);
        playerRoleData.put(RiotAPIConstants.TAG_MAGE, mageCounter);
        playerRoleData.put(RiotAPIConstants.TAG_MARKSMAN, marksmanCounter);
        playerRoleData.put(RiotAPIConstants.TAG_SUPPORT, supportCounter);
        playerRoleData.put(RiotAPIConstants.TAG_TANK, tankCounter);

        return playerRoleData;

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
