package com.lb.lahorebroast.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class Products
{
    @SerializedName("id")
    @Expose
     var id: Int? = null

    @SerializedName("product_description")
    @Expose
     var productDescription: String? = null

    @SerializedName("name")
    @Expose
     var name: String? = null

    @SerializedName("image")
    @Expose
     var image: String? = null

    @SerializedName("sell_price_inc_tax")
    @Expose
     var sellPriceIncTax: String? = null

    @SerializedName("unit")
    @Expose
     var unit: String? = null

    @SerializedName("category")
    @Expose
     var category: String? = null

    @SerializedName("variation_id")
    @Expose
     var variation_id: Int? = null

    @SerializedName("is_promoted")
    @Expose
    var is_promoted: String? = null


}