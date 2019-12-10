package edu.utep.cs.cs4330.mypricewatcher;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Represents activity which handles getting the information to create new item or edit the
 * existing one.
 *
 * @author Tomas Patro
 * @version 0.4
 * @see MainActivity
 */
public class ItemFormActivity extends AppCompatActivity {
    private EditText nameEditText;
    private EditText urlEditText;
    private ItemManager itemManager;
    private int id;

    /**
     * Method which is called when the activity is created. It checks whether we are creating new
     * item or editing existing one and adjust the attributes.
     *
     * @param savedInstanceState an attribute representing the saved state of the current instance
     *                           of the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_form);

        nameEditText = findViewById(R.id.nameEditText);
        urlEditText = findViewById(R.id.urlEditText);
        TextView editItemHeading = findViewById(R.id.editItemHeading);

        Intent intent = getIntent();

        id = intent.getIntExtra("id", -1);
        String url = intent.getStringExtra("url");
        itemManager = new ItemManager(new ScraperBehavior(), this);

        if (id == -1) {
            editItemHeading.setText("Add Item");
            if (url != null)
                urlEditText.setText(url);
        }
        else {
            Item item = itemManager.getItem(id);
            urlEditText.setText(item.getUrl());
            nameEditText.setText(item.getName());
        }
    }

    /**
     * Handles the click event from the submit button and persists a new item or shows appropriate
     * error message.
     *
     * @param view current view
     */
    public void submitClicked(View view) {
        String name = String.valueOf(nameEditText.getText());
        String url = String.valueOf(urlEditText.getText());

        Pair<Integer,String> validationResult = Item.validate(name, url);

        if (validationResult.first != 0) {
            makeToast(validationResult.second);
            return;
        }

        ProgressBar itemProgressBar = findViewById(R.id.itemProgressBar);
        Button submitButton = findViewById(R.id.submitButton);
        new AsyncTask<Integer, Void, Integer>() {
            protected void onPreExecute() {
                itemProgressBar.setVisibility(View.VISIBLE);
                submitButton.setEnabled(false);
            }
            protected Integer doInBackground(Integer... params) {
                if (params[0] == -1)
                    id = itemManager.addItem(name, url);
                else {
                    Item item = itemManager.getItem(params[0]);
                    id = itemManager.updateItem(item, name, url);
                }
                return id;
            }
            protected void onPostExecute(Integer id) {
                itemProgressBar.setVisibility(View.GONE);
                submitButton.setEnabled(true);
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        }.execute(id);
    }

    private void makeToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
