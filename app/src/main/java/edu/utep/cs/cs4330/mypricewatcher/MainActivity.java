package edu.utep.cs.cs4330.mypricewatcher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

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
    private ItemsListAdapter itemsListAdapter;

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

        itemsListAdapter = new ItemsListAdapter(this,
                priceFinder.getItems());

        ListView itemListView = findViewById(R.id.itemListView);
        itemListView.setAdapter(itemsListAdapter);

        registerForContextMenu(itemListView);
        itemListView.setOnItemClickListener(this::itemClicked);

        checkForExternalUrlSource();
    }

    private void itemClicked(AdapterView<?> parent, final View view, int position, long id) {
        Item item = (Item) parent.getItemAtPosition(position);

        if (item != null) {
            Intent intent = new Intent(this, ItemEditActivity.class);
            intent.putExtra("name", item.getName());
            intent.putExtra("url", item.getUrl());
            intent.putExtra("isNewItem", false);

            startActivityForResult(intent, 1);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                priceFinder.updateData();
                itemsListAdapter.notifyDataSetChanged();
                return true;
            case R.id.action_add_item:
                Intent intent = new Intent(this, ItemEditActivity.class);
                intent.putExtra("isNewItem", true);
                startActivityForResult(intent, 2);
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case 1:
                if (resultCode == Activity.RESULT_OK) {
                    String oldName = data.getStringExtra("oldName");
                    String newName = data.getStringExtra("name");
                    String url = data.getStringExtra("url");

                    Item item = priceFinder.getItemByName(oldName);

                    if (priceFinder.renameItem(item, newName)) {
                        item.setUrl(url);
                        itemsListAdapter.notifyDataSetChanged();
                    }
                }
                break;
            case 2:
                if (resultCode == Activity.RESULT_OK) {
                    if (priceFinder.addItem(new Item(data.getStringExtra("name"),
                            data.getStringExtra("url"))))
                        itemsListAdapter.notifyDataSetChanged();
                }
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo ) {
        getMenuInflater().inflate(R.menu.menu_context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)
                        item.getMenuInfo();

                if (priceFinder.removeItem(itemsListAdapter.getItem(info.position)))
                    itemsListAdapter.notifyDataSetChanged();

                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void checkForExternalUrlSource() {
        String action = getIntent().getAction();
        String type = getIntent().getType();

        if (Intent.ACTION_SEND.equalsIgnoreCase(action) && type != null &&
                "text/plain".equals(type)) {
            String url = getIntent().getStringExtra(Intent.EXTRA_TEXT);
            Intent intent = new Intent(this, ItemEditActivity.class);
            intent.putExtra("isNewItem", true);
            intent.putExtra("url", url);
            startActivityForResult(intent, 2);
        }
    }
}
