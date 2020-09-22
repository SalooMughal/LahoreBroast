package com.lb.lahorebroast.utilities

import com.lb.lahorebroast.model.*

class CacheManager {
    private val hashMap : HashMap<String,Boolean> = HashMap()
    private val hashMapInitialData : HashMap<String,ArrayList<Promotions.Datum>> = HashMap()
    private val hashMapInitialData2 : HashMap<String,ArrayList<Products>> = HashMap()
    private val hashMapInitialData3 : HashMap<String,ArrayList<Category>> = HashMap()
    private val hashMapInitialData4 : HashMap<String,ArrayList<Cities>> = HashMap()
    private val user : HashMap<String, RegisterationResponse.Data> = HashMap()
    private val isLogin : HashMap<String,Boolean> = HashMap()
    private object HOLDER  {
        val INSTANCE = CacheManager()
    }
    companion object {
        val instance: CacheManager by lazy { HOLDER.INSTANCE }
    }

    fun setMode(value : Boolean)
    {
         hashMap["MODE"] = value
    }
    fun getIsTabletMode(): Boolean
    {
        if(hashMap.containsKey("MODE")) return hashMap["MODE"]!!
        return false
    }
    fun setInitialData(initialData : ArrayList<Promotions.Datum>)
    {
        hashMapInitialData["promotions"] = initialData
    }

    fun getInitialData(): ArrayList<Promotions.Datum>
    {
        if(hashMapInitialData.containsKey("promotions")) return hashMapInitialData["promotions"]!!
        return ArrayList<Promotions.Datum>()
    }

    fun setProductsData(initialData : ArrayList<Products>)
    {
        hashMapInitialData2["products"] = initialData
    }

    fun getProductsData(): ArrayList<Products>
    {
        if(hashMapInitialData2.containsKey("products")) return hashMapInitialData2["products"]!!
        return ArrayList<Products>()
    }

    fun setCategoryData(initialData : ArrayList<Category>)
    {
        hashMapInitialData3["categories"] = initialData
    }

    fun getCategoriesData(): ArrayList<Category>
    {
        if(hashMapInitialData3.containsKey("categories")) return hashMapInitialData3["categories"]!!
        return ArrayList<Category>()
    }

    fun setLocationData(initialData : ArrayList<Cities>)
    {
        hashMapInitialData4["location"] = initialData
    }

    fun getLocationsData(): ArrayList<Cities>
    {
        if(hashMapInitialData4.containsKey("location")) return hashMapInitialData4["location"]!!
        return ArrayList<Cities>()
    }

    fun setUserData(initialData :  RegisterationResponse.Data)
    {
        user["user"] = initialData
    }

    fun getUserData(): RegisterationResponse.Data
    {
        if(user.containsKey("user")) return user["user"]!!
        return RegisterationResponse.Data()
    }

    fun setisLogin(initialData : Boolean)
    {
        isLogin["isLogin"] = initialData
    }

    fun getIsLogin(): Boolean
    {
        if(isLogin.containsKey("isLogin")) return isLogin["isLogin"]!!
        return false
    }

}