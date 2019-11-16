package vn.silverbot99.farm_traders.func.cataloge.presentation

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import vn.silverbot99.core.base.presentation.mvp.base.MvpPresenter
import vn.silverbot99.core.base.presentation.mvp.base.MvpView
import vn.silverbot99.farm_traders.func.nearest_farm.presentation.LocationFarmNearestContract

interface CatalogeContract {
    interface View: MvpView {
        fun showLoading()

        fun hideLoading()

        fun showToast(message: String)

        fun showError(message: String)

        fun showDetailInfo(data: List<ViewModel>)
    }
    abstract class Presenter: MvpPresenter<View>() {

        abstract fun getCataloge()
        abstract fun gotoProductList()

    }
}