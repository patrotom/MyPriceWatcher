package edu.utep.cs.cs4330.mypricewatcher;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ItemsListAdapter extends ArrayAdapter<Item> {
    private ArrayList<Item> items;
    private Context context;

    ItemsListAdapter(Context context, ArrayList<Item> data) {
        super(context, R.layout.fragment_item_detail, data);
        this.items = data;
        this.context = context;
    }

    @Override
    @NonNull
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View rowView = inflater.inflate(R.layout.fragment_item_detail, parent,false);

        TextView itemNameView = rowView.findViewById(R.id.itemNameView);
        TextView itemPriceView = rowView.findViewById(R.id.itemPriceView);
        TextView itemPriceChangeView = rowView.findViewById(R.id.itemPriceChangeView);
        TextView itemInitialInfoView = rowView.findViewById(R.id.itemInitialInfoView);

        Item item = items.get(position);

        itemNameView.setText(item.getName());
        itemPriceView.setText(String.format("$%.2f", item.getCurrentPrice()));

        if (item.getPercentageChange() > 0.0) {
            itemPriceChangeView.setText(String.format("+" + "%.2f",
                    item.getPercentageChange()) + "%");
            itemPriceChangeView.setTextColor(Color.RED);
        }
        else if (item.getPercentageChange() < 0.0) {
            itemPriceChangeView.setText(String.format("%.2f",
                    item.getPercentageChange()) + "%");
            itemPriceChangeView.setTextColor(ContextCompat.getColor(context,
                    R.color.colorPrimaryDark));
        }
        else
            itemPriceChangeView.setText(String.format("%.2f",
                    item.getPercentageChange()) + "%");

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        itemInitialInfoView.setText(String.format("%s ($%.2f)", sdf.format(new Date()),
                item.getInitialPrice()));

        return rowView;
    }

}
