package manuele.bryan.lolwinrate.Helpers;

import android.app.Application;

import constant.Region;
import main.java.riotapi.RiotApi;
import manuele.bryan.lolwinrate.Databases.JsonIO;

public class LolStatsApplication extends Application {
    public static String riotApiKey = "";
    public static RiotApi riotApi;

    @Override
    public void onCreate() {
        super.onCreate();

        riotApiKey = JsonIO.getRiotApiKey(getAssets());

        riotApi = new RiotApi(riotApiKey, Region.NA);

        //TODO: make regions changeable
    }

}
