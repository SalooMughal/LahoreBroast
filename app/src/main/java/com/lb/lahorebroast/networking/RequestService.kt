package com.lb.lahorebroast.networking

import com.lb.lahorebroast.model.*
import okhttp3.RequestBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

interface RequestService {
        @Headers("Content-Type: application/json")
        @POST("customer-login")
        fun userLogin(
                @Body name: HashMap<String, Any>
        ): Call<RegisterationResponse>

        //
        @Headers("Content-Type: application/json")
        @POST("customer-register")
        fun userRegister(
                @Body name: HashMap<String, Any>
        ): Call<RegisterationResponse>

        @Headers("Content-Type: application/json")
        @POST("customer-update-profile")
        fun userUpdate(
                @Body name: HashMap<String, Any>
        ): Call<RegisterationResponse>


        @GET("user/order/requests")
        fun getOrder(
                @Query("id") id : Int
        ) : Call<OrderResponseGet>
//
//    @FormUrlEncoded
//    @POST("coupon")
//    fun validateCoupon(
//        @Field("coupon_code") coupon_code: String
//    ) : Call<CouponResponse>
//
//        @FormUrlEncoded
//        @POST("user/forget")
//        fun forgetPassword(
//            @Field("email") email: String
//        ) : Call<String>
//
//        @FormUrlEncoded
//        @POST("user/orders/create")
//        fun placeOrder(
//            @Field("user_id") user_id: String,
//            @Field("type") type: String,
//            @Field("cart_items") cart_items: JSONArray,
//            @Field("total_price") total_price: String,
//            @Field("time_slot") time_slot: JSONObject,
//            @Field("coupon_code") coupon_code: JSONObject,
//            @Field("total_items") total_items: Int,
//            @Field("notes") notes: String
//
//        ) : Call<OrderResponse>
//
//        @FormUrlEncoded
//        @POST("search")
//        fun searchItem(
//            @Field("query") query: String,
//            @Field("page")  page : Int
//        ) : Call<SearchResponse>
//
        @GET("promotions")
        fun getInitialData(
        ): Call<Promotions>

        @GET("locations")
        fun getLocations(
        ): Call<LocationResponse>

        @GET("categories")
        fun getCategories(
        ): Call<ArrayList<Category>>

        @GET("products")
        fun getProducts(
        ): Call<ArrayList<Products>>

        @Headers("Content-Type: application/json")
        @POST("save-order-request")
        fun createOrders(
                @Body name: HashMap<String, Any>
        ): Call<OrderResponse>
}