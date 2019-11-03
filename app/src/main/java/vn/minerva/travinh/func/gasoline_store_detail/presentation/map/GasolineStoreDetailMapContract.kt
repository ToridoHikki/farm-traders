package vn.minerva.travinh.func.gasoline_store_detail.presentation.map

import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import vn.minerva.core.base.presentation.mvp.base.MvpPresenter
import vn.minerva.core.base.presentation.mvp.base.MvpView
import vn.minerva.travinh.app.data.network.response.GasolineStoreDetailResponse

interface GasolineStoreDetailMapContract {
    interface View : MvpView {
        fun showLoading()

        fun hideLoading()

        fun showToast(message: String)

        fun showError(message: String)

        fun showGasolineStoreLocation(data: GasolineStoreDetailResponse)

        fun getMapView(): MapView

        fun loadData()

        fun getOnMapReadyCallback(): OnMapReadyCallback


    }

    abstract class Presenter : MvpPresenter<View>() {
        abstract fun getLocationDetail(id: Int)
    }
}