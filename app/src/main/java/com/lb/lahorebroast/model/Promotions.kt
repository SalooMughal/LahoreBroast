package com.lb.lahorebroast.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class Promotions {
    @SerializedName("success")
    @Expose
     var success: Boolean? = null

    @SerializedName("status")
    @Expose
     var status: Int? = null

    @SerializedName("data")
    @Expose
     var data: List<Datum?>? = null

    @SerializedName("message")
    @Expose
     var message: String? = null


    class Datum {
        @SerializedName("id")
        @Expose
        var id: Int? = null

        @SerializedName("url")
        @Expose
        var url: String? = null

    }
}