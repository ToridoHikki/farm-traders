package vn.minerva.travinh.func.gasoline_store_detail.presentation.contact

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import vn.minerva.core.base.presentation.mvp.android.AndroidMvpView
import vn.minerva.core.base.presentation.mvp.android.MvpActivity
import vn.minerva.core.base.presentation.mvp.base.MvpPresenter
import vn.minerva.core.base.presentation.mvp.base.MvpView
import vn.minerva.travinh.app.data.network.response.GasolineStoreDetailResponse
import vn.minerva.travinh.func.gasoline_store_detail.presentation.GasolineStoreDetailContract
import vn.minerva.travinh.func.gasoline_store_detail.presentation.GasolineStoreDetailView

interface GasolineStoreDetailContactContract{
    interface View: MvpView {
        fun showLoading()

        fun hideLoading()

        fun showToast(message: String)

        fun showError(message: String)

        fun loadData()

        fun showContactInfo(data: GasolineStoreDetailResponse)

        //fun renderDefaultData(list: List<ViewModel>)
    }
    abstract class Presenter: MvpPresenter<View>(){
        abstract fun getDataGasolineStoreContact(id: Int)
    }
}