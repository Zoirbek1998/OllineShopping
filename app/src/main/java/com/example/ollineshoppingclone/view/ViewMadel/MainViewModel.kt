package com.example.ollineshoppingclone.view.ViewMadel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ollineshopping.model.CategoryModel
import com.example.ollineshopping.model.OfferModel
import com.example.ollineshopping.model.ProductModel
import com.example.ollineshoppingclone.api.repasitory.ShopRepasitory

class MainViewModel : ViewModel() {

    val repasitory = ShopRepasitory()

    val error = MutableLiveData<String>()
    val offerData = MutableLiveData<List<OfferModel>>()
    val categoryData = MutableLiveData<List<CategoryModel>>()
    val productData = MutableLiveData<List<ProductModel>>()
    val progress = MutableLiveData<Boolean>()


    fun getOffers(){
    repasitory.getOffers(error,progress,offerData)
    }
    fun getCategory(){
        repasitory.getCategory(error,progress,categoryData)
    }
    fun getTopProducts(){
        repasitory.getTopProducts(error,progress, productData)
    }
    fun getProductByCategory(id:Int){
        repasitory.getProductByCategory(id,error,progress,productData)
    }
    fun getProductByIds(ids: List<Int>){
        repasitory.getProductByIds(ids,error,progress,productData)
    }

//    //Malumotlar bazasiga yukalash
//    fun insertAllProducts2DB(items : List<ProductModel>){
//        CoroutineScope(Dispatchers.IO).launch {
//            AppDataBase.getdataBase().getProductDao().insertAll(items)
//            CoroutineScope(Dispatchers.Main).launch {
//                error.value = "Malumotlar bazzasiga yuklandi"
//            }
//        }
//    }
//
//    fun insertAllCategory2DB(items : List<CategoryModel>){
//        CoroutineScope(Dispatchers.IO).launch {
//            AppDataBase.getdataBase().getCategoryDao().insertAll(items)
//
//        }
//    }
//
//    fun getAllDBProduct(){
//        CoroutineScope(Dispatchers.Main).launch {
//            productData.value = withContext(Dispatchers.IO){
//                AppDataBase.getdataBase().getProductDao().getAllProducts()
//            }
//        }
//    }
//    fun getAllDBCAtegories(){
//        CoroutineScope(Dispatchers.Main).launch {
//            categoryData.value = withContext(Dispatchers.IO){
//                AppDataBase.getdataBase().getCategoryDao().getAllCategories()
//            }
//        }
//    }
}