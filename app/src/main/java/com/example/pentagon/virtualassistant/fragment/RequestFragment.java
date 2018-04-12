package com.example.pentagon.virtualassistant.fragment;

import android.Manifest;
import android.app.Dialog;
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
import com.example.pentagon.virtualassistant.R;
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






//permission();
//permission();




     //   getPlans();
      //  sorting(0);
        return view;
    }


//    private void search(String type, String text) {
//        plantemp=new ArrayList<>();
//        plan=planbackup;
//        int flag=0;
//        Set<String> keys = search.keySet();
//        for(int j=0;j<plan.size();j++){
//            flag=1;
//            for(String key: keys){
//                // System.out.println("Value of "+key+" is: "+autodata1.get(key));
//                //customerdatalist.add(autodata1.get(key));
//                if(key.equals("Sqft above")){
//                    if(Double.parseDouble(plan.get(j).getSqft())>=(Double.parseDouble(search.get(key)))){
//
//                      //  plantemp.add(plan.get(j));
//                    }else
//                        flag=0;
//                }
//                else if(key.equals("Sqft below")){
//                    if(Double.parseDouble(plan.get(j).getSqft())<=(Double.parseDouble(search.get(key)))){
//
//                     //   plantemp.add(plan.get(j));
//                    }else
//                        flag=0;
//                }
//                else if(key.equals("story")){
//                    if(Double.parseDouble(plan.get(j).getStory())==(Double.parseDouble(search.get(key)))){
//
//                       // plantemp.add(plan.get(j));
//                    }else
//                        flag=0;
//                }
//                else if(key.equals("bedroom")){
//
//                    if(Double.parseDouble(plan.get(j).getBedroom())==(Double.parseDouble(search.get(key)))){
//
//                      //  plantemp.add(plan.get(j));
//                    }else
//                        flag=0;
//                }
//
//                else if(key.equals("bathroom")){
//                    if(Double.parseDouble(plan.get(j).getBathroom())==(Double.parseDouble(search.get(key)))){
//
//                    //    plantemp.add(plan.get(j));
//                    }else
//                        flag=0;
//
//                }
//                else if(key.equals("rate above")){
//                    if(Double.parseDouble(plan.get(j).getRate())>=(Double.parseDouble(search.get(key)))){
//
//                        //plantemp.add(plan.get(j));
//                    }else
//                        flag=0;
//
//                }
//                else if(key.equals("rate below")){
//                    if(Double.parseDouble(plan.get(j).getRate())<=(Double.parseDouble(search.get(key)))){
//
//                       // plantemp.add(plan.get(j));
//                    }else
//                        flag=0;
//                }
//
//            }
//if(flag==1)
//    plantemp.add(plan.get(j));
//
//
//           // compareinner();
//
//
//
//        }
//        plan=plantemp;
//        setRecyclerView(plan);
//      // adapter1.notifyDataSetChanged();
//
//    }
//
//    private void compareinner() {
//ArrayList<PlanData> plantemp1=new ArrayList<>();
//plantemp1=plantemp;
//plantemp=new ArrayList<>();
//        Set<String> keys = search.keySet();
//        for(int j=0;j<plantemp1.size();j++){
//            for(String key: keys){
//                // System.out.println("Value of "+key+" is: "+autodata1.get(key));
//                //customerdatalist.add(autodata1.get(key));
//                if(key.equals("Sqft above")){
//                    if(Double.parseDouble(plantemp1.get(j).getSqft())>=(Double.parseDouble(search.get(key)))){
//
//                        plantemp.add(plantemp1.get(j));
//                    }
//                }
//                else if(key.equals("Sqft below")){
//                    if(Double.parseDouble(plantemp1.get(j).getSqft())<=(Double.parseDouble(search.get(key)))){
//
//                        plantemp.add(plantemp1.get(j));
//                    }
//                }
//                else if(key.equals("story")){
//                    if(Double.parseDouble(plantemp1.get(j).getStory())==(Double.parseDouble(search.get(key)))){
//
//                        plantemp.add(plantemp1.get(j));
//                    }
//                }
//                else if(key.equals("bedroom")){
//
//                    if(Double.parseDouble(plan.get(j).getBedroom())==(Double.parseDouble(search.get(key)))){
//
//                        plantemp.add(plantemp1.get(j));
//                    }
//                }
//
//                else if(key.equals("bathroom")){
//                    if(Double.parseDouble(plantemp1.get(j).getBathroom())==(Double.parseDouble(search.get(key)))){
//
//                        plantemp.add(plantemp1.get(j));
//                    }
//
//                }
//                else if(key.equals("rate above")){
//                    if(Double.parseDouble(plantemp1.get(j).getRate())>=(Double.parseDouble(search.get(key)))){
//
//                        plantemp.add(plantemp1.get(j));
//                    }
//
//                }
//                else if(key.equals("rate below")){
//                    if(Double.parseDouble(plantemp1.get(j).getRate())<=(Double.parseDouble(search.get(key)))){
//
//                        plantemp.add(plantemp1.get(j));
//                    }
//                }
//            }
//
//
//
//
//
//
//
//        }
//    }
//
//
//    void initilize() {
//        search=new HashMap<>();
//        sort=(Button)view.findViewById(R.id.sort) ;
//        sort.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(sort.getTag().toString().equals("1")){
//
//                    sorting(0);
//                 //sort.setBackgroundResource(R.drawable.ic_decrese_24dp);
//                }else {
//
//                    sorting(1);
//
//                    // sort.setBackgroundResource(R.drawable.ic_increase_24dp);
//                }
//            }
//        });
//
//        cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(planbackup!=null)
//                    plan=planbackup;
//                setRecyclerView(plan);
//              sorting(0);
//                searchrslt.setText("");
//                search=new HashMap<>();
//            }
//        });
//add.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View v) {
//        if(!searchtxt.getText().toString().isEmpty()){
//        Set<String> keys = search.keySet();
//
//            for(String key: keys){
//
//                if(key.equals(searchtype.getSelectedItem().toString())){
//               search.remove(key);
//               break;
//                }
//            }
//        search.put(searchtype.getSelectedItem().toString(),searchtxt.getText().toString());
//        String ss="";
//        for(String key: keys){
//
//          ss=ss+"("+key+":"+search.get(key)+")";
//        }
//        searchrslt.setText(ss);
//            searchtxt.setText("");
//    }}
//});
//
//    }
//    public void getPlans(){
//
//
//        Map<String, String> params = new HashMap<>();
//        //jObj = new JSONObject();
////params.put("latitude","1.2");
////params.put("longitude","1.23.2");
//
//
//        Call<JsonObject> jsonObjectCall = new Retrofit_Helper().getRetrofitBuilder().getcategory(Utility.getplanurl);
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
//                plan=new ArrayList<>();
//                if (jsonObj != null) {
//                    Log.d("getfromserver", jsonObj.toString()+" 11");
//                    JSONObject actor = null;
//                    try {
//                        if (jsonObj.has("plan")) {
//                            JSONArray login_array = jsonObj.getJSONArray("plan");
//
//                            //  actor = jsonObj.getJSONObject("USER");
////                            Log.i("new_loginid_456", "" + actor.getInt("USER_ID"));
//                            //     Utility.mandatory=new ArrayList<>();
//
//                            PlanData ca;
//                            for(int k=0;k<login_array.length();k++){
//                                ca=new PlanData();
////{"plan":[{"plan_id":"1","plan_sqft":"2650 sqft","plan_rate":"Rs. 3500000","plan_bedroom":"5",
//// "plan_bathroom":"5","plan_story":"2","plan_path":"http:\/\/lapisapps.in\/dreamhome\/plan\/20180201.png","modified_date":"2018-02-01 15:40:23"}]}
//                                actor = login_array.getJSONObject(k);
//
//                                ca.setPlanid(actor.getString("plan_id"));
//
//                                ca.setSqft(actor.getString("plan_sqft"));
//                                ca.setRate(actor.getString("plan_rate"));
//                                //  ca.setImage(actor.getString("buildname"));
//                                ca.setBedroom(actor.getString("plan_bedroom"));
//                                ca.setBathroom(actor.getString("plan_bathroom"));
//                                ca.setStory(actor.getString("plan_story"));
//                                ca.setImage(actor.getString("plan_path"));
//                                ca.setContact(actor.getString("contact_no"));
//                                plan.add(ca);
//
//                            }
//
//planbackup=plan;
//
//                            setRecyclerView(plan);
//                            sorting(0);
//                        }
//                        if(ResponseHandler.progressDialog!=null)
//                            ResponseHandler.progressDialog.dismiss();
//                        setRecyclerView(plan);
//                        sorting(0);
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
//    private  void setRecyclerView(final List<PlanData> plan1) {
//
//        adapter1 = new RecyclerViewAdapterPlan(getContext(),plan1,this);
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
//
//    }
//
//    private void loadImage(String image) {
//        Dialog dd=new Dialog(getContext());
//        dd.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dd.setContentView(R.layout.dialogimage);
//        dd.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//        dd.setCancelable(true);
//
//        Glide.with(getContext()).load(image).into((ImageView) dd.findViewById(R.id.image));
//        dd.show();
//        Window window = dd.getWindow();
//        window.setLayout(LinearLayoutCompat.LayoutParams.MATCH_PARENT, 650);
//        ((ImageView) dd.findViewById(R.id.image)).setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                ImageView view = (ImageView) v;
//                dumpEvent(event);
//
//                // Handle touch events here...
//                switch (event.getAction() & MotionEvent.ACTION_MASK) {
//                    case MotionEvent.ACTION_DOWN:
//                        savedMatrix.set(matrix);
//                        start.set(event.getX(), event.getY());
//                        Log.d(TAG, "mode=DRAG");
//                        mode = DRAG;
//                        break;
//                    case MotionEvent.ACTION_POINTER_DOWN:
//                        oldDist = spacing(event);
//                        Log.d(TAG, "oldDist=" + oldDist);
//                        if (oldDist > 10f) {
//                            savedMatrix.set(matrix);
//                            midPoint(mid, event);
//                            mode = ZOOM;
//                            Log.d(TAG, "mode=ZOOM");
//                        }
//                        break;
//                    case MotionEvent.ACTION_UP:
//                    case MotionEvent.ACTION_POINTER_UP:
//                        mode = NONE;
//                        Log.d(TAG, "mode=NONE");
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        if (mode == DRAG) {
//                            // ...
//                            matrix.set(savedMatrix);
//                            matrix.postTranslate(event.getX() - start.x, event.getY()
//                                    - start.y);
//                        } else if (mode == ZOOM) {
//                            float newDist = spacing(event);
//                            Log.d(TAG, "newDist=" + newDist);
//                            if (newDist > 10f) {
//                                matrix.set(savedMatrix);
//                                float scale = newDist / oldDist;
//                                matrix.postScale(scale, scale, mid.x, mid.y);
//                            }
//                        }
//                        break;
//                }
//
//                view.setImageMatrix(matrix);
//                return true;
//            }
//        });
//
//    }
////    @Override
////    public boolean onTouch(View v, MotionEvent event) {
////        // TODO Auto-generated method stub
////
////
////    }
//
//    private void dumpEvent(MotionEvent event) {
//        String names[] = { "DOWN", "UP", "MOVE", "CANCEL", "OUTSIDE",
//                "POINTER_DOWN", "POINTER_UP", "7?", "8?", "9?" };
//        StringBuilder sb = new StringBuilder();
//        int action = event.getAction();
//        int actionCode = action & MotionEvent.ACTION_MASK;
//        sb.append("event ACTION_").append(names[actionCode]);
//        if (actionCode == MotionEvent.ACTION_POINTER_DOWN
//                || actionCode == MotionEvent.ACTION_POINTER_UP) {
//            sb.append("(pid ").append(
//                    action >> MotionEvent.ACTION_POINTER_ID_SHIFT);
//            sb.append(")");
//        }
//        sb.append("[");
//        for (int i = 0; i < event.getPointerCount(); i++) {
//            sb.append("#").append(i);
//            sb.append("(pid ").append(event.getPointerId(i));
//            sb.append(")=").append((int) event.getX(i));
//            sb.append(",").append((int) event.getY(i));
//            if (i + 1 < event.getPointerCount())
//                sb.append(";");
//        }
//        sb.append("]");
//        Log.d(TAG, sb.toString());
//    }
//
//    /** Determine the space between the first two fingers */
//    private float spacing(MotionEvent event) {
//        float x = event.getX(0) - event.getX(1);
//        float y = event.getY(0) - event.getY(1);
//        return (float) Math.sqrt(x * x + y * y);
//    }
//
//    /** Calculate the mid point of the first two fingers */
//    private void midPoint(PointF point, MotionEvent event) {
//        float x = event.getX(0) + event.getX(1);
//        float y = event.getY(0) + event.getY(1);
//        point.set(x / 2, y / 2);
//    }
//    public  void putplace(){
//
//
//    }
//
//
//
//
//    private void getFollowers() {
//
//
//    }
//    public void permission(){
//
//        if (ContextCompat.checkSelfPermission(getActivity(),
//                Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//
//            // Should we show an explanation?
//            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
//                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//
//                // Show an explanation to the user *asynchronously* -- don't block
//                // this thread waiting for the user's response! After the user
//                // sees the explanation, try again to request the permission.
//
//                ActivityCompat.requestPermissions(getActivity(),
//                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                        1);
//
//            } else {
//
//                // No explanation needed, we can request the permission.
//
//                ActivityCompat.requestPermissions(getActivity(),
//                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
//                        1);
//
//                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
//                // app-defined int constant. The callback method gets the
//                // result of the request.
//            }
//        }
//    }
//    @Override
//    public void onRequestPermissionsResult(int requestCode,
//                                           String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case 2: {
//                // If request is cancelled, the result arrays are empty.
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                    // permission was granted, yay! Do the
//                    // contacts-related task you need to do.
//                    putplace();
//
//                } else {
//
//                    // permission denied, boo! Disable the
//                    // functionality that depends on this permission.
//                    getActivity().getSupportFragmentManager().popBackStack();
//                }
//                return;
//            }
//            case 1: {
//                // If request is cancelled, the result arrays are empty.
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                    // permission was granted, yay! Do the
//                    // contacts-related task you need to do.
//
//                } else {
//
//                    // permission denied, boo! Disable the
//                    // functionality that depends on this permission.
//                }
//                return;
//            }
//
//
//
//
//            // other 'case' lines to check for other
//            // permissions this app might request.
//        }
//    }
//    private void sorting(int i) {
//
//        if(i==0){
//            sort.setTag("0");
//            sort.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_decrease_24dp, 0, 0, 0);
//
//            for(int j=0;j<plan.size();j++){
//                for(int k=j+1;k<plan.size();k++){
//                    if(Double.parseDouble(plan.get(j).getRate())> Double.parseDouble(plan.get(k).getRate())){
//                        PlanData cc;
//                        cc=plan.get(j);
//                        plan.set(j,plan.get(k));
//                        plan.set(k,cc);
//
//                    }
//
//                }
//            }}else {
//            sort.setTag("1");
//            sort.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_increase_24dp, 0, 0, 0);
//
//            for(int j=0;j<plan.size();j++){
//                for(int k=j+1;k<plan.size();k++){
//                    if(Double.parseDouble(plan.get(j).getRate())< Double.parseDouble(plan.get(k).getRate())){
//                        PlanData cc;
//                        cc=plan.get(j);
//                        plan.set(j,plan.get(k));
//                        plan.set(k,cc);
//
//                    }
//
//                }
//            }
//
//        }
//        adapter1.notifyDataSetChanged();
//    }

}