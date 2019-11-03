package vn.minerva.travinh.func.medical_detail.presentation.contact

import vn.minerva.core.base.presentation.mvp.base.MvpPresenter
import vn.minerva.core.base.presentation.mvp.base.MvpView
import vn.minerva.travinh.app.data.network.response.MedicalDetailResponse

interface MedicalDetailContactContract {
    interface View: MvpView{
        fun showLoading()

        fun hideLoading()

        fun showToast(message: String)

        fun showError(message: String)

        fun loadData()

        fun showContactInfo(data: MedicalDetailResponse)
    }
    abstract class Presenter: MvpPresenter<View>(){
        abstract fun getDataMedicalContact(id: Int)
    }
}