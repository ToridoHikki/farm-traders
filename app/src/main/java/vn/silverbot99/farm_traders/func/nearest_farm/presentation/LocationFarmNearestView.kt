package vn.silverbot99.farm_traders.func.nearest_farm.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.support.annotation.NonNull
import android.support.design.widget.BottomSheetBehavior
import android.support.v4.content.res.ResourcesCompat
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
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
import kotlinex.string.getValueOrDefaultIsEmpty
import kotlinx.android.synthetic.main.layout_medical_gasstation_map.view.*
import vn.silverbot99.core.app.util.BitmapUtils
import vn.silverbot99.core.app.view.SimpleDividerItemDecoration
import vn.silverbot99.core.app.view.loading.Loadinger
import vn.silverbot99.core.base.domain.listener.OnActionNotify
import vn.silverbot99.core.base.presentation.mvp.android.AndroidMvpView
import vn.silverbot99.core.base.presentation.mvp.android.MvpActivity
import vn.silverbot99.core.base.presentation.mvp.android.list.LinearRenderConfigFactory
import vn.silverbot99.core.base.presentation.mvp.android.list.ListViewMvp
import vn.silverbot99.farm_traders.R
import vn.silverbot99.farm_traders.app.presentation.navigater.AndroidScreenNavigator
import vn.silverbot99.farm_traders.func.nearest_farm.presentation.model.LocationFarmItemModel

class LocationFarmNearestView (mvpActivity: MvpActivity, viewCreator: LocationFarmNearestView.ViewCreator) : AndroidMvpView(mvpActivity,viewCreator),
LocationFarmNearestContract.View {

    private val mPresenter =
        LocationFarmNearestPresenter(
            AndroidScreenNavigator(mvpActivity)
        )
    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private var listViewMvp: ListViewMvp? = null
    private var listData: MutableList<ViewModel> = mutableListOf()
    private var mapboxMap: MapboxMap? = null
    private val renderInput = LinearRenderConfigFactory.Input(
        context = mvpActivity,
        orientation = LinearRenderConfigFactory.Orientation.VERTICAL,
        decoration = SimpleDividerItemDecoration(mvpActivity, R.drawable.line_divider_grey_c5c5c7)

    )
    private val renderConfig = LinearRenderConfigFactory(renderInput).create()
    private val resourceProvider= LocationFarmNearestResourceProvider()

    private val listGasStore = mutableListOf<MarkerOptions>()
    private val listMedical = mutableListOf<MarkerOptions>()


    private var isNotDraw = true
    private var selectedMarker: Marker? = null

    private lateinit var recyclerVehicleBottomSheet: BottomSheetBehavior<LinearLayout>

    override fun initCreateView() {
//        initBottomSheet()
        view.faFab.setOnClickListener{onClickfaFab()}
/*        mapboxMap?.setOnMarkerClickListener { marker ->
            mark
        }*/
    }



    private fun onClickfaFab() {
        if (mapboxMap?.getStyle() != null) {
            enableLocationComponent(mapboxMap!!.style)
        }
    }



/*    private fun initListView(viewBottom: LinearLayout?) {
        //mPresenter.getMedicalList(page = 1)
        listViewMvp = ListViewMvp(mvpActivity, viewBottom!!.recyclerview_list_items, renderConfig)
        listViewMvp?.createView()
        listViewMvp?.addViewRenderer(LocationMedicalRender(mvpActivity))
        Log.i("LocationData","Check Ordinal addRender")
        listViewMvp?.addViewRenderer(LocationGasolineRender(mvpActivity))
        listViewMvp?.setOnItemRvClickedListener(onActionBottomVehicleItemOnClick)
//        viewBottom.view_data_empty.visibility = View.GONE
    }*/
/*    private val onActionBottomVehicleItemOnClick: OnItemRvClickedListener<ViewModel> = object : OnItemRvClickedListener<ViewModel> {
        override fun onItemClicked(view: View, position: Int, dataItem: ViewModel) {
            if (dataItem is LocationFarmItemModel) {
                selectMedical(dataItem)
            }
        }
    }*/
    private fun selectMedical(data: LocationFarmItemModel) {
        runWithCheckMultiTouch("onActionSearch_click_item", object : OnActionNotify {
            override fun onActionNotify() {
                val vehiclePosition = CameraPosition.Builder()
                    .target(LatLng(data.farmLap, data.farmLon))
                    .zoom(15.0) // Khoang cach zoom gan hay xa, muon gan thi tang gia tri len
//                    .tilt(12.0)
                    .build()
                selectedMarker?.hideInfoWindow()/*
                selectedMarker = listMedical.find {
                    it.title == data.medicalName.getValueOrDefaultIsEmpty()
                }?.marker*/
                mapboxMap?.let {
                    selectedMarker?.showInfoWindow(it, getMapView())
                    it.animateCamera(CameraUpdateFactory.newCameraPosition(vehiclePosition), 2000)
                }
                recyclerVehicleBottomSheet.state = BottomSheetBehavior.STATE_HIDDEN
            }
        })
    }
    override fun getOnMapReadyCallback(): OnMapReadyCallback = OnMapReadyCallback { mapBox ->
        mapboxMap = mapBox
        mapboxMap?.setOnFpsChangedListener {
            selectedMarker?.infoWindow?.update()
        }
        mapboxMap?.uiSettings?.isRotateGesturesEnabled = false
        mapBox.setStyle(Style.Builder().fromUrl(resourceProvider.getMapViewStyle())) {
            enableLocationComponent(it)
        }
        if (isNotDraw) {
            drawBuldingOnMap()
        }

    }

    private fun drawBuldingOnMap() {
        if (mapboxMap == null) {
            isNotDraw = true
        }
        val icon = IconFactory.getInstance(mvpActivity).fromResource(R.drawable.ic_hospital)
        var  drawable : Drawable?= ResourcesCompat.getDrawable(mvpActivity.resources, R.drawable.ic_gas_station, null);
        var mBitmap : Bitmap = BitmapUtils.drawableToBitmap(drawable)
        if (listData.isNotEmpty()) {
            listData.map { item ->
                if (item is LocationFarmItemModel) {
                    val medicalLocation = LatLng(item.farmLap, item.farmLon)
                    listMedical.add(MarkerOptions()
                        .position(medicalLocation)
                        .icon(icon)
                        .title(item.farmName.getValueOrDefaultIsEmpty())
                    )
                }
                mapboxMap?.addMarkers(listMedical)
            }
            mapboxMap?.setOnInfoWindowClickListener { marker ->

                for (position in 0 until listMedical.size) {
                    val item = listData[position] as LocationFarmItemModel
                    if (item.farmName.equals(marker.title)) {
                       // mPresenter.getMedicalDetail(item.medicalId)
                        //todo chuyen den trang trang thong tin nong trai
                        break
                    }
                }
                true
            }
        isNotDraw = false
        }
    }

    override fun getMapView(): MapView {
        return view.mapView
    }

    override fun loadData() {
        mapboxMap?.clear()
        view.mapView.getMapAsync(getOnMapReadyCallback())

//        mPresenter.getMedicalList(page = 1)
        // todo lay du lieu
    }

    override fun initData() {
        super.initData()
        view.mapView.getMapAsync(getOnMapReadyCallback())
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



    override fun showDetailInfo(data: List<ViewModel>) {
        listData.clear()
        if (data.isNotEmpty()) {
            listData.addAll(data)
        }
        listViewMvp?.setItems(listData)
        listViewMvp?.notifyDataChanged()
//        view.bottom_sheet.view_data_empty.visibility = View.GONE
        drawBuldingOnMap()
    }
    @SuppressLint("MissingPermission")
    private fun enableLocationComponent(@NonNull loadedMapStyle: Style?) {
        // Check if permissions are enabled and if not request
        if (PermissionsManager.areLocationPermissionsGranted(mvpActivity)) {

            // Get an instance of the component
            val locationComponent = mapboxMap?.locationComponent


            val locationComponentOptions = LocationComponentOptions.builder(mvpActivity)
                .foregroundDrawable(R.drawable.e_current_location)
                .build()


            // Activate with options
            val locationComponentActivationOptions = LocationComponentActivationOptions
                .builder(mvpActivity, loadedMapStyle!!)
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
    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_farm_nearest, context, viewGroup)
}



