package edu.utep.cs.cs4330.mypricewatcher;

import android.content.Context;

import java.util.List;

/**
 * Represents the handler for the items/products. It uses different strategies from the strategy
 * pattern to get the price of the item/product.
 *
 * @author Tomas Patro
 * @version 0.3
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
    ItemManager(PriceFindBehavior priceFindBehavior, Context context) {
        this.priceFindBehavior = priceFindBehavior;
        dbHelper = new ItemDatabaseHelper(context);
    }

    /**
     * Adds a new item to the database and returns the ID of the added item.
     *
     * @param name name of the new item
     * @param url url of the new item
     * @return
     */
    public int addItem(String name, String url) {
        Item item = new Item(name, url);

        Double price = priceFindBehavior.findPrice(item);

        if ((int)Math.round(price) < 0.0)
            return (int)Math.round(price);

        item.setInitialPrice(price);
        item.setCurrentPrice(price);

        return dbHelper.addItem(item);
    }

    /**
     * Updates current prices of all items in the database.
     */
    public void updateAllPrices() {
        for (Item item: getItems()) {
            item.setCurrentPrice(priceFindBehavior.findPrice(item));
            dbHelper.update(item);
        }
    }

    /**
     * Returns a particular item from the database based on the given ID.
     *
     * @param id id of the item to be retrieved from the database
     * @return
     */
    public Item getItem(int id) {
        return dbHelper.getItem(id);
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
     * Removes given item from the database.
     *
     * @param id id of the item to be removed
     */
    public void removeItem(int id) {
        dbHelper.delete(id);
    }

    /**
     * Updates the given item with the new values.
     *
     * @param item item to be updated
     * @param name new name for the item
     * @param url new url for the item
     */
    public int updateItem(Item item, String name, String url) {
        item.setName(name);
        item.setUrl(url);
        dbHelper.update(item);
        return item.getId();
    }
}
