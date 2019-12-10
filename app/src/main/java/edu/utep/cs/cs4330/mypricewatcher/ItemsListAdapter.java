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
import java.util.List;

/**
 * Represents custom list view adapter, which is used to display the added items.
 *
 * @author Tomas Patro
 * @version 0.4
 * @see MainActivity
 */
public class ItemsListAdapter extends ArrayAdapter<Item> {
    private List<Item> items;
    private Context context;

    /**
     * Class constructor.
     *
     * @param context current context of the application
     * @param data list of the items to be displayed
     */
    ItemsListAdapter(Context context, List<Item> data) {
        super(context, R.layout.fragment_item_detail, data);
        this.items = data;
        this.context = context;
    }

    /**
     * Sets data of the particular item to the views representing particular attributes of the item.
     *
     * @param position numerical position of the item in the menu
     * @param view current processed view
     * @param parent parent view group
     * @return
     */
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
