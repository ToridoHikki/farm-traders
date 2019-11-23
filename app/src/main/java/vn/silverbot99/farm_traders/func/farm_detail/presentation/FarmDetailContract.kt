package vn.silverbot99.farm_traders.func.farm_detail.presentation

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import vn.silverbot99.core.base.presentation.mvp.base.MvpPresenter
import vn.silverbot99.core.base.presentation.mvp.base.MvpView

interface FarmDetailContract {
    interface View: MvpView {
        fun showLoading()

        fun hideLoading()

        fun showToast(message: String)

        fun showError(message: String)

        fun showDetailInfo(data: List<ViewModel>)
    }
    abstract class Presenter: MvpPresenter<View>() {

        abstract fun getFarmDetail()

        abstract fun getFarmerbyFarmId(farmId: String)

    }
}