package manuele.bryan.lolwinrate.LolStatistics;

import android.content.Context;

import java.util.HashMap;

import manuele.bryan.lolwinrate.Databases.PreferencesDataBase;

public class SortPreferences {
    public static final int BY_POPULARITY = 0;
    public static final int BY_WINRATE    = 1;

    public static final int FILTER_ALL     = 0;
    public static final int FILTER_JUNGLE  = 1;
    public static final int FILTER_ADC     = 2;
    public static final int FILTER_MID     = 3;
    public static final int FILTER_TOP     = 4;
    public static final int FILTER_SUPPORT = 5;

    public static final int ASCENDING_ORDER  = 1;
    public static final int DESCENDING_ORDER = 2;

    public int sortBy, filter, sortOrder;

    Context context;

    public SortPreferences(Context context) {
        this.context = context;

        PreferencesDataBase preferences = new PreferencesDataBase(context);
        HashMap<String, Integer> settings = preferences.getSortPreferences();

        sortBy = settings.get(PreferencesDataBase.KEY_SORTBY);
        filter = settings.get(PreferencesDataBase.KEY_FILTER);
        sortOrder = settings.get(PreferencesDataBase.KEY_SORT_ORDER);
    }

    public String printSortBy() {
        switch (sortBy) {
            case BY_POPULARITY:
                return "popularity";
            default: //BY_WINRATE
                return "winrate";
        }
    }

    public String printFilter() {
        switch (filter) {
            case FILTER_ALL:
                return "All Roles";
            case FILTER_JUNGLE:
                return "Junglers";
            case FILTER_ADC:
                return "AD Carries";
            case FILTER_MID:
                return "Mid Laners";
            case FILTER_TOP:
                return "Top Laners";
            default: //FILTER_SUPPORT
                return "Supports";
        }
    }

    public String printSortOrder() {
        switch (sortOrder) {
            case ASCENDING_ORDER:
                return "Ascending Order";
            default: //DESCENDING_ORDER
                return "Descending Order";
        }
    }

}

//TODO: Support seeing only certain champion types through filter lameness
