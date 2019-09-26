package edu.utep.cs.cs4330.mypricewatcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView nameView, initialPriceView, currentPriceView, percentageChangeView;
    private Button updatePriceButton, openItemWebsiteButton;
    private PriceFinder priceFinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameView = findViewById(R.id.nameView);
        initialPriceView = findViewById(R.id.initialPriceView);
        currentPriceView = findViewById(R.id.currentPriceView);
        percentageChangeView = findViewById(R.id.percentageChangeView);
        updatePriceButton = findViewById(R.id.updatePriceButton);
        openItemWebsiteButton = findViewById(R.id.openItemWebsiteButton);

        priceFinder = new PriceFinder(new SimulatedBehavior(), new Item("Dummy Item", "https://www.utep.edu/"));

        nameView.setText(priceFinder.getInitialItemName());
        initialPriceView.setText(String.valueOf(priceFinder.getInitialItemInitialPrice()));

        currentPriceView.setText("-");
        percentageChangeView.setText("-");
    }

    public void updatePriceClicked(View view) {
        currentPriceView.setText(String.valueOf(priceFinder.getInitialItemCurrentPrice()));
        initialPriceView.setText(String.valueOf(priceFinder.getInitialItemInitialPrice()));

        Double percentageChange = priceFinder.countPercentageChange();

        if (percentageChange > 0.0)
            percentageChangeView.setText(String.format("+" + "%.2f", percentageChange) + "%");
        else
            percentageChangeView.setText(String.format("%.2f", percentageChange) + "%");
    }

    public void openItemWebsiteClicked(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(priceFinder.getInitialItemUrl()));
        startActivity(browserIntent);
    }
}
