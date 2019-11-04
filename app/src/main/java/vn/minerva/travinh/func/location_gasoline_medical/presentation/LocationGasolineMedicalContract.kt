package vn.minerva.travinh.func.location_gasoline_medical.presentation

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import vn.minerva.core.base.presentation.mvp.base.MvpPresenter
import vn.minerva.core.base.presentation.mvp.base.MvpView

interface LocationGasolineMedicalContract {
    interface View: MvpView {
        fun showLoading()

        fun hideLoading()

        fun showToast(message: String)

        fun showError(message: String)

        fun loadData()

        fun showDetailInfo(data: List<ViewModel>)
        //fun showGasolineStoreDatail(data: List<ViewModel>)

        fun getOnMapReadyCallback(): OnMapReadyCallback
/*

        fun renderMedicalLocationDetail(data: MedicalViewModel)

        fun renderGasStoreLocationDetail(data: GasolineViewModel)
*/


        fun getMapView(): MapView
    }
    abstract class Presenter: MvpPresenter<View>() {
        abstract fun getGasStationList(page: Int)
        abstract fun getMedicalList(page: Int)
        abstract fun getMedicalDetail(id: Int)
        abstract fun getGasStationDetail(id: Int)

    }
}