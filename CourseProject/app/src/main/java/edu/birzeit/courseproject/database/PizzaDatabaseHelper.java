package edu.birzeit.courseproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import edu.birzeit.courseproject.models.PizzaType;

import java.util.ArrayList;

public class PizzaDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "pizzas.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_PIZZAS = "pizzas";
    private static final String TAG = "PizzaDatabaseHelper";

    public PizzaDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PIZZAS_TABLE = "CREATE TABLE " + TABLE_PIZZAS + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "price REAL, " +
                "category TEXT)";
        db.execSQL(CREATE_PIZZAS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PIZZAS);
        onCreate(db);
    }

    public boolean addPizzaType(PizzaType pizzaType) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", pizzaType.getName());
        values.put("price", pizzaType.getPrice());
        values.put("category", pizzaType.getCategory());

        long result = db.insert(TABLE_PIZZAS, null, values);
        db.close();
        Log.d(TAG, "addPizzaType: result=" + result);
        return result != -1;
    }

    public ArrayList<PizzaType> getAllPizzaTypes() {
        ArrayList<PizzaType> pizzaList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PIZZAS, null);

        if (cursor.moveToFirst()) {
            do {
                PizzaType pizzaType = new PizzaType(
                        cursor.getString(cursor.getColumnIndexOrThrow("name")),
                        cursor.getDouble(cursor.getColumnIndexOrThrow("price")),
                        cursor.getString(cursor.getColumnIndexOrThrow("category"))
                );
                pizzaList.add(pizzaType);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return pizzaList;
    }
}
