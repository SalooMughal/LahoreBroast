package com.lb.lahorebroast.cart

import androidx.lifecycle.LiveData

class Repository private constructor(private val dao: CartDao) {

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: Repository? = null

        fun getInstance(dao: CartDao) =
            instance ?: synchronized(this) {
                instance ?: Repository(dao).also { instance = it }
            }
    }

    fun getAllUsers(): LiveData<List<Cart>> = dao.getAll()

    fun insertUser(cart: Cart) = dao.insertAll(cart)

    fun deleteCart(cart: Cart) = dao.delete(cart)

    fun deleteAll() = dao.deleteAll()

    fun getByID(id: Int) = dao.findById(id)

    fun updateByID(id: Int, quantity: Int , totalCount: Double) = dao.updateByID(id, quantity,totalCount)

}