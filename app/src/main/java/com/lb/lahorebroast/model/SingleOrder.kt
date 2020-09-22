package com.lb.lahorebroast.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SingleOrder {
    @SerializedName("unit_price")
    @Expose
    var unitPrice: String? = null

    @SerializedName("unit_price_inc_tax")
    @Expose
    var unitPriceIncTax: String? = null

    @SerializedName("product_id")
    @Expose
    var productId: Int? = null

    @SerializedName("product_name")
    @Expose
    var productName: String? = null

    @SerializedName("quantity")
    @Expose
    var quantity: Int? = null

    @SerializedName("tax")
    @Expose
    var tax: Int? = null

    @SerializedName("total")
    @Expose
    var total: String? = null

    @SerializedName("variation_id")
    @Expose
    var variationId: Int? = null
}