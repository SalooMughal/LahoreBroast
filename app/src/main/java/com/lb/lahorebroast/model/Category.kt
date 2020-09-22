package com.lb.lahorebroast.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Category {
    @SerializedName("id")
    @Expose
     val id: Int? = null

    @SerializedName("name")
    @Expose
     val name: String? = null

}