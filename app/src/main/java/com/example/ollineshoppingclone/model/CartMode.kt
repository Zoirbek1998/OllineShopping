package com.example.ollineshopping.model

import java.io.Serializable

data class CartMode(
    val product_id: Int,
    var count: Int
):Serializable
