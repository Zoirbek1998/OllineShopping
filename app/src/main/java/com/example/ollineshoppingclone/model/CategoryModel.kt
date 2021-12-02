package com.example.ollineshopping.model


//@Entity(tableName = "categories")
data class CategoryModel(
//    @PrimaryKey(autoGenerate = true)
    val uid:Int,
    val id : Int,
    val title : String,
    val icon : String,
    var chacked : Boolean = false
    )