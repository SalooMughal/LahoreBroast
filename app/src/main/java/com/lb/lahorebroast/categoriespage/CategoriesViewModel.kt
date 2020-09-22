package com.lb.lahorebroast.categoriespage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lb.lahorebroast.model.Category
import com.lb.lahorebroast.model.Products
import com.lb.lahorebroast.utilities.CacheManager

class CategoriesViewModel : ViewModel()
{
    private var productLiveList: MutableLiveData<ArrayList<Products>> = MutableLiveData<ArrayList<Products>>()
    private var productList: ArrayList<Products> = ArrayList()
    private var filterLiveList: MutableLiveData<ArrayList<Products>> = MutableLiveData<ArrayList<Products>>()
    private var filterList: ArrayList<Products> = ArrayList()
    private var catLiveList: MutableLiveData<ArrayList<Category>> = MutableLiveData<ArrayList<Category>>()
    private var catList: ArrayList<Category> = ArrayList()


        init {
            CacheManager.instance.getProductsData().let {
                    it -> productList.addAll(it)
            }

            CacheManager.instance.getCategoriesData().let {
                    it -> catList.addAll(it)
            }
        }

    fun getProducts(): MutableLiveData<ArrayList<Products>> {

        productLiveList.value = productList
        return productLiveList
    }

    fun getFilterProducts(): MutableLiveData<ArrayList<Products>> {

        filterLiveList.value = filterList
        return filterLiveList
    }
    fun getCategories(): MutableLiveData<ArrayList<Category>> {

        catLiveList.value = catList
        return catLiveList
    }

    fun filterList(categories: String)
    {
        filterList.clear()
        if(categories.equals("All"))
        {
            filterList.addAll(productList)
        }else {
            filterList.addAll(productList.filter { it.category.equals(categories) })
        }
        filterLiveList.value = filterList

    }
}