package com.example.pentagon.virtualassistant;

import android.Manifest;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.pentagon.virtualassistant.Rectrofit.ResponseCallback;
import com.example.pentagon.virtualassistant.Rectrofit.ResponseHandler;
import com.example.pentagon.virtualassistant.Rectrofit.Retrofit_Helper;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;

import static java.security.AccessController.getContext;

public class AddEventActivity extends AppCompatActivity {
EditText voiceInput,date,time;
Spinner sp;
TextView datetxt;
public  ArrayList<String> dayily;
RecyclerView recyclerView;
RecyclerViewAdapterDay adapter1;
    ArrayList<DataEvent> dd;
Pattern Time=Pattern.compile("(1[012]|[1-9])(:[0-5][0-9])?(\\s)?(?i)(a.m|p.m)");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initial();
        setContentView(R.layout.activity_add_event);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{Manifest.permission.READ_CALENDAR,Manifest.permission.WRITE_CALENDAR},
                            0);
                }

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant


            }

        voiceInput=(EditText)findViewById(R.id.editText2);
        date=(EditText)findViewById(R.id.editText);
        time=(EditText)findViewById(R.id.txttime);
        datetxt=(TextView)findViewById(R.id.textView2);

        sp=(Spinner)findViewById(R.id.spinner);
        String []aa={"Daily","Monthly","One Time","Yearly"};
        sp.setAdapter(
                new ArrayAdapter<String>(AddEventActivity.this,R.layout.support_simple_spinner_dropdown_item,aa));



        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {




                Object item1 = adapterView.getItemAtPosition(i);
                if (item1 instanceof String) {

               String  sem = (String) item1;

init(sem);
                 //   getNotification();


                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int hour=c.get(Calendar.HOUR_OF_DAY);
        int minute=c.get(Calendar.MINUTE);
        date.setText(day + "-" + (month + 1) + "-" + year);
        time.setText(hour + ":" + minute);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tiemPicker();
            }
        });
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dd = new DatePickerDialog(AddEventActivity.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        date.setText(day + "-" + (month + 1) + "-" + year);

                    }
                }, year, month, day);

//        DialogFragment newFragment = new SaleOrdersFragment.DatePickerFragment();
//
//        newFragment.show(getFragmentManager(),"timePicker");
                // tempdate=dateto;
                dd.show();

            }
        });

    }
    public class WrapContentLinearLayoutManager extends LinearLayoutManager {
        public WrapContentLinearLayoutManager(Context context) {
            super(context);
        }

        public WrapContentLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
            super(context, orientation, reverseLayout);
        }

        public WrapContentLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
        }

        //... constructor
        @Override
        public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
            try {
                super.onLayoutChildren(recycler, state);
            } catch (IndexOutOfBoundsException e) {
                Log.e("Error", "IndexOutOfBoundsException in RecyclerView happens");
            }
        }
    }
    private void setView(ArrayList<String> ss) {
Utility.albumList1=ss;
Log.e("size",ss.size()+"");
        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        adapter1 = new RecyclerViewAdapterDay(AddEventActivity.this,ss);
        recyclerView.setVisibility(View.VISIBLE);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(AddEventActivity.this, 3,GridLayoutManager.HORIZONTAL,false);
        // mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);



       // recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(AddEventActivity.this,GridLayoutManager.HORIZONTAL,false));
        recyclerView.setLayoutManager(mLayoutManager);
        //  recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter1);


//        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(AddEventActivity.this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, final int position) {
//                CheckBox ch=view.findViewById(R.id.item);
//                ch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    if (isChecked) {
//
//                        RecyclerViewAdapterDay.albumList1.add(dayily.get(position));
//                    } else{
//
//                        Log.e("ff",position+"");
//                        RecyclerViewAdapterDay.albumList1.remove(position);}
//
//                }
//            });
//            }
//
//            @Override
//            public void onLongItemClick(View view, int position) {
//
//            }
//        }));
    }

    private void initial() {



    }

    private void askSpeechInput() {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Hi speak something");
        try {
            startActivityForResult(intent, 100);
        } catch (ActivityNotFoundException a) {

        }
    }

    // Receiving speech input

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 100: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    voiceInput.setText(result.get(0));
                    //String mydata = "some string with 'the data i want' inside";
                    Pattern pattern = Pattern.compile("'(.*?)'");
                    Matcher matcher = Time.matcher(voiceInput.getText());
                    if (matcher.find())
                    {

                        System.out.println(matcher.group(1));
                        Log.e("msg",matcher.group());
                    }
                }
                break;
            }

        }}

    public void speek(View view) {
        askSpeechInput();
    }

    public void addevent(View view) {
        if(voiceInput.getText().toString().isEmpty()){

            Toast.makeText(this, "Speak to add event description", Toast.LENGTH_SHORT).show();
       return;
        }
dd=new ArrayList<>();
DataEvent event=new DataEvent();
event.setDesc(voiceInput.getText().toString());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        try {
            java.util.Date ddd=sdf.parse(date.getText().toString()+" "+time.getText().toString());
            event.setDate(ddd.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
       // event.setDate(date.getText().toString()+" "+time.getText().toString());
event.setUser("123");
     event.setType(sp.getSelectedItem().toString());

     if(sp.getSelectedItem().equals("Daily")||sp.getSelectedItem().equals("Monthly"))
     {
         String ff="";
         for(int k=0;k<Utility.albumList1.size();k++){

                 if(ff.isEmpty())
                     ff=Utility.albumList1.get(k);
                 else
                 ff+=","+Utility.albumList1.get(k);}

         Log.e("vvv",ff);
         event.setValue(ff);
     }
else {

         event.setValue("");
     }
dd.add(event);

addevent();
    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(AddEventActivity.this,MainActivity.class);
        startActivity(i);
        finish();

    }
    int mHour,mMinute;
    private void tiemPicker(){
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        mHour = hourOfDay;
                        mMinute = minute;

                        time.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, true);
        timePickerDialog.show();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 0: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! do the
                    // calendar task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'switch' lines to check for other
            // permissions this app might request
        }
    }
    public void init(String sem){

        if(sem.equals("Daily")){
            dayily=new ArrayList<>();
            for (Utility.Day s : Utility.Day .values())
                dayily.add(s.toString());
            datetxt.setText("Date From");

            setView(dayily);

        }
        else if(sem.equals("Monthly")){
            dayily=new ArrayList<>();
            for (Utility.Month s : Utility.Month .values())
                dayily.add(s.toString());
            datetxt.setText("Date From");
            setView(dayily);

        }
        else {
            datetxt.setText("Set Date");
            recyclerView.setVisibility(View.GONE);
        }
    }
    public void addevent() {

//        uid
//                date
//        event
//                type
//        value
        Map<String, String> params = new HashMap<>();
        //jObj = new JSONObject();
        // params.put("uid", uid);
        dd.get(0).setUser(Utility.USERID);
        params.put("uid", Utility.USERID);
        params.put("date", dd.get(0).getDate());
        params.put("event", dd.get(0).getDesc());
        params.put("type", dd.get(0).getType());
        params.put("value", dd.get(0).getValue());
        //  params.put("address", address);

        Call<JsonObject> jsonObjectCall = new Retrofit_Helper().getRetrofitBuilder().getfromServer(Utility.addevent, params);
        jsonObjectCall.clone().enqueue(new ResponseHandler(AddEventActivity.this, new ResponseCallback() {
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
                        if (jsonObj.has("event")) {
//                        //    Log.i("new_loginid_456", "" + actor.getInt("USER_ID"));

                            //	actor = login_array.getJSONObject(0);
                            dd.get(0).setId(Integer.parseInt(jsonObj.getString("event")));
                            if(new Cabd(AddEventActivity.this).insertEvent(dd)){

                                Toast.makeText(AddEventActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                                init(sp.getSelectedItem().toString());
                                voiceInput.setText("");
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

                    Toast.makeText(AddEventActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(AddEventActivity.this, "Can't Connect with server", Toast.LENGTH_SHORT).show();

                }
                ResponseHandler.progressDialog.dismiss();

            }


        }, jsonObjectCall,1));
    }
}
