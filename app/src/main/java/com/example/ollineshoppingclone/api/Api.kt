package com.example.ollineshoppingclone.api

import com.example.ollineshopping.model.BaseResponse
import com.example.ollineshopping.model.CategoryModel
import com.example.ollineshopping.model.OfferModel
import com.example.ollineshopping.model.ProductModel
import com.example.ollineshopping.model.request.GetProductsByIdsReequest
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Api {
    @GET("get_offers")
    fun getOffers() : Observable<BaseResponse<List<OfferModel>>>

    @GET("get_categories")
    fun getCategoriy() : Observable<BaseResponse<List<CategoryModel>>>

    @GET("get_top_products")
    fun getTopProducts() : Observable<BaseResponse<List<ProductModel>>>

    @GET("get_products/{category_id}")
    fun getCategoryProduct(@Path("category_id") categoryId : Int) : Observable<BaseResponse<List<ProductModel>>>

    @POST("get_products_by_ids")
    fun getProductByIds(@Body requset: GetProductsByIdsReequest) : Observable<BaseResponse<List<ProductModel>>>
}