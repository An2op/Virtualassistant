package com.example.pentagon.virtualassistant;

/**
 * Created by asus on 14-Sep-17.
 */

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



/**
 * Created by root on 17/4/17.
 */

public class Utility {

    public static FloatingActionButton fab;

    public static String USER_NAME = "";
    public static String PASSWORD = "";
//    public static String BASE_URL ="http://lapisapps.in/georgian/android/";



    public static String USERID="";

    public static String loginurl="login.php";

    public static String vieworderlisturl="vieworders.php";

    public static String signurl="registration.php";

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
}