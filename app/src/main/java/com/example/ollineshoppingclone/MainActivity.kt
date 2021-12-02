package com.example.ollineshoppingclone

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.ollineshoppingclone.screen.cart.CartFragment
import com.example.ollineshoppingclone.screen.cart.CartFragment.Companion.newInstance
import com.example.ollineshoppingclone.screen.changeLanguage.ChangeLanguageFragment
import com.example.ollineshoppingclone.screen.favorite.FavoriteFragment
import com.example.ollineshoppingclone.screen.home.HomeFragment
import com.example.ollineshoppingclone.screen.profile.ProfileFragment
import com.example.ollineshoppingclone.utils.LocaleManager
import com.example.ollineshoppingclone.view.ViewMadel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.reflect.Array.newInstance

class MainActivity : AppCompatActivity() {

    val homeFragment = HomeFragment.newInstance()
    val favoriteFragment = FavoriteFragment.newInstance()
    val cartFragment = CartFragment.newInstance()
    val profileFragment = ProfileFragment.newInstance()
    var activityFragment: Fragment = homeFragment

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = MainViewModel()

//        viewModel.productData.observe(this, Observer {
//            viewModel.insertAllProducts2DB(it)
//            homeFragment.loadData()
//        })
//        viewModel.categoryData.observe(this,{
//            viewModel.insertAllCategory2DB(it)
//            homeFragment.loadData()
//        })

        supportFragmentManager.beginTransaction()
            .add(R.id.flContainer, homeFragment, homeFragment.tag).hide(homeFragment).commit()

        supportFragmentManager.beginTransaction()
            .add(R.id.flContainer, favoriteFragment, favoriteFragment.tag).hide(favoriteFragment)
            .commit()
        supportFragmentManager.beginTransaction()
            .add(R.id.flContainer, cartFragment, cartFragment.tag).hide(cartFragment).commit()
        supportFragmentManager.beginTransaction()
            .add(R.id.flContainer, profileFragment, profileFragment.tag).hide(profileFragment)
            .commit()

        supportFragmentManager.beginTransaction().show(activityFragment).commit()

        bottomNavigationView.setOnItemSelectedListener {
            if (it.itemId == R.id.actionHome) {
                supportFragmentManager.beginTransaction().hide(activityFragment).show(homeFragment)
                    .commit()
                activityFragment = homeFragment
            } else
                if (it.itemId == R.id.actionCart) {
                    supportFragmentManager.beginTransaction().hide(activityFragment)
                        .show(cartFragment).commit()
                    activityFragment = cartFragment
                } else
                    if (it.itemId == R.id.actionFavorite) {
                        supportFragmentManager.beginTransaction().hide(activityFragment)
                            .show(favoriteFragment).commit()
                        activityFragment = favoriteFragment
                    } else
                        if (it.itemId == R.id.actionProfile) {
                            supportFragmentManager.beginTransaction().hide(activityFragment)
                                .show(profileFragment).commit()
                            activityFragment = profileFragment
                        }
            return@setOnItemSelectedListener false
        }


        btnMenu.setOnClickListener {
            val fragment = ChangeLanguageFragment.newInstance()
            fragment.show(supportFragmentManager,fragment.tag)
        }
        loadData()
    }

    fun loadData(){
//        viewModel.getTopProducts()
//        viewModel.getCategory()
    }

        override fun attachBaseContext(newBase: Context?) {
            super.attachBaseContext(LocaleManager.setLocale(newBase))
        }

}



