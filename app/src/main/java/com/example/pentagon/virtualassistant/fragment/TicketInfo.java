package com.example.pentagon.virtualassistant.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pentagon.virtualassistant.AddEventActivity;
import com.example.pentagon.virtualassistant.LoginActivity;
import com.example.pentagon.virtualassistant.R;
import com.example.pentagon.virtualassistant.Rectrofit.ResponseCallback;
import com.example.pentagon.virtualassistant.Rectrofit.ResponseHandler;
import com.example.pentagon.virtualassistant.Rectrofit.Retrofit_Helper;
import com.example.pentagon.virtualassistant.SharedPreferenceClass;
import com.example.pentagon.virtualassistant.SignupActivity;
import com.example.pentagon.virtualassistant.Utility;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


import retrofit2.Call;


public class TicketInfo extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View view;

    int profile=0;
    Button btnpost;
EditText from,to,desc,date;
Spinner type;

    private static String userid="";
    EditText title,description;
    AutoCompleteTextView place;
    String placeid="-1";

    public TicketInfo() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =inflater.inflate(R.layout.fragment_ticket, container, false);
Utility.fab.setVisibility(View.GONE);
//if(getActivity().getSupportFragmentManager().getBackStackEntryAt(0).getName()!=null)
//Log.i("ddd",getActivity().getSupportFragmentManager().getBackStackEntryAt(0).getName());
        //initilize();
        type=(Spinner)view.findViewById(R.id.spinner);
        desc=(EditText)view.findViewById(R.id.editText4) ;
        from=(EditText)view.findViewById(R.id.from);
        to=(EditText)view.findViewById(R.id.to);
        date=(EditText)view.findViewById(R.id.editText3);
        btnpost=(Button)view.findViewById(R.id.button) ;
        btnpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag=true;
               if(from.getText().toString().isEmpty()){
                   from.setError("Enter From location");
                   flag=false;
               }
                if(to.getText().toString().isEmpty()){
                    to.setError("Enter To location");
                    flag=false;
                }
                if(desc.getText().toString().isEmpty()){
                    to.setError("Enter description");
                    flag=false;
                }
if(flag)
                sendrequest();
            }
        });
        String[] type1={"Bus","Train","Plane"};
        type.setAdapter(
                new ArrayAdapter<String>(getActivity(),R.layout.support_simple_spinner_dropdown_item,type1));

        Log.i("ddd","222");
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int hour=c.get(Calendar.HOUR_OF_DAY);
        int minute=c.get(Calendar.MINUTE);
        date.setText(day + "-" + (month + 1) + "-" + year);


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dd = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

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

        return view;
    }

    private void sendrequest() {

        Map<String, String> params = new HashMap<>();
        //jObj = new JSONObject();
        // params.put("uid", uid);
        params.put("uid", Utility.USERID);
        params.put("ticket_type", type.getSelectedItem().toString());
        params.put("from_to", from.getText().toString()+"-"+to.getText().toString());
        params.put("travel_date", date.getText().toString());
        params.put("details", desc.getText().toString());
        // uid
        //ticket_type
        //from_to
        //travel_date
        //details

        Call<JsonObject> jsonObjectCall = new Retrofit_Helper().getRetrofitBuilder().getfromServer("ticketbooking.php", params);
        jsonObjectCall.clone().enqueue(new ResponseHandler(getActivity(), new ResponseCallback() {
            @Override
            public void getResponse(int code, JsonObject jsonObject) {

                JSONObject jsonObj = null;
                try {
                    jsonObj = new JSONObject(jsonObject.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                //{"ticket":2}

                if (jsonObj != null) {
                    Log.d("getfromserver", "b4 extract");
                    JSONObject actor = null;
                    try {
                        if (jsonObj.has("ticket")) {
//                        //    Log.i("new_loginid_456", "" + actor.getInt("USER_ID"));

                            //	actor = login_array.getJSONObject(0);
                            if (Integer.parseInt(jsonObj.getString("ticket"))>0) {

                                Toast.makeText(getActivity(), "Booking Success", Toast.LENGTH_SHORT).show();

                          from.setText("");
                          to.setText("");
                          desc.setText("");
                            }
                            else

                                Toast.makeText(getActivity(), "something went wrong..", Toast.LENGTH_SHORT).show();
                        }
                        } catch(Exception eee){
                            eee.printStackTrace();
                        }

                    }
            }

            @Override
            public void getFailure(Call<JsonObject> call, int code) {
                if (code == 1) {

                    Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getActivity(), "Can't Connect with server", Toast.LENGTH_SHORT).show();

                }
                ResponseHandler.progressDialog.dismiss();

            }


        }, jsonObjectCall,1));
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("ddd","111");
    }

    void initilize() {
//        title = (EditText) view.findViewById(R.id.title);
//        description = (EditText) view.findViewById(R.id.description);
//
//        btnpost.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int flag = 0;
//                if (TextUtils.isEmpty(title.getText())) {
//                    flag = 1;
//                    title.setError("add title");
//                }
//                if (TextUtils.isEmpty(description.getText())) {
//                    flag = 1;
//                    title.setError("add description");
//                }
//                if (flag == 0) {
//
//                    //  saveEvent();
//                }
//            }
//        });
    }
//    public void getOrder(){
//
//
//        Map<String, String> params = new HashMap<>();
//        //jObj = new JSONObject();
//        params.put("uid", Utility.USERID);
//
//
//
//
//        Call<JsonObject> jsonObjectCall = new Retrofit_Helper().getRetrofitBuilder().getfromServer(Utility.vieworderlisturl,params);
//        jsonObjectCall.clone().enqueue(new ResponseHandler(getContext(), new ResponseCallback() {
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
//                    Log.d("getfromserver", jsonObj.toString()+" 11");
//                    JSONObject actor = null;
//                    try {
//                        if (jsonObj.has("orders")) {
//                            JSONArray login_array = jsonObj.getJSONArray("orders");
////{"orders":[{"order_id":"5","user_id":"9","order_type":"Materials & Services","total_price":"12000","order_shipping_address":"kappadu",
//// "contact_no":"9847131977","email":"as@gmail.com","order_status":"REQUESTED","modified_date":"2018-01-31 14:36:13"},
//                            //  actor = jsonObj.getJSONObject("USER");
////                            Log.i("new_loginid_456", "" + actor.getInt("USER_ID"));
//                            //     Utility.mandatory=new ArrayList<>();
//                            requests=new ArrayList<>();
//                            RequestData ca;
//                            for(int k=0;k<login_array.length();k++){
//                                ca=new RequestData();
//
//                                actor = login_array.getJSONObject(k);
//
//                                ca.setOrder_id(actor.getString("order_id"));
//                                ca.setOrder_type(actor.getString("order_type"));
//                                ca.setTotal_price(actor.getString("total_price"));
//                                ca.setOrder_shipping_address(actor.getString("order_shipping_address"));
//                                ca.setContact_no(actor.getString("contact_no"));
//                                ca.setEmail(actor.getString("email"));
//                                ca.setOrder_status(actor.getString("order_status"));
//                                ca.setDate(actor.getString("modified_date"));
//                                requests.add(ca);
//
//                            }
//
//
//                            setRecyclerView();
//                        }
//                        if(ResponseHandler.progressDialog!=null)
//                            ResponseHandler.progressDialog.dismiss();
//                        setRecyclerView();
//
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
//                    Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
//
//                } else {
//                    Toast.makeText(getContext(), "Can't Connect with server", Toast.LENGTH_SHORT).show();
//
//                }
//
//                if(ResponseHandler.progressDialog!=null)
//                    ResponseHandler.progressDialog.dismiss();
//
//            }
//
//
//        }, jsonObjectCall,1));
//
//    }
//
//
//
//
//
//    private  void setRecyclerView() {
//
//        adapter1 = new RecyclerViewAdapterRequests(getContext(),requests);
//        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
//
//        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL,false);
//        // mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//
//        recyclerView.setLayoutManager(mLayoutManager);
//        //  recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(adapter1);
//        recyclerView.setNestedScrollingEnabled(false);
//        recyclerView.addOnItemTouchListener(
//                new RecyclerItemClickListener(getContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(View view, final int position) {
////                           Toast.makeText(getContext(), Utility.build.get(position).getType(), Toast.LENGTH_SHORT).show();
////                        Utility.pcbuildid=Utility.savedPcs.get(position).getBpcid();
////
////                        loadComponents(Utility.pcbuildid,position);
//getOrderDetails(requests.get(position).getOrder_id());
//                    }
//
//                    @Override
//                    public void onLongItemClick(View view, final int position) {
//
//
//                    }
//                }));
//    }
//
//    public  void putplace(){
//
//
//   }
//
//
//
//
//    private void getFollowers() {
//
//
//    }
//
//
//    public void getOrderDetails(String orderid) {
//        Map<String, String> params = new HashMap<>();
//        //jObj = new JSONObject();
//        params.put("orderid", orderid);
//
//
//
//
//        Call<JsonObject> jsonObjectCall = new Retrofit_Helper().getRetrofitBuilder().getfromServer(Utility.orderdetailsurl,params);
//        jsonObjectCall.clone().enqueue(new ResponseHandler(getContext(), new ResponseCallback() {
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
//String shipAddress=null,Contact=null,email=null,type,order_id,amount,status;
//                if (jsonObj != null) {
//                    Log.d("getfromserver", jsonObj.toString()+" 11");
//                    JSONObject actor = null;
//                    try {
//                        if (jsonObj.has("orders")) {
//                            JSONArray login_array = jsonObj.getJSONArray("orders");
//                      //      {"orders":[{"order_id":"5","user_id":"9","order_type":"Materials & Services","total_price":"12000","order_shipping_address":"kappadu","contact_no":"9847131977","email":"as@gmail.com","order_status":"REQUESTED"
//                            // ,"modified_date":"2018-01-31 14:36:13"}],
//                            // "material":[{"order_item_id":"3","order_id":"5","worker_material_id":"5","no_unit":"3","total_price":"250","order_item_status":"REQUESTED","modified_date":"2018-02-01 10:42:04"}],
//                            // "service":[{"order_service_id":"2","order_id":"5","worker_service_id":"1","requestdetails":"GET","order_service_status":"REQUESTED","modified_date":"2018-02-01 10:42:13"}]}                         //  actor = jsonObj.getJSONObject("USER");
////                            Log.i("new_loginid_456", "" + actor.getInt("USER_ID"));
//                            //     Utility.mandatory=new ArrayList<>();
//                            actor = login_array.getJSONObject(0);
//shipAddress=actor.getString("order_shipping_address");
//Contact=actor.getString("contact_no");
//                            email=actor.getString("email");
//                          amount=actor.getString("total_price");
//                          type=actor.getString("order_type");
//                          order_id=actor.getString("order_id");
//                            status=actor.getString("status_code");
//
//                            if (jsonObj.has("material")) {
//                                JSONArray login_array1 = jsonObj.getJSONArray("material");
//                                Utility.cartmaterial=new ArrayList<>();
//                                Material m;
//                                for(int k=0;k<login_array1.length();k++){
//                                    m=new Material();
//                                    actor = login_array1.getJSONObject(k);
//                                    m.setName(actor.getString("material_name"));
//                                    m.setWorker_name(actor.getString("worker_name"));
//m.setQty(actor.getString("no_unit"));
//Double priceperUnit= Double.parseDouble(actor.getString("total_price"))/ Double.parseDouble(actor.getString("no_unit"));
//m.setPriceperunit(String.valueOf(priceperUnit));
//m.setTotalprice(actor.getString("total_price"));
//
////                                    actor = login_array.getJSONObject(k);
////
////                                    m.set(actor.getString("order_id"));
////
////                                    requests.add(ca);
//                                    Utility.cartmaterial.add(m);
//
//                                }
//
//                            }
//                            if (jsonObj.has("service")) {
//                                JSONArray login_array2 = jsonObj.getJSONArray("service");
//                                Utility.cartservice=new ArrayList<>();
//                                Services m;
//                                for(int k=0;k<login_array2.length();k++){
//                                    m=new Services();
//                                    actor = login_array2.getJSONObject(k);
//                                    m.setName(actor.getString("service_name"));
//                                    m.setWorker_name(actor.getString("worker_name"));
//
//m.setAdddetails(actor.getString("requestdetails"));
//                                    m.setRate(actor.getString("rate"));
//
//
//                                    Utility.cartservice.add(m);
//
//                                }
//
//                            }
//                            requests=new ArrayList<>();
//                            RequestData ca;
//
//                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                            android.support.v4.app.Fragment  fragment = null;
//                            fragment = new Cart();
//                            transaction.replace(R.id.frame_container, fragment);
//                            Bundle build=new Bundle();
//                            if(shipAddress!=null){
//                            build.putString("shipAddress",shipAddress);
//                            build.putString("contactno",Contact);
//                                build.putString("email",email);
//                                build.putString("type",type);
//                                build.putString("orderid",order_id);
//                                build.putString("amount",amount);
//                                build.putString("Status",status);
//
//                            fragment.setArguments(build);}
//                           // getActivity().getSupportFragmentManager().popBackStack();
//                           // transaction.addToBackStack(null);
//                            transaction.commit();
//
//                       //     setRecyclerView();
//                        }
//                        if(ResponseHandler.progressDialog!=null)
//                            ResponseHandler.progressDialog.dismiss();
//                        setRecyclerView();
//
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
//                    Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
//
//                } else {
//                    Toast.makeText(getContext(), "Can't Connect with server", Toast.LENGTH_SHORT).show();
//
//                }
//
//                if(ResponseHandler.progressDialog!=null)
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