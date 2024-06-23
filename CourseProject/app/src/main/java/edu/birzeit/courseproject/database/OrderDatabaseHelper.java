package edu.birzeit.courseproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import edu.birzeit.courseproject.models.Order;

public class OrderDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "orders.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_ORDERS = "orders";
    private static final String TAG = "OrderDatabaseHelper";

    public OrderDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ORDERS_TABLE = "CREATE TABLE " + TABLE_ORDERS + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "pizzaName TEXT, " +
                "userEmail TEXT, " +
                "date INTEGER)";
        db.execSQL(CREATE_ORDERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDERS);
        onCreate(db);
    }

    public boolean addOrder(Order order) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("pizzaName", order.getPizzaName());
        values.put("userEmail", order.getUserEmail());
        values.put("date", order.getDate());

        long result = db.insert(TABLE_ORDERS, null, values);
        db.close();
        Log.d(TAG, "addOrder: result=" + result);
        return result != -1;
    }

    public ArrayList<Order> getUserOrders(String userEmail) {
        ArrayList<Order> orders = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ORDERS, new String[]{"id", "pizzaName", "userEmail", "date"},
                "userEmail=?", new String[]{userEmail}, null, null, "date DESC");

        if (cursor.moveToFirst()) {
            do {
                Order order = new Order(
                        cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("pizzaName")),
                        cursor.getString(cursor.getColumnIndexOrThrow("userEmail")),
                        cursor.getLong(cursor.getColumnIndexOrThrow("date"))
                );
                orders.add(order);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return orders;
    }

    public boolean updateOrder(int id, String pizzaName, String userEmail, long date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("pizzaName", pizzaName);
        values.put("userEmail", userEmail);
        values.put("date", date);

        int result = db.update(TABLE_ORDERS, values, "id=?", new String[]{String.valueOf(id)});
        db.close();
        Log.d(TAG, "updateOrder: result=" + result);
        return result > 0;
    }

    public boolean removeOrder(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_ORDERS, "id=?", new String[]{String.valueOf(id)});
        db.close();
        Log.d(TAG, "removeOrder: result=" + result);
        return result > 0;
    }
}
