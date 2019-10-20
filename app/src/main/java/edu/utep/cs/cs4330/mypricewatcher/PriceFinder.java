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
    private ArrayList<Item> items;
    private PriceFindBehavior priceFindBehavior;

    /**
     * Class constructor.
     *
     * @param priceFindBehavior strategy which is used to calculate the price
     */
    PriceFinder(PriceFindBehavior priceFindBehavior) {
        this.priceFindBehavior = priceFindBehavior;
        items = new ArrayList<>();
    }

    public void addItem(Item item) {
        if (getItemByName(item.getName()) != null)
            return;

        Long price = priceFindBehavior.findPrice(item);
        item.setInitialPrice(price);
        item.setCurrentPrice(price);

        items.add(item);
    }

    public List<String> names() {
        List<String> names = new ArrayList<>(items.size());
        for (Item i: items) {
            names.add(i.getName());
        }
        return names;
    }

    public void updateData() {
        for (Item item: items)
            item.setCurrentPrice(priceFindBehavior.findPrice(item));
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public Item getItemByName(String name) {
        for (Item i: items)
            if (i.getName() == name)
                return i;
        return null;
    }
}
