package manuele.bryan.lolwinrate.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import manuele.bryan.lolwinrate.Databases.JsonIO;
import manuele.bryan.lolwinrate.Helpers.StringHelper;
import manuele.bryan.lolwinrate.Items.StaticChampion;
import manuele.bryan.lolwinrate.R;

public class ChampionInfoFragment extends Fragment {

    public static final String KEY_CHAMPNAME = "champname";
    public static final String KEY_WINRATE = "winrate";
    public static final String KEY_POPULARITY = "popularity";
    String champName = "";
    String champDisplayName = "";
    String winrate = "";
    String popularity = "";

    public static ChampionInfoFragment newInstance(String champName, String winrate, String popularity) {
        ChampionInfoFragment fragment = new ChampionInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_CHAMPNAME, champName);
        bundle.putString(KEY_WINRATE, winrate);
        bundle.putString(KEY_POPULARITY, popularity);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            champName = getArguments().getString(KEY_CHAMPNAME);
            champDisplayName = StringHelper.capitalizeFirstLetter(champName);
            winrate = getArguments().getString(KEY_WINRATE);
            popularity = getArguments().getString(KEY_POPULARITY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_champion_info, container, false);

        TextView championNameTextView = (TextView) view.findViewById(R.id.championName);
        TextView championTitleTextView = (TextView) view.findViewById(R.id.championTitle);
        TextView winrateTextView = (TextView) view.findViewById(R.id.winrate);
        TextView popularityTextView = (TextView) view.findViewById(R.id.popularity);
        TextView bioTextView = (TextView) view.findViewById(R.id.bioText);
        TextView allyTipsTextView = (TextView) view.findViewById(R.id.allytips);
        TextView enemyTipsTextView = (TextView) view.findViewById(R.id.enemytips);

        winrateTextView.setText(winrate);
        popularityTextView.setText(popularity);

        StaticChampion championInfo = JsonIO.loadChampionInfo(getActivity(), champName);

        championNameTextView.setText(championInfo.name);
        championTitleTextView.setText(championInfo.title);
        bioTextView.setText(championInfo.lore);

        String allytips = StringHelper.createBulletPointText(championInfo.allytips);
        String enemytips = StringHelper.createBulletPointText(championInfo.enemytips);

        allyTipsTextView.setText(allytips);
        enemyTipsTextView.setText(enemytips);



        return view;
    }

}
