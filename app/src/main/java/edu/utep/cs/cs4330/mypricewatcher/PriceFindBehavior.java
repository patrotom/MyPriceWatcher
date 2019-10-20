package edu.utep.cs.cs4330.mypricewatcher;

/**
 * Interface which is representing the behavior of the finding the price of the product. It is used
 * in the strategy pattern for defining different strategies of the finding the price of the
 * product.
 *
 * @author Tomas Patro
 * @version 0.1
 * @see SimulatedBehavior
 * @see ScraperBehavior
 */
public interface PriceFindBehavior {
    /**
     * Interface method for finding the price of the product. Each strategy implementing this
     * interface has to implement this method.
     *
     * @param item represents the item for which we want to find the current price
     * @return current price of the product
     * @see Item
     */
    Double findPrice(Item item);
}
