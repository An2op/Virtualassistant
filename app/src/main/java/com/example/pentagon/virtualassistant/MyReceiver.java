package com.example.pentagon.virtualassistant;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MyReceiver extends BroadcastReceiver {

    public static String CHANNEL_ID="VA";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
//        if(intent.getIntExtra("id",-1)==null)
//            return;
     int id=intent.getIntExtra("id",-1);
        String event=intent.getStringExtra("Event");
        String type=intent.getStringExtra("type");
        Intent intent1 = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent1, 0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.logoround)
                .setContentTitle("Today Event")
                .setColor(Color.rgb(1,1,1))
                .setContentText(event)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        mBuilder.setSound(alarmSound);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

// notificationId is a unique int for each notification that you must define
        notificationManager.notify(id, mBuilder.build());
        Toast.makeText(context, "Alarm....", Toast.LENGTH_LONG).show();

        if(type.equals("Daily"))
        {
            DataEvent dd=new DataEvent();
            dd.setType(type);
            dd.setId(id);
            dd.setDesc(event);
            Calendar c = Calendar.getInstance();
            dd.setDate(c.getTime().toString());
            String day=intent.getStringExtra("day");
            ArrayList<String> mylist=new ArrayList<>();
            mylist.add(day);

            Utility.setAlarmdaily(mylist,dd,context,1);

        }else if(type.equals("Monthly")){

            DataEvent dd=new DataEvent();
            dd.setType(type);
            dd.setId(id);
            dd.setDesc(event);
            Calendar c = Calendar.getInstance();
            dd.setDate(c.getTime().toString());
            String day=intent.getStringExtra("day");
            ArrayList<String> mylist=new ArrayList<>();
            mylist.add(day);

            Utility.setAlarmMonthly(mylist,dd,context,1);


        }
        else if(type.equals("Yearly")){

            DataEvent dd=new DataEvent();
            dd.setType(type);
            dd.setId(id);
            dd.setDesc(event);
            Date ddd=Utility.convertStringToDate(intent.getStringExtra("datetime"));
            Calendar c = Calendar.getInstance();
            //Setting the date to the given date
            c.setTime(ddd);
            c.add(Calendar.YEAR,1);
            dd.setDate(c.getTime().toString());
            String day=intent.getStringExtra("day");
            ArrayList<String> mylist=new ArrayList<>();
            mylist.add(day);

            Utility.setAlarmOnce(dd,context);


        }


    }
}
