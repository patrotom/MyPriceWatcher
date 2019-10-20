package edu.utep.cs.cs4330.mypricewatcher;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ItemFormActivity extends AppCompatActivity {
    private EditText nameEditText;
    private EditText urlEditText;
    private TextView ediItemHeading;
    private String oldName;
    private boolean isNewItem;


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
