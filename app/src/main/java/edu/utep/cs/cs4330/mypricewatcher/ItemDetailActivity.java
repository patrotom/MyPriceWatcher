package edu.utep.cs.cs4330.mypricewatcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class ItemDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        Intent intent = getIntent();

        ItemDetailFragment fragment = (ItemDetailFragment)
                getSupportFragmentManager().findFragmentById(R.id.itemDetailFragment);

        fragment.setInformation(intent.getStringExtra("name"),
                intent.getStringExtra("url"),
                intent.getLongExtra("initialPrice", 0),
                intent.getLongExtra("currentPrice", 0),
                intent.getDoubleExtra("percentageChange", 0.0));
    }
}
