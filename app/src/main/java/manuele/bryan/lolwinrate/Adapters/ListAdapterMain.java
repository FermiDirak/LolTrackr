package manuele.bryan.lolwinrate.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import manuele.bryan.lolwinrate.LolStatistics.Champion;
import manuele.bryan.lolwinrate.R;

public class ListAdapterMain extends BaseAdapter {
    public Context context;
    public List<Champion> champs;

    private LayoutInflater layoutInflater;

    public ListAdapterMain(Context context, List<Champion> champs) {
        if (champs == null) {
            throw new IllegalArgumentException("champs data null");
        }
        this.context = context;
        this.champs = champs;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return champs.size();
    }

    @Override
    public Object getItem(int position) {
        return champs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View itemView, ViewGroup parent) {
        ViewHolder holder;

        if (itemView == null) {
            itemView = layoutInflater.inflate(R.layout.main_list_item, null);
            holder = new ViewHolder(itemView);
            itemView.setTag(holder);
        } else {
            holder = (ViewHolder) itemView.getTag();
        }

        Champion champ = champs.get(position);

        holder.place.setText(("" + (position + 1)));
        Drawable portraitImage = context.getResources().getDrawable(
                context.getResources().getIdentifier(champ.champName, "drawable",
                        context.getApplicationContext().getPackageName()));
        holder.portrait.setImageDrawable(portraitImage);
        holder.winRateTextView.setText("" + champ.winrate + "%");

        //progressBar max value is 100 bc max winrate is 60%; lowest is 40%
        holder.winRateBar.setProgress((int) ((champ.winrate * 500.0) - 200.0));

        holder.matchesTextView.setText("" + champ.matches);
        holder.popularityBar.setProgress((int) (100.0 * champ.popularity));

        return itemView;
    }

    public static final class ViewHolder {
        TextView place;
        ImageView portrait;
        TextView winRateTextView;
        ProgressBar winRateBar;
        TextView matchesTextView;
        ProgressBar popularityBar;

        public ViewHolder(View itemView) {
            place = (TextView) itemView.findViewById(R.id.position);
            portrait = (ImageView) itemView.findViewById(R.id.portrait);
            winRateTextView = (TextView) itemView.findViewById(R.id.winrateTextView);
            winRateBar = (ProgressBar) itemView.findViewById(R.id.winrateBar);
            matchesTextView = (TextView) itemView.findViewById(R.id.matchesTextView);
            popularityBar = (ProgressBar) itemView.findViewById(R.id.matchesBar);
        }
    }

}
