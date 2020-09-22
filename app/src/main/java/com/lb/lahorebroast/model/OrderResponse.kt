package com.lb.lahorebroast.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OrderResponse {
    @SerializedName("data")
    @Expose
    var data: Data? = null

    @SerializedName("status")
    @Expose
    var status: Boolean? = null

    @SerializedName("statusCode")
    @Expose
    var statusCode: Int? = null

    @SerializedName("msg")
    @Expose
    var msg: String? = null

    inner class Data {
        @SerializedName("customer_id")
        @Expose
        var customerId: Int? = null

        @SerializedName("instructions")
        @Expose
        var instructions: String? = null

        @SerializedName("order")
        @Expose
        var order: String? = null

        @SerializedName("complete_json")
        @Expose
        var completeJson: String? = null

        @SerializedName("status")
        @Expose
        var status: String? = null

        @SerializedName("city_id")
        @Expose
        var cityId: Int? = null

        @SerializedName("branch_id")
        @Expose
        var branchId: Int? = null

        @SerializedName("area_id")
        @Expose
        var areaId: Int? = null

        @SerializedName("address")
        @Expose
        var address: String? = null

        @SerializedName("order_source")
        @Expose
        var orderSource: String? = null

        @SerializedName("updated_at")
        @Expose
        var updatedAt: String? = null

        @SerializedName("created_at")
        @Expose
        var createdAt: String? = null

        @SerializedName("id")
        @Expose
        var id: Int? = null
    }
}