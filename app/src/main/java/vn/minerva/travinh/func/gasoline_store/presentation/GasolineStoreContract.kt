package vn.minerva.travinh.func.gasoline_store.presentation

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import vn.minerva.core.base.presentation.mvp.base.MvpPresenter
import vn.minerva.core.base.presentation.mvp.base.MvpView

interface GasolineStoreContract {
    interface View: MvpView {
        fun showLoading()

        fun hideLoading()

        fun showToast(message: String)

        fun showError(message: String)

        //fun loadData()

        fun showGasolineStoreDetail(data: List<ViewModel>,totalGasStore: Int)
    }
    abstract class Presenter: MvpPresenter<View>() {
        abstract fun getGasStationList(page: Int)
        abstract fun gotoGasStationDetail(id:Int)
        abstract fun gobackMainView()
    }
}