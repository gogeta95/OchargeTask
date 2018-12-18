package `in`.farmguide.myapplication.ui.main

import `in`.farmguide.myapplication.R
import `in`.farmguide.myapplication.data.ui.CategorizedRestaurants
import `in`.farmguide.myapplication.data.ui.Resource
import `in`.farmguide.myapplication.data.ui.ResourceState
import `in`.farmguide.myapplication.extensions.longToast
import `in`.farmguide.myapplication.ui.base.BaseActivity
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity<MainViewModel>() {

    @Inject
    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var categoryAdapter: CategoryAdapter

    override fun getViewModel() = mainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = categoryAdapter

        bindViewModel()
    }

    private fun bindViewModel() {
        getViewModel().getCategoriesObservable().observe(this, Observer { resource ->
            resource?.let {
                handleCategories(it)
            }
        })
    }

    private fun handleCategories(resource: Resource<List<CategorizedRestaurants>>) {
        when (resource.status) {
            ResourceState.LOADING -> showLoading()
            ResourceState.ERROR -> showError(resource.messageResId)
            ResourceState.SUCCESS -> showData(resource.data)
        }
    }

    private fun showData(data: List<CategorizedRestaurants>?) {
        progress_bar.visibility = View.GONE
        recycler_view.visibility = View.VISIBLE
        categoryAdapter.categories = data ?: emptyList()
    }

    private fun showError(messageResId: Int?) {
        progress_bar.visibility = View.GONE
        recycler_view.visibility = View.GONE
        categoryAdapter.categories = emptyList()

        messageResId?.let {
            longToast(getString(it))
        }
    }

    private fun showLoading() {
        progress_bar.visibility = View.VISIBLE
        recycler_view.visibility = View.GONE
        categoryAdapter.categories = emptyList()
    }
}
