package vn.minerva.travinh.func.news.presentation

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import vn.minerva.core.base.presentation.mvp.base.MvpPresenter
import vn.minerva.core.base.presentation.mvp.base.MvpView

interface NewsContract {
    interface View:MvpView{
        fun showLoading()

        fun hideLoading()

        fun showToast(message: String)

        fun showError(message: String)

        fun showNewsList(data: List<ViewModel>)
    }
    abstract class Presenter: MvpPresenter<View>(){
        abstract fun getNewsList(page: Int)
        abstract fun gotoMainView()

    }
}