package com.example.ollineshoppingclone.screen.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ollineshopping.utils.PrefUtils
import com.example.ollineshopping.view.ProductAdapter
import com.example.ollineshoppingclone.R
import com.example.ollineshoppingclone.view.ViewMadel.MainViewModel
import kotlinx.android.synthetic.main.fragment_favorite.*


class FavoriteFragment : Fragment() {
    lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.productData.observe(this, Observer {
            recyclerFavorite.adapter = ProductAdapter(it)
        })
        viewModel.error.observe(this, Observer {
            Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
        })
        viewModel.progress.observe(this, Observer {
            swipe.isRefreshing = it
        })
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerFavorite.layoutManager = LinearLayoutManager(requireActivity())

        swipe.setOnRefreshListener {
            loadData()
        }
        loadData()
    }
    fun loadData(){
        viewModel.getProductByIds(PrefUtils.getFavoriteList())
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            FavoriteFragment()
    }
}