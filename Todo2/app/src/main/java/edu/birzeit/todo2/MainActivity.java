package edu.birzeit.todo2;

import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private NotificationDbHelper dbHelper;

    EditText editTitle = findViewById(R.id.editTextID);
    EditText editBody = findViewById(R.id.editTextName);
    EditText editID = findViewById(R.id.editTextText3);

    Button createNot = findViewById(R.id.button);
    Button displayNot = findViewById(R.id.button4);
    Button clearNot = findViewById(R.id.button5);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dbHelper = new NotificationDbHelper(this);

        createNot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertNotification(editTitle.getText().toString(),editBody.getText().toString());
            }
        });

        displayNot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayNotification();
            }
        });

        clearNot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearNotifications();
            }
        });
    }

    private void insertNotification(String title, String body) {
        if (!title.isEmpty() && !body.isEmpty()) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("title", title);
            values.put("body", body);
            long newRowId = db.insert("notifications", null, values);
            if (newRowId == -1) {
                Toast.makeText(this, "Error inserting", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Inserted successfully", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "Title and body are empty", Toast.LENGTH_SHORT).show();
        }
    }

    private void displayNotification() {
        String idString = editID.getText().toString();

        if (!idString.isEmpty()) {
            int id = Integer.parseInt(idString);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.query(
                    "notifications",
                    null,
                    "id = ?",
                    new String[]{idString},
                    null,
                    null,
                    null
            );
            if (cursor != null && cursor.moveToFirst()) {
                int titleIndex = cursor.getColumnIndex("title");
                int bodyIndex = cursor.getColumnIndex("body");

                if (titleIndex != -1 && bodyIndex != -1) {
                    String title = cursor.getString(titleIndex);
                    String body = cursor.getString(bodyIndex);

                    // Build the notification
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channel_id")
                            .setSmallIcon(R.drawable.ic_launcher_foreground)
                            .setContentTitle(title)
                            .setContentText(body)
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                    // Show the notification
                    NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    if (notificationManager != null) {
                        notificationManager.notify(id, builder.build());
                    }
                } else {
                    Toast.makeText(this, "Notification not found", Toast.LENGTH_SHORT).show();
                }
                if (cursor != null) {
                    cursor.close();
                }

            } else {
                Toast.makeText(this, "Notification ID cannot be empty", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void clearNotifications() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("notifications", null, null);
        Toast.makeText(this, "Notifications cleared", Toast.LENGTH_SHORT).show();
    }
}