package com.example.healthifyapp.services;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.healthifyapp.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {


    @Override
    public void onMessageReceived(@NonNull RemoteMessage remotemessage) {
        super.onMessageReceived(remotemessage);

        getFirebaseMessage(remotemessage.getNotification().getTitle(),remotemessage.getNotification().getBody());
    }

    public void getFirebaseMessage(String title,String msg){
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,"MyFirebasechanel")
                .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
                .setContentTitle(title)
                .setContentText(msg)
                .setAutoCancel(true);
        NotificationManagerCompat managerCompat=NotificationManagerCompat.from(this);
        managerCompat.notify(101,builder.build());

    }
}
