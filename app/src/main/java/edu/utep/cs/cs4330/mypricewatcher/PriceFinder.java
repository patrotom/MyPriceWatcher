package edu.utep.cs.cs4330.mypricewatcher;

public class PriceFinder {
    private Item initialItem;
    private PriceFindBehavior strategy;

    PriceFinder(PriceFindBehavior strategy, Item initialItem) {
        this.strategy = strategy;
        this.initialItem = initialItem;
//        this.initialItem.setCurrentPrice(strategy.findPrice(initialItem));
    }

    public Long getCurrentPrice() {
        return initialItem.getCurrentPrice();
    }

    public Long getInitialPrice() {
        return initialItem.getInitialPrice();
    }

    public void setStarategy(PriceFindBehavior strategy) {
        this.strategy = strategy;
    }
}
