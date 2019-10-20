package edu.utep.cs.cs4330.mypricewatcher;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ItemEditActivity extends AppCompatActivity {
    private EditText nameEditText;
    private EditText urlEditText;
    private Button submitEditButton;
    private String oldName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_edit);

        nameEditText = findViewById(R.id.nameEditText);
        urlEditText = findViewById(R.id.urlEditText);
        submitEditButton = findViewById(R.id.submitEditButton);

        Intent intent = getIntent();

        oldName = intent.getStringExtra("name");
        nameEditText.setText(oldName);
        urlEditText.setText(intent.getStringExtra("url"));
    }

    public void submitEditClicked(View view) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("name", String.valueOf(nameEditText.getText()));
        returnIntent.putExtra("oldName", oldName);
        returnIntent.putExtra("url", String.valueOf(urlEditText.getText()));
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }
}
