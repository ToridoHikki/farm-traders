package vn.minerva.travinh.func.gasoline_store_detail.presentation

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import vn.minerva.core.base.presentation.mvp.base.MvpPresenter
import vn.minerva.core.base.presentation.mvp.base.MvpView
import vn.minerva.travinh.app.data.network.response.GasolineStoreDetailResponse

interface GasolineStoreDetailContract {
    interface View: MvpView{
        fun showLoading()

        fun hideLoading()

        fun showToast(message: String)

        fun showError(message: String)

        fun showDetailToolbar(data: GasolineStoreDetailResponse)

        fun showImageList(data: List<ViewModel>)


    }
    abstract class Presenter: MvpPresenter<View>(){
/*        abstract fun getDataGasolineStoreContact(id: Int)
        abstract fun getDataGasolineStoreLocation(id: Int)
        abstract fun getDataGasolineStoreInspect(id: Int)
        abstract fun getDataGasolineStorelistImage(id: Int)*/
        abstract fun gotoLibrary()
       // abstract fun getDetailToolbar(id: Int)
        abstract fun getDetailInfo(id: Int)
        abstract fun goBackGasolineStoreList()
    }

}