package com.example.pentagon.virtualassistant;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
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

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import retrofit2.Call;

public class SignupActivity extends AppCompatActivity {
    EditText fname, lname, email, pass, repass,phone,uname;
    private EditText currentpass;
    Button signup;
    String uid="-1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        fname = (EditText) findViewById(R.id.fname);
        fname = (EditText) findViewById(R.id.fname);
        // lname = (EditText) findViewById(R.id.lname);
        pass = (EditText) findViewById(R.id.password);
        repass = (EditText) findViewById(R.id.repassword);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.mobile);
        //address = (EditText) findViewById(R.id.address);
        uname = (EditText) findViewById(R.id.uname);
        signup=(Button) findViewById(R.id.signup);
        currentpass=(EditText) findViewById(R.id.curpassword);
        String s = getIntent().getStringExtra("type");
      //  if(s.equals("edit"))
         //   loaddata();
    }

    public void login(String fname, final String lname, String email, final String pass, String mob, String address) {


        Map<String, String> params = new HashMap<>();
        //jObj = new JSONObject();
       // params.put("uid", uid);
        params.put("username", lname);
        params.put("password", pass);
        params.put("name", fname);
        params.put("email", email);
        params.put("phone", mob);
      //  params.put("address", address);

        Call<JsonObject> jsonObjectCall = new Retrofit_Helper().getRetrofitBuilder().getfromServer(Utility.signurl, params);
        jsonObjectCall.clone().enqueue(new ResponseHandler(SignupActivity.this, new ResponseCallback() {
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
//                        //    Log.i("new_loginid_456", "" + actor.getInt("USER_ID"));

                            //	actor = login_array.getJSONObject(0);
                            if(Boolean.parseBoolean(jsonObj.getString("user"))){



                                if(uid.equals("-1"))
                                    Toast.makeText(SignupActivity.this, "Registration success", Toast.LENGTH_SHORT).show();
                                else
                                    Toast.makeText(SignupActivity.this, "Updation success", Toast.LENGTH_SHORT).show();

                                Intent i = new Intent(SignupActivity.this, LoginActivity.class);
                                new SharedPreferenceClass(getSharedPreferences(SharedPreferenceClass.PROPERTY_USERNAME, Context.MODE_PRIVATE)).setPropertyUsername("");
                                new SharedPreferenceClass(getSharedPreferences(SharedPreferenceClass.PROPERTY_PASSWORD, Context.MODE_PRIVATE)).setPropertyPassword("");
                                new SharedPreferenceClass(getSharedPreferences(SharedPreferenceClass.PROPERTY_USERID, Context.MODE_PRIVATE)).setPropertyUserid("");

                                startActivity(i);

                                finish();}else {
                                if(uid.equals("-1"))
                                    Toast.makeText(SignupActivity.this, "Registration Unsuccess", Toast.LENGTH_SHORT).show();
                                else
                                    Toast.makeText(SignupActivity.this, "Updation Unsuccess", Toast.LENGTH_SHORT).show();

                            }

                        }
                    } catch (Exception eee) {
                        eee.printStackTrace();
                    }

                }
            }

            @Override
            public void getFailure(Call<JsonObject> call, int code) {
                if (code == 1) {

                    Toast.makeText(SignupActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(SignupActivity.this, "Can't Connect with server", Toast.LENGTH_SHORT).show();

                }
                ResponseHandler.progressDialog.dismiss();

            }


        }, jsonObjectCall,1));
    }

    private boolean validEmail(String email1) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;

        return pattern.matcher(email1).matches();
    }

    public void signup(View view) {
        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.

        if (TextUtils.isEmpty(phone.getText().toString())) {

            phone.setError(getString(R.string.error_field_required));
            focusView = phone;
            if (cancel == false)
                cancel = true;
        } else if ((phone.length() < 10)||(phone.length() > 12)) {

            phone.setError("invalid_phone_Number");
            focusView = phone;
            if (cancel == false)
                cancel = true;
        }
        if (TextUtils.isEmpty(fname.getText().toString())) {
            fname.setError(getString(R.string.error_field_required));
            focusView = fname;
            if (cancel == false)
                cancel = true;
        }
        if (TextUtils.isEmpty(uname.getText().toString())) {
            uname.setError(getString(R.string.error_field_required));
            focusView = uname;
            if (cancel == false)
                cancel = true;
        }
//        if (TextUtils.isEmpty(address.getText().toString())) {
//            address.setError(getString(R.string.error_field_required));
//            focusView = address;
//            if (cancel == false)
//                cancel = true;
//        }
        if (TextUtils.isEmpty(email.getText())) {
            email.setError(getString(R.string.error_field_required));
            focusView = email;
            if (cancel == false)
                cancel = true;
        } else {

            if (!validEmail(email.getText().toString())) {
                email.setError(getString(R.string.error_invalid_email));
                focusView = email;
                if (cancel == false)
                    cancel = true;

            }
        }
        if (TextUtils.isEmpty(pass.getText().toString())) {
            pass.setError(getString(R.string.error_field_required));
            focusView = pass;
            if (cancel == false)
                cancel = true;
        }else if(pass.getText().toString().length()<8){
            pass.setError("Password must 8 characters");
            focusView = pass;
            if (cancel == false)
                cancel = true;

        }else {
            if (TextUtils.isEmpty(repass.getText().toString())) {
                repass.setError(getString(R.string.error_field_required));
                focusView = repass;
                if (cancel == false)
                    cancel = true;
            }

            if (!pass.getText().toString().equals(repass.getText().toString())) {
                pass.setError("password missmatch");
                focusView = pass;
                if (cancel == false)
                    cancel = true;
            }

        }
//    if (TextUtils.isEmpty(hno)) {
//      edcpwd.setError(getString(R.string.error_field_required));
//      focusView = edcpwd;
//      if (cancel == false)
//        cancel = true;
//    }if (TextUtils.isEmpty(hname)) {
//      edcpwd.setError(getString(R.string.error_field_required));
//      focusView = edcpwd;
//      if (cancel == false)
//        cancel = true;
//    }if (TextUtils.isEmpty(wono)) {
//      edcpwd.setError(getString(R.string.error_field_required));
//      focusView = edcpwd;
//      if (cancel == false)
//        cancel = true;
//    }


        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {

            //   login(fname.getText().toString(), uname.getText().toString(), email.getText().toString(), pass.getText().toString(),phone.getText().toString(),address.getText().toString());
            if (uid.equals("-1"))
                login(fname.getText().toString(), uname.getText().toString(), email.getText().toString(), pass.getText().toString(), phone.getText().toString(),"");
            else {
                if(currentpass.getText().toString().equals(Utility.PASSWORD))
                    login(fname.getText().toString(), uname.getText().toString(), email.getText().toString(), pass.getText().toString(), phone.getText().toString(), "");
                else {
                    Toast.makeText(this, "Incorrect password", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    public void signin(View view) {

        Intent i = new Intent(SignupActivity.this, LoginActivity.class);
        startActivity(i);

        finish();
    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(SignupActivity.this, LoginActivity.class);
        startActivity(i);

        finish();
        super.onBackPressed();
    }
//    private void loaddata() {
//
//        //jObj = new JSONObject();
//        signup.setText("Save");
//        ((TextView) findViewById(R.id.textViewhead)).setText("Edit Profile");
//        Map<String, String> params = new HashMap<>();
//        //jObj = new JSONObject();
//        params.put("ulogin", Utility.USER_NAME);
//        params.put("upwd", Utility.PASSWORD);
//
//        Call<JsonObject> jsonObjectCall = new Retrofit_Helper().getRetrofitBuilder().getfromServer(Utility.loginurl,params);
//        jsonObjectCall.clone().enqueue(new ResponseHandler(RegistrationActivity.this, new ResponseCallback() {
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
////user":[{"userid":"12","username":"anoop","password":"123","usertype":"USER","fullname":"anoop","address":"dfgg","mobile":"3265987452","email":"asd@gmail.com","status":"ACTIVE","modified_date":"2018-01-11 13:58:52"}]}                           fname.setText(actor.getString("fullname"));
//                                fname.setText(actor.getString("fullname"));
//                                address.setText(actor.getString("address"));
//                                email.setText(actor.getString("email"));
//                                phone.setText(actor.getString("mobile"));
//                                uname.setText(Utility.USER_NAME);
//                                pass.setText(Utility.PASSWORD);
//                                //  pass.setHint("New Password");
//                                currentpass.setVisibility(View.VISIBLE);
//                                repass.setText("");
//                                uid=actor.getString("userid");
//                                // ((TextView) findViewById(R.id.textViewhead)).setText("Edit Profile");
//
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
//                    Toast.makeText(RegistrationActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
//
//                } else {
//                    Toast.makeText(RegistrationActivity.this, "Can't Connect with server", Toast.LENGTH_SHORT).show();
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
//    }
}
