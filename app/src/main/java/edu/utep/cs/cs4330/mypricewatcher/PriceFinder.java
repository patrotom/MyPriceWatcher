package edu.utep.cs.cs4330.mypricewatcher;

/**
 * Represents the handler for the items/products. In the current version, this class only handles
 * the initial item. It uses different strategies from the strategy pattern to get the price of the
 * item/product.
 *
 * @author Tomas Patro
 * @version 0.1
 * @see PriceFindBehavior
 * @see SimulatedBehavior
 * @see ScraperBehavior
 */
public class PriceFinder {
    private Item initialItem;
    private PriceFindBehavior strategy;

    /**
     * Class constructor.
     *
     * @param strategy strategy which is used to calculate the price
     * @param initialItem initial item used to get its initial price, current price and percentage
     *                    change
     */
    PriceFinder(PriceFindBehavior strategy, Item initialItem) {
        this.strategy = strategy;
        this.initialItem = initialItem;
        this.initialItem.setCurrentPrice(this.strategy.findPrice(this.initialItem));
    }

    /**
     * Counts the percentage change between the initial and current price of the initial item.
     *
     * @return percentage change between the initial and current price of the initial item
     */
    public Double countPercentageChange() {
        return ((double)(initialItem.getCurrentPrice() - initialItem.getInitialPrice()) /
                (double)initialItem.getInitialPrice()) * 100;
    }

    /**
     * Standard getter method which returns the URL of the initial item.
     *
     * @return URL of the initial item
     */
    public String getInitialItemUrl() {
        return initialItem.getUrl();
    }

    /**
     * Standard getter method which returns the name of the initial item.
     *
     * @return name of the initial item
     */
    public String getInitialItemName() {
        return initialItem.getName();
    }

    /**
     * Standard getter method which returns the current price of the initial item. Before returning
     * the current price, it checks whether there is a change in the current price and sets the
     * new value.
     *
     * @return current price of the initial item
     */
    public Long getInitialItemCurrentPrice() {
        initialItem.setCurrentPrice(strategy.findPrice(initialItem));
        return initialItem.getCurrentPrice();
    }

    /**
     * Standard getter method which returns the initial price of the initial item.
     *
     * @return initial price of the initial item
     */
    public Long getInitialItemInitialPrice() {
        return initialItem.getInitialPrice();
    }

    /**
     * Standard setter method which sets a new strategy for calculating the price of the item.
     *
     * @param strategy designated strategy to be used to calculate a price of the item
     */
    public void setStarategy(PriceFindBehavior strategy) {
        this.strategy = strategy;
    }
}
