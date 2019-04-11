package com.reynaldiwijaya.simplenotification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText edtTitle, edtContent;
    public static final int NOTIFICATION_ID = 1;
    public static String CHANNEL_ID = "channel_01";
    public static CharSequence CHANNEL_NAME = "idn channel";
    private Uri alarmSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtTitle = findViewById(R.id.edt_title);
        edtContent = findViewById(R.id.edt_content);

        alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    }

    public void sendNotification(View view) {
        // Mensetting Notification Manager
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_add_alert_black_24dp)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_add_alert_black_24dp))
                .setContentTitle(edtTitle.getText().toString())
                .setContentText(edtContent.getText().toString())
                .setSound(alarmSound)
                .setSubText("Aldi Ganteng Banget")
                .setAutoCancel(true);

        // Untuk android oreo ke atas perlu menambahkan notification channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            mBuilder.setChannelId(CHANNEL_ID);
            if (mNotificationManager != null) {
                mNotificationManager.createNotificationChannel(channel);
            }
        }

        Notification mNotification = mBuilder.build();
        if (mNotificationManager != null) {
            mNotificationManager.notify(NOTIFICATION_ID, mNotification);
        }
    }
}
