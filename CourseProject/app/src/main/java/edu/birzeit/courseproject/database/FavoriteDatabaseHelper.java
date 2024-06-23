package edu.birzeit.courseproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import edu.birzeit.courseproject.models.Favorite;

public class FavoriteDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "favorites.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_FAVORITES = "favorites";
    private static final String TAG = "FavoriteDatabaseHelper";

    public FavoriteDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FAVORITES_TABLE = "CREATE TABLE " + TABLE_FAVORITES + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "pizzaName TEXT, " +
                "userEmail TEXT, " +
                "dateAdded INTEGER)";
        db.execSQL(CREATE_FAVORITES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITES);
        onCreate(db);
    }

    public boolean addFavorite(Favorite favorite) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("pizzaName", favorite.getPizzaName());
        values.put("userEmail", favorite.getUserEmail());
        values.put("dateAdded", favorite.getDateAdded());

        long result = db.insert(TABLE_FAVORITES, null, values);
        db.close();
        Log.d(TAG, "addFavorite: result=" + result);
        return result != -1;
    }

    public ArrayList<Favorite> getUserFavorites(String userEmail) {
        ArrayList<Favorite> favorites = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_FAVORITES, new String[]{"id", "pizzaName", "userEmail", "dateAdded"},
                "userEmail=?", new String[]{userEmail}, null, null, "dateAdded DESC");

        if (cursor.moveToFirst()) {
            do {
                Favorite favorite = new Favorite(
                        cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("pizzaName")),
                        cursor.getString(cursor.getColumnIndexOrThrow("userEmail")),
                        cursor.getLong(cursor.getColumnIndexOrThrow("dateAdded"))
                );
                favorites.add(favorite);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return favorites;
    }

    public boolean updateFavorite(int id, String pizzaName, String userEmail, long dateAdded) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("pizzaName", pizzaName);
        values.put("userEmail", userEmail);
        values.put("dateAdded", dateAdded);

        int result = db.update(TABLE_FAVORITES, values, "id=?", new String[]{String.valueOf(id)});
        db.close();
        Log.d(TAG, "updateFavorite: result=" + result);
        return result > 0;
    }

    public boolean removeFavorite(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_FAVORITES, "id=?", new String[]{String.valueOf(id)});
        db.close();
        Log.d(TAG, "removeFavorite: result=" + result);
        return result > 0;
    }
}
