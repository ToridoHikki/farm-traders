package vn.silverbot99.core.base.presentation.mvp.android

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import vn.silverbot99.core.base.presentation.mvp.android.lifecycle.LifeCycleAndroidMvpView
import vn.silverbot99.core.base.presentation.mvp.base.lifecycle.LifeCycleDispatcherSetter

abstract class MvpFragment : Fragment(), LifeCycleDispatcherSetter<LifeCycleAndroidMvpView> {
    private var mContext: Context? = null
    private var mRootView: View? = null
    private var androidMvpView: LifeCycleAndroidMvpView? = null
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mContext = context
    }

    override fun addLifeCycle(lifeCycle: LifeCycleAndroidMvpView) {
        getMvpActivity().addLifeCycle(lifeCycle)
    }

    fun getMvpActivity(): MvpActivity {
        return activity as MvpActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        androidMvpView = createAndroidMvpView()
        mRootView = (androidMvpView as AndroidMvpView).createView()
        addLifeCycle((androidMvpView as AndroidMvpView))
        initMvpView()
        initViewAfterCreateView(savedInstanceState)
        return mRootView
    }

    protected open fun initViewAfterCreateView(savedInstanceState: Bundle?){}

    override fun onResume() {
        super.onResume()
        androidMvpView?.startMvpView()
    }

    override fun onPause() {
        super.onPause()
        androidMvpView?.stopMvpView()
    }

    private fun initMvpView() {
        androidMvpView?.initMvpView()
    }

    abstract fun createAndroidMvpView(): AndroidMvpView
}