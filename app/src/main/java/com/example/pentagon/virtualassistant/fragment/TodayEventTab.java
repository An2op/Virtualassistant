package com.example.pentagon.virtualassistant.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.VideoView;

import com.example.pentagon.virtualassistant.AddEventActivity;
import com.example.pentagon.virtualassistant.Cabd;
import com.example.pentagon.virtualassistant.DataEvent;
import com.example.pentagon.virtualassistant.R;
import com.example.pentagon.virtualassistant.RecyclerViewAdapterEvents;
import com.example.pentagon.virtualassistant.Utility;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.example.pentagon.virtualassistant.Utility.convertStringToDate;


public class TodayEventTab extends Fragment {
    View view;

    VideoView v;

    private String resp;
AutoCompleteTextView search;
    private Button btnmaterial;
    private Button btnservices;
    private Button sort;
String currentData;
Spinner sp;
public RecyclerViewAdapterEvents adapter1;
ArrayList<DataEvent> dataEvents;
RecyclerView recyclerView;
    public TodayEventTab() {
        // Required empty public constructor
    }

//    public static SavedPcFragment newInstance(String param1, String param2) {
//        SavedPcFragment fragment = new SavedPcFragment();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =inflater.inflate(R.layout.fragment_home, container, false);
//search=(AutoCompleteTextView)view.findViewById(R.id.search);
recyclerView=(RecyclerView)view.findViewById(R.id.recyclerView);
      //  initilize();
//if(search.getText().toString().isEmpty())
//
//       // getServiceList();
        Utility.fab.setVisibility(View.VISIBLE);
        dataEvents=new ArrayList<>();
        sp=(Spinner)view.findViewById(R.id.spinner2);
        String []aa={"Today","Daily","Monthly","One Time","Yearly"};
        sp.setAdapter(
                new ArrayAdapter<String>(getActivity(),R.layout.support_simple_spinner_dropdown_item,aa));



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
setView();
        return view;
  }

    private void init(String sem) {
        if(sem.equals("Today")) {
            dataEvents = new Cabd(getContext()).getEvent("");
            setToday();

        } else
        dataEvents=new Cabd(getContext()).getEvent(sem);
            setView();
    }




    public void setView() {

Utility.todayEventTab =this;
    adapter1 = new RecyclerViewAdapterEvents(getContext(),dataEvents);
    recyclerView.setVisibility(View.VISIBLE);

    recyclerView.setHasFixedSize(true);
    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1,GridLayoutManager.VERTICAL,false);
    // mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

    recyclerView.setLayoutManager(mLayoutManager);
    //  recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    recyclerView.setAdapter(adapter1);

    recyclerView.setNestedScrollingEnabled(false);

}
public void setToday(){

        ArrayList<DataEvent> todayevents=new ArrayList<>();
    for(int k=0;k<dataEvents.size();k++){
        DataEvent de=new DataEvent();
        de=dataEvents.get(k);
        if(de.getType().equals("Daily")){
            ArrayList<String> myList = new ArrayList<String>(Arrays.asList(de.getValue().split(",")));
            if(calcDaily(myList,de)==1){
                todayevents.add(dataEvents.get(k));
               // dataEvents.remove(k);

            }
        }else if(de.getType().equals("Monthly")){

            ArrayList<String> myList = new ArrayList<String>(Arrays.asList(de.getValue().split(",")));
            if(calcMOnthly(myList,de)==1){
                todayevents.add(dataEvents.get(k));
              //  dataEvents.remove(k);

            }

        }
        else if(de.getType().equals("One Time")){

            Date dd=convertStringToDate(de.getDate());
//            Calendar c = Calendar.getInstance();
//            //Setting the date to the given date
//            c.setTime(dd);
//            Calendar kk = Calendar.getInstance();
//            Date start= Utility.convertStringToDate(product.getDate())
            if(DateUtils.isToday(dd.getTime())){

                todayevents.add(dataEvents.get(k));
            }
        }
        else if(de.getType().equals("Yearly")){

            Date dd=convertStringToDate(de.getDate());
            Calendar c = Calendar.getInstance();
            //Setting the date to the given date
            c.setTime(dd);
            Calendar kk = Calendar.getInstance();
            if(kk.get(Calendar.DAY_OF_WEEK)==c.get(Calendar.DAY_OF_WEEK)&&kk.get(Calendar.MONTH)==c.get(Calendar.MONTH)){

                todayevents.add(dataEvents.get(k));
            }
        }


    }
dataEvents=todayevents;
}

public int calcMOnthly(ArrayList<String> myList, DataEvent de){
int flag=0;
    for(int j=0;j<myList.size();j++){

        Date dd=convertStringToDate(de.getDate());
        Calendar c = Calendar.getInstance();
        //Setting the date to the given date
        c.setTime(dd);
        Calendar kk = Calendar.getInstance();


        Log.e("mm",new SimpleDateFormat("MMM", Locale.ENGLISH).format(c.getTime())) ;
        if(new SimpleDateFormat("MMM", Locale.ENGLISH).format(c.getTime()).toString().equalsIgnoreCase(myList.get(j))&&kk.get(Calendar.DAY_OF_MONTH)==c.get(Calendar.DAY_OF_MONTH)) {
flag=1;

        }




    }
 return flag;
}
    public int calcDaily(ArrayList<String> myList, DataEvent de){
        int flag=0;
        for(int j=0;j<myList.size();j++){

            Date dd=convertStringToDate(de.getDate());
            Calendar c = Calendar.getInstance();
            //Setting the date to the given date
            c.setTime(dd);
            Calendar kk = Calendar.getInstance();


            Log.e("mm",new SimpleDateFormat("EE", Locale.ENGLISH).format(c.getTime())) ;
            if(new SimpleDateFormat("EE", Locale.ENGLISH).format(c.getTime()).toString().equalsIgnoreCase(myList.get(j))) {
                flag=1;
            }




        }
        return flag;
    }
}