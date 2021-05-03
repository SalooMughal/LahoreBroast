package com.lb.lahorebroast.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OrderResponseGet {
    @SerializedName("data")
    @Expose
    var data: List<Data>? = null

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
        @SerializedName("id")
        @Expose
        val id: Int? = null

        @SerializedName("customer_id")
        @Expose
        val customerId: Int? = null

        @SerializedName("delivery_cost")
        @Expose
        val deliveryCost: Any? = null

        @SerializedName("instructions")
        @Expose
        val instructions: String? = null

        @SerializedName("order_source")
        @Expose
        val orderSource: String? = null

        @SerializedName("order")
        @Expose
        val order: String? = null

        @SerializedName("complete_json")
        @Expose
        val completeJson: String? = null

        @SerializedName("status")
        @Expose
        val status: String? = null

        @SerializedName("reviewed_by")
        @Expose
        val reviewedBy: Any? = null

        @SerializedName("transaction_id")
        @Expose
        val transactionId: Any? = null

        @SerializedName("city_id")
        @Expose
        val cityId: Int? = null

        @SerializedName("branch_id")
        @Expose
        val branchId: Int? = null

        @SerializedName("area_id")
        @Expose
        val areaId: Int? = null

        @SerializedName("address")
        @Expose
        val address: String? = null


        @SerializedName("cart_total")
        @Expose
        val cart_total: Double? = null

        @SerializedName("cart_qty")
        @Expose
        val cart_qty: Int? = null

        @SerializedName("coupon")
        @Expose
        val coupon_code: Coupon? = null

        @SerializedName("created_at")
        @Expose
        val createdAt: String? = null

        @SerializedName("updated_at")
        @Expose
        val updatedAt: String? = null
    }

    class Coupon {
        @SerializedName("coupon_code")
        @Expose
        var couponCode: String? = null

        @SerializedName("name")
        @Expose
        var name: String? = null

        @SerializedName("end_date")
        @Expose
        var endDate: String? = null

        @SerializedName("discount_amount")
        @Expose
        var discountAmount: String? = null

        @SerializedName("discount_type")
        @Expose
        var discountType: String? = null

    }
}