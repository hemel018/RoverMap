package com.rana.rovermap.fcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.rana.rovermap.MapsActivity;
import com.rana.rovermap.R;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by rana on 5/23/17.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        String image = remoteMessage.getNotification().getIcon();
        String title = remoteMessage.getNotification().getTitle();
        String text = remoteMessage.getNotification().getBody();
        String sound = remoteMessage.getNotification().getSound();

        int id = 0;
        Object obj = remoteMessage.getData().get("id");
        if(obj != null)
        {
            id = Integer.valueOf(obj.toString());
        }

        sendNotification(new NotificationData(image, id, title, text, sound));

    }

    private void sendNotification(NotificationData notification)
    {
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra(NotificationData.TEXT, notification.getTextMessage());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notificationBuilder = null;

        try {
            notificationBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.ic_gesture_black_24dp)
                    .setContentTitle(URLDecoder.decode(notification.getTitle(), "UTF-8"))
                    .setContentText(URLDecoder.decode(notification.getTextMessage(), "UTF-8"))
                    .setAutoCancel(true)
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                    .setContentIntent(pendingIntent);
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        if(notificationBuilder != null)
        {
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(notification.getId(), notificationBuilder.build());
        }

    }
}
