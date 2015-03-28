package manuele.bryan.lolwinrate.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import manuele.bryan.lolwinrate.Adapters.ChampionListItemAdapter;
import manuele.bryan.lolwinrate.Helpers.LolStatsApplication;
import manuele.bryan.lolwinrate.LolStatistics.StatisticsChampionList;
import manuele.bryan.lolwinrate.R;

public class ChampionListFragment extends Fragment {
    private ChampionListItemAdapter championListItemAdapter;

    public static final String SORT_TYPE = "sorttype";

    public static final int SORT_BY_POPULARITY = 0;
    public static final int SORT_BY_WINRATE = 1;

    public int sortType;

    static Context context;

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    StatisticsChampionList statisticsChampionList = null;

    public static ChampionListFragment newInstance(int sortType) {
        ChampionListFragment championListFragment = new ChampionListFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(SORT_TYPE, sortType);

        championListFragment.setArguments(bundle);
        return championListFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            sortType = getArguments().getInt(SORT_TYPE);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_champion_list, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.mainRecyclerView);
        recyclerView.setHasFixedSize(false);
        linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        statisticsChampionList = LolStatsApplication.statisticsChampionList;

        if (sortType == SORT_BY_POPULARITY) {
            statisticsChampionList.sortByPopularity();
        } else {
            statisticsChampionList.sortByWinrate();
        }

        championListItemAdapter = new ChampionListItemAdapter(context, statisticsChampionList.statisticsChampions);
        recyclerView.setAdapter(championListItemAdapter);

        return view;
    }

    public void sortList(int sortType) {
        this.sortType = sortType;

        if (sortType == SORT_BY_POPULARITY) {
            statisticsChampionList.sortByPopularity();
        } else {
            statisticsChampionList.sortByWinrate();
        }

        championListItemAdapter = new ChampionListItemAdapter(context, statisticsChampionList.statisticsChampions);
        recyclerView.setAdapter(championListItemAdapter);

    }

    public static void openChampionInfoActivity(String champName, String winrate, String popularity) {
        Fragment championInfoFragment = ChampionInfoFragment.newInstance(champName, winrate, popularity);
        replaceFragment(championInfoFragment);

    }

    public static void replaceFragment(Fragment newFragment) {
        Activity activity = (Activity) context;

        activity.getFragmentManager().beginTransaction()
                .replace(R.id.content_frame, newFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
    }

    //TODO: implement Listeners to update the recyclerView adapter for queryFragment and sortPrefs

}
