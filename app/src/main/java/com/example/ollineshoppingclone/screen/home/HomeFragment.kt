package com.example.ollineshoppingclone.screen.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.ollineshopping.model.CategoryModel
import com.example.ollineshopping.model.OfferModel
import com.example.ollineshopping.view.CategoryAdapter
import com.example.ollineshopping.view.CategoryAdapterCallBack
import com.example.ollineshopping.view.ProductAdapter
import com.example.ollineshoppingclone.R
import com.example.ollineshoppingclone.view.ViewMadel.MainViewModel
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        swipe.setOnRefreshListener {
            loadData()
        }
        recyclerCategory.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        recyclerProduct.layoutManager =LinearLayoutManager(requireActivity(),LinearLayoutManager.VERTICAL,false)

        viewModel.error.observe(requireActivity(), Observer {
            Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
        })
        viewModel.offerData.observe(requireActivity(), Observer {
            carouselView.setImageListener { position, imageView ->
                Glide.with(imageView)
                    .load("http://osonsavdo.sd-group.uz/images/${it[position].image}")
                    .into(imageView)
            }
            carouselView.pageCount = it.count()
        })

        // CategoryAdapterCallBack bu yerda item bosiliwi
        viewModel.categoryData.observe(requireActivity(), Observer {
            recyclerCategory.adapter = CategoryAdapter(it,object : CategoryAdapterCallBack{
                override fun onClikItem(item: CategoryModel) {
                    viewModel.getProductByCategory(item.id)
                }

            })
        })
        viewModel.productData.observe(requireActivity(), Observer {
            recyclerProduct.adapter = ProductAdapter(it)
        })
        viewModel.progress.observe(requireActivity(), Observer {
            swipe.isRefreshing = it
        })
        loadData()
    }
    fun loadData() {
            viewModel.getCategory()
        viewModel.getOffers()
//        viewModel.getAllDBProduct()
//        viewModel.getAllDBCAtegories()
        viewModel.getTopProducts()
    }
    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}


