package manuele.bryan.lolwinrate.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import manuele.bryan.lolwinrate.LolStatistics.ChampionList;
import manuele.bryan.lolwinrate.Databases.DataBaseIO;
import manuele.bryan.lolwinrate.Adapters.ListAdapterMain;
import manuele.bryan.lolwinrate.LolStatistics.QueryPreferences;
import manuele.bryan.lolwinrate.R;
import manuele.bryan.lolwinrate.LolStatistics.SortPreferences;

public class ListFragment extends Fragment {
    private ListAdapterMain listAdapterMain;

    private QueryPreferences queryPreferences;
    private SortPreferences sortPreferences;

    Context context;

    private ListView listView;

    public static ListFragment newInstance() {
        ListFragment listFragment = new ListFragment();

        Bundle args = new Bundle();
        listFragment.setArguments(args);

        return listFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        listView = (ListView) view.findViewById(R.id.mainListView);

        DataBaseIO dataBaseIO = new DataBaseIO(context);
        ChampionList championList = new ChampionList(dataBaseIO.getChampions());

        listAdapterMain = new ListAdapterMain(context, championList.champions);
        listView.setAdapter(listAdapterMain);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
    }

    //TODO: implement Listeners to update the listView adapter for queryFragment and sortPrefs

}
