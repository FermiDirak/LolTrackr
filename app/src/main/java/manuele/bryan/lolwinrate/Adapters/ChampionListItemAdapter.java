package manuele.bryan.lolwinrate.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import manuele.bryan.lolwinrate.Fragments.ChampionListFragment;
import manuele.bryan.lolwinrate.LolStatistics.StatisticsChampion;
import manuele.bryan.lolwinrate.R;

public class ChampionListItemAdapter extends RecyclerView.Adapter<ChampionListItemAdapter.ChampionListItemHolder> {
    public static Context context;
    public static List<StatisticsChampion> champs;

    public ChampionListItemAdapter(Context context, List<StatisticsChampion> champs) {
        if (champs == null) {
            throw new IllegalArgumentException("champs data null");
        }
        this.context = context;
        this.champs = champs;
    }

    @Override
    public int getItemCount() {
        return champs.size();
    }

    @Override
    public void onBindViewHolder(ChampionListItemHolder holder, int i) {
        StatisticsChampion champ = champs.get(i);

        holder.place.setText(("" + (i + 1)));
        Drawable portraitImage = context.getResources().getDrawable(
                context.getResources().getIdentifier(champ.champName, "drawable",
                        context.getApplicationContext().getPackageName()));
        holder.portrait.setImageDrawable(portraitImage);
        holder.winRateTextView.setText("" + champ.winrateString + "%");

        //progressBar max value is 100 bc max winrate is 60%; lowest is 40%

        int displayWinrate = (int) ((champ.winrate * 500.0) - 200.0);
        holder.winRateBar.setProgress(displayWinrate);

        holder.matchesTextView.setText("" + ((int) (champ.matches / 1000.0)) + "k");

        int displayPopularity = (int) (champ.popularity * 3333.0);
        holder.popularityBar.setProgress(displayPopularity);

        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/robotolight.ttf");

        holder.place.setTypeface(typeface);
        holder.winRateTextView.setTypeface(typeface);
        holder.matchesTextView.setTypeface(typeface);
        holder.winrateStatic.setTypeface(typeface);
        holder.matchesStatic.setTypeface(typeface);

    }

    @Override
    public ChampionListItemHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_list_item, viewGroup, false);
        ChampionListItemAdapter.ChampionListItemHolder viewHolder = new ChampionListItemHolder(itemView);
        return viewHolder;
    }

    public static class ChampionListItemHolder extends RecyclerView.ViewHolder {

        public TextView place;
        public ImageView portrait;
        public TextView winRateTextView;
        public ProgressBar winRateBar;
        public TextView matchesTextView;
        public ProgressBar popularityBar;

        public TextView winrateStatic;
        public TextView matchesStatic;

        public ChampionListItemHolder(View itemView) {
            super(itemView);
            place = (TextView) itemView.findViewById(R.id.position);
            portrait = (ImageView) itemView.findViewById(R.id.portrait);
            winRateTextView = (TextView) itemView.findViewById(R.id.winrateTextView);
            winRateBar = (ProgressBar) itemView.findViewById(R.id.winrateBar);
            matchesTextView = (TextView) itemView.findViewById(R.id.matchesTextView);
            popularityBar = (ProgressBar) itemView.findViewById(R.id.matchesBar);

            winrateStatic = (TextView) itemView.findViewById(R.id.winrateStatic);
            matchesStatic = (TextView) itemView.findViewById(R.id.matchesStatic);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = Integer.parseInt(place.getText().toString());

                    StatisticsChampion champion = champs.get(position-1);

                    String championName = champion.champName;
                    String winrate = "" + champion.winrateString + "%";
                    String popularity = "" + ((int) (champion.matches / 1000.0)) + "k";

                    ChampionListFragment.openChampionInfoActivity(championName, winrate, popularity);
                }
            });
        }
    }

}
