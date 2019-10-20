package edu.utep.cs.cs4330.mypricewatcher;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemsListAdapter extends ArrayAdapter<Item> {
    ArrayList<Item> items;
    Context context;

    public ItemsListAdapter(Context context, ArrayList<Item> data) {
        super(context, R.layout.fragment_item_detail, data);
        this.items = data;
        this.context = context;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View rowView = inflater.inflate(R.layout.fragment_item_detail, parent,false);

        TextView itemNameView = rowView.findViewById(R.id.itemNameView);
        TextView itemPriceView = rowView.findViewById(R.id.itemPriceView);
        TextView itemPriceChangeView = rowView.findViewById(R.id.itemPriceChangeView);
        TextView itemInitialInfoView = rowView.findViewById(R.id.itemInitialInfoView);

        itemNameView.setText(items.get(position).getName());
        itemPriceView.setText(String.valueOf(items.get(position).getCurrentPrice()));

        if (items.get(position).getPercentageChange() > 0.0) {
            itemPriceChangeView.setText(String.format("+" + "%.2f",
                    items.get(position).getPercentageChange()) + "%");
            itemPriceChangeView.setTextColor(Color.RED);
        }
        else if (items.get(position).getPercentageChange() < 0.0) {
            itemPriceChangeView.setText(String.format("%.2f",
                    items.get(position).getPercentageChange()) + "%");
            itemPriceChangeView.setTextColor(Color.GREEN);
        }
        else
            itemPriceChangeView.setText(String.format("%.2f",
                    items.get(position).getPercentageChange()) + "%");

        return rowView;
    }

}
