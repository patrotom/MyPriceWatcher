package edu.utep.cs.cs4330.mypricewatcher;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ItemDatabaseHelper extends SQLiteOpenHelper{
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "price_watcher_db";
    private static final String ITEMS_TABLE = "items";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_URL = "url";
    private static final String KEY_INITIAL_PRICE = "initial_price";
    private static final String KEY_CURRENT_PRICE = "current_price";
    private static final String[] COLUMNS = { KEY_ID, KEY_NAME, KEY_URL, KEY_INITIAL_PRICE, KEY_CURRENT_PRICE };

    public ItemDatabaseHelper(Context context){
        super (context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String table = "CREATE TABLE " + ITEMS_TABLE + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_NAME + " TEXT, "
                + KEY_URL + " TEXT, "
                + KEY_INITIAL_PRICE + " REAL, "
                + KEY_CURRENT_PRICE + " REAL" + ")";
        db.execSQL(table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + ITEMS_TABLE);
        onCreate(database);
    }

    public void addItem(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, item.getName());
        values.put(KEY_URL, item.getUrl());
        values.put(KEY_INITIAL_PRICE, item.getInitialPrice());
        values.put(KEY_CURRENT_PRICE, item.getCurrentPrice());
        long id = db.insert(ITEMS_TABLE, null, values);
        item.setId((int) id);
        db.close();
    }

    public List<Item> allItems() {
        List<Item> items = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + ITEMS_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String url = cursor.getString(2);
                Double initialPrice = cursor.getDouble(3);
                Double currentPrice = cursor.getDouble(3);

                Item item = new Item(id, name, url);
                item.setInitialPrice(initialPrice);
                item.setCurrentPrice(currentPrice);
                items.add(item);
            } while (cursor.moveToNext());
        }
        return items;
    }

    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ITEMS_TABLE, null, new String[]{});
        db.close();
    }

    public void delete(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ITEMS_TABLE, KEY_ID + " = ?", new String[] { Integer.toString(id) } );
        db.close();
    }

    public void update(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, item.getName());
        values.put(KEY_URL, item.getUrl());
        values.put(KEY_INITIAL_PRICE, item.getInitialPrice());
        values.put(KEY_CURRENT_PRICE, item.getCurrentPrice());
        db.update(ITEMS_TABLE, values, KEY_ID + " = ?", new String[]{String.valueOf(item.getId())});
        db.close();
    }
}
