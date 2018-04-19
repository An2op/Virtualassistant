package com.example.pentagon.virtualassistant;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pentagon.virtualassistant.Rectrofit.ResponseCallback;
import com.example.pentagon.virtualassistant.Rectrofit.ResponseHandler;
import com.example.pentagon.virtualassistant.Rectrofit.Retrofit_Helper;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;

public class LoginActivity extends AppCompatActivity {
    EditText uname,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        uname=(EditText)findViewById(R.id.username);
        pass=(EditText)findViewById(R.id.password);
        try {
            TextView textView = (TextView) findViewById(R.id.signup);
            SpannableString content = new SpannableString(getString(R.string.signup));
            content.setSpan(new UnderlineSpan(), 0, content.length(), 0);

            Utility.USERID=new SharedPreferenceClass(getSharedPreferences(SharedPreferenceClass.PROPERTY_USERID, Context.MODE_PRIVATE)).getPropertyUserid();

            Utility.USER_NAME=new SharedPreferenceClass(getSharedPreferences(SharedPreferenceClass.PROPERTY_USERNAME,Context.MODE_PRIVATE)).getPropertyUsername();

            Utility.PASSWORD=new SharedPreferenceClass(getSharedPreferences(SharedPreferenceClass.PROPERTY_PASSWORD,Context.MODE_PRIVATE)).getPropertyPassword();

            if(!Utility.USER_NAME.equals("")){

                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);

                finish();
            }
        }catch (Exception e){}



        ((Button)findViewById(R.id.login))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tologin();
                    }
                });
    }
    public void login(String uname,String pword){


        Map<String, String> params = new HashMap<>();
        //jObj = new JSONObject();
        params.put("uid", uname);
        params.put("upwd", pword);


        Call<JsonObject> jsonObjectCall = new Retrofit_Helper().getRetrofitBuilder().getfromServer(Utility.loginurl,params);
        jsonObjectCall.clone().enqueue(new ResponseHandler(LoginActivity.this, new ResponseCallback() {
            @Override
            public void getResponse(int code, JsonObject jsonObject) {

                JSONObject jsonObj = null;
                try {
                    jsonObj = new JSONObject(jsonObject.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                //JSONObject jsonObj = new JSONObject(datafromserver);

                if (jsonObj != null) {
                    Log.d("getfromserver", "b4 extract");
                    JSONObject actor = null;
                    try {
                        if (jsonObj.has("user")) {
                            JSONArray login_array = jsonObj.getJSONArray("user");
                            //       actor = jsonObj.getJSONObject("user");
//  {"user":[{"Id":"2","name":"David","email":"an2opvs@gmail.com","phone":"9847131977","username":"david","password":"12345678"}],"event":[]}
                            if(login_array.length()>0){
                                actor = login_array.getJSONObject(0);


                                Utility.USER_NAME=actor.getString("username");
                                Utility.PASSWORD=actor.getString("password");
                                Utility.USERID=actor.getString("Id");





                                new SharedPreferenceClass(getSharedPreferences(SharedPreferenceClass.PROPERTY_USERNAME, Context.MODE_PRIVATE)).setPropertyUsername(Utility.USER_NAME);
                                new SharedPreferenceClass(getSharedPreferences(SharedPreferenceClass.PROPERTY_PASSWORD, Context.MODE_PRIVATE)).setPropertyPassword(Utility.PASSWORD);
                                new SharedPreferenceClass(getSharedPreferences(SharedPreferenceClass.PROPERTY_USERID, Context.MODE_PRIVATE)).setPropertyUserid(Utility.USERID);


                                if (jsonObj.has("event")) {
                              login_array = jsonObj.getJSONArray("event");
                              ArrayList<DataEvent> de=new ArrayList<>();
                                    //       actor = jsonObj.getJSONObject("user");
//"event":[{"id":"1","userid":"2","date":"2018 April 23","event":"test","type":"daily","value":"gnbfhmj,"},{"id":"3","userid":"2","date":"2018 April 23","event":"test","type":"daily","value":"gnbfhmj,"}]}
                                    for(int i=0;i<login_array.length();i++){
                                        actor = login_array.getJSONObject(i);
DataEvent dd=new DataEvent();
dd.setId(Integer.parseInt(actor.getString("id")));
dd.setValue(actor.getString("value"));
dd.setDate(actor.getString("date"));
dd.setDesc(actor.getString("event"));
dd.setUser(actor.getString("userid"));
dd.setType(actor.getString("type"));
de.add(dd);




                                    }
                                    if(de.size()>0)
                                    new Cabd(LoginActivity.this).insertEvent(de) ;
                                }

                                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(i);

                                finish();}else {

                                Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();

                            }
                        }


                    } catch (Exception eee) {
                        eee.printStackTrace();
                    }

                }
            }
            @Override
            public void getFailure(Call<JsonObject> call, int code) {
                if (code==1) {

                    Toast.makeText(LoginActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(LoginActivity.this, "Can't Connect with server", Toast.LENGTH_SHORT).show();

                }
                if( ResponseHandler.progressDialog!=null)
                    ResponseHandler.progressDialog.dismiss();

            }


        }, jsonObjectCall,1));
    }

    public void gotsignup(View view) {

    }

    public void tologin() {
        int log=0;
        if(TextUtils.isEmpty(uname.getText())){
            log=1;
            uname.setError("Enter user name");
        }

        if(TextUtils.isEmpty(pass.getText())){
            pass.setError("Enter password");
            log=1;

        }
        if(log==0) login(uname.getText().toString(),pass.getText().toString());
    }

//    public void signup(View view) {
//
//        Intent i = new Intent(LoginActivity.this, RegistrationActivity.class);
//        Bundle bb=new Bundle();
//        i.putExtra("type", "new");
//        startActivity(i);
//
//        finish();
//    }
    public void signup(View view) {
        Intent i=new Intent(LoginActivity.this,SignupActivity.class);
        startActivity(i);
        finish();
    }
}
