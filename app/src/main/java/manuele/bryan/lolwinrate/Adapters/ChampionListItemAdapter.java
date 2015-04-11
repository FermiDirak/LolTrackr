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

import java.io.IOException;
import java.util.List;

import manuele.bryan.lolwinrate.Fragments.ChampionListFragment;
import manuele.bryan.lolwinrate.Helpers.ProgressBarAnimation;
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

        Drawable portraitImage = null;

        try {
            portraitImage = Drawable.createFromStream(context.getAssets().open("images/champicons/" + champ.champName + ".png"), null);
            holder.portrait.setImageDrawable(portraitImage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        holder.winRateTextView.setText("" + champ.winrateString + "%");

        //progressBar max value is 100 bc max winrate is 60%; lowest is 40%

        int displayWinrate = (int) ((champ.winrate * 500.0) - 200.0);

        ProgressBarAnimation progressBarAnimationWinRate = new ProgressBarAnimation(holder.winRateBar, 0, displayWinrate);
        progressBarAnimationWinRate.setDuration(500);
        holder.winRateBar.startAnimation(progressBarAnimationWinRate);

        holder.matchesTextView.setText("" + ((int) (champ.matches / 1000.0)) + "k");

        int displayPopularity = (int) (champ.popularity * 3333.0);

        ProgressBarAnimation progressBarAnimationPopularity = new ProgressBarAnimation(holder.popularityBar, 0, displayPopularity);
        progressBarAnimationPopularity.setDuration(500);
        holder.popularityBar.startAnimation(progressBarAnimationPopularity);

    }

    @Override
    public ChampionListItemHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_list_item, viewGroup, false);
        ChampionListItemAdapter.ChampionListItemHolder viewHolder = new ChampionListItemHolder(context, itemView);
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

        public ChampionListItemHolder(Context context, View itemView) {
            super(itemView);
            place = (TextView) itemView.findViewById(R.id.position);
            portrait = (ImageView) itemView.findViewById(R.id.portrait);
            winRateTextView = (TextView) itemView.findViewById(R.id.winrateTextView);
            winRateBar = (ProgressBar) itemView.findViewById(R.id.winrateBar);
            matchesTextView = (TextView) itemView.findViewById(R.id.matchesTextView);
            popularityBar = (ProgressBar) itemView.findViewById(R.id.matchesBar);

            winrateStatic = (TextView) itemView.findViewById(R.id.winrateStatic);
            matchesStatic = (TextView) itemView.findViewById(R.id.matchesStatic);

            Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/robotolight.ttf");

            place.setTypeface(typeface);
            winRateTextView.setTypeface(typeface);
            matchesTextView.setTypeface(typeface);
            winrateStatic.setTypeface(typeface);
            matchesStatic.setTypeface(typeface);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = Integer.parseInt(place.getText().toString());

                    StatisticsChampion champion = champs.get(position-1);

                    String championName = champion.champName;
                    String winrate = "" + champion.winrateString + "%";
                    String popularity = "" + ((int) (champion.matches / 1000.0)) + "k";

                    ChampionListFragment.openChampionInfoFragment(championName, winrate, popularity);
                }
            });
        }
    }

}
