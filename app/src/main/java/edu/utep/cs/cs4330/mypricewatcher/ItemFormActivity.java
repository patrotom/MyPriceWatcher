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
 * @version 0.2
 * @see MainActivity
 */
public class ItemFormActivity extends AppCompatActivity {
    private EditText nameEditText;
    private EditText urlEditText;
    private TextView ediItemHeading;
    private String oldName;
    private boolean isNewItem;


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
        ediItemHeading = findViewById(R.id.editItemHeading);

        Intent intent = getIntent();

        isNewItem = intent.getBooleanExtra("isNewItem", true);

        String url = intent.getStringExtra("url");

        if (url != null)
            urlEditText.setText(intent.getStringExtra("url"));

        if (!isNewItem) {
            oldName = intent.getStringExtra("name");
            nameEditText.setText(oldName);
        }
        else {
            ediItemHeading.setText("Add Item");
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
        returnIntent.putExtra("name", String.valueOf(nameEditText.getText()));
        if (!isNewItem)
            returnIntent.putExtra("oldName", oldName);
        returnIntent.putExtra("url", String.valueOf(urlEditText.getText()));
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }
}
