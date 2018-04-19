package com.example.pentagon.virtualassistant.fragment;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pentagon.virtualassistant.Cabd;
import com.example.pentagon.virtualassistant.DataRequest;
import com.example.pentagon.virtualassistant.LoginActivity;
import com.example.pentagon.virtualassistant.R;
import com.example.pentagon.virtualassistant.Rectrofit.ResponseCallback;
import com.example.pentagon.virtualassistant.Rectrofit.ResponseHandler;
import com.example.pentagon.virtualassistant.Rectrofit.Retrofit_Helper;
import com.example.pentagon.virtualassistant.RecyclerViewAdapterEvents;
import com.example.pentagon.virtualassistant.RecyclerViewAdapterRequests;
import com.example.pentagon.virtualassistant.SharedPreferenceClass;
import com.example.pentagon.virtualassistant.SignupActivity;
import com.example.pentagon.virtualassistant.Utility;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


import retrofit2.Call;

import static android.content.ContentValues.TAG;

public class RequestFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Matrix matrix = new Matrix();
    Matrix savedMatrix = new Matrix();

    // We can be in one of these 3 states
    static final int NONE = 0;
    static final int DRAG = 1;
    static final int ZOOM = 2;
    int mode = NONE;

    // Remember some things for zooming
    PointF start = new PointF();
    PointF mid = new PointF();
    float oldDist = 1f;
    String savedItemClicked;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View view;
HashMap<String,String> search;
    int profile=0;
    Button btnpost;
ArrayList<DataRequest> dataRequests;
    private static String userid="";
    EditText title,description;
    AutoCompleteTextView place;
    Spinner searchtype;
    EditText searchtxt;
    TextView searchrslt;
    Button btnsearch;
    String placeid="-1";
    ImageButton cancel,add;
    private Button sort;
RecyclerView recyclerView;
    private RecyclerViewAdapterRequests adapter1;

    public RequestFragment() {
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
        view =inflater.inflate(R.layout.fragment_requests, container, false);
        Utility.fab.setVisibility(View.GONE);

        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerView);



Utility.requestFragment=this;
        dataRequests=new ArrayList<>();

        getRequests();
        return view;
    }

    public void getRequests() {


        Map<String, String> params = new HashMap<>();
        //jObj = new JSONObject();
        // params.put("uid", uid);
        params.put("uid", Utility.USERID);

        //  params.put("address", address);

        Call<JsonObject> jsonObjectCall = new Retrofit_Helper().getRetrofitBuilder().getfromServer("getrequests.php", params);
        jsonObjectCall.clone().enqueue(new ResponseHandler(getActivity(), new ResponseCallback() {
            @Override
            public void getResponse(int code, JsonObject jsonObject) {

                JSONObject jsonObj = null;
                try {
                    jsonObj = new JSONObject(jsonObject.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                //   // {"ticket":[{"Id":"1","userid":"2","ticket_type":"Bus","from_to":"EKM - TVM","travel_date":"24-4-2018","status":"Requested","details":"Any Timing","request_date":"2018-04-17 23:32:30"},

                if (jsonObj != null) {
                    Log.d("getfromserver", "b4 extract");
                    JSONObject actor = null;
                    try {
                        dataRequests=new ArrayList<>();
                        if (jsonObj.has("ticket")) {
                           JSONArray login_array = jsonObj.getJSONArray("ticket");
//                        //    Log.i("new_loginid_456", "" + actor.getInt("USER_ID"));
for(int i=0;i<login_array.length();i++){
    actor = login_array.getJSONObject(i);
    DataRequest de=new DataRequest();
    de.setId(actor.getString("Id"));
    de.setTicket_type(actor.getString("ticket_type"));
    de.setFrom_to(actor.getString("from_to"));
    de.setTravel_date(actor.getString("travel_date"));
    de.setStatus(actor.getString("status"));
    de.setDetails(actor.getString("details"));
    de.setRequest_date(actor.getString("request_date"));

    dataRequests.add(de);
}
                            //

setView();
                        }
                    } catch (Exception eee) {
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
    public void setView() {
       // dataRequests=new Cabd(getContext()).getEvent();
     //   Utility.re=this;
        adapter1 = new RecyclerViewAdapterRequests(getContext(),dataRequests);
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

}