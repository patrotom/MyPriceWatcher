package edu.utep.cs.cs4330.mypricewatcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Pair;
import android.view.View;
import android.widget.Toast;

/**
 * Activity which is started when the background service {@code ConnectionCheckService} detects that
 * the device is not connected to the Internet.
 *
 * @author Tomas Patro
 * @version 0.4
 * @see ConnectionCheckService
 */
public class NoInternetActivity extends AppCompatActivity {
    private boolean retryAlreadyClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet);
        retryAlreadyClicked = false;
    }

    /**
     * Checks whether the Internet connection has been restored and returns user to the main page or
     * shows warning that there is still no Internet connection.
     *
     * @param view current view
     */
    public void retryClicked(View view) {
        if (!retryAlreadyClicked) {
            new AsyncTask<Context, Void, Pair<Context, Boolean>>() {
                protected void onPreExecute() {
                    retryAlreadyClicked = true;
                }
                protected Pair<Context, Boolean> doInBackground(Context... params) {
                    return new Pair<>(params[0], Utilities.isInternetWorking(params[0]));
                }
                protected void onPostExecute(Pair<Context, Boolean> result) {
                    if (result.second.booleanValue()) {
                        startService(new Intent(result.first, ConnectionCheckService.class));
                        startActivity(new Intent(result.first, MainActivity.class));
                    }
                    else {
                        makeToast();
                    }
                    retryAlreadyClicked = false;
                }
            }.execute(this);
        }
        else
            makeToast();
    }

    /**
     * Redirects user to the network settings of the device.
     *
     * @param view current view
     */
    public void settingsClicked(View view) {
        startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
    }

    private void makeToast() {
        Toast.makeText(this, "Still no Internet!", Toast.LENGTH_LONG).show();
    }
}
