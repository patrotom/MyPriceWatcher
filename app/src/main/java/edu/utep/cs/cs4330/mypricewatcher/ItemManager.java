package edu.utep.cs.cs4330.mypricewatcher;

import java.util.ArrayList;
import java.util.List;

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
public class ItemManager {
    private ItemDatabaseHelper dbHelper;
    private PriceFindBehavior priceFindBehavior;

    /**
     * Class constructor.
     *
     * @param priceFindBehavior strategy which is used to calculate the price
     */
    ItemManager(PriceFindBehavior priceFindBehavior) {
        this.priceFindBehavior = priceFindBehavior;
    }

    /**
     * Adds new item to the list. If the item with the same name already exists, returns false.
     *
     * @param item new item to be added to the list
     * @return
     */
    public boolean addItem(String name, String url) {
        Item item = new Item(name, url);

        Double price = priceFindBehavior.findPrice(item);
        item.setInitialPrice(price);
        item.setCurrentPrice(price);

        dbHelper.addItem(item);
        return true;
    }

    /**
     * Updates current prices of all items in the list.
     */
    public void updateAllPrices() {
        for (Item item: getItems()) {
            item.setCurrentPrice(priceFindBehavior.findPrice(item));
            dbHelper.update(item);
        }
    }

    /**
     * Returns the list containing all the items.
     *
     * @return list of items
     */
    public List<Item> getItems() {
        return dbHelper.allItems();
    }

    /**
     * Removes given item from the list. If the item cannot be found, returns false.
     *
     * @param item item to be removed
     * @return indicator of the success/failure of the remove operation
     */
    public void removeItem(int id) {
        dbHelper.delete(id);
    }

    /**
     * Replaces the name of the given item with the given string. If an item with the given name
     * already exists, returns false.
     *
     * @param item item to be renamed
     * @param newName new name for the item
     * @return indicator of the success/failure of the rename operation
     */
    public void updateItem(Item item, String name, String url) {
        item.setName(name);
        item.setUrl(url);
        dbHelper.update(item);
    }
}
