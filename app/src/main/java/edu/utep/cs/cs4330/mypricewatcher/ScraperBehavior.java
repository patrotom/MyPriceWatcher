package edu.utep.cs.cs4330.mypricewatcher;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Represents one of the strategies to calculate a price of an item/product. In the current version,
 * this strategy is not implemented yet.
 *
 * @author Tomas Patro
 * @version 0.3
 * @see PriceFindBehavior
 * @see SimulatedBehavior
 */
public class ScraperBehavior implements PriceFindBehavior {
    /**
     * Do not use this method yet since it is not implemented yet.
     *
     * @param item represents the item for which we want to find the current price
     * @return current price of the item. In the current version, only {@code null} value.
     * @see Item
     */
    @Override
    public Double findPrice(Item item) {
        try {
            String url = getUrlContent(item.getUrl());
            // TODO
            return 0.0;
        } catch (Exception e) {
            Log.w("SC", e.getMessage());
            return null;
        }
    }

    public String getUrlContent(String urlStr) {
        try {
            URL url = new URL(urlStr);
            URLConnection con = null;
            con = url.openConnection();
            String encoding = con.getContentEncoding();
            if (encoding == null) {
                encoding = "ISO-8859-1";
            }
            BufferedReader r = new BufferedReader(new InputStreamReader(con.getInputStream(),
                    encoding));
            StringBuilder sb = new StringBuilder();
            try {
                String s;
                while ((s = r.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
            } finally {
                r.close();
            }
            return sb.toString();
        } catch (IOException ex) {
            return "";
        }
    }
}
