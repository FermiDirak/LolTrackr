package manuele.bryan.lolwinrate.Helpers;

import android.app.Application;
import android.graphics.Color;

import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import constant.Region;
import main.java.riotapi.RiotApi;
import manuele.bryan.lolwinrate.Databases.JsonIO;
import manuele.bryan.lolwinrate.LolItems.StaticLolItemsList;
import manuele.bryan.lolwinrate.LolStatistics.StatisticsChampionList;
import manuele.bryan.lolwinrate.UserStatistics.MatchHistory;
import manuele.bryan.lolwinrate.UserStatistics.RankedStatsInfo;
import manuele.bryan.lolwinrate.UserStatistics.UserInfo;
import manuele.bryan.lolwinrate.UserStatistics.UserSummaryInfo;
import manuele.bryan.lolwinrate.UserStatistics.UsersLeagueInfo;

public class LolStatsApplication extends Application {
    public static String riotApiKey = "";
    public static RiotApi riotApi;

    public static UserInfo userInfo = null;
    public static RankedStatsInfo rankedStatsInfo = null;
    public static UserSummaryInfo userSummaryInfo = null;
    public static UsersLeagueInfo usersLeagueInfo = null;
    public static MatchHistory matchHistory = null;

    public static ArrayList<Integer> colors = new ArrayList<>();
    static {
        colors.add(Color.rgb(255,209,140));
        colors.add(Color.rgb(197,255,139));
        colors.add(Color.rgb(139,234,255));
        colors.add(Color.rgb(255,142,154));
        colors.add(Color.rgb(107,243,173));
        colors.add(ColorTemplate.getHoloBlue());
    }

    public static StaticLolItemsList staticLolItemsList = null; //huge

    public static StatisticsChampionList statisticsChampionList = null;

    @Override
    public void onCreate() {
        super.onCreate();

        riotApiKey = JsonIO.getRiotApiKey(getAssets());

        riotApi = new RiotApi(riotApiKey, Region.NA);

        //TODO: make regions changeable

        //TODO: add global typefaces here
    }

}
