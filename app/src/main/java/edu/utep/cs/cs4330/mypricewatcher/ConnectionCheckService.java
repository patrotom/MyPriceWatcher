package edu.utep.cs.cs4330.mypricewatcher;

import android.app.IntentService;
import android.content.Intent;

/**
 * Simple intent service that runs in the background and checks whether the device is connected to
 * the Internet.
 *
 * @author Tomas Patro
 * @version 0.4
 * @see NoInternetActivity
 */
public class ConnectionCheckService extends IntentService {

    /**
     * Class constructor.
     */
    public ConnectionCheckService() {
        super("ConnectionCheckService");
    }

    /**
     * Defined behavior of the service. This method runs in the background when the service starts.
     *
     * @param intent input intent
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        while (true) {
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (!Utilities.isInternetWorking(this))
                break;
        }
        startActivity(new Intent(this, NoInternetActivity.class));
    }

}
