package edu.utep.cs.cs4330.mypricewatcher;

public class PriceFinder {
    private Item initialItem;
    private PriceFindBehavior strategy;

    PriceFinder(PriceFindBehavior strategy, Item initialItem) {
        this.strategy = strategy;
        this.initialItem = initialItem;
        this.initialItem.setCurrentPrice(this.strategy.findPrice(this.initialItem));
    }

    public Double countPercentageChange() {
        return ((double)(initialItem.getCurrentPrice() - initialItem.getInitialPrice()) / (double)initialItem.getInitialPrice()) * 100;
    }

    public String getInitialItemUrl() {
        return initialItem.getUrl();
    }

    public String getInitialItemName() {
        return initialItem.getName();
    }

    public Long getInitialItemCurrentPrice() {
        initialItem.setCurrentPrice(strategy.findPrice(initialItem));
        return initialItem.getCurrentPrice();
    }

    public Long getInitialItemInitialPrice() {
        return initialItem.getInitialPrice();
    }

    public void setStarategy(PriceFindBehavior strategy) {
        this.strategy = strategy;
    }
}
