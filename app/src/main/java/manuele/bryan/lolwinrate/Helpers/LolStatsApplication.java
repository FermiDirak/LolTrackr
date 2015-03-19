package manuele.bryan.lolwinrate.Helpers;

import android.app.Application;

import constant.Region;
import main.java.riotapi.RiotApi;
import manuele.bryan.lolwinrate.Databases.JsonIO;
import manuele.bryan.lolwinrate.UserStatistics.RankedStatsInfo;
import manuele.bryan.lolwinrate.UserStatistics.UserInfo;
import manuele.bryan.lolwinrate.UserStatistics.UserSummaryInfo;

public class LolStatsApplication extends Application {
    public static String riotApiKey = "";
    public static RiotApi riotApi;

    public static UserInfo userInfo = null;
    public static RankedStatsInfo rankedStatsInfo = null;
    public static UserSummaryInfo userSummaryInfo = null;

    @Override
    public void onCreate() {
        super.onCreate();

        riotApiKey = JsonIO.getRiotApiKey(getAssets());

        riotApi = new RiotApi(riotApiKey, Region.NA);

        //TODO: make regions changeable
    }

}
