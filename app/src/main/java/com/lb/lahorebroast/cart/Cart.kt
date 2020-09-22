package com.lb.lahorebroast.cart

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class Cart(
        @PrimaryKey(autoGenerate = true) var id: Long?,
        @ColumnInfo(name = "product_id") var product_id: Int,
        @ColumnInfo(name = "variation_id") var variation_id: Int,
        @ColumnInfo(name = "product_name") var product_name: String,
        @ColumnInfo(name = "product_price") var product_price: Double,
        @ColumnInfo(name = "product_qty") var product_qty: Int,
        @ColumnInfo(name = "product_unit") var product_unit: String,
        @ColumnInfo(name = "product_total") var product_total: Double,
        @ColumnInfo(name = "product_image") var product_image: String

    )
