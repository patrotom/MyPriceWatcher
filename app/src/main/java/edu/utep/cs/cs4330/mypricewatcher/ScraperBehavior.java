package edu.utep.cs.cs4330.mypricewatcher;

import android.util.Log;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

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


            Document doc = Jsoup.parse(urlToHtmlString(item.getUrl()));
            Double price = Double.valueOf(doc.
                    getElementsByClass("priceView-hero-price priceView-customer-price").
                    first().getElementsByTag("span").first().text().
                    replace("$", ""));

            return price;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String urlToHtmlString(String url) {
        try {
            URLConnection connection = (new URL(url)).openConnection();
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.connect();

            InputStream in = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder html = new StringBuilder();
            for (String line; (line = reader.readLine()) != null; ) {
                html.append(line);
            }
            in.close();
            return html.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
