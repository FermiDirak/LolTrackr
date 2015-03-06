package manuele.bryan.lolwinrate.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import manuele.bryan.lolwinrate.Items.DrawerItem;
import manuele.bryan.lolwinrate.R;

public class DrawerAdapter extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater;

    List<DrawerItem> drawerItemList;

    public DrawerAdapter(Context context, List<DrawerItem> drawerItemList) {
        this.context = context;
        this.drawerItemList = drawerItemList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return drawerItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return drawerItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View itemView, ViewGroup parent) {
        DrawerItemHolder holder;

        if (itemView == null) {
            itemView = layoutInflater.inflate(R.layout.drawer_item, null);
            holder = new DrawerItemHolder(itemView);
            itemView.setTag(holder);
        } else {
            holder = (DrawerItemHolder) itemView.getTag();
        }

        DrawerItem drawerItem = drawerItemList.get(position);

        if (drawerItem.currentlySelected) {
            holder.backgroundLayout.setBackgroundColor(context.getResources().getColor(
                    R.color.darkergray
            ));
        }

        holder.itemName.setText(drawerItem.itemName);
        holder.icon.setImageDrawable(itemView.getResources()
                .getDrawable(drawerItem.imgId));

        return itemView;
    }

    private static final class DrawerItemHolder {
        LinearLayout backgroundLayout;
        TextView itemName;
        ImageView icon;

        DrawerItemHolder(View view) {
            backgroundLayout = (LinearLayout) view.findViewById(R.id.itemLayout);
            itemName = (TextView) view.findViewById(R.id.drawer_itemName);
            icon = (ImageView) view.findViewById(R.id.drawer_icon);
        }
    }
}
