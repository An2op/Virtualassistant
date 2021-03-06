package com.example.pentagon.virtualassistant.Rectrofit;

import com.google.gson.JsonObject;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by az-sys on 19/8/17.
 */

public interface Web_Interface {
   @Headers({"Content-Type: application/json", "Cache-Control: max-age=640000"})
   @POST("{id}")
   Call<JsonObject> getfromServer(@Path("id") String groupId, @Body Map<String, String> params);//@Field("id")String id, @Field("phone_number")String phone_number

    @Headers({"Content-Type: application/json", "Cache-Control: max-age=640000"})
    @POST("{id}")
    Call<JsonObject> getfromServersave(@Path("id") String groupId, @Body String params);//@Field("id")String id, @Field("phone_number")String phone_number

    @Headers("Cache-Control: max-age=640000")
    @GET("{id}")
    Call<JsonObject> getcategory(@Path("id") String groupId);//@Field("id")String id, @Field("phone_number")String phone_number






//    @POST("sp_Auth_Retrieve_Chat_Data_For_Local_11102017.php")
// Call<JsonObject> getChat(@Body Map<String, String> params);//@Field("id")String id, @Field("phone_number")String phone_number
//
//   @Headers({"Content-Type: application/json", "Cache-Control: max-age=640000"})
   @POST("sp_Auth_Retrieve_Chat_Data_For_Local_11102017.php")
   Call<JsonObject> login(@Body Map<String, String> params);//@Field("id")String id, @Field("phone_number")String phone_number

//"sp_Login_Retrieve_Chat_All_27062017.php"
    @FormUrlEncoded
    @POST("register/en")
    public Call<JsonObject> UserRegister(@Field("name") String name, @Field("email") String email, @Field("password") String password, @Field("phone_number") String phone_number);


    @FormUrlEncoded
    @POST("send-otp/en")
    public Call<JsonObject> sendOtp(@Field("id") String id, @Field("phone_number") String phone_number);


    @FormUrlEncoded
    @POST("login.php")
    public Call<JsonObject> verifyOtp(@Field("ulogin") String id, @Field("upwd") String phone_number);



    @FormUrlEncoded
    @POST("todayEventTab/stopped")
    public Call<JsonObject> getStoppedVehicle_List(@Field("user_id") String user_id, @Field("timezone_diff") String timezone_diff, @Field("client_id") String client_id);

    @FormUrlEncoded
    @POST("todayEventTab/halted")
    public Call<JsonObject> gethaltedVehicle_List(@Field("user_id") String user_id, @Field("timezone_diff") String timezone_diff, @Field("client_id") String client_id);


    @FormUrlEncoded
    @POST("todayEventTab/lost_connection")
    public Call<JsonObject> getConnectionlostVehicle_List(@Field("user_id") String user_id, @Field("timezone_diff") String timezone_diff, @Field("client_id") String client_id);


    @FormUrlEncoded
    @POST("todayEventTab/vehicle_count")
    public Call<JsonObject> getVehicle_Count(@Field("user_id") String user_id, @Field("timezone_diff") String timezone_diff, @Field("client_id") String client_id);

    @FormUrlEncoded
    @POST("vehicle/vehicle_details")
    public Call<JsonObject> getVehicleDetails(@Field("user_id") String user_id, @Field("client_id") String client_id, @Field("vehicle_id") String vehicle_id, @Field("time_diff") String time_diff);

    @FormUrlEncoded
    @POST("todayEventTab/tamper_alert")
    public Call<JsonObject> getTamperAlerts(@Field("user_id") String user_id, @Field("client_id") String client_id, @Field("timezone_diff") String time_diff);

    @FormUrlEncoded
    @POST("todayEventTab/insurance_alert")
    public Call<JsonObject> getInsuranceAlerts(@Field("user_id") String user_id, @Field("client_id") String client_id, @Field("timezone_diff") String time_diff);

    @FormUrlEncoded
    @POST("todayEventTab/shift_alert")
    public Call<JsonObject> getShiftAlerts(@Field("user_id") String user_id, @Field("client_id") String client_id, @Field("timezone_diff") String time_diff);

    @FormUrlEncoded
    @POST("todayEventTab/power_alert")
    public Call<JsonObject> getPowerAlerts(@Field("user_id") String user_id, @Field("client_id") String client_id, @Field("timezone_diff") String time_diff);


    @FormUrlEncoded
    @POST("tracking/live_data")
    public Call<JsonObject> getLivedata(@Field("vehicle_id") String vehicle_id, @Field("vehicle_group") String vehicle_group, @Field("user_id") String user_id, @Field("client_id") String client_id);

    @FormUrlEncoded
    @POST("tracking/history")
    public Call<JsonObject> getHistory(@Field("vehicle_id") String vehicle_id, @Field("from_datetime") String from_datetime, @Field("user_id") String user_id, @Field("to_datetime") String to_datetime);

    @FormUrlEncoded
    @POST("todayEventTab/logout")
    public Call<JsonObject> logout_User(@Field("user_id") String user_id);

}
