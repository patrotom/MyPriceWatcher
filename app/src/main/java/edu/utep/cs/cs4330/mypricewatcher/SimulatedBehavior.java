package edu.utep.cs.cs4330.mypricewatcher;
import java.util.Random;

/**
 * Represents one of the strategies to calculate a price of an item/product. This strategy simulates
 * a change in price of the item by using normally-distributed values generated using Java’s
 * {@code Random.nextGaussian} method.
 *
 * @author Tomas Patro
 * @version 0.4
 * @see PriceFindBehavior
 * @see ScraperBehavior
 * @see Random
 */
public class SimulatedBehavior implements PriceFindBehavior {
    /**
     * Simulates the finding of the price of the item by generating normally-distributed values.
     *
     * @param item represents the item for which we want to find the current price
     * @return current simulated price of the item
     * @see Item
     */
    @Override
    public Double findPrice(Item item) {
        Random r = new Random();
        Double val = Math.floor(r.nextGaussian() * 100 + 500);

        return val;
    }
}
