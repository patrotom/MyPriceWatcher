package edu.utep.cs.cs4330.mypricewatcher;

import java.util.ArrayList;
import java.util.List;

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
    private static ArrayList<Item> items;
    private PriceFindBehavior strategy;

    /**
     * Class constructor.
     *
     * @param strategy strategy which is used to calculate the price
     */
    PriceFinder(PriceFindBehavior strategy) {
        this.strategy = strategy;
        items = new ArrayList<>();
    }

    /**
     * Counts the percentage change between the initial and current price of the initial item.
     *
     * @return percentage change between the initial and current price of the initial item
     */
    public Double percentageChange(Item item) {
        return ((double)(item.getCurrentPrice() - item.getInitialPrice()) /
                (double)item.getInitialPrice()) * 100;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public static List<String> names() {
        List<String> names = new ArrayList<>(items.size());
        for (Item i: items) {
            names.add(i.getName());
        }
        return names;
    }
}
