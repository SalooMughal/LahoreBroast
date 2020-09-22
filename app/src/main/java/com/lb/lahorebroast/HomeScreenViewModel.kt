package com.lb.lahorebroast

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lb.lahorebroast.model.Products
import com.lb.lahorebroast.model.Promotions
import com.lb.lahorebroast.utilities.CacheManager

class HomeScreenViewModel : ViewModel() {
    private var productLiveList: MutableLiveData<ArrayList<Products>> = MutableLiveData<ArrayList<Products>>()
    private var productList: ArrayList<Products> = ArrayList()
    private var sliderLiveList: MutableLiveData<ArrayList<Promotions.Datum>> = MutableLiveData()
    private var sliderList: ArrayList<Promotions.Datum> = ArrayList()
    init {
        CacheManager.instance.getProductsData().let {
            it ->
            val list = it.filter { it.is_promoted == "true"}
                productList.addAll(list)


        }
        CacheManager.instance.getInitialData().let {
            it -> sliderList.addAll(it)
        }
    }

    fun getIsSlider():Boolean
    {
        return CacheManager.instance.getInitialData().isNotEmpty();
    }
    fun getSliders(): MutableLiveData<ArrayList<Promotions.Datum>> {

        sliderLiveList.value = sliderList
        return sliderLiveList
    }


    fun getProducts(): MutableLiveData<ArrayList<Products>> {

        productLiveList.value = productList
        return productLiveList
    }

    fun setLists(list: ArrayList<Products>) {
        productLiveList.value = list
    }
}