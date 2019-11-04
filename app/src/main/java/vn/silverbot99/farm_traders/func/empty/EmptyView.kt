package vn.silverbot99.farm_traders.func.empty

import android.content.Context
import android.view.ViewGroup
import vn.silverbot99.core.base.presentation.mvp.android.AndroidMvpView
import vn.silverbot99.core.base.presentation.mvp.android.MvpActivity
import vn.silverbot99.farm_traders.R

class EmptyView(mvpActivity: MvpActivity, viewCreator: EmptyView.ViewCreator) :
        AndroidMvpView(mvpActivity, viewCreator) {
    override fun initCreateView() {
    }


    //Create view layout
    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
            AndroidMvpView.LayoutViewCreator(R.layout.layout_empty, context, viewGroup)
}