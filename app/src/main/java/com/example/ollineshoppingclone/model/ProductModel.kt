package com.example.ollineshopping.model


import java.io.Serializable
//@Entity(tableName = "product")
data class ProductModel(
//    @PrimaryKey(autoGenerate = true)
    val uid : Int,
    val id : Int,
    val name : String,
    val price : String,
    val image : String,
    var cartCount : Int
):Serializable