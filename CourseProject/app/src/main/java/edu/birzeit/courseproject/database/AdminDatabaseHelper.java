package edu.birzeit.courseproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import edu.birzeit.courseproject.models.Admin;

public class AdminDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "admins.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_ADMINS = "admins";
    private static final String TAG = "AdminDatabaseHelper";

    public AdminDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ADMINS_TABLE = "CREATE TABLE " + TABLE_ADMINS + " (" +
                "email TEXT PRIMARY KEY, " +
                "phone TEXT, " +
                "firstName TEXT, " +
                "lastName TEXT, " +
                "gender TEXT, " +
                "password TEXT)";
        db.execSQL(CREATE_ADMINS_TABLE);

        Admin defaultAdmin = new Admin("momenassaf20@gmail.com", "0599674742", "Admin", "one", "Male", hashPassword("password123"));
        addDefaultAdmin(db, defaultAdmin);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADMINS);
        onCreate(db);
    }

    private void addDefaultAdmin(SQLiteDatabase db, Admin admin) {
        ContentValues values = new ContentValues();
        values.put("email", admin.getEmail());
        values.put("phone", admin.getPhone());
        values.put("firstName", admin.getFirstName());
        values.put("lastName", admin.getLastName());
        values.put("gender", admin.getGender());
        values.put("password",admin.getPassword());

        long result = db.insert(TABLE_ADMINS, null, values);
        if (result != -1) {
            Log.d(TAG, "Default admin added successfully");
        } else {
            Log.d(TAG, "Failed to add default admin");
        }
    }

    public boolean addAdmin(Admin admin) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email", admin.getEmail());
        values.put("phone", admin.getPhone());
        values.put("firstName", admin.getFirstName());
        values.put("lastName", admin.getLastName());
        values.put("gender", admin.getGender());
        values.put("password", admin.getPassword());

        long result = db.insert(TABLE_ADMINS, null, values);
        db.close();
        Log.d(TAG, "addAdmin: result=" + result);
        return result != -1;
    }

    public Admin getAdmin(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ADMINS, new String[]{"email", "phone", "firstName", "lastName", "gender", "password"},
                "email=? AND password=?", new String[]{email, password}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            Admin admin = new Admin(
                    cursor.getString(cursor.getColumnIndexOrThrow("email")),
                    cursor.getString(cursor.getColumnIndexOrThrow("phone")),
                    cursor.getString(cursor.getColumnIndexOrThrow("firstName")),
                    cursor.getString(cursor.getColumnIndexOrThrow("lastName")),
                    cursor.getString(cursor.getColumnIndexOrThrow("gender")),
                    cursor.getString(cursor.getColumnIndexOrThrow("password"))
            );
            cursor.close();
            return admin;
        } else {
            if (cursor != null) {
                cursor.close();
            }
            return null;
        }
    }

    public Admin getAdminByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ADMINS, new String[]{"email", "phone", "firstName", "lastName", "gender", "password"},
                "email=?", new String[]{email}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            Admin admin = new Admin(
                    cursor.getString(cursor.getColumnIndexOrThrow("email")),
                    cursor.getString(cursor.getColumnIndexOrThrow("phone")),
                    cursor.getString(cursor.getColumnIndexOrThrow("firstName")),
                    cursor.getString(cursor.getColumnIndexOrThrow("lastName")),
                    cursor.getString(cursor.getColumnIndexOrThrow("gender")),
                    cursor.getString(cursor.getColumnIndexOrThrow("password"))
            );
            cursor.close();
            return admin;
        } else {
            if (cursor != null) {
                cursor.close();
            }
            return null;
        }
    }

    public boolean updateAdmin(Admin admin) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("phone", admin.getPhone());
        values.put("firstName", admin.getFirstName());
        values.put("lastName", admin.getLastName());
        values.put("gender", admin.getGender());
        values.put("password", admin.getPassword());

        int result = db.update(TABLE_ADMINS, values, "email=?", new String[]{admin.getEmail()});
        db.close();
        Log.d(TAG, "updateAdmin: result=" + result);
        return result > 0;
    }

    public boolean removeAdmin(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_ADMINS, "email=?", new String[]{email});
        db.close();
        Log.d(TAG, "removeAdmin: result=" + result);
        return result > 0;
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
