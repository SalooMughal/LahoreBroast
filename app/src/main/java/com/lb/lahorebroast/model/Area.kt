package com.lb.lahorebroast.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Area {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("area_name")
    @Expose
    var areaName: String? = null

    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("created_by")
    @Expose
    var createdBy: Int? = null

    @SerializedName("branch_id")
    @Expose
    var branchId: Int? = null
}