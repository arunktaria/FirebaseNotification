package com.example.firebasenotification;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;

public class NotificationBuilderClss extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        NotificationBuilderClss();
    }

    public void NotificationBuilderClss() {

        NotificationChannel notificationChannel=new NotificationChannel("test","test", NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager manager=getSystemService(NotificationManager.class);
        manager.createNotificationChannel(notificationChannel);

    }
}
