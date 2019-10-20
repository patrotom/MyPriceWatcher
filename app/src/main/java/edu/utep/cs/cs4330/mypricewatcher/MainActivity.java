package edu.utep.cs.cs4330.mypricewatcher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        priceFinder = new PriceFinder(new SimulatedBehavior());

        ItemListFragment fragment = (ItemListFragment)
                getSupportFragmentManager().findFragmentById(R.id.itemListFragment);
        fragment.setListener(this::itemClicked);
    }

    private void itemClicked(String name) {

    }
}
