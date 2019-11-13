package edu.utep.cs.cs4330.mypricewatcher;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Represents activity which handles getting the information to create new item or edit the
 * existing one.
 *
 * @author Tomas Patro
 * @version 0.3
 * @see MainActivity
 */
public class ItemFormActivity extends AppCompatActivity {
    private EditText nameEditText;
    private EditText urlEditText;
    private TextView editItemHeading;
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
        editItemHeading = findViewById(R.id.editItemHeading);

        Intent intent = getIntent();

        id = intent.getIntExtra("id", -1);
        itemManager = new ItemManager(new SimulatedBehavior(), this);

        if (id == -1) {
            editItemHeading.setText("Add Item");
        }
        else {
            Item item = itemManager.getItem(id);
            urlEditText.setText(item.getUrl());
            nameEditText.setText(item.getName());
        }
    }

    /**
     * Handles the click event from the submit button and returns input data back to the previous
     * activity.
     *
     * @param view current view
     */
    public void submitClicked(View view) {
        Intent returnIntent = new Intent();
        String name = String.valueOf(nameEditText.getText());
        String url = String.valueOf(urlEditText.getText());

        if (id == -1) {
            id = itemManager.addItem(name, url);
        }
        else {
            Item item = itemManager.getItem(id);
            itemManager.updateItem(item, name, url);
        }

        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}
