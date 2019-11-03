package vn.minerva.travinh.func.medical_detail.presentation

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import vn.minerva.core.base.presentation.mvp.base.MvpPresenter
import vn.minerva.core.base.presentation.mvp.base.MvpView
import vn.minerva.travinh.app.data.network.response.MedicalDetailResponse

interface MedicalDetailContract {
    interface View: MvpView{
        fun showLoading()

        fun hideLoading()

        fun showToast(message: String)

        fun showError(message: String)

        fun showDetailToolbar(data: MedicalDetailResponse)

        fun showImageList(data: List<ViewModel>)


    }
    abstract class Presenter: MvpPresenter<View>(){
        abstract fun getMedicalInfo(id: Int)
        abstract fun gotoLibrary()
        abstract fun gotoMedicalListActivity()
    }
}