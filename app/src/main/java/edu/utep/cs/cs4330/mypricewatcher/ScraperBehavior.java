package edu.utep.cs.cs4330.mypricewatcher;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
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
            URL url = new URL(item.getUrl());
            String html = urlToHtmlString(url);

            if (html == null)
                return -1.0;

            Document doc = Jsoup.parse(html);

            switch (url.getHost().replace("www.", "")) {
                case "bestbuy.com":
                    return getBestBuyPrice(doc);
                case "homedepot.com":
                    return getHomeDepotPrice(doc);
                default:
                    return -1.0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1.0;
        }
    }

    private Double getBestBuyPrice(Document doc) {
        return Double.valueOf(doc.
                getElementsByClass("priceView-hero-price priceView-customer-price").
                first().getElementsByTag("span").first().text().
                replace("$", ""));
    }

    private Double getHomeDepotPrice(Document doc) {
        Elements priceElements = doc.getElementById("ajaxPrice").getAllElements();
        double price;
        if (priceElements.size() > 1) {
            double dollars = Double.valueOf(priceElements.get(2).text());
            double cents = Double.valueOf(priceElements.get(3).text()) / 100.00;
            price = dollars + cents;
        }
        else
            price = Double.valueOf(priceElements.first().text().replace("$", ""));

        return price;
    }

    private String urlToHtmlString(URL url) {
        try {
            URLConnection connection = url.openConnection();
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
