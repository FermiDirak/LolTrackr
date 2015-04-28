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
        KEY_USER_REGION = "userregion";

    public static final String DEFAULT_USER_NAME = "zed",
            DEFAULT_USER_REGION = "na";

    public static final String KEY_JSON_USER_INFO = "jsoninfo",
        KEY_JSON_USER_STATS = "jsonstats",
        KEY_JSON_USER_SUMMARY = "jsonsummary",
        KEY_JSON_USER_LEAGUE = "jsonleague",
        KEY_JSON_MATCH_HISTORY = "matchhistory";


    public PreferencesDataBase(Context context) {
        this.context = context;
    }

    public void updateSetting(String key, int value) {
        SharedPreferences settings = getSharedPreferences();
        SharedPreferences.Editor settingsEditor = settings.edit();

        settingsEditor.putInt(key, value);
        settingsEditor.apply();
    }

    public void updateSetting(String key, String value) {
        SharedPreferences settings = getSharedPreferences();
        SharedPreferences.Editor settingsEditor = settings.edit();

        settingsEditor.putString(key, value);
        settingsEditor.apply();
    }

    public String getSetting(String key) {
        SharedPreferences settings = getSharedPreferences();
        return settings.getString(key, "");
    }

    private SharedPreferences getSharedPreferences() {
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

        settingsEditor.putString(KEY_USER_NAME, DEFAULT_USER_NAME);
        settingsEditor.putString(KEY_USER_REGION, DEFAULT_USER_REGION);

        emptyJSON();

        settingsEditor.apply();
    }

    public void updateLastOpenedTab(int i) {
        updateSetting(KEY_LAST_OPENED_TAB, i);
    }

    public int getLastOpenedTab() {
        SharedPreferences settings = getSharedPreferences();
        return settings.getInt(KEY_LAST_OPENED_TAB, 2);
    }

    public void updateJSON(String jsonUserInfo, String jsonUserStats, String jsonUserSummary, String userLeagueInfo, String matchHistory) {
        updateSetting(KEY_JSON_USER_INFO, jsonUserInfo);
        updateSetting(KEY_JSON_USER_SUMMARY, jsonUserSummary);
        updateSetting(KEY_JSON_USER_STATS, jsonUserStats);
        updateSetting(KEY_JSON_USER_LEAGUE, userLeagueInfo);
        updateSetting(KEY_JSON_MATCH_HISTORY, matchHistory);
    }

    public void emptyJSON() {
        updateSetting(KEY_JSON_USER_INFO, "");
        updateSetting(KEY_JSON_USER_SUMMARY, "");
        updateSetting(KEY_JSON_USER_STATS, "");
        updateSetting(KEY_JSON_USER_LEAGUE, "");
        updateSetting(KEY_JSON_MATCH_HISTORY, "");
    }

    public void updateUser(String username, String region) {
        updateSetting(KEY_USER_NAME, username);
        updateSetting(KEY_USER_REGION, region);
    }

    public String getUsername() {
        SharedPreferences settings = getSharedPreferences();
        return settings.getString(KEY_USER_NAME, DEFAULT_USER_NAME);
    }

    public String getUserRegion() {
        SharedPreferences settings = getSharedPreferences();
        return settings.getString(KEY_USER_REGION, DEFAULT_USER_REGION);
    }

    public void defaultUserInfo() {
        updateUser(PreferencesDataBase.DEFAULT_USER_NAME, PreferencesDataBase.DEFAULT_USER_REGION);
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
