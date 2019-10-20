package edu.utep.cs.cs4330.mypricewatcher;

import java.util.ArrayList;

/**
 * Represents the handler for the items/products. It uses different strategies from the strategy
 * pattern to get the price of the item/product.
 *
 * @author Tomas Patro
 * @version 0.2
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

    /**
     * Adds new item to the list. If the item with the same name already exists, returns false.
     *
     * @param item new item to be added to the list
     * @return
     */
    public boolean addItem(Item item) {
        if (getItemByName(item.getName()) != null)
            return false;

        Double price = priceFindBehavior.findPrice(item);
        item.setInitialPrice(price);
        item.setCurrentPrice(price);

        items.add(item);
        return true;
    }

    /**
     * Updates current prices of all items in the list.
     */
    public void updateData() {
        for (Item item: items)
            item.setCurrentPrice(priceFindBehavior.findPrice(item));
    }

    /**
     * Returns the list containing all the items.
     *
     * @return list of items
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * Returns item with the specified name. If the item cannot be found returns, {@code null}.
     *
     * @param name name of the item we look for
     * @return item with the specified name or null
     */
    public Item getItemByName(String name) {
        for (Item i: items)
            if (i.getName().equals(name))
                return i;
        return null;
    }

    /**
     * Removes given item from the list. If the item cannot be found, returns false.
     *
     * @param item item to be removed
     * @return indicator of the success/failure of the remove operation
     */
    public boolean removeItem(Item item) {
        return items.remove(item);
    }

    /**
     * Replaces the name of the given item with the given string. If an item with the given name
     * already exists, returns false.
     *
     * @param item item to be renamed
     * @param newName new name for the item
     * @return indicator of the success/failure of the rename operation
     */
    public boolean renameItem(Item item, String newName) {
        if (getItemByName(newName) != null || item == null)
            return false;

        item.setName(newName);

        return true;
    }
}
