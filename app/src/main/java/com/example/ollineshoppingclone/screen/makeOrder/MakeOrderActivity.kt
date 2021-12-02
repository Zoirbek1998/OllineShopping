package com.example.ollineshoppingclone.screen.makeOrder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ollineshopping.model.AddressModel
import com.example.ollineshopping.model.ProductModel
import com.example.ollineshopping.utils.Constatn
import com.example.ollineshoppingclone.MapsActivity
import com.example.ollineshoppingclone.R
import kotlinx.android.synthetic.main.activity_make_order.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class MakeOrderActivity : AppCompatActivity() {

    var address: AddressModel? = null
    lateinit var items:List<ProductModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_order)

        edAdress.setOnClickListener {
            startActivity(Intent(this, MapsActivity::class.java))
        }

        cardViewBack.setOnClickListener {
            finish()
        }

        items = intent.getSerializableExtra(Constatn.EXTRA_DATA) as List<ProductModel>
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }

        tvTotalAmount.setText(items.sumByDouble { it.cartCount.toDouble() * (it.price.replace(" ","").toDoubleOrNull() ?: 0.0) }.toString())

    }
    override fun onDestroy()
    {
        super.onDestroy()
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
    }

    @Subscribe
    fun onEvent(address: AddressModel) {
        this.address = address
        edAdress.setText("${address.latitude},${address.longitude}")
    }
}