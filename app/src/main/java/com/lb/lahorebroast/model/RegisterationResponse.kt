package com.lb.lahorebroast.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RegisterationResponse {

    @SerializedName("success")
    @Expose
     val success: Boolean? = null

    @SerializedName("status")
    @Expose
     val status: Int? = null

    @SerializedName("data")
    @Expose
     val data: Data? = null

    @SerializedName("message")
    @Expose
     val message: String? = null

    class Data {
        @SerializedName("email")
        @Expose
        val email: String? = null

        @SerializedName("mobile")
        @Expose
        val mobile: String? = null

        @SerializedName("name")
        @Expose
        val name: String? = null

        @SerializedName("type")
        @Expose
        val type: String? = null

        @SerializedName("customer_group_id")
        @Expose
        val customerGroupId: String? = null

        @SerializedName("business_id")
        @Expose
        val businessId: Int? = null

        @SerializedName("created_by")
        @Expose
        val createdBy: Int? = null

        @SerializedName("custom_field1")
        @Expose
        val customField1: String? = null

        @SerializedName("contact_id")
        @Expose
        val contactId: String? = null

        @SerializedName("updated_at")
        @Expose
        val updatedAt: String? = null

        @SerializedName("created_at")
        @Expose
        val createdAt: String? = null

        @SerializedName("id")
        @Expose
        val id: Int? = null
        @SerializedName("shipping_address")
        @Expose
        val shipping_address: String? = null
    }

}