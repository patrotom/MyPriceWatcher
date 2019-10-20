package edu.utep.cs.cs4330.mypricewatcher;

/**
 * Represents the item which is the direct representation of the product with its price.
 *
 * @author Tomas Patro
 * @version 0.1
 */
public class Item {
    private String url, name;
    private Double initialPrice, currentPrice;

    /**
     * Class constructor.
     *
     * @param name name of the item/product
     * @param url URL of the item/product
     */
    Item(String name, String url) {
        this.url = url;
        this.name = name;
        this.initialPrice = new Double(0);
        this.currentPrice = new Double(0);
    }

    public boolean equals(Object obj){
        if (obj instanceof Item) {
            Item item = (Item) obj;
            return (item.name == this.name);
        }
        else {
            return false;
        }
    }

    public Double getPercentageChange() {
        return ((currentPrice - initialPrice) / initialPrice) * 100;
    }

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

    /**
     * Standard setter method which sets the value of the indicator whether the initial price
     * of the item/product has been already set or not.
     *
     * @param initialPriceSet new value of the indicator whether the initial price of the
     *                        item/product has been already set
     */
}
