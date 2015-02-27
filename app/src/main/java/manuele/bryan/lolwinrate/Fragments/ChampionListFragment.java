package manuele.bryan.lolwinrate.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import manuele.bryan.lolwinrate.Adapters.ChampionListItemAdapter;
import manuele.bryan.lolwinrate.LolStatistics.StatisticsChampionList;
import manuele.bryan.lolwinrate.Databases.DataBaseIO;
import manuele.bryan.lolwinrate.LolStatistics.QueryPreferences;
import manuele.bryan.lolwinrate.R;
import manuele.bryan.lolwinrate.LolStatistics.SortPreferences;

public class ChampionListFragment extends Fragment {
    private ChampionListItemAdapter championListItemAdapter;

    private QueryPreferences queryPreferences;
    private SortPreferences sortPreferences;

    Context context;

    private ListView listView;

    public static ChampionListFragment newInstance() {
        ChampionListFragment championListFragment = new ChampionListFragment();

        Bundle args = new Bundle();
        championListFragment.setArguments(args);

        return championListFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_champion_list, container, false);

        listView = (ListView) view.findViewById(R.id.mainListView);

        DataBaseIO dataBaseIO = new DataBaseIO(context);
        StatisticsChampionList statisticsChampionList = new StatisticsChampionList(dataBaseIO.getChampions());

        championListItemAdapter = new ChampionListItemAdapter(context, statisticsChampionList.statisticsChampions);
        listView.setAdapter(championListItemAdapter);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
    }

    //TODO: implement Listeners to update the listView adapter for queryFragment and sortPrefs

}
