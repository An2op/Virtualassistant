package com.example.pentagon.virtualassistant.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.pentagon.virtualassistant.R;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;




public class Home extends Fragment {
    View view;

    VideoView v;

    private String resp;
AutoCompleteTextView search;
    private Button btnmaterial;
    private Button btnservices;
    private Button sort;
String currentData;


    public Home() {
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

      //  initilize();
//if(search.getText().toString().isEmpty())
//
//       // getServiceList();
        return view;
  }

//    public void getMaterialList(){
//
//
////        Map<String, String> params = new HashMap<>();
////        //jObj = new JSONObject();
////        params.put("uid", Utility.USERID);
////params.put("latitude","1.2");
////params.put("longitude","1.23.2");
//
//
//        Call<JsonObject> jsonObjectCall = new Retrofit_Helper().getRetrofitBuilder().getcategory(Utility.getmaterilalisturl);
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
//                        if (jsonObj.has("material")) {
//                            JSONArray login_array = jsonObj.getJSONArray("material");
//
//                            //  actor = jsonObj.getJSONObject("USER");
////                            Log.i("new_loginid_456", "" + actor.getInt("USER_ID"));
//                            //     Utility.mandatory=new ArrayList<>();
//                            Utility.materialsList=new ArrayList<>();
//                            Material ca;
//                            for(int k=0;k<login_array.length();k++){
//                                ca=new Material();
//
//                                actor = login_array.getJSONObject(k);
//
//                                ca.setName(actor.getString("material_name"));
//                                ca.setId(actor.getString("material_id"));
//                                ca.setDescription(actor.getString("description"));
////                                ca.setFiletype(actor.getString("filetype"));
////                                ca.setUserid(actor.getString("user_id"));
////                                //  ca.setImage(actor.getString("buildname"));
////                                ca.setDppic(actor.getString("profile_pic"));
////                                ca.setTitle(actor.getString("place_name"));
////                                ca.setFilepath(actor.getString("filepath"));
////                                ca.setDescription(actor.getString("description"));
////                                ca.setDate(actor.getString("modified_date"));
////                                ca.setIsjoined("-1");
//                                Utility.materialsList.add(ca);
//
//                            }
//
//
//                            //    setRecyclerViewServices();
//                            materialSearch();
//
//                        }
//                        if(ResponseHandler.progressDialog!=null)
//                            ResponseHandler.progressDialog.dismiss();
//                        //    setRecyclerViewServices();
//                        materialSearch();
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
//    public void getServiceList(){
//
//
////        Map<String, String> params = new HashMap<>();
////        //jObj = new JSONObject();
////        params.put("uid", Utility.USERID);
////params.put("latitude","1.2");
////params.put("longitude","1.23.2");
//
//
//        Call<JsonObject> jsonObjectCall = new Retrofit_Helper().getRetrofitBuilder().getcategory(Utility.getservicelisturl);
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
//                        if (jsonObj.has("service")) {
//                            JSONArray login_array = jsonObj.getJSONArray("service");
//
//                            //  actor = jsonObj.getJSONObject("USER");
////                            Log.i("new_loginid_456", "" + actor.getInt("USER_ID"));
//                            //     Utility.mandatory=new ArrayList<>();
//                            Utility.servicesList=new ArrayList<>();
//                            Services ca;
//                            for(int k=0;k<login_array.length();k++){
//                                ca=new Services();
//
//                                actor = login_array.getJSONObject(k);
//
//                                ca.setName(actor.getString("service_name"));
//ca.setId(actor.getString("service_id"));
////                                ca.setFiletype(actor.getString("filetype"));
////                                ca.setUserid(actor.getString("user_id"));
////                                //  ca.setImage(actor.getString("buildname"));
////                                ca.setDppic(actor.getString("profile_pic"));
////                                ca.setTitle(actor.getString("place_name"));
////                                ca.setFilepath(actor.getString("filepath"));
////                                ca.setDescription(actor.getString("description"));
////                                ca.setDate(actor.getString("modified_date"));
////                                ca.setIsjoined("-1");
//                                Utility.servicesList.add(ca);
//
//                            }
//
//
//                        //    setRecyclerViewServices();
//                            serviceSearch();
//
//                        }
//                        if(ResponseHandler.progressDialog!=null)
//                            ResponseHandler.progressDialog.dismiss();
//                    //    setRecyclerViewServices();
//                        serviceSearch();
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
//    public void getWorkersService(String id, final String name){
//
////        if(id.equals("-1")&&services.size()>0){
////            setRecyclerViewServices();
////return;
////        }
//        Map<String, String> params = new HashMap<>();
//        //jObj = new JSONObject();
//        params.put("serviceid", id);
////params.put("latitude","1.2");
////params.put("longitude","1.23.2");
//
//
//        Call<JsonObject> jsonObjectCall = new Retrofit_Helper().getRetrofitBuilder().getfromServer(Utility.getserviceworkurl,params);
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
//                        if (jsonObj.has("data")) {
//                            JSONArray login_array = jsonObj.getJSONArray("data");
//
//                            //  actor = jsonObj.getJSONObject("USER");
////                            Log.i("new_loginid_456", "" + actor.getInt("USER_ID"));
//                            //     Utility.mandatory=new ArrayList<>();
//                            Utility.services=new ArrayList<>();
//                            Services ca;
//                            for(int k=0;k<login_array.length();k++){
//                                ca=new Services();
//
//                                actor = login_array.getJSONObject(k);
//
//                            //    ca.setName(actor.getString("service_name"));
//                                ca.setId(actor.getString("service_id"));
//                                ca.setName(actor.getString("service_name"));
//                                ca.setWorker_service_id(actor.getString("worker_service_id"));
//                                ca.setWorker_id(actor.getString("worker_id"));
//                                  ca.setRate(actor.getString("rate"));
//                                ca.setDetails(actor.getString("details"));
//                                ca.setLogin_id(actor.getString("login_id"));
//                                ca.setWorker_name(actor.getString("worker_name"));
//                                ca.setWorker_address(actor.getString("worker_address"));
//                                ca.setWorker_description(actor.getString("worker_description"));
//   ca.setContact(actor.getString("contact_no"));
//                                ca.setEmail(actor.getString("email"));
//                                Utility.services.add(ca);
//
//                            }
//
//
//                              setRecyclerViewServices();
//                            sorting(0);
//                            //serviceSearch();
//
//                        }
//                        if(ResponseHandler.progressDialog!=null)
//                            ResponseHandler.progressDialog.dismiss();
//                          setRecyclerViewServices();
//                        sorting(0);
//                      //  serviceSearch();
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
//        }, jsonObjectCall,0));
//
//    }
//    public void getWorkersMaterials(String id, final String name){
//
////if(id.equals("-1")&&materials.size()>0){
////    setRecyclerViewMaterial();
////    return;
////}
//        Map<String, String> params = new HashMap<>();
//        //jObj = new JSONObject();
//        params.put("materialid", id);
////params.put("latitude","1.2");
////params.put("longitude","1.23.2");
//
//
//        Call<JsonObject> jsonObjectCall = new Retrofit_Helper().getRetrofitBuilder().getfromServer(Utility.getmaterialeworkurl,params);
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
//                        if (jsonObj.has("data")) {
//                            JSONArray login_array = jsonObj.getJSONArray("data");
//
//                            //  actor = jsonObj.getJSONObject("USER");
////                            Log.i("new_loginid_456", "" + actor.getInt("USER_ID"));
//                            //     Utility.mandatory=new ArrayList<>();
//                            materials=new ArrayList<>();
//                            Material ca;
//                            for(int k=0;k<login_array.length();k++){
//                                ca=new Material();
//
//                                actor = login_array.getJSONObject(k);
////{"data":[{"worker_material_id":"1","material_id":"2","worker_id":"1","priceperunit":"35","unit":"1","details":"12*8","modified_time":
//// "2018-01-18 00:00:00","login_id":"1","worker_name":"sam","worker_address":"pala","worker_description":"electric"
//                                ca.setName(actor.getString("material_name"));
//                                ca.setWorker_material_id(actor.getString("worker_material_id"));
//                                ca.setMaterial_id(actor.getString("material_id"));
//                                ca.setWorker_id(actor.getString("worker_id"));
//                                ca.setPriceperunit(actor.getString("priceperunit"));
//                                  ca.setDetails(actor.getString("details"));
//                                ca.setWorker_name(actor.getString("worker_name"));
//                                ca.setContact(actor.getString("contact_no"));
//                                ca.setEmail(actor.getString("email"));
//                                ca.setWorker_address(actor.getString("worker_address"));
//                                ca.setWorker_description(actor.getString("worker_description"));
//                                ca.setImage(actor.getString("image"));
//ca.setTotalprice(actor.getString("priceperunit"));
//                                materials.add(ca);
//
//                            }
//
//
//                            //    setRecyclerViewServices();
//                            setRecyclerViewMaterial();
//                            sorting(0);
//
//                        }
//                        if(ResponseHandler.progressDialog!=null)
//                            ResponseHandler.progressDialog.dismiss();
//                        setRecyclerViewMaterial();
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
//        }, jsonObjectCall,0));
//
//    }
//    void initilize(){
////
////        materials=new ArrayList<>();
//        sort=(Button)view.findViewById(R.id.sort) ;
//        sort.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(sort.getTag().toString().equals("1")){
//
//                    sorting(0);
//                    //sort.setBackgroundResource(R.drawable.ic_decrese_24dp);
//                }else {
//
//                    sorting(1);
//
//                    // sort.setBackgroundResource(R.drawable.ic_increase_24dp);
//                }
//            }
//        });
//if(Utility.servicesList==null)
//    Utility.servicesList=new ArrayList<>();
//if(Utility.materialsList==null)
//    Utility.materialsList=new ArrayList<>();
//        btnmaterial=(Button)view.findViewById(R.id.btnmaterial);
//        btnservices=(Button)view.findViewById(R.id.btnservices);
//
//        btnservices.setBackgroundColor(getResources().getColor(R.color.white));
//        btnmaterial.setBackgroundColor(getResources().getColor(R.color.c1));
//        btnmaterial.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                currentData="mat";
//                search.setText("");
//                if(Utility.materialsList.size()<=0){
//
//                    getMaterialList();
//                }else
//                    materialSearch();
////                Utility.services=new ArrayList<>();
////                adapter1.notifyDataSetChanged();
//                btnservices.setBackgroundColor(getResources().getColor(R.color.white));
//                btnmaterial.setBackgroundColor(getResources().getColor(R.color.c1));
//                if(adapter1!=null){
//                    Utility.services=new ArrayList<>();
//                    adapter1.notifyDataSetChanged();}
//
//            }
//        });
//        btnservices.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                search.setText("");
//                currentData="ser";
//                if(Utility.servicesList.size()<=0){
//
//                    getServiceList();
//                }else
//                    serviceSearch();
////                Utility.materials=new ArrayList<>();
////                adapter2.notifyDataSetChanged();
//                btnservices.setBackgroundColor(getResources().getColor(R.color.c1));
//                btnmaterial.setBackgroundColor(getResources().getColor(R.color.white));
//                if(adapter2!=null){
//                    Log.i("ddd","ddd");
//Utility.materials=null;
//                    adapter2.notifyDataSetChanged();}
//            }
//        });
//if(Utility.services==null)
//    Utility.services=new ArrayList<>();
//else if(Utility.services.size()>0) {
//    btnservices.setBackgroundColor(getResources().getColor(R.color.c1));
//    btnmaterial.setBackgroundColor(getResources().getColor(R.color.white));
//    serviceSearch();
//    setRecyclerViewServices();
//   return;
//}
//        if(Utility.materials==null)
//            Utility.materials=new ArrayList<>();
//        else if(Utility.materials.size()>0) {
//            btnservices.setBackgroundColor(getResources().getColor(R.color.white));
//            btnmaterial.setBackgroundColor(getResources().getColor(R.color.c1));
//            materialSearch();
//
//            setRecyclerViewMaterial();
//return;
//        }
//
//
//        if(Utility.materialsList==null)
//            getMaterialList();
//        else if(Utility.materialsList.size()<=0)
//            getMaterialList();
//        else
//            materialSearch();
//      //  materialSearch();
//
//
//    }
//    private void sorting(int i) {
//     if(currentData.equals("mat")){
//         ArrayList<Material> plan=new ArrayList<>();
//         plan=Utility.materials;
//
//         if(i==0){
//             sort.setTag("0");
//             sort.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_decrease_24dp, 0, 0, 0);
//
//             for(int j=0;j<plan.size();j++){
//                 for(int k=j+1;k<plan.size();k++){
//
//                     if(Double.parseDouble(plan.get(j).getPriceperunit())> Double.parseDouble(plan.get(k).getPriceperunit())){
//                         Material cc;
//                         cc=plan.get(j);
//                         plan.set(j,plan.get(k));
//                         plan.set(k,cc);
//
//                     }
//
//                 }
//             }}else {
//             sort.setTag("1");
//             sort.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_increase_24dp, 0, 0, 0);
//
//             for(int j=0;j<plan.size();j++){
//                 for(int k=j+1;k<plan.size();k++){
//                     if(Double.parseDouble(plan.get(j).getPriceperunit())< Double.parseDouble(plan.get(k).getPriceperunit())){
//                         Material cc;
//                         cc=plan.get(j);
//                         plan.set(j,plan.get(k));
//                         plan.set(k,cc);
//
//                     }
//
//                 }
//             }
//
//         }
//         Utility.materials=plan;
//         adapter2.notifyDataSetChanged();
//
//
//     }else {
//
//         ArrayList<Services> plan=new ArrayList<>();
//         plan=Utility.services;
//         if(i==0){
//             sort.setTag("0");
//             sort.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_decrease_24dp, 0, 0, 0);
//
//             for(int j=0;j<plan.size();j++){
//                 for(int k=j+1;k<plan.size();k++){
//                     if(Double.parseDouble(plan.get(j).getRate())> Double.parseDouble(plan.get(k).getRate())){
//                       Services cc;
//                         cc=plan.get(j);
//                         plan.set(j,plan.get(k));
//                         plan.set(k,cc);
//
//                     }
//
//                 }
//             }}else {
//             sort.setTag("1");
//             sort.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_increase_24dp, 0, 0, 0);
//
//             for(int j=0;j<plan.size();j++){
//                 for(int k=j+1;k<plan.size();k++){
//                     if(Double.parseDouble(plan.get(j).getRate())< Double.parseDouble(plan.get(k).getRate())){
//                         Services cc;
//                         cc=plan.get(j);
//                         plan.set(j,plan.get(k));
//                         plan.set(k,cc);
//
//                     }
//
//                 }
//             }
//
//         }
//         Utility.services=plan;
//         adapter1.notifyDataSetChanged();
//     }
//
//
//    }
//    private void setRecyclerViewMaterial() {
//        currentData="mat";
//Utility.services=new ArrayList<>();
//        adapter2 = new RecyclerViewAdapterMaterials(getContext(),materials);
//        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
//
//        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL,false);
//        // mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//
//        recyclerView.setLayoutManager(mLayoutManager);
//        //  recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(adapter2);
//        recyclerView.setNestedScrollingEnabled(false);
////        recyclerView.addOnItemTouchListener(
////                new RecyclerItemClickListener(getContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
////                    @Override
////                    public void onItemClick(View view, final int position) {
////
////
////                    }
////
////                    @Override
////                    public void onLongItemClick(View view, final int position) {
////
////
////                    }
////                }));
//    }
//
//    private void setRecyclerViewServices() {
//        currentData="ser";
//materials=new ArrayList<>();
//        adapter1 = new RecyclerViewAdapterServices(getContext(),Utility.services);
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
////        recyclerView.addOnItemTouchListener(
////                new RecyclerItemClickListener(getContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
////                    @Override
////                    public void onItemClick(View view, final int position) {
////
////
////                    }
////
////                    @Override
////                    public void onLongItemClick(View view, final int position) {
////
////
////                    }
////                }));
//    }
//
//
//    public void materialSearch(){
////        materials=new ArrayList<>();
//////String [] ddd = new String[100];
////        String dd[]={"Anoop","Ramu","bindu"};
////for(int j=0;j<3;j++){
////Material m=new Material();
////m.setName(dd[j]);
////    materials.add(m);
////}
//        getWorkersMaterials("-1","");
//        search.setText("");
//setRecyclerViewMaterial();
//        ArrayAdapter<Material> adapter = new ArrayAdapter<Material>(getActivity(),android.R.layout.select_dialog_item,Utility.materialsList);
//        search.setThreshold(1);//will start working from first character
//        search.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
//        //  place.setTextColor(Color.RED);
//
//        search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
//                                    long arg3) {
//                Material selected = (Material) arg0.getAdapter().getItem(arg2);
////                Toast.makeText(getContext(),
////                        "Clicked " + arg2 + " name: " + selected.getPlacename(),
////                        Toast.LENGTH_SHORT).show();
//                getWorkersMaterials(selected.getId(),selected.getName());
//
//            }
//        });
//    }
//    public void serviceSearch(){
////        services=new ArrayList<>();
//////String [] ddd = new String[100];
////        String dd[]={"plumber","electric","painter"};
////        for(int j=0;j<3;j++){
////            Services m=new Services();
////            m.setName(dd[j]);
////            services.add(m);
////        }
//
//        getWorkersService("-1","");
//        search.setText("");
//setRecyclerViewServices();
//        ArrayAdapter<Services> adapter = new ArrayAdapter<Services>(getActivity(),android.R.layout.select_dialog_item,Utility.servicesList);
//        search.setThreshold(1);//will start working from first character
//        search.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
//        //  place.setTextColor(Color.RED);
//
//        search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
//                                    long arg3) {
//                Services selected = (Services) arg0.getAdapter().getItem(arg2);
////                Toast.makeText(getContext(),
////                        "Clicked " + arg2 + " name: " + selected.getPlacename(),
////                        Toast.LENGTH_SHORT).show();
//                getWorkersService(selected.getId(),selected.getName());
//
//            }
//        });
//    }
}