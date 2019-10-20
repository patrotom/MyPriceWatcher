package edu.utep.cs.cs4330.mypricewatcher;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ItemDetailFragment extends Fragment {
    public ItemDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_detail, container, false);
    }

    public void setInformation(String name, String url, Long initialPrice, Long currentPrice,
                               Double percentageChange) {
        View view = getView();
        TextView itemNameView = view.findViewById(R.id.itemNameView);
        TextView itemPriceView = view.findViewById(R.id.itemPriceView);
        TextView itemPriceChangeView = view.findViewById(R.id.itemPriceChangeView);
        TextView itemInitialInfoView = view.findViewById(R.id.itemInitialInfoView);

        itemNameView.setText(name);
        itemPriceView.setText(String.valueOf(currentPrice));

        if (percentageChange > 0.0) {
            itemPriceChangeView.setText(String.format("+" + "%.2f", percentageChange) + "%");
            itemPriceChangeView.setTextColor(Color.RED);
        }
        else if (percentageChange < 0.0) {
            itemPriceChangeView.setText(String.format("%.2f", percentageChange) + "%");
            itemPriceChangeView.setTextColor(Color.GREEN);
        }
        else
            itemPriceChangeView.setText(String.format("%.2f", percentageChange) + "%");


    }

}
