package vn.silverbot99.farm_traders.func.category.presentation

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import vn.silverbot99.core.base.presentation.mvp.base.MvpPresenter
import vn.silverbot99.core.base.presentation.mvp.base.MvpView
import vn.silverbot99.farm_traders.app.data.network.response.CategoriesResponse


interface CategoryContract {
    interface View: MvpView {
        fun showLoading()

        fun hideLoading()

        fun showToast(message: String)

        fun showError(message: String)

        fun showDetailInfo(data: List<ViewModel>)
    }
    abstract class Presenter: MvpPresenter<View>() {

        internal abstract fun getCategoryList()

        abstract fun gotoProductList()

    }
}