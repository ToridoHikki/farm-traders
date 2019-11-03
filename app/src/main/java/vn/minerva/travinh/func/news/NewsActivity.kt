package vn.minerva.travinh.func.news

import be.trikke.intentbuilder.BuildIntent
import vn.minerva.core.base.presentation.mvp.android.AndroidMvpView
import vn.minerva.core.base.presentation.mvp.android.MvpActivity
import vn.minerva.travinh.func.news.presentation.NewsView

@BuildIntent
class NewsActivity :MvpActivity(){
    override fun createAndroidMvpView(): AndroidMvpView {
        return NewsView(this, NewsView.ViewCreator(this, null))
    }
}