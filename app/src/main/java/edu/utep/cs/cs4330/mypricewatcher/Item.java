package edu.utep.cs.cs4330.mypricewatcher;

public class Item {
    private String url;
    private Long initialPrice;
    private Long currentPrice;
    private boolean initialPriceSet;

    Item(String url) {
        this.url = url;
        initialPriceSet = false;
    }

    public String getUrl() {
        return url;
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
