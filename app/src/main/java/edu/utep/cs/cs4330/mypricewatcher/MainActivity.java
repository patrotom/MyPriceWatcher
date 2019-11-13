package edu.utep.cs.cs4330.mypricewatcher;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
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
 * @version 0.3
 * @see ItemManager
 * @see SimulatedBehavior
 */
public class MainActivity extends AppCompatActivity {
    private ItemManager itemManager;
    private ItemsListAdapter itemsListAdapter;
    private ListView itemListView;

    /**
     * Method which is called when the activity is created. It is used to initialize graphic
     * components of the application alongside the private attributes of the class. It is also used
     * to initialize URL of the initial item which can be derived from the external source using the
     * {@link Intent} class. Otherwise, the default value is being used which is
     * <a href="https://www.utep.edu/">UTEP's main website</a>.
     *
     * @param savedInstanceState an attribute representing the saved state of the current instance
     *                           of the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        refreshList();

        checkForExternalUrlSource();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                itemManager.updateAllPrices();
                refreshList();
                itemsListAdapter.notifyDataSetChanged();
                return true;
            case R.id.action_add_item:
                Intent intent = new Intent(this, ItemFormActivity.class);
                intent.putExtra("id", -1);
                startActivityForResult(intent, 1);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == Activity.RESULT_OK) {
                    refreshList();
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
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)
                item.getMenuInfo();
        Item i = itemsListAdapter.getItem(info.position);
        switch (item.getItemId()) {
            case R.id.action_delete:
                AlertDialog diaBox = AskOption(i);
                diaBox.show();
                return true;
            case R.id.action_open_browser:
                if (Patterns.WEB_URL.matcher(i.getUrl()).matches()) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(i.getUrl()));
                    startActivity(browserIntent);
                }
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void itemClicked(AdapterView<?> parent, final View view, int position, long id) {
        Item item = (Item) parent.getItemAtPosition(position);

        if (item != null) {
            Intent intent = new Intent(this, ItemFormActivity.class);
            intent.putExtra("id", item.getId());

            startActivityForResult(intent, 1);
        }
    }

    private void checkForExternalUrlSource() {
        String action = getIntent().getAction();
        String type = getIntent().getType();

        if (Intent.ACTION_SEND.equalsIgnoreCase(action) && type != null &&
                "text/plain".equals(type)) {
            String url = getIntent().getStringExtra(Intent.EXTRA_TEXT);
            Intent intent = new Intent(this, ItemFormActivity.class);
            intent.putExtra("isNewItem", true);
            intent.putExtra("url", url);
            startActivityForResult(intent, 2);
        }
    }

    private AlertDialog AskOption(Item item) {
        AlertDialog deleteDialogBox = new AlertDialog.Builder(this)
                .setTitle("Delete")
                .setMessage("Are you sure you want to delete this item?")
                .setIcon(R.drawable.delete_icon)
                .setPositiveButton("Delete", (DialogInterface dialog, int whichButton) -> {
                    itemManager.removeItem(item.getId());
                    refreshList();
                    itemsListAdapter.notifyDataSetChanged();
                    dialog.dismiss();
                })
                .setNegativeButton("Cancel", (DialogInterface dialog, int which) ->
                        dialog.dismiss())
                .create();

        return deleteDialogBox;
    }

    private void refreshList() {
        itemManager = new ItemManager(new SimulatedBehavior(), this);

        itemsListAdapter = new ItemsListAdapter(this,
                itemManager.getItems());

        itemListView = findViewById(R.id.itemListView);
        itemListView.setAdapter(itemsListAdapter);

        registerForContextMenu(itemListView);
        itemListView.setOnItemClickListener(this::itemClicked);
    }

}
