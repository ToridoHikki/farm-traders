package vn.minerva.travinh.func.gasoline_store_detail.presentation.map

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.drawable.Drawable

import android.support.annotation.NonNull
import android.support.v4.content.ContextCompat
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
import vn.minerva.travinh.app.data.network.response.GasolineStoreDetailResponse
import vn.minerva.travinh.func.gasoline_store_detail.presentation.GasolineStoreDetailResourceProvider

class GasolineStoreDetailMapView(mvpActivity: MvpActivity, viewCreator: GasolineStoreDetailMapView.ViewCreator, var id: Int) : AndroidMvpView(mvpActivity, viewCreator), GasolineStoreDetailMapContract.View {
    private val resourceProvider = GasolineStoreDetailResourceProvider()
    private var gasStoreLap: Double = 0.0
    private var gasStoreLon: Double = 0.0
    private var gasStoreName: String? = null
    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val mPresenter = GasolineStoreDetailMapPresenter()
    private var mapboxMap: MapboxMap? = null

    private val listMarket = mutableListOf<MarkerOptions>()
    private var isNotDraw = true
    private var selectedMarker: Marker? = null

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
            .target(LatLng(gasStoreLap, gasStoreLon))
            .zoom(12.0) // Khoang cach zoom gan hay xa, muon gan thi tang gia tri len
            .tilt(20.0)
            .build()
        selectedMarker?.hideInfoWindow()/*
        selectedMarker = listMarket.find {
            it.title == gasStoreName
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
        var  drawable : Drawable?= ResourcesCompat.getDrawable(mvpActivity.resources, R.drawable.ic_gas_station, null);
        var mBitmap :Bitmap= BitmapUtils.drawableToBitmap(drawable)
        val icon = IconFactory.recreate("gas-store-id",mBitmap)
            val gasStoreLocation = LatLng(gasStoreLap, gasStoreLon)
            listMarket.add(MarkerOptions()
                .position(gasStoreLocation)
                .icon(icon)
                .title(gasStoreName)
            )
            mapboxMap?.addMarkers(listMarket)

        isNotDraw = false

    }
    @SuppressLint("MissingPermission")
    private fun enableLocationComponent(@NonNull loadedMapStyle: Style) {
        // Check if permissions are enabled and if not request
        if (PermissionsManager.areLocationPermissionsGranted(mvpActivity)) {

            // Get an instance of the component
            val locationComponent = mapboxMap?.locationComponent


            val locationComponentOptions = LocationComponentOptions.builder(mvpActivity)
                .foregroundDrawable(R.drawable.e_current_location)
                .build()


            // Activate with options
            val locationComponentActivationOptions = LocationComponentActivationOptions
                .builder(mvpActivity, loadedMapStyle)
                .locationComponentOptions(locationComponentOptions)
                .useDefaultLocationEngine(true)
                .build()

            locationComponent?.activateLocationComponent(locationComponentActivationOptions)

            // Enable to make component visible
            locationComponent?.isLocationComponentEnabled = true

            // Set the component's camera mode
            locationComponent?.cameraMode = CameraMode.TRACKING

            // Set the component's render mode
            locationComponent?.renderMode = RenderMode.COMPASS
        }
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

    override fun showGasolineStoreLocation(data: GasolineStoreDetailResponse) {

        gasStoreLap = data.storeLat
        gasStoreLon = data.storeLon
        gasStoreName = data.storeName

        drawBuldingOnMap()
        Log.d("âsasa","showGasolineStoreLocation ${data.storeLat}  ${data.storeLon}  ${data.storeName} ")

    }
    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_gasoline_store_detail_map, context, viewGroup)
}

