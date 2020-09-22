package com.lb.lahorebroast.cart

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CartDao {
    @Query("SELECT * FROM cart")
    fun getAll(): LiveData<List<Cart>>

    @Query("SELECT * FROM cart WHERE product_id LIKE :product_id")
    fun findById(product_id: Int): Cart?

    @Query("UPDATE cart SET product_qty = :quantity , product_total = :total  WHERE product_id == :product_id")
    fun updateByID(product_id: Int, quantity : Int , total : Double)

    @Query("DELETE FROM cart")
    fun deleteAll()

    @Insert
    fun insertAll(vararg cart: Cart)

    @Delete
    fun delete(cart: Cart)

    @Update
    fun updateCart(vararg cart: Cart)
}