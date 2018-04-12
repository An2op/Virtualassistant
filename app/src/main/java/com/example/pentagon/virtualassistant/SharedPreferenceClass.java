package com.example.pentagon.virtualassistant;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPreferenceClass {
    public static final String PROPERTY_USERNAME = "SNAPPHELP_USERNAME";
    public static final String PROPERTY_PASSWORD = "SNAPPHELP_PASSWORD";
    public static final String PROPERTY_USERID = "SNAPPHELP_USERID";
    public static final String PROPERTY_EMAILID = "Emailid";
    public static final String PROPERTY_USERDETAILS = "userdetails";
    public static final String PROPERTY_Delete_Last_Modified_Time = "SNAPPHELP_Delete_Last_Modified_Time";
    public static final String PROPERTY_Chat_Last_Modified_Time = "SNAPPHELP_Chat_Last_Modified_Time";
    public static final String PROPERTY_Trade_Last_Modified_Time = "SNAPPHELP_Trade_Last_Modified_Time";


    public static final String PROPERTY_Trade_Max_ID = "SNAPPHELP_Trade_MAx_ID";
    public static final String PROPERTY_FIREBASE_TOKEN = "SNAPPHELP_Firebase_Token";



    public static  final String PROPERTY_IS_TOKEN_REGISTERED="SNAPPHELP_IS_TOKEN_REGISTERED";
    SharedPreferences prefs = null;

    public SharedPreferenceClass(SharedPreferences prefs) {
        this.prefs = prefs;
    }
    public boolean getPropertyIsTokenRegistered() {



        boolean value = false;


        try {

            value = prefs.getBoolean(PROPERTY_IS_TOKEN_REGISTERED, false);

        } catch (NullPointerException ert) {
        }

        return value;

    }



    public void setPropertyIsTokenRegistered(boolean value) {

        //   final SharedPreferences prefs = getSharedPreferencesForServer(context);

        SharedPreferences.Editor editor = prefs.edit();

        editor.putBoolean(PROPERTY_IS_TOKEN_REGISTERED, value);


        editor.commit();

    }
    public void setPropertyUserdetails(String value) {

        //   final SharedPreferences prefs = getSharedPreferencesForServer(context);

        SharedPreferences.Editor editor = prefs.edit();

        editor.putString(PROPERTY_USERDETAILS, value);


        editor.commit();

    }
    public String getPropertyUserdetails() {



        String USERNAME = null;


        try {

            USERNAME = prefs.getString(PROPERTY_USERDETAILS, null);

        } catch (NullPointerException ert) {
        }

        return USERNAME;

    }
    public void setPropertyEmailid(String value) {

        //   final SharedPreferences prefs = getSharedPreferencesForServer(context);

        SharedPreferences.Editor editor = prefs.edit();

        editor.putString(PROPERTY_EMAILID, value);


        editor.commit();

    }
    public String getPropertyEmailid() {



        String USERNAME = null;


        try {

            USERNAME = prefs.getString(PROPERTY_EMAILID, null);

        } catch (NullPointerException ert) {
        }

        return USERNAME;

    }

    public void setPropertyUsername(String value) {

        //   final SharedPreferences prefs = getSharedPreferencesForServer(context);

        SharedPreferences.Editor editor = prefs.edit();

        editor.putString(PROPERTY_USERNAME, value);


        editor.commit();

    }
    public String getPropertyUsername() {



        String USERNAME = null;


        try {

            USERNAME = prefs.getString(PROPERTY_USERNAME, null);

        } catch (NullPointerException ert) {
        }

        return USERNAME;

    }
    public int getPropertyMaxTradeId() {



        int value = 0;


        try {

            value = prefs.getInt(PROPERTY_Trade_Max_ID, 0);

        } catch (NullPointerException ert) {
            ert.printStackTrace();
        }

        return value;

    }

    public String getPropertyPassword() {


        String PASSWORD = null;

        try {
            PASSWORD = prefs.getString(PROPERTY_PASSWORD, null);
        } catch (NullPointerException ert) {

        }

        return PASSWORD;

    }

    public String getPropertyUserid() {


        String USERID = null;

        try {
            USERID = prefs.getString(PROPERTY_USERID, null);
        } catch (NullPointerException ert) {
            return null;
        }

        return USERID;

    }

    public String getPropertyFirebaseToken() {


        String TOKEN = null;

        try {
            TOKEN = prefs.getString(PROPERTY_FIREBASE_TOKEN, null);
        } catch (NullPointerException ert) {
            return null;
        }

        return TOKEN;

    }

    public void clearPreference(){

        prefs.edit().clear().commit();
    }

    public String getPROPERTY_Delete_Last_Modified_Time() {



        String value = "0000-00-00 00:00:00";


        try {
           value= prefs.getString(PROPERTY_Delete_Last_Modified_Time, "0000-00-00 00:00:00");
        } catch (NullPointerException ert) {

        }

        return value;

    }

    public String getPROPERTY_Chat_Last_Modified_Time() {


        String value = "0000-00-00 00:00:00";

        try {
            value = prefs.getString(PROPERTY_Chat_Last_Modified_Time, "0000-00-00 00:00:00");
        } catch (NullPointerException ert) {

        }

        return value;

    }

    public String getPROPERTY_Trade_Last_Modified_Time() {



        String value = "0000-00-00 00:00:00";

        try {
            value = prefs.getString(PROPERTY_Trade_Last_Modified_Time, "0000-00-00 00:00:00");
        } catch (NullPointerException ert) {
        }

        return value;

    }



    public void setPropertyPassword( String value) {

        //   final SharedPreferences prefs = getSharedPreferencesForServer(context);

        SharedPreferences.Editor editor = prefs.edit();

        editor.putString(PROPERTY_PASSWORD, value);


        editor.commit();

    }

    public void setPropertyUserid( String value) {


        SharedPreferences.Editor editor = prefs.edit();

        editor.putString(PROPERTY_USERID, value);


        editor.commit();

    }

    public void setPROPERTY_Delete_Last_Modified_Time( String value) {



        SharedPreferences.Editor editor = prefs.edit();

        editor.putString(PROPERTY_Delete_Last_Modified_Time, value);


        editor.commit();

    }

    public void setPROPERTY_Chat_Last_Modified_Time(String value) {



        SharedPreferences.Editor editor = prefs.edit();

        editor.putString(PROPERTY_Chat_Last_Modified_Time, value);


        editor.commit();

    }

    public void setPROPERTY_Trade_Last_Modified_Time( String value) {

        //   final SharedPreferences prefs = getSharedPreferencesForServer(context);

        SharedPreferences.Editor editor = prefs.edit();

        editor.putString(PROPERTY_Trade_Last_Modified_Time, value);


        editor.commit();

    }

    public void setPROPERTY_Firebase_Token( String value) {

        //   final SharedPreferences prefs = getSharedPreferencesForServer(context);

        SharedPreferences.Editor editor = prefs.edit();

        editor.putString(PROPERTY_FIREBASE_TOKEN, value);


        editor.commit();

    }

    public void setPROPERTY_Trade_Max_ID( int value) {

        //   final SharedPreferences prefs = getSharedPreferencesForServer(context);

        SharedPreferences.Editor editor = prefs.edit();

        editor.putInt(PROPERTY_Trade_Max_ID, value);


        editor.commit();

    }

    private SharedPreferences getSharedPreferencesForServer(Context context) {

        // This sample app persists the serverIp in shared preferences, but

        // how you store the regID in your app is up to you.

        return PreferenceManager.getDefaultSharedPreferences(context);//context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);

    }


}