package com.example.notificationapp;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            PackageManager PackageManager = null;
            if (checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS}, 1);
            }
        }
    }

    public void notificationButton(View view) {
        final String CHANNEL_ID = "1"; // Match this with the channel created in onCreate()

        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.image_icon);

        Intent activityIntent = new Intent(this, video.class);
        PendingIntent playContentIntent = PendingIntent.getActivity(this, 0, activityIntent, PendingIntent.FLAG_IMMUTABLE);

        Intent activityCancelIntent = new Intent(this, video.class);
        PendingIntent cancelContentIntent = PendingIntent.getActivity(this, 0, activityCancelIntent, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.baseline_notifications_active_24)
                .setContentTitle("Instagram")
                .setContentText("adityasawant_7 notify")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("This is a sample notification with more text content."))
                .setLargeIcon(largeIcon)
                .addAction(R.mipmap.ic_launcher, "Play", playContentIntent)
                .addAction(R.mipmap.ic_launcher, "Cancel", cancelContentIntent)
                .setColor(Color.RED)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        MediaPlayer notificationSound = MediaPlayer.create(MainActivity.this, R.raw.notification);
        notificationSound.start();

        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.notify(1, builder.build());
    }
}