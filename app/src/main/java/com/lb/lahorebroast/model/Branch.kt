package com.lb.lahorebroast.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Branch {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("branch_name")
    @Expose
    var branchName: String? = null

    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("created_by")
    @Expose
    var createdBy: Int? = null

    @SerializedName("city_id")
    @Expose
    var cityId: Int? = null
    @SerializedName("areas")
    @Expose
    var areas: List<Area>? = null
}