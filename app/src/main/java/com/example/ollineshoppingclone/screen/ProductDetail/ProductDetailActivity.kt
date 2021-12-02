package com.example.ollineshoppingclone.screen.ProductDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.ollineshopping.model.ProductModel
import com.example.ollineshopping.utils.Constatn
import com.example.ollineshopping.utils.PrefUtils
import com.example.ollineshoppingclone.R
import kotlinx.android.synthetic.main.activity_product_detail.*

class ProductDetailActivity : AppCompatActivity() {
    lateinit var item: ProductModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        cardViewBack.setOnClickListener {
            finish()
        }

        cartViewFavorid.setOnClickListener {
            PrefUtils.setFAvorites(item)

            if (PrefUtils.chackFavorite(item)) {
                imgFavorid.setImageResource(R.drawable.ic_favorite_black)
            } else {
                imgFavorid.setImageResource(R.drawable.ic_favorite)
            }
        }

        item = intent.getSerializableExtra(Constatn.EXTRA_DATA) as ProductModel

        tvtitle.text = item.name
        tvProductPrice.text = item.price
        imgProductName.text = item.name

        if (PrefUtils.getCartCount(item) > 0) {
            btnAdd2Cart.visibility = View.GONE
        }

        if (PrefUtils.chackFavorite(item)) {
            imgFavorid.setImageResource(R.drawable.ic_favorite_black)

        } else {
            imgFavorid.setImageResource(R.drawable.ic_favorite)

        }

        Glide.with(this).load(Constatn.HOST_IMAGE + item.image).into(imgProduct)

        btnAdd2Cart.setOnClickListener {
            item.cartCount = 1
            PrefUtils.setCart(item)
            Toast.makeText(this, "Product added to cart!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}