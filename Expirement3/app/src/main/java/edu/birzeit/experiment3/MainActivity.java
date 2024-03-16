package edu.birzeit.experiment3;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button dial, gmail, maps, toast, notification, switchB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dial = findViewById(R.id.dial);
        gmail = findViewById(R.id.gmail);
        maps = findViewById(R.id.maps);
        toast = findViewById(R.id.toast);
        notification = findViewById(R.id.notification);
        switchB = findViewById(R.id.switchB);

        dial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dialInt = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+970 596"));
                startActivity(dialInt);
            }
        });

        gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gmailInt = new Intent(Intent.ACTION_SENDTO);
                gmailInt.setType("message/rfc822");
                gmailInt.setData(Uri.parse("mailto:"));
                gmailInt.putExtra(Intent.EXTRA_EMAIL, "momenssf@gmail.com");
                gmailInt.putExtra(Intent.EXTRA_SUBJECT, "1191592@student.birzeit.edu");
                gmailInt.putExtra(Intent.EXTRA_TEXT, "Text Message from expirement 3");
                startActivity(gmailInt);
            }
        });

        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapInt = new Intent(Intent.ACTION_VIEW);
                mapInt.setData(Uri.parse("geo:19.076,72.8777"));
                startActivity(mapInt);
            }
        });

        toast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toastInt = Toast.makeText(MainActivity.this, "TOAST MESSAGE", Toast.LENGTH_SHORT);
                toastInt.show();
            }
        });

        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNotification();
            }
        });

        switchB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchActivity = new Intent(MainActivity.this, SwitchActivity.class);
                MainActivity.this.startActivity(switchActivity);
                finish();
            }
        });
    }

    private static final String CHANNEL_ID = "my chanle 1";
    private static final String CHANNLE_NAME = "my chanle 1";
    private static final int NOTIFICATION_ID = 123;
    private static final String NOTIFICATION_TITLE = "Expirement 3 test";
    private static final String NOTIFICATION_BODY = "This is the body of expirement 3 notification done by momen assaf";

    private void createNotificationChannle() {
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channle = new NotificationChannel(CHANNEL_ID, CHANNLE_NAME, importance);
        NotificationManager manager = getSystemService(NotificationManager.class);
        if (manager != null) {
            manager.createNotificationChannel(channle);
        }
    }

    private void createNotification() {
        Intent notificationInt = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationInt, PendingIntent.FLAG_IMMUTABLE);

        createNotificationChannle();
        NotificationCompat.Builder build = new NotificationCompat.Builder(this, CHANNEL_ID).setSmallIcon(R.mipmap.ic_launcher).setContentTitle(NOTIFICATION_TITLE)
                .setContentText(NOTIFICATION_BODY).setStyle(new NotificationCompat.BigTextStyle().bigText(NOTIFICATION_BODY)).setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        notificationManager.notify(NOTIFICATION_ID, build.build());
    }

}