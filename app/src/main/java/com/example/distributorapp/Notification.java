package com.example.distributorapp;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.util.Log;

public class Notification extends Application {

    public static final String ordernotification ="OrderNotification";
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("Notification class","chala");



        createNotificationchannels();
    }

    public void createNotificationchannels(){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            Log.e("Notification method","chala");
            NotificationChannel OrderNotification = new NotificationChannel( ordernotification, " Order notification" , NotificationManager.IMPORTANCE_HIGH);
            OrderNotification.setDescription("New Order Created");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(OrderNotification);


        }
    }
}

