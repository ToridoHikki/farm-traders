package vn.minerva.travinh.func.medical.presentation

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import vn.minerva.core.base.presentation.mvp.base.MvpPresenter
import vn.minerva.core.base.presentation.mvp.base.MvpView

interface MedicalContract {
    interface View: MvpView{
        fun showLoading()

        fun hideLoading()

        fun showToast(message: String)

        fun showError(message: String)

        //fun loadData()

        fun showMedicalDetail(data: List<ViewModel>,totalMedical: Int)
        //fun showMedicalDatail(data: List<ViewModel>)
    }
    abstract class Presenter: MvpPresenter<View>() {
        //abstract fun getGasStationList(list: MutableList<ViewModel>)
        abstract fun getMedicalList(/*latitude: Long, longitude: Long, */page: Int)
        abstract fun gobackMainView()
        abstract fun goMedicalDetail(id: Int)
    }
}