package com.example.pentagon.virtualassistant;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Ravi Tamada on 18/05/16.
 */
public class RecyclerViewAdapterDay extends RecyclerView.Adapter<RecyclerViewAdapterDay.MyViewHolder> {

    private final ArrayList<String> albumList;
//    public static ArrayList<String> albumList1;
    private Context mContext;
  //  private List<S> albumList;

private int type;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView date,event,phone,pincode,stationtype;
        public ImageView thumbnail, btnrequest;
public EditText reply;
        CheckedTextView check;
public Button delete;
        public MyViewHolder(View view) {
            super(view);
            check = (CheckedTextView) view.findViewById(R.id.item);

            event = (TextView) view.findViewById(R.id.station_type);

            //status = (TextView) view.findViewById(R.id.status);

        }
    }


    public RecyclerViewAdapterDay(Context mContext, ArrayList<String> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;

        this.type=type;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row1, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final String product = albumList.get(position);
        try {
            holder.check.setText(product);
            holder.check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.check.isChecked()){
                        holder.check.setChecked(false);
                        for(int j=0;j<Utility.albumList1.size();j++){
Log.e("ddd",j+product);
                            if(Utility.albumList1.get(j).equals(product))
                             Utility.albumList1.remove(j);
                        }

                }else{

                        holder.check.setChecked(true);
                        Utility.albumList1.add(product);
                    }

                }
            });
//            holder.check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    if (isChecked)
//                        albumList1.add(product);
//                    else
//                        albumList1.remove(holder.getAdapterPosition());
//
//                }
//            });
            //   holder.file.setTag(product.getImage());
            //  holder.shipaddress.setText("shipping_address:"+product.getOrder_shipping_address());
        }catch (Exception e){e.printStackTrace();}

    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view) {
//        // inflate menu
//        PopupMenu popup = new PopupMenu(mContext, view);
//        popup.getMenuInflater().inflate(R.menu.s, popup.getMenu());
////        MenuInflater inflater = popup.getMenuInflater();
////        inflater.inflate(R.menu.menu_product, popup.getMenu());
//        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
//        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
//    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {
//
//        public MyMenuItemClickListener() {
//        }
//
//        @Override
//        public boolean onMenuItemClick(MenuItem menuItem) {
//            switch (menuItem.getItemId()) {
//                case R.id.action_add_favourite:
//                    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
//                    return true;
//                case R.id.action_play_next:
//                    Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
//                    return true;
//                default:
//            }
//            return false;
//        }
//    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }
//    private void deletearrang(final String noidd) {
//
//
//        Map<String, String> params = new HashMap<>();
//        //jObj = new JSONObject();
//        params.put("markid", noidd);
//
//
//
//        Call<JsonObject> jsonObjectCall = new Retrofit_Helper().getRetrofitBuilder().getfromServer(Utility.markremoveurl,params);
//        jsonObjectCall.clone().enqueue(new ResponseHandler(mContext, new ResponseCallback() {
//            @Override
//            public void getResponse(int code, JsonObject jsonObject) {
//
//                JSONObject jsonObj = null;
//                try {
//                    jsonObj = new JSONObject(jsonObject.toString());
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
////{"users":[{"admin_id":"admin","password":"admin"}]}
//
//                //JSONObject jsonObj = new JSONObject(datafromserver);
//
//                if (jsonObj != null) {
//                    Log.d("getfromserver", "b4 extract");
//                    JSONObject actor = null;
//
//
//                    try {
//                        if (jsonObj.has("mark")) {
//                            if(jsonObj.getString("mark").equals("true")){
//                                Toast.makeText(mContext, "Removed", Toast.LENGTH_SHORT).show();
////for(int i=0;i<station.size();i++){
////
////    if(station.get(i).getFaculty_id().equals(noid)){
////        station.remove(i);
////        break;
////
////    }
////}
//                                Utility.MarkViewActivity.getNotification();
//                                //  Log.i("ddd",station.size()+"");
////adapter1.notifyDataSetChanged();
////setView();
//
//                                //  JSONArray login_array = jsonObj.getJSONArray("faculty");
//
//                            }else {
//                                Toast.makeText(mContext, "Failed", Toast.LENGTH_SHORT).show();
//
//
//                            }}
//
//
//                    } catch (Exception eee) {
//                        eee.printStackTrace();
//                    }
//                    //     setView();
//                }
//            }
//            @Override
//            public void getFailure(Call<JsonObject> call, int code) {
//                if (code==1) {
//
//                    Toast.makeText(mContext, "No Internet Connection", Toast.LENGTH_SHORT).show();
//
//                } else {
//                    Toast.makeText(mContext, "Can't Connect with server", Toast.LENGTH_SHORT).show();
//
//                }
//                if( ResponseHandler.progressDialog!=null)
//                    ResponseHandler.progressDialog.dismiss();
//
//            }
//
//
//        }, jsonObjectCall,1));
//    }
}
