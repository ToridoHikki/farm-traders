package vn.minerva.travinh.func.medical_detail.presentation.map

import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import vn.minerva.core.base.presentation.mvp.base.MvpPresenter
import vn.minerva.core.base.presentation.mvp.base.MvpView
import vn.minerva.travinh.app.data.network.response.MedicalDetailResponse

interface MedicalDetailMapContract {
    interface View : MvpView {
        fun showLoading()

        fun hideLoading()

        fun showToast(message: String)

        fun showError(message: String)

        fun showMedicalLocation(data: MedicalDetailResponse)

        fun getMapView(): MapView

        fun loadData()

        fun getOnMapReadyCallback(): OnMapReadyCallback


    }

    abstract class Presenter : MvpPresenter<View>() {
        abstract fun getLocationDetail(id: Int)
    }
}