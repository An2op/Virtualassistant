package com.example.pentagon.virtualassistant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;



/**
 * Created by asus on 05-Feb-17.
 */


public class Cabd extends SQLiteOpenHelper {
    public static String mob;
    public static String type="mmm";
    public static String db="Cab.db";
// "id": "1",
//         "name": "Cash In Hand",
//         "code": "Cash",
//         "ref_id": "0",
//         "ref_code": "yes",
//         "is_default": "0",
//         "acc_main_id": "4",
//         "openning_bal": "0.00",
//         "published": "yes"
//id
//
//        voucher_id
//
//    from voucher table
//
//            acc_ledgers_id
//
//    from screen and cash
//
//    credit_amounnt
//
//0 or from screen
//
//            debit_amount
//
//0 or from screen
//
//            published
//
//    yes

    public static String CREATE_TABLE_EVENTS="CREATE TABLE tblevent("+
            "id INTEGER PRIMARY KEY,"+
            "date TEXT DEFAULT NULL,"+
            "event TEXT DEFAULT NULL)";

    SQLiteDatabase dd;
    Context co;
   public Cabd(Context context)
   {
       super(context,db,null,19);
       co=context;

   }
    @Override
    public void onCreate(SQLiteDatabase db) {

try{
    Log.i("error","gggggg");
    db.execSQL(CREATE_TABLE_EVENTS);

}catch (Exception e){
e.printStackTrace();
Log.i("error",e.toString());}
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try{

            db.execSQL(CREATE_TABLE_EVENTS);

            Log.i("dbcreated","sss");


            //   Toast.makeText(co, "success", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
            Log.i("error",e.toString());}
    }
    public boolean insertEvent(ArrayList<DataEvent> jsn){
       DataEvent actor;

        SQLiteDatabase dd=this.getReadableDatabase();
       Cursor cursor=dd.rawQuery("select id from tblevent",null );
       int transactioncount=cursor.getCount();
       cursor.close();
        boolean	status=false;
//        SQLiteDatabase db=this.getWritableDatabase();
//        "id INTEGER UNSIGNED NOT NULL,"+0
//                "acc_ledgers_id TEXT DEFAULT NULL,"+1
//                "acc_ledgers_name TEXT DEFAULT NULL,"+2
//                "date TEXT DEFAULT NULL,"+3
//                "credit_amounnt TEXT DEFAULT '0',"+4
//                "debit_amount TEXT DEFAULT '0',"+5
//                "type TEXT DEFAULT 'Yes'," +6
//                "sync Text DEFAULT '0')";7
        //   "id": "1",
//                            "name": "Cash In Hand",
//                            "code": "Cash",
//                            "ref_id": "0",
//                            "ref_code": "yes",
//                            "is_default": "0",
//                            "acc_main_id": "4",
//                            "openning_bal": "0.00",
//                            "published": "yes"
        String insertCategoryQuery = "INSERT INTO tblevent(" +
                "id,"+
                "date,"+
                "event"+")"+
                " VALUES (?,?,?,?,?,?,?,?)";

        dd= this.getWritableDatabase();
        SQLiteStatement insertCategory = dd.compileStatement(insertCategoryQuery);

        try {
            //JSONArray jsn=new JSONArray(data);


            for(int i=0;i<jsn.size();i++){

                actor   = new DataEvent();
actor=jsn.get(i);
                try {

                    insertCategory.clearBindings();
                    insertCategory.bindLong(1,transactioncount++);
                    insertCategory.bindString(2,actor.getDate());
                    insertCategory.bindString(3,actor.getDesc());

                    //insertCategory.bindString(9,actor.getString("published"));

                    Log.i("error1","111");

                    insertCategory.execute();//Insert();
                    status=true;
                }catch (android.database.sqlite.SQLiteConstraintException ee){
                    ee.printStackTrace();
                    Log.i("error",ee.toString()+"");
                }
                //       "INSERT OR REPLACE INTO tax(gstid,cgst,sgst,igst,GST,published) VALUES(" + actor.getString("gstid") + ",'" + actor.getString("cgst") + "," + actor.getString("igst") + actor.getString("igst") + ")";

            }

        }catch(Exception e){

            e.printStackTrace();
        }




        return status;
    }
    public ArrayList<DataEvent> getVoucherSync(){
        SQLiteDatabase dd=this.getReadableDatabase();

        String dde="select * from tblevent";
        Cursor cursor=dd.rawQuery(dde,null );


        // Cursor cursor=dd.rawQuery("select voucher.id,,voucher.amount,voucher.naration, from voucher,acc_ledgers where vourcher_type='"+type+"'",null );
        DataEvent dt;
        ArrayList<DataEvent> dta=new ArrayList<DataEvent>();
        if (cursor.moveToFirst()) {
            do {
                try {
//                [{ "amount":"1000",
//                        "narration":"shyam",
//                        "date":"2018-02-11",
//                        "created_by":"1",
//                        "type":"pay",
//                        "vid":"137",
//                        "toid":"1"}]

                    dt = new DataEvent();
                    dt.setId(cursor.getString(0));
                    dt.setDate(cursor.getString(1));

                    dt.setDesc(cursor.getString(2));

                    dta.add(dt);


                } catch (Exception ee) {
                    ee.printStackTrace();
                    Log.e("er",ee.toString()+"");
                }
            } while (cursor.moveToNext());
        }
        dd.close();
        return dta;

    }

























}