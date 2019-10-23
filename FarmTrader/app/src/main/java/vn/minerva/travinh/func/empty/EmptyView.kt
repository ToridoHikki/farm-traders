package vn.minerva.travinh.func.empty

import android.content.Context
import android.view.ViewGroup
import vn.minerva.core.base.presentation.mvp.android.AndroidMvpView
import vn.minerva.core.base.presentation.mvp.android.MvpActivity
import vn.minerva.travinh.R

class EmptyView(mvpActivity: MvpActivity, viewCreator: EmptyView.ViewCreator) :
        AndroidMvpView(mvpActivity, viewCreator) {
    override fun initCreateView() {
    }


    //Create view layout
    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
            AndroidMvpView.LayoutViewCreator(R.layout.layout_empty, context, viewGroup)
}