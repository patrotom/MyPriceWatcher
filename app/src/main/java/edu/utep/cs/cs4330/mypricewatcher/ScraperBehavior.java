package edu.utep.cs.cs4330.mypricewatcher;

/**
 * Represents one of the strategies to calculate a price of an item/product. In the current version,
 * this strategy is not implemented yet.
 *
 * @author Tomas Patro
 * @version 0.2
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
        /* TODO */
        return null;
    }
}
