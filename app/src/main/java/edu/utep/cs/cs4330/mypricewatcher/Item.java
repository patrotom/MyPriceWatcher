package edu.utep.cs.cs4330.mypricewatcher;

public class Item {
    private String url, name;
    private Long initialPrice, currentPrice;
    private boolean initialPriceSet;

    Item(String name, String url) {
        this.url = url;
        this.name = name;
        initialPriceSet = false;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public Long getInitialPrice() {
        if (!initialPriceSet) {
            return new Long(0);
        }
        return initialPrice;
    }

    public Long getCurrentPrice() {
        return currentPrice;
    }

    public boolean getInitialPriceSet() {
        return initialPriceSet;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInitialPrice(Long initialPrice) {
        this.initialPrice = initialPrice;
    }

    public void setCurrentPrice(Long currentPrice) {
        this.currentPrice = currentPrice;
    }

    public void setInitialPriceSet(boolean initialPriceSet) {
        this.initialPriceSet = initialPriceSet;
    }
}
