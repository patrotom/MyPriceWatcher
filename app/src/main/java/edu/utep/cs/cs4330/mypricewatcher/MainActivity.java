package edu.utep.cs.cs4330.mypricewatcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Represents the main activity of the application which represents the first and main screen shown
 * to user after they run the application.
 *
 * @author Tomas Patro
 * @version 0.1
 * @see PriceFinder
 * @see SimulatedBehavior
 */
public class MainActivity extends AppCompatActivity {
    private TextView nameView, initialPriceView, currentPriceView, percentageChangeView;
    private Button updatePriceButton, openItemWebsiteButton;
    private PriceFinder priceFinder;

    /**
     * Method which is called when the activity is created. It is used to initialize graphic
     * components of the application alongside the private attributes of the class. It is also used
     * to initialize URL of the initial item which can be derived from the external source using the
     * {@link Intent} class. Otherwise, the default value is being used which is
     * <a href="https://www.utep.edu/">UTEP's main website</a>.
     *
     * @param savedInstanceState an attribute representing the saved state of the current instance
     *                           of the activity
     * @see Intent
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        nameView = findViewById(R.id.nameView);
//        initialPriceView = findViewById(R.id.initialPriceView);
//        currentPriceView = findViewById(R.id.currentPriceView);
//        percentageChangeView = findViewById(R.id.percentageChangeView);
//        updatePriceButton = findViewById(R.id.updatePriceButton);
//        openItemWebsiteButton = findViewById(R.id.openItemWebsiteButton);
//
//        String action = getIntent().getAction();
//        String type = getIntent().getType();
//        String url;
//
//        if (Intent.ACTION_SEND.equalsIgnoreCase(action) && type != null &&
//                "text/plain".equals(type))
//            url = getIntent().getStringExtra(Intent.EXTRA_TEXT);
//        else
//            url = "https://www.utep.edu/";
//
//        priceFinder = new PriceFinder(new SimulatedBehavior(), new Item("Dummy Item", url));
//
//        nameView.setText(priceFinder.getInitialItemName());
//        initialPriceView.setText(String.valueOf(priceFinder.getInitialItemInitialPrice()));
//
//        currentPriceView.setText("-");
//        percentageChangeView.setText("-");
    }

    /**
     * Handler for the click event on the {@code updatePriceButton} button. It sets the text of the
     * {@code currentPriceView} to the value of current price of the initial item and text of the
     * {@code initialPriceView} to the value of initial price of the initial item. It also updates
     * the percentage change between the initial and current price and set this value to the
     * {@code percentageChangeView} view.
     *
     * @param view current view where the {@code updatePriceButton} is located in
     */
    public void updatePriceClicked(View view) {
        currentPriceView.setText(String.valueOf(priceFinder.getInitialItemCurrentPrice()));
        initialPriceView.setText(String.valueOf(priceFinder.getInitialItemInitialPrice()));

        Double percentageChange = priceFinder.countPercentageChange();

        if (percentageChange > 0.0)
            percentageChangeView.setText(String.format("+" + "%.2f", percentageChange) + "%");
        else
            percentageChangeView.setText(String.format("%.2f", percentageChange) + "%");
    }

    /**
     * Handler for the click event on the {@code openItemWebsiteButton} button. It opens the browser
     * and loads the website with the URL of the initial item.
     *
     * @param view current view where the {@code openItemWebsiteButton} is located in
     */
    public void openItemWebsiteClicked(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(priceFinder.getInitialItemUrl()));
        startActivity(browserIntent);
    }
}
