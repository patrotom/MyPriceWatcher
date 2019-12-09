package edu.utep.cs.cs4330.mypricewatcher;

import android.util.Pair;

import java.net.URL;
import java.util.ArrayList;

/**
 * Represents the item which is the direct representation of the product with its price.
 *
 * @author Tomas Patro
 * @version 0.3
 */
public class Item {
    private int id;
    private String url, name;
    private Double initialPrice, currentPrice;

    /**
     * Class constructor.
     *
     * @param name name of the item/product
     * @param url URL of the item/product
     */
    Item(String name, String url) {
        this(0, name, url);
    }

    /**
     * Class constructor.
     *
     * @param id id of the item/product
     * @param name name of the item/product
     * @param url URL of the item/product
     */
    Item(int id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.initialPrice = new Double(0);
        this.currentPrice = new Double(0);
    }

    /**
     * Counts percentage change between initial and current price.
     *
     * @return percentage change between initial and current price
     */
    public Double getPercentageChange() {
        return ((currentPrice - initialPrice) / initialPrice) * 100;
    }

    /**
     * Standard getter method which returns the unique ID of the item/product.
     *
     * @return ID of the item/product
     */
    public int getId() { return id; }

    /**
     * Standard getter method which returns the URL of the item/product.
     *
     * @return URL of the item/product
     */
    public String getUrl() {
        return url;
    }

    /**
     * Standard getter method which returns the name of the item/product.
     *
     * @return name of the item/product
     */
    public String getName() {
        return name;
    }

    /**
     * Standard getter method which returns the initial price of the item/product. If the initial
     * price has not been set yet, returns 0.
     *
     * @return initial price of the item/product
     */
    public Double getInitialPrice() {
        return initialPrice;
    }

    /**
     * Standard getter method which returns the current price of the item/product.
     *
     * @return current price of the item/product
     */
    public Double getCurrentPrice() {
        return currentPrice;
    }

    /**
     * Standard setter method which sets the value of the ID of the item/product.
     *
     * @param id new value of the ID to be set
     */
    public void setId(int id) { this.id = id; }

    /**
     * Standard setter method which sets the value of the URL of the item/product.
     *
     * @param url new value of the URL to be set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Standard setter method which sets the value of the name of the item/product.
     *
     * @param name new value of the name to be set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Standard setter method which sets the value of the initial price of the item/product.
     *
     * @param initialPrice new value of the initial price to be set
     */
    public void setInitialPrice(Double initialPrice) {
        this.initialPrice = initialPrice;
    }

    /**
     * Standard setter method which sets the value of the current price of the item/product.
     *
     * @param currentPrice new value of the current price to be set
     */
    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public static Pair<Integer,String> validate(String name, String url) {

        if (name.equals(""))
            return new Pair<>(1, "Name can not be empty!");

        ArrayList<String> supportedStores = new ArrayList<String>() {
            {
                add("bestbuy.com");
                add("homedepot.com");
            }
        };

        try {
            URL u = new URL(url);
            if (!supportedStores.contains(u.getHost().replace("www.", "")))
                return new Pair<>(3, "Unsupported store!");
        }
        catch (Exception e) {
            return new Pair<>(2, "Invalid URL!");
        }

        return new Pair<>(0, "");
    }
}
