package com.example.pentagon.virtualassistant;

/**
 * Created by asus on 14-Sep-17.
 */

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pentagon.virtualassistant.fragment.TodayEventTab;
import com.example.pentagon.virtualassistant.fragment.RequestFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 * Created by root on 17/4/17.
 */

public class Utility {
    public static TodayEventTab todayEventTab;
    public static RequestFragment requestFragment;
    public static ArrayList<String> albumList1;
    public static String addevent="insertevent.php";
    public static String eventremoveurl="removeevent.php";

   public enum Day{
        mon(1), tue(2), wed(3), thu(4),fri(5),sat(6),sun(7);

        public int value;
       Day(int value){
            this.value=value;
        }
    }
  public   enum Month{
        jan(1), feb(2), mar(3), apr(4),may(5),jun(6),jul(7),aug(8),sep(9),oct(10),nov(11),dec(12);

        public int value;
         Month(int value){
            this.value=value;
        }
    }


    public static FloatingActionButton fab;

    public static String USER_NAME = "";
    public static String PASSWORD = "";
//    public static String BASE_URL ="http://lapisapps.in/georgian/android/";



    public static String USERID="";

    public static String loginurl="login.php";

    public static String vieworderlisturl="vieworders.php";

    public static String signurl="register.php";

    public static String getservicelisturl="servicelist.php";

    public static String getmaterilalisturl="materiallist.php";

    public static String getserviceworkurl="workerservicelist.php";

    public static String getmaterialeworkurl="workermateriallist.php";

    public static String sendorderurl="order.php";

    public static String Email="as@gmail.com";

    public static String getplanurl="planlist.php";

    public static String orderdetailsurl="orderdetails.php";

    public static String profileurl="viewprofile.php";


//    public static DataUser userdata=null;
    //  public static ArrayList<Dataloc> dataloc;
   // public static ArrayList<DataComplaint> datacomplaint;


    public static void nextActivity(Activity activity, Class nextClass){
        Intent intent = new Intent(activity.getApplicationContext(),nextClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.getApplicationContext().startActivity(intent);
        activity.finish();
    }

    public static void toastShow(Context context, String msg){
        Toast.makeText(context,msg, Toast.LENGTH_LONG).show();
    }


    public static AlertDialog.Builder alertdialog(Context c, String title, String msg)
    {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(c);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        LayoutInflater inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //alertDialog.setView(inflater.inflate(R.layout.deleteallmessagerequest_dialog, null));
        assert inflater != null;
        @SuppressLint("InflateParams") View dialogView = inflater.inflate(R.layout.alertdialog, null);
        alertDialog.setView(dialogView);

        TextView tt=(TextView)dialogView.findViewById(R.id.titleq);
        tt.setText(title);
        TextView ms=(TextView)dialogView.findViewById(R.id.msg);
        ms.setText(msg);

        return alertDialog;
    }


//    public static void viewProfile(final Context mcontext, String userid) {
//
//        Map<String, String> params = new HashMap<>();
//        //jObj = new JSONObject();
//        params.put("uid", userid);
//
//
//
//        Call<JsonObject> jsonObjectCall = new Retrofit_Helper().getRetrofitBuilder().getfromServer(Utility.profileurl,params);
//        jsonObjectCall.clone().enqueue(new ResponseHandler(mcontext, new ResponseCallback() {
//            @Override
//            public void getResponse(int code, JsonObject jsonObject) {
//
//                JSONObject jsonObj = null;
//                try {
//                    jsonObj = new JSONObject(jsonObject.toString());
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//
//                //JSONObject jsonObj = new JSONObject(datafromserver);
//
//                if (jsonObj != null) {
//                    Log.d("getfromserver", "b4 extract");
//                    JSONObject actor = null;
//                    try {
//                        if (jsonObj.has("user")) {
//                            JSONArray login_array = jsonObj.getJSONArray("user");
//                            //       actor = jsonObj.getJSONObject("user");
////                            Log.i("new_loginid_456", "" + actor.getInt("USER_ID"));
//                            if(login_array.length()>0){
//                                actor = login_array.getJSONObject(0);
//
//                                Dialog dd=new Dialog(mcontext);
//                                dd.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                                dd.setContentView(R.layout.dialogprofile);
//                                dd.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//                                dd.setCancelable(true);
//                                ((EditText)dd.findViewById(R.id.fname)).setText(actor.getString("fullname"));
//                                ((EditText)dd.findViewById(R.id.address)).setText(actor.getString("address"));
//                                ((EditText)dd.findViewById(R.id.email)).setText(actor.getString("email"));
//                                ((EditText)dd.findViewById(R.id.mobile)).setText(actor.getString("phone"));
//                                dd.show();
//        Window window = dd.getWindow();
//        window.setLayout(LinearLayoutCompat.LayoutParams.MATCH_PARENT,650 );
//                            }
//                        }
//                    } catch (Exception eee) {
//                        eee.printStackTrace();
//                    }
//
//                }
//            }
//            @Override
//            public void getFailure(Call<JsonObject> call, int code) {
//                if (code==1) {
//
//                    Toast.makeText(mcontext, "No Internet Connection", Toast.LENGTH_SHORT).show();
//
//                } else {
//                    Toast.makeText(mcontext, "Can't Connect with server", Toast.LENGTH_SHORT).show();
//
//                }
//                if( ResponseHandler.progressDialog!=null)
//                    ResponseHandler.progressDialog.dismiss();
//
//            }
//
//
//        }, jsonObjectCall,1));
//
//
//    }
    public static void setAlaram(Context cc,ArrayList<DataEvent> dataEvents){

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            for(int k=0;k<dataEvents.size();k++) {
                Log.e("eee",dataEvents.get(k).getDate());
                java.util.Date dd = sdf.parse(dataEvents.get(k).getDate());
                Calendar cal = Calendar.getInstance();
//                cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY));
//                cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE)+5);
//                cal.set(Calendar.SECOND, 0);
//                cal.set(Calendar.MILLISECOND, 0);
cal.set(dd.getYear(), dd.getMonth(), dd.getDay(),cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE)+5,0);
             //   cal.set(dd.getYear(), dd.getMonth(), dd.getDay());
dd.setHours(cal.get(Calendar.HOUR_OF_DAY));
dd.setMinutes(cal.get(Calendar.MINUTE)+5);

              //  dd=cal.getTime();
                AlarmManager alarmMgr = (AlarmManager) cc.getSystemService(Context.ALARM_SERVICE);
                Intent intent = new Intent(cc, MyReceiver.class);
                intent.putExtra("id",dataEvents.get(k).getId());
                intent.putExtra("Event",dataEvents.get(k).getDesc());

                PendingIntent pendingIntent = PendingIntent.getBroadcast(cc, dataEvents.get(k).getId(), intent, 0);
                // cal.add(Calendar.SECOND, 5);
              //  String dateString = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(cal.getTimeInMillis()));
                String givenDateString = "Tue Apr 23 16:08:28 GMT+05:30 2013";
                SimpleDateFormat sdf1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
                try {
                    Date mDate = sdf1.parse(dd.toString());
                    long timeInMilliseconds = mDate.getTime();
                    System.out.println("Date in milli :: " + timeInMilliseconds);

                Log.e("eee", mDate.toString()+" "+mDate.getTime()+" "+cal.getTimeInMillis());

                alarmMgr.set(AlarmManager.RTC_WAKEUP,mDate.getTime(), pendingIntent);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
    public static String Datetomil(String date,String time){
        long timeInMilliseconds = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        try {
            Date dd = sdf.parse(date);
            Calendar cal = Calendar.getInstance();
//                cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY));
//                cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE)+5);
//                cal.set(Calendar.SECOND, 0);
//                cal.set(Calendar.MILLISECOND, 0);
            cal.set(dd.getYear(), dd.getMonth(), dd.getDay(),cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE)+5,0);
            //   cal.set(dd.getYear(), dd.getMonth(), dd.getDay());
            int hour=Integer.parseInt(time.substring(0,time.indexOf(":")));
            int min=Integer.parseInt(time.substring(0,time.indexOf(":")));

            dd.setHours(hour);
            dd.setMinutes(min);
            SimpleDateFormat sdf1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");

                Date mDate = sdf1.parse(dd.toString());
            timeInMilliseconds = mDate.getTime();


        } catch (ParseException e) {
            e.printStackTrace();
        }




        return String.valueOf(timeInMilliseconds);
    }
    public static Date convertStringToDate(String dateString)
    {
        Date date = null;
        Date formatteddate = null;
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        try {
            date=sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return date;
    }
    public static void setAlaram1(Context cc,ArrayList<DataEvent> dataEvents){

       for(int k=0;k<dataEvents.size();k++){
           DataEvent de=new DataEvent();
           de=dataEvents.get(k);
           if(de.getType().equals("Daily")){
               ArrayList<String> myList = new ArrayList<String>(Arrays.asList(de.getValue().split(",")));
           setAlarmdaily(myList,de,cc,0);
           }else if(de.getType().equals("Monthly")){

               ArrayList<String> myList = new ArrayList<String>(Arrays.asList(de.getValue().split(",")));
               setAlarmMonthly(myList,de,cc,0);

           }
           else{

               setAlarmOnce(de,cc);
           }
       }
    }

    public static void setAlarmOnce(DataEvent de, Context cc) {
        Date dd=convertStringToDate(de.getDate());
        Calendar c = Calendar.getInstance();
        //Setting the date to the given date
        c.setTime(dd);

        Calendar kk=Calendar.getInstance();
        if(kk.getTime().before(c.getTime())) {
            AlarmManager alarmMgr = (AlarmManager) cc.getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(cc, MyReceiver.class);
            intent.putExtra("id",de.getId());
            intent.putExtra("type",de.getType());
            intent.putExtra("Event",de.getDesc());

            Log.e("date",c.getTimeInMillis()+"");
            intent.putExtra("datetime", c.getTime().toString());
            Log.e("date", c.getTimeInMillis() + "");
            int pen= new Cabd(cc).insertTemp(String.valueOf(de.getId()),String.valueOf(c.getTimeInMillis()));

            PendingIntent pendingIntent = PendingIntent.getBroadcast(cc, pen, intent, 0);
            alarmMgr.set(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(), pendingIntent);
            // alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP,timeInMilliseconds,86400000*30, pendingIntent);
            //alarmMgr.setInexactRepeating();
        }

    }

    public static void setAlarmMonthly(ArrayList<String> myList, DataEvent de, Context cc,int from) {

        for(int j=0;j<myList.size();j++){

            Date dd=convertStringToDate(de.getDate());
            Calendar c = Calendar.getInstance();
            //Setting the date to the given date
            c.setTime(dd);
           Log.e("mm",new SimpleDateFormat("MMM", Locale.ENGLISH).format(c.getTime())) ;


            int n1 = 0,n2=0;
            int nodays=0;
            for (Month s : Month.values()){
                if(s.toString().equals(myList.get(j))){

                    n1=s.value;
                }
                if(new SimpleDateFormat("MMM", Locale.ENGLISH).format(c.getTime()).toString().equalsIgnoreCase(s.toString())){

                    n2=s.value;
                }
                Log.e("n1n2",n1+" "+n2+new SimpleDateFormat("MMM", Locale.ENGLISH).format(c.getTime()).toString());
            }
            if(n1<n2 ){
                nodays=(n1+12)-n2;

            }
            else if(n1>n2){
                nodays=(n1-n2);

            }
            Log.e("no.ofdays",nodays+"");
            Calendar kk=Calendar.getInstance();
            if(from==0)
            c.add(Calendar.MONTH, nodays);
            else
                c.add(Calendar.MONTH, 12);
//            SimpleDateFormat sdf1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
//
//            Date mDate = null;
//            try {
//                mDate = sdf1.parse(c.getTime().toString());
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//            long timeInMilliseconds = mDate.getTime();
//            System.out.println("Date in milli :: " + timeInMilliseconds);
//
//            Log.e("eee", mDate.toString()+" "+mDate.getTime()+" ");
            Log.e("eee", c.getTime().toString()+" "+c.getTimeInMillis()+" ");
            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
            String newDate = sdf.format(c.getTime());
            //Date after adding the days to the given date


            AlarmManager alarmMgr = (AlarmManager) cc.getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(cc, MyReceiver.class);
            intent.putExtra("id",de.getId());
            intent.putExtra("type",de.getType());
            intent.putExtra("Event",de.getDesc());
            intent.putExtra("day",myList.get(j));
            Log.e("date",c.getTimeInMillis()+"");
                intent.putExtra("datetime", c.getTime().toString());
                Log.e("date", c.getTimeInMillis() + "");
                int pen= new Cabd(cc).insertTemp(String.valueOf(de.getId()),String.valueOf(c.getTimeInMillis()));

                PendingIntent pendingIntent = PendingIntent.getBroadcast(cc, pen, intent, 0);
            alarmMgr.set(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(), pendingIntent);
           // alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP,timeInMilliseconds,86400000*30, pendingIntent);
       //alarmMgr.setInexactRepeating();


        }


    }

    public static void setAlarmdaily(ArrayList<String> myList, DataEvent de,Context cc,int from) {




        for(int j=0;j<myList.size();j++){

            Date dd=convertStringToDate(de.getDate());
            Calendar c = Calendar.getInstance();
            //Setting the date to the given date
            c.setTime(dd);
            new SimpleDateFormat("EE", Locale.ENGLISH).format(c.getTime());

int n1 = 0,n2=0;
            int nodays=0;

            for (Day s : Day.values()){

                if(s.toString().equals(myList.get(j))){
                    Log.e("value",s.toString());
                    n1=s.value;
                }
                if(new SimpleDateFormat("EE", Locale.ENGLISH).format(c.getTime()).toString().equalsIgnoreCase(s.toString())){

                    n2=s.value;
                }

            }

            Log.e("n1n2",n1+" "+n2+new SimpleDateFormat("EE", Locale.ENGLISH).format(c.getTime()).toString());

                if(n1<n2 ){
    nodays=(n1+7)-n2;

}
else if(n1>n2){
    nodays=(n1-n2);

}
Log.e("no.ofdays",nodays+"");
            Calendar kk=Calendar.getInstance();
            if(from==1)
                c.add(Calendar.DAY_OF_MONTH, 7);
                else
                c.add(Calendar.DAY_OF_MONTH, nodays);
            SimpleDateFormat sdf1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");

            Date mDate = null;
            try {
                mDate = sdf1.parse(c.getTime().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            long timeInMilliseconds = mDate.getTime();
                System.out.println("Date in milli :: " + timeInMilliseconds);

                Log.e("eee", mDate.toString()+" "+c.getTimeInMillis()+" ");

            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
            String newDate = sdf.format(c.getTime());
            //Date after adding the days to the given date



    // if(kk.getTime().before(c.getTime())) {


                AlarmManager alarmMgr = (AlarmManager) cc.getSystemService(Context.ALARM_SERVICE);
                Intent intent = new Intent(cc, MyReceiver.class);
                intent.putExtra("id", de.getId());
                intent.putExtra("Event", de.getDesc());
                intent.putExtra("type", de.getType());
                intent.putExtra("day", myList.get(j));
                intent.putExtra("datetime", c.getTime().toString());
                Log.e("date", c.getTimeInMillis() + "");
               int pen= new Cabd(cc).insertTemp(String.valueOf(de.getId()),String.valueOf(c.getTimeInMillis()));

                PendingIntent pendingIntent = PendingIntent.getBroadcast(cc, pen, intent, 0);
                alarmMgr.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
                // alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP,timeInMilliseconds,86400000*7, pendingIntent);

           // }
        }


    }
    public static void addReminder(ArrayList<DataEvent> dataEvents,Context context){
for(int k=0;k<dataEvents.size();k++){
    Date dd=convertStringToDate(dataEvents.get(k).getDate());
    Calendar c = Calendar.getInstance();
    //Setting the date to the given date
    c.setTime(dd);
       // Calendar beginTime = Calendar.getInstance();
     //   beginTime.set(statrYear, startMonth, startDay, startHour, startMinut);
        long startMillis = c.getTimeInMillis();

        Calendar endTime = Calendar.getInstance();
      //  endTime.set(endYear, endMonth, endDay, endHour, endMinuts);
        long endMillis = c.getTimeInMillis();

        String eventUriString = "content://com.android.calendar/events";
        ContentValues eventValues = new ContentValues();

        eventValues.put(CalendarContract.Events.CALENDAR_ID, 1);
        eventValues.put(CalendarContract.Events.TITLE, dataEvents.get(k).getDesc());
        eventValues.put(CalendarContract.Events.DESCRIPTION, "Virtual Assistant");
        eventValues.put(CalendarContract.Events.EVENT_TIMEZONE, String.valueOf(c.getTimeZone()));
        eventValues.put(CalendarContract.Events.DTSTART, startMillis);
        eventValues.put(CalendarContract.Events.DTEND, endMillis);

        //eventValues.put(Events.RRULE, "FREQ=DAILY;COUNT=2;UNTIL="+endMillis);
     //   eventValues.put("eventStatus", 1);
   //     eventValues.put("visibility", 3);
        //eventValues.put("transparency", 0);
        eventValues.put(CalendarContract.Events.HAS_ALARM, 1);

        Uri eventUri = context.getContentResolver().insert(Uri.parse(eventUriString), eventValues);
        long eventID = Long.parseLong(eventUri.getLastPathSegment());

        /***************** Event: Reminder(with alert) Adding reminder to event *******************/

        String reminderUriString = "content://com.android.calendar/reminders";

        ContentValues reminderValues = new ContentValues();

        reminderValues.put("event_id", eventID);
        reminderValues.put("minutes", 1);
        reminderValues.put("method", 1);

        Uri reminderUri = context.getContentResolver().insert(Uri.parse(reminderUriString), reminderValues);

}

    }


}