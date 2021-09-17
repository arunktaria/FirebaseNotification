package com.example.firebasenotification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {
    EditText title, des;
    Button shownotificationbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        title = findViewById(R.id.title);
        des = findViewById(R.id.des);
        shownotificationbtn = findViewById(R.id.btnnotify);


        getSupportActionBar().setSubtitle("Notification App");

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("notification", "notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        FirebaseMessaging.getInstance().subscribeToTopic("logged")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "can recieve general msg!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "failed to get Message", Toast.LENGTH_SHORT).show();
                        }

                    }
                });


        shownotificationbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNotification(title.getText().toString(), des.getText().toString());
            }
        });





    }

    private void showNotification(String titlestr, String desstr) {
        Notification notification = new NotificationCompat.Builder(this, "test")
                .setContentTitle(titlestr)
                .setContentText(desstr)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.userimg1))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setSmallIcon(R.drawable.ic_launcher_foreground).build();

       NotificationManagerCompat managerCompat=NotificationManagerCompat.from(MainActivity.this);
        managerCompat.notify(103,notification);

    }

   /* private void showNotification1(String titlestr, String desstr) {
       NotificationChannel channel = new NotificationChannel("notification", "notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
    }*/


}