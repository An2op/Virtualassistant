package com.example.pentagon.virtualassistant;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
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
            "userid TEXT DEFAULT NULL,"+

            "date TEXT DEFAULT NULL,"+
            "event TEXT DEFAULT NULL,type TEXT DEFAULT NULL,value TEXT DEFAULT NULL)";
    public static String CREATE_TABLE_TEMP="CREATE TABLE tbltemp("+
            "id INTEGER DEFAULT NULL,"+
            "pid TEXT DEFAULT NULL)";

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
    db.execSQL(CREATE_TABLE_TEMP);

}catch (Exception e){
e.printStackTrace();
Log.i("error",e.toString());}
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try{

            db.execSQL(CREATE_TABLE_EVENTS);
            db.execSQL(CREATE_TABLE_TEMP);
            Log.i("dbcreated","sss");


            //   Toast.makeText(co, "success", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
            Log.i("error",e.toString());}
    }


    public int insertTemp(String eid,String pid){
        DataEvent actor;

        SQLiteDatabase dd=this.getReadableDatabase();
        Cursor cursor=dd.rawQuery("select id from tbltemp",null );
        int transactioncount=cursor.getCount();
        ArrayList<DataEvent> df=new ArrayList<>();
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
        String insertCategoryQuery = "INSERT INTO tbltemp(" +
                "id,"+
                "pid)"+

                " VALUES (?,?)";

        dd= this.getWritableDatabase();
        SQLiteStatement insertCategory = dd.compileStatement(insertCategoryQuery);



                    insertCategory.clearBindings();

                    insertCategory.bindString(1,eid);
                    insertCategory.bindString(2,String.valueOf(transactioncount++));

                    //insertCategory.bindString(9,actor.getString("published"));

                    Log.i("error1","111");

                    insertCategory.execute();//Insert();
                    status=true;


                //       "INSERT OR REPLACE INTO tax(gstid,cgst,sgst,igst,GST,published) VALUES(" + actor.getString("gstid") + ",'" + actor.getString("cgst") + "," + actor.getString("igst") + actor.getString("igst") + ")";






        return transactioncount;
    }
    public boolean insertEvent(ArrayList<DataEvent> jsn){
       DataEvent actor;

        SQLiteDatabase dd=this.getReadableDatabase();
       Cursor cursor=dd.rawQuery("select id from tblevent",null );
       int transactioncount=cursor.getCount();
       ArrayList<DataEvent> df=new ArrayList<>();
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
                "userid,"+
                "date,"+
                "event,type,value"+")"+
                " VALUES (?,?,?,?,?,?)";

        dd= this.getWritableDatabase();
        SQLiteStatement insertCategory = dd.compileStatement(insertCategoryQuery);

        try {
            //JSONArray jsn=new JSONArray(data);


            for(int i=0;i<jsn.size();i++){

                actor   = new DataEvent();
actor=jsn.get(i);
                try {

                    insertCategory.clearBindings();
                    transactioncount++;

                    insertCategory.bindLong(1,actor.getId());
                    insertCategory.bindString(2,actor.getUser());
                    insertCategory.bindString(3,actor.getDate());
                    Log.e("date",actor.getDate());
                    insertCategory.bindString(4,actor.getDesc());
                    insertCategory.bindString(5,actor.getType());
                    insertCategory.bindString(6,actor.getValue());
                    //insertCategory.bindString(9,actor.getString("published"));

                    Log.i("error1","111");

                    insertCategory.execute();//Insert();
                    status=true;
                    df.add(actor);
                }catch (android.database.sqlite.SQLiteConstraintException ee){
                    ee.printStackTrace();
                    Log.i("error",ee.toString()+"");
                }
                //       "INSERT OR REPLACE INTO tax(gstid,cgst,sgst,igst,GST,published) VALUES(" + actor.getString("gstid") + ",'" + actor.getString("cgst") + "," + actor.getString("igst") + actor.getString("igst") + ")";

            }

        }catch(Exception e){

            e.printStackTrace();
        }

//Utility.addReminder(df,co);
Utility.setAlaram1(co,df);

        return status;
    }
    public ArrayList<DataEvent> getEvent(String type){
        SQLiteDatabase dd=this.getReadableDatabase();
        String dde;
if(!type.equals(""))
         dde="select * from tblevent where type='"+type+"'";
else
     dde="select * from tblevent order by date desc";
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
                    dt.setId(Integer.parseInt(cursor.getString(0)));
                    dt.setUser(cursor.getString(1));
                    dt.setDate(cursor.getString(2));

                    dt.setDesc(cursor.getString(3));
                    dt.setType(cursor.getString(4));
                    dt.setValue(cursor.getString(5));
                    dta.add(dt);


                } catch (Exception ee) {
                    ee.printStackTrace();
                    Log.e("er",ee.toString()+"");
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        dd.close();
        return dta;

    }























    public ArrayList<Cursor> getData(String Query){
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[] { "message" };
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2= new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);

        try{
            String maxQuery = Query ;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);

            //add value to cursor2
            Cursor2.addRow(new Object[] { "Success" });

            alc.set(1,Cursor2);
            if (null != c && c.getCount() > 0) {

                alc.set(0,c);
                c.moveToFirst();

                return alc ;
            }
            return alc;
        } catch(SQLException sqlEx){
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+sqlEx.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        } catch(Exception ex){
            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+ex.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        }

}
public boolean delete(String id){
boolean status=false;
    SQLiteDatabase dd=this.getWritableDatabase();

    String dde="delete  from tblevent where id='"+id+"'";
  dd.execSQL(dde);
    SQLiteDatabase dd1=this.getReadableDatabase();

    String dde1="select pid from tbltemp where id='"+id+"'";
    Cursor cursor=dd.rawQuery(dde1,null );
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

                AlarmManager alarmMgr = (AlarmManager) co.getSystemService(Context.ALARM_SERVICE);
                Intent intent = new Intent(co, MyReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(co, Integer.parseInt(cursor.getString(0)), intent, 0);
alarmMgr.cancel(pendingIntent);

            } catch (Exception ee) {
                ee.printStackTrace();
                Log.e("er",ee.toString()+"");
            }
        } while (cursor.moveToNext());
    }
cursor.close();
  dde="delete  from tbltemp where id='"+id+"'";
    dd.execSQL(dde);
    return true;
}

    public boolean deleteAll(){
        boolean status=false;
        SQLiteDatabase dd=this.getWritableDatabase();

        String dde="delete  from tblevent";
        dd.execSQL(dde);
        SQLiteDatabase dd1=this.getReadableDatabase();

        String dde1="select pid from tbltemp";
        Cursor cursor=dd.rawQuery(dde1,null );
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

                    AlarmManager alarmMgr = (AlarmManager) co.getSystemService(Context.ALARM_SERVICE);
                    Intent intent = new Intent(co, MyReceiver.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(co, Integer.parseInt(cursor.getString(0)), intent, 0);
                    alarmMgr.cancel(pendingIntent);

                } catch (Exception ee) {
                    ee.printStackTrace();
                    Log.e("er",ee.toString()+"");
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        dde="delete  from tbltemp";
        dd.execSQL(dde);
        return true;
    }
}