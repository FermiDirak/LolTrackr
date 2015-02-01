package manuele.bryan.lolwinrate;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.List;

import manuele.bryan.lolwinrate.Databases.PreferencesDataBase;

public class QueryPreferences {
    public static final int REGION_ALL  = 0;
    public static final int REGION_NA   = 1;
    public static final int REGION_EUW  = 2;
    public static final int REGION_EUNE = 3;
    public static final int REGION_BR   = 4;
    public static final int REGION_TR   = 5;
    public static final int REGION_RU   = 6;
    public static final int REGION_LAN  = 7;
    public static final int REGION_LAS  = 8;
    public static final int REGION_OCE  = 9;
    public static final int REGION_KR   = 10;

    public static final int QUEUE_ALL = 0;
    public static final int QUEUE_NORMAL = 1;
    public static final int QUEUE_RANKED = 2;

    public static final int RANK_ALL     = 0;
    public static final int RANK_BRONZE  = 1;
    public static final int RANK_SILVER  = 2;
    public static final int RANK_GOLD    = 3;
    public static final int RANK_PLAT    = 4;
    public static final int RANK_DIAMOND = 5;
    public static final int RANK_MASTERS = 6;
    public static final int RANK_CHALL   = 7;

    public static final int TIME_DAY   = 1;
    public static final int TIME_WEEK  = 7;
    public static final int TIME_MONTH = 30;

    public int region, queueType, rankedTier, time;

    private Context context;

    public QueryPreferences (Context context) {
        this.context = context;

        PreferencesDataBase preferences = new PreferencesDataBase(context);
        HashMap<String, Integer> settings = preferences.getQueryPreferences();

        region = settings.get(PreferencesDataBase.KEY_REGION);
        queueType = settings.get(PreferencesDataBase.KEY_QUEUE_TYPE);
        rankedTier = settings.get(PreferencesDataBase.KEY_RANKED_TIER);
        time = settings.get(PreferencesDataBase.KEY_TIME);
    }

    public String createLink() {
        String link = "http://loldb.gameguyz.com/statistics/winRate";

        link = link + "/" + region;
        link = link + "/" + 0;
        link = link + "/" + 2;
        link = link + "/" + queueType;
        link = link + "/" + rankedTier;
        link = link + "/" + time;

        return link;
    }

    //TODO: refactor strings names to xml
    public String printRegion() {
        switch (region) {
            case REGION_ALL:
                return "all";
            case REGION_NA:
                return "North America (NA)";
            case REGION_EUW:
                return "EU West (EUW)";
            case REGION_EUNE:
                return "EU Nordic and East";
            case REGION_BR:
                return "Brazil";
            case REGION_TR:
                return "Turkey";
            case REGION_RU:
                return "Russia";
            case REGION_LAN:
                return "Latin America North";
            case REGION_LAS:
                return "Latin America South";
            case REGION_OCE:
                return "Oceania";
            default: // REGION_KR
                return "Korea";
        }
    }

    public String printQueueType() {
        switch (queueType) {
            case QUEUE_ALL:
                return "Normal And Ranked Queue";
            case QUEUE_NORMAL:
                return "Normal Queue";
            default: //QUEUE_RANKED
                return "Ranked Queue";
        }

    }

    public String printRankedTier() {
        switch (rankedTier) {
            case RANK_ALL:
                return "All";
            case RANK_BRONZE:
                return "Bronze";
            case RANK_SILVER:
                return "Silver";
            case RANK_GOLD:
                return "Gold";
            case RANK_PLAT:
                return "Plat";
            case RANK_DIAMOND:
                return "Diamond";
            case RANK_MASTERS:
                return "Masters";
            default: //RANK_CHALL
                return "Challenger";
        }

    }

    public String printTime() {
        switch(time) {
            case TIME_DAY:
                return "Today";
            case TIME_WEEK:
                return "This Week";
            default: //TIME_MONTH
                return "This Month";
        }
    }

}
