package manuele.bryan.lolwinrate.Databases;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

import manuele.bryan.lolwinrate.LolStatistics.QueryPreferences;
import manuele.bryan.lolwinrate.LolStatistics.SortPreferences;

public class PreferencesDataBase {
    public Context context;

    public static final String PACKAGE_NAME = "manuele.bryan.lolwinrate";

    public static final String KEY_REGION = "region",
            KEY_QUEUE_TYPE ="queuetype",
            KEY_RANKED_TIER = "rankedtier",
            KEY_TIME = "time";

    public static final String KEY_SORTBY = "sortby",
            KEY_FILTER = "filter",
            KEY_SORT_ORDER = "sortorder";

    public static final String KEY_LAST_OPENED_TAB = "lostopened";

    public static final String KEY_USER_NAME = "username",
        KEY_USER_REGION = "region";
    //TODO:implement this


    public PreferencesDataBase(Context context) {
        this.context = context;
    }

    public void updateSetting(String key, int value) {
        SharedPreferences settings = getSharedPreferences();
        SharedPreferences.Editor settingsEditor = settings.edit();

        settingsEditor.putInt(key, value);

        settingsEditor.apply();
    }

    public SharedPreferences getSharedPreferences() {
        return context.getSharedPreferences(PACKAGE_NAME,
                Context.MODE_PRIVATE);
    }

    public void createDefaultSettings() {
        SharedPreferences settings = getSharedPreferences();

        SharedPreferences.Editor settingsEditor = settings.edit();

        //QUERY
        settingsEditor.putInt(KEY_REGION, QueryPreferences.REGION_ALL);
        settingsEditor.putInt(KEY_QUEUE_TYPE, QueryPreferences.QUEUE_ALL);
        settingsEditor.putInt(KEY_RANKED_TIER, QueryPreferences.RANK_ALL);
        settingsEditor.putInt(KEY_TIME, QueryPreferences.TIME_DAY);

        //SORT
        settingsEditor.putInt(KEY_SORTBY, SortPreferences.BY_WINRATE);
        settingsEditor.putInt(KEY_FILTER, SortPreferences.FILTER_ALL);
        settingsEditor.putInt(KEY_SORT_ORDER, SortPreferences.DESCENDING_ORDER);

        settingsEditor.putInt(KEY_LAST_OPENED_TAB, 2);

        settingsEditor.apply();
    }

    public void updateLastOpenedTab(int i) {
        updateSetting(KEY_LAST_OPENED_TAB, i);
    }

    public int getLastOpenedTab() {
        SharedPreferences settings = getSharedPreferences();
        return settings.getInt(KEY_LAST_OPENED_TAB, 2);
    }

    public HashMap<String, Integer> getQueryPreferences() {
        SharedPreferences settings = context.getSharedPreferences(PACKAGE_NAME,
                Context.MODE_PRIVATE);

        HashMap<String, Integer> values = new HashMap<>();

        int region = settings.getInt(KEY_REGION, QueryPreferences.REGION_ALL);
        int queueType = settings.getInt(KEY_QUEUE_TYPE, QueryPreferences.QUEUE_ALL);
        int rankedTier = settings.getInt(KEY_RANKED_TIER, QueryPreferences.RANK_ALL);
        int time = settings.getInt(KEY_TIME, QueryPreferences.TIME_DAY);

        values.put(KEY_REGION, region);
        values.put(KEY_QUEUE_TYPE, queueType);
        values.put(KEY_RANKED_TIER, rankedTier);
        values.put(KEY_TIME, time);

        return values;
    }

    public HashMap<String, Integer> getSortPreferences() {
        SharedPreferences settings = context.getSharedPreferences(PACKAGE_NAME,
                Context.MODE_PRIVATE);

        HashMap<String, Integer> values = new HashMap<>();

        int sortBy = settings.getInt(KEY_SORTBY, SortPreferences.BY_WINRATE);
        int filter = settings.getInt(KEY_FILTER, SortPreferences.FILTER_ALL);
        int sortOrder = settings.getInt(KEY_SORT_ORDER, SortPreferences.DESCENDING_ORDER);

        values.put(KEY_SORTBY, sortBy);
        values.put(KEY_FILTER, filter);
        values.put(KEY_SORT_ORDER, sortOrder);

        return values;
    }
}
