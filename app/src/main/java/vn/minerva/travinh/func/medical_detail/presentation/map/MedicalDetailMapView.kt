package vn.minerva.travinh.func.medical_detail.presentation.map

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.support.annotation.NonNull
import android.support.v4.content.res.ResourcesCompat
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import com.mapbox.android.core.permissions.PermissionsManager
import com.mapbox.mapboxsdk.annotations.IconFactory
import com.mapbox.mapboxsdk.annotations.Marker
import com.mapbox.mapboxsdk.annotations.MarkerOptions
import com.mapbox.mapboxsdk.camera.CameraPosition
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions
import com.mapbox.mapboxsdk.location.LocationComponentOptions
import com.mapbox.mapboxsdk.location.modes.CameraMode
import com.mapbox.mapboxsdk.location.modes.RenderMode
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import com.mapbox.mapboxsdk.maps.Style
import kotlinex.mvpactivity.showErrorAlert
import kotlinx.android.synthetic.main.layout_gasoline_store_detail_map.view.*
import vn.minerva.core.app.util.BitmapUtils
import vn.minerva.core.app.view.loading.Loadinger
import vn.minerva.core.base.presentation.mvp.android.AndroidMvpView
import vn.minerva.core.base.presentation.mvp.android.MvpActivity
import vn.minerva.travinh.R
import vn.minerva.travinh.app.data.network.response.MedicalDetailResponse
import vn.minerva.travinh.func.gasoline_store_detail.presentation.GasolineStoreDetailResourceProvider

class MedicalDetailMapView (mvpActivity: MvpActivity, viewCreator: MedicalDetailMapView.ViewCreator, var id: Int) : AndroidMvpView(mvpActivity, viewCreator), MedicalDetailMapContract.View {
    private val resourceProvider = GasolineStoreDetailResourceProvider()
    private var medicalLap: Double = 0.0
    private var medicalLon: Double = 0.0
    private var medicalName: String? = null
    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val mPresenter = MedicalDetailMapPresenter()
    private var mapboxMap: MapboxMap? = null

    private val listMarket = mutableListOf<MarkerOptions>()
    private var isNotDraw = true
    private var selectedMarker: Marker? = null

    override fun showMedicalLocation(data: MedicalDetailResponse) {
        data?.let {
            medicalLap = data.medicalLap
            medicalLon = data.medicalLon
            medicalName = data.medicalName
            Log.d("âsasa","showMedicalLocation ${data.medicalLap}  ${data.medicalLon}  ${data.medicalName} ")
        }
        drawBuldingOnMap()
    }

    override fun loadData() {
        mPresenter.getLocationDetail(id)
        view.mapViewGasStoreDetail.getMapAsync(getOnMapReadyCallback())
    }

    override fun getOnMapReadyCallback(): OnMapReadyCallback = OnMapReadyCallback { mapBox ->
        mapboxMap = mapBox
        mapboxMap?.setOnFpsChangedListener {
            selectedMarker?.infoWindow?.update()
        }
        mapboxMap?.uiSettings?.isRotateGesturesEnabled = false
        mapBox.setStyle(Style.Builder().fromUrl(resourceProvider.getMapViewStyle())) {
            //enableLocationComponent(it)
            zoomStore()
        }
        if (isNotDraw) {
            drawBuldingOnMap()
        }

    }
    private fun zoomStore(){
        val storePosition = CameraPosition.Builder()
            .target(LatLng(medicalLap, medicalLon))
            .zoom(17.0) // Khoang cach zoom ga n hay xa, muon gan thi tang gia tri len
            .tilt(10.0)
            .build()
        selectedMarker?.hideInfoWindow()/*
        selectedMarker = listMarket.find {
            it.title == medicalName
        }?.marker*/
        mapboxMap?.let {
            selectedMarker?.showInfoWindow(it, getMapView())
            it.animateCamera(CameraUpdateFactory.newCameraPosition(storePosition), 2000)
        }
    }
    private fun drawBuldingOnMap() {
        if (mapboxMap == null) {
            isNotDraw = true
            return
        }
        /*var  drawable : Drawable?= ResourcesCompat.getDrawable(mvpActivity.resources, R.drawable.ic_building, null);
        var mBitmap : Bitmap = BitmapUtils.drawableToBitmap(drawable)
        val icon = IconFactory.recreate("gas-store-id",mBitmap)*/
        val icon = IconFactory.getInstance(mvpActivity).fromResource(R.drawable.ic_hospital)
        val medicalLocation = LatLng(medicalLap, medicalLon)
        listMarket.add(
            MarkerOptions()
                .position(medicalLocation)
                .icon(icon)
                .title(medicalName)
        )
        mapboxMap?.addMarkers(listMarket)

        isNotDraw = false

    }

    override fun initCreateView() {

    }

    override fun showLoading() {
        loadingView.show()
    }

    override fun hideLoading() {
        loadingView.hide()
    }

    override fun showToast(message: String) {
        Toast.makeText(mvpActivity, message, Toast.LENGTH_LONG).show()
    }

    override fun showError(message: String) {
        mvpActivity.showErrorAlert(message)
    }

    override fun startMvpView() {
        mPresenter.attachView(this)
        super.startMvpView()
    }

    override fun stopMvpView() {
        mPresenter.detachView()
        super.stopMvpView()
    }

    override fun getMapView(): MapView {
        return view.mapViewGasStoreDetail
    }


    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_gasoline_store_detail_map, context, viewGroup)
}

