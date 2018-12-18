package `in`.farmguide.myapplication.ui.main

import `in`.farmguide.myapplication.R
import `in`.farmguide.myapplication.data.ui.Resource
import `in`.farmguide.myapplication.data.ui.ResourceState
import `in`.farmguide.myapplication.extensions.longToast
import `in`.farmguide.myapplication.repository.db.post.PostMinimal
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
    lateinit var postsAdapter: PostsAdapter

    override fun getViewModel() = mainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = postsAdapter

        bindListeners()
        bindViewModel()
    }

    private fun bindListeners() {
        btn_show_posts.setOnClickListener {
            getViewModel().onLoadPostsClicked()
        }
    }

    private fun bindViewModel() {
        getViewModel().getPostsObservable().observe(this, Observer { resource ->
            resource?.let {
                handlePosts(it)
            }
        })
    }

    private fun handlePosts(resource: Resource<List<PostMinimal>>) {
        when (resource.status) {
            ResourceState.LOADING -> showLoading()
            ResourceState.ERROR -> showError(resource.messageResId)
            ResourceState.SUCCESS -> showData(resource.data)
        }
    }

    private fun showData(data: List<PostMinimal>?) {
        progress_bar.visibility = View.GONE
        btn_show_posts.visibility = View.GONE
        recycler_view.visibility = View.VISIBLE
        postsAdapter.posts = data ?: emptyList()
    }

    private fun showError(messageResId: Int?) {
        progress_bar.visibility = View.GONE
        btn_show_posts.visibility = View.VISIBLE
        recycler_view.visibility = View.GONE
        postsAdapter.posts = emptyList()

        messageResId?.let {
            longToast(getString(it))
        }
    }

    private fun showLoading() {
        progress_bar.visibility = View.VISIBLE
        btn_show_posts.visibility = View.GONE
        recycler_view.visibility = View.GONE
        postsAdapter.posts = emptyList()
    }
}
