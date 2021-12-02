package com.example.ollineshopping.utils

import com.example.ollineshopping.model.CartMode
import com.example.ollineshopping.model.ProductModel
import com.orhanobut.hawk.Hawk

object PrefUtils {

    const val PREF_FAVORITES = "pref_favorites"
    const val PREF_CART = "pref_cart"
    const val PREF_FCM_TOKEN = "pref_fcm_tokn"

    fun setFAvorites(item: ProductModel) {
        val items = Hawk.get(PREF_FAVORITES, arrayListOf<Int>())

        if (items.filter { it == item.id }.firstOrNull() != null) {
            items.remove(item.id)
        } else {
            items.add(item.id)
        }
        Hawk.put(PREF_FAVORITES, items)
    }

    fun getFavoriteList(): ArrayList<Int> {
        return Hawk.get(PREF_FAVORITES, arrayListOf<Int>())
    }

    // Favaritlar ro'yhatida bor yoki yoq
    fun chackFavorite(item: ProductModel): Boolean {
        val items = Hawk.get(PREF_FAVORITES, arrayListOf<Int>())
        return items.filter { it == item.id }.firstOrNull() != null
    }

    fun setCart(item: ProductModel) {
        val items = Hawk.get<ArrayList<CartMode>>(PREF_CART, arrayListOf<CartMode>())
        val cart = items.filter { it.product_id == item.id }.firstOrNull()
        if (cart != null) {
            if (item.cartCount > 0) {
                cart.count = item.cartCount
            } else {
                items.remove(cart)
            }
        }
        else{
            val newCart = CartMode(item.id,item.cartCount)
            items.add(newCart)
        }

        Hawk.put(PREF_CART, items)
    }

    fun getCartList(): ArrayList<CartMode> {
        return Hawk.get(PREF_CART, arrayListOf<CartMode>())
    }

    fun getCartCount(item: ProductModel): Int {
         val items = Hawk.get<ArrayList<CartMode>>(PREF_CART, arrayListOf<CartMode>())
        return items.filter { it.product_id == item.id }.firstOrNull()?.count?:0
    }

    fun setFCMToken(value : String){
        Hawk.put(PREF_FCM_TOKEN,value)
    }
    fun getFCMToken() : String{
        return Hawk.get(PREF_FCM_TOKEN,"")
    }
}