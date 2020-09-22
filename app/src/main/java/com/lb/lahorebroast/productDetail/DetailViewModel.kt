package com.lb.lahorebroast.productDetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.lb.lahorebroast.cart.Cart
import com.lb.lahorebroast.cart.Repository
import com.lb.lahorebroast.utilities.AppDatabase

class DetailViewModel (application: Application) : AndroidViewModel(application) {
    private var allCart : LiveData<List<Cart>>? = null
    private var repository : Repository
    private var isSlider = false


    init {
        val db = AppDatabase(application)
        repository = Repository.getInstance(db.todoDao())
        allCart = getAllCart()
    }



    fun getAllCart(): LiveData<List<Cart>> = repository.getAllUsers()

    fun insertCart(cart: Cart) = repository.insertUser(cart)

    fun getProductByID(id : Int ) = repository.getByID(id)

    fun updateCart(id : Int , quantity : Int , total : Double) = repository.updateByID(id,quantity,total)
}