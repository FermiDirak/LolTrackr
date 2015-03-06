package manuele.bryan.lolwinrate.Helpers;

import android.app.Application;

public class LolStatsApplication extends Application {
    public static String riotApiKey = "";

    @Override
    public void onCreate() {
        super.onCreate();

        riotApiKey = JsonHelper.getRiotApiKey(getAssets());
    }

}
