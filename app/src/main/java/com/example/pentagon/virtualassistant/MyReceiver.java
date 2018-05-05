package com.example.pentagon.virtualassistant;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
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
        int id = intent.getIntExtra("id", -1);
        String event = intent.getStringExtra("Event");
        String type = intent.getStringExtra("type");
        Log.e("count","ddd");

  try {
      Intent intent1 = new Intent(context, MainActivity.class);
      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
      PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent1, 0);

      NotificationCompat.Builder mBuilder = null;

      mBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
              .setSmallIcon(R.drawable.ic_stat_name)
              .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),
                      R.mipmap.ic_launcher))
              .setContentTitle("Today Event")
              .setColor(Color.rgb(1, 1, 1))
              .setContentText(event)

              // Set the intent that will fire when the user taps the notification

              .setAutoCancel(true);
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
          mBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
      } else {

          mBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
      }

//      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//          mBuilder.setPriority(Notification.PRIORITY_HIGH);
//      }
//      if (Build.VERSION.SDK_INT >= 21) mBuilder.setVibrate(new long[0]);
      Intent resultIntent = new Intent(context, MainActivity.class);
      TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
      stackBuilder.addParentStack(MainActivity.class);

// Adds the Intent that starts the Activity to the top of the stack
      stackBuilder.addNextIntent(resultIntent);
      PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
      mBuilder.setContentIntent(resultPendingIntent);
      //  .setContentIntent(pendingIntent)

      Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
      mBuilder.setSound(alarmSound);
      NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

// notificationId is a unique int for each notification that you must define
      notificationManager.notify(id, mBuilder.build());
      // Toast.makeText(context, "Alarm....", Toast.LENGTH_LONG).show();

      if (type.equals("Daily")) {
          DataEvent dd = new DataEvent();
          dd.setType(type);
          dd.setId(id);
          dd.setDesc(event);
          Calendar c = Calendar.getInstance();
          dd.setDate(c.getTime().toString());
          String day = intent.getStringExtra("day");
          ArrayList<String> mylist = new ArrayList<>();
          mylist.add(day);

          Utility.setAlarmdaily(mylist, dd, context, 1);

      } else if (type.equals("Monthly")) {

          DataEvent dd = new DataEvent();
          dd.setType(type);
          dd.setId(id);
          dd.setDesc(event);
          Calendar c = Calendar.getInstance();
          dd.setDate(c.getTime().toString());
          String day = intent.getStringExtra("day");
          ArrayList<String> mylist = new ArrayList<>();
          mylist.add(day);

          Utility.setAlarmMonthly(mylist, dd, context, 1);


      } else if (type.equals("Yearly")) {

          DataEvent dd = new DataEvent();
          dd.setType(type);
          dd.setId(id);
          dd.setDesc(event);
          Date ddd = Utility.convertStringToDate(intent.getStringExtra("datetime"));
          Calendar c = Calendar.getInstance();
          //Setting the date to the given date
          c.setTime(ddd);
          c.add(Calendar.YEAR, 1);
          dd.setDate(c.getTime().toString());
          String day = intent.getStringExtra("day");
          ArrayList<String> mylist = new ArrayList<>();
          mylist.add(day);

          Utility.setAlarmyearly(dd, context);


      }

  }catch (Exception e){e.printStackTrace();}
    }
}
