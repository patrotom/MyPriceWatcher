package edu.utep.cs.cs4330.mypricewatcher;

public class PriceFinder {
    private Item initialItem;
    private PriceFindBehavior strategy;

    PriceFinder(PriceFindBehavior strategy, Item initialItem) {
        this.strategy = strategy;
        this.initialItem = initialItem;
        this.initialItem.setCurrentPrice(this.strategy.findPrice(this.initialItem));
    }

    public String getInitialItemUrl() {
        return initialItem.getUrl();
    }

    public String getInitialItemName() {
        return initialItem.getName();
    }

    public Long getCurrentPrice() {
        this.initialItem.setCurrentPrice(strategy.findPrice(initialItem));
        return initialItem.getCurrentPrice();
    }

    public Long getInitialPrice() {
        return initialItem.getInitialPrice();
    }

    public void setStarategy(PriceFindBehavior strategy) {
        this.strategy = strategy;
    }
}
