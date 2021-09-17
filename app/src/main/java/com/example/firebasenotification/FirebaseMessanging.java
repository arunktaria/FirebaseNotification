package com.example.firebasenotification;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FirebaseMessanging extends FirebaseMessagingService {
    Bitmap bitmap;
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
       bitmap=BitmapFactory.decodeResource(getResources(),R.drawable.userimg1);
        showMessage(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());


    }

    void showMessage(String title, String des) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setLargeIcon(bitmap)
                .setContentTitle(title)
                .setContentText(des);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(100, builder.build());
    }
}
