package `in`.farmguide.myapplication.ui.base

import `in`.farmguide.myapplication.extensions.longToast
import android.app.ProgressDialog
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection

abstract class BaseActivity<out VM : BaseViewModel> : AppCompatActivity() {

    private var mProgressDialog: ProgressDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (hasInjector())
            AndroidInjection.inject(this)

        getViewModel().getErrorObservable().observe(this, Observer {
            longToast(it)
        })

        getViewModel().getInternalErrorObservable().observe(this, Observer {
            it?.let {
                val message = if (it.second == null) getString(it.first) else getString(it.first, it.second)
                longToast(message)
            }
        })

        getViewModel().getToastObservable().observe(this, Observer {
            it?.let {
                val message = if (it.second == null) getString(it.first) else getString(it.first, it.second)
                longToast(message)
            }
        })

        getViewModel().getSuccessObservable().observe(this, Observer {
            it?.let {
                longToast(it)
            }
        })
    }

    open fun hasInjector() = true

    abstract fun getViewModel(): VM

}