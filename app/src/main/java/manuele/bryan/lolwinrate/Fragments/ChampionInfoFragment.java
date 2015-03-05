package manuele.bryan.lolwinrate.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import manuele.bryan.lolwinrate.R;

public class ChampionInfoFragment extends Fragment {

    public static final String KEY_CHAMPNAME = "champname";
    public static final String KEY_WINRATE = "winrate";
    public static final String KEY_POPULARITY = "popularity";
    String champName = "";
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
            winrate = getArguments().getString(KEY_WINRATE);
            popularity = getArguments().getString(KEY_POPULARITY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_champion_info, container, false);

        TextView championNameTV = (TextView) view.findViewById(R.id.championName);
        TextView championTitleTV = (TextView) view.findViewById(R.id.championTitle);
        TextView winrateTV = (TextView) view.findViewById(R.id.winrate);
        TextView popularityTV = (TextView) view.findViewById(R.id.popularity);
        TextView bioTV = (TextView) view.findViewById(R.id.bioText);
        TextView allyTipsTV = (TextView) view.findViewById(R.id.allytips);
        TextView enemyTipsTV = (TextView) view.findViewById(R.id.enemytips);

        championNameTV.setText(champName);
        winrateTV.setText(winrate);
        popularityTV.setText(popularity);

        //TODO: access ddragon to get all the other info

        return view;
    }


}
