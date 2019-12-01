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
import kotlinx.android.synthetic.main.layout_farm_nearest.view.*
import vn.silverbot99.core.app.util.BitmapUtils
import vn.silverbot99.core.app.view.SimpleDividerItemDecoration
import vn.silverbot99.core.app.view.loading.Loadinger
import vn.silverbot99.core.base.domain.listener.OnActionNotify
import vn.silverbot99.core.base.presentation.mvp.android.AndroidMvpView
import vn.silverbot99.core.base.presentation.mvp.android.MvpActivity
import vn.silverbot99.core.base.presentation.mvp.android.list.LinearRenderConfigFactory
import vn.silverbot99.core.base.presentation.mvp.android.list.ListViewMvp
import vn.silverbot99.farm_traders.R
import vn.silverbot99.farm_traders.app.config.ConfigUtil
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

    private val listfarm = mutableListOf<MarkerOptions>()
    val listFarmSaved:MutableList<ViewModel> = mutableListOf()

    private var isNotDraw = true
    private var selectedMarker: Marker? = null

    private lateinit var recyclerVehicleBottomSheet: BottomSheetBehavior<LinearLayout>

    override fun initCreateView() {
//        initBottomSheet()
        //view.faFab.setOnClickListener{onClickfaFab()}
/*        mapboxMap?.setOnMarkerClickListener { marker ->
            mark
        }*/
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
        val icon = IconFactory.getInstance(mvpActivity).fromResource(R.drawable.ic_farm_24)
/*        if (ConfigUtil.listFarm!= null){
            listFarmSaved.addAll(ConfigUtil.listFarm!!)
            listFarmSaved.map { item ->
                if (item is LocationFarmItemModel) {
                    val farmLocation = LatLng(item.lat, item.long)
                    listfarm.add(MarkerOptions()
                        .position(farmLocation)
                        .icon(icon)
                        .title(item.name.getValueOrDefaultIsEmpty())
                    )
                }
                mapboxMap?.addMarkers(listfarm)
            }
            mapboxMap?.setOnInfoWindowClickListener { marker ->

                for (position in 0 until listfarm.size) {
                    val item = listFarmSaved[position] as LocationFarmItemModel
                    if (item.name.equals(marker.title)) {
                        // mPresenter.getMedicalDetail(item.medicalId)
                        mPresenter.gotoFarmDetail(item)
                        break
                    }
                }
                true
            }
            isNotDraw = false
        }*/
//        else {
        if (listData.isNotEmpty()) {
            listData.map { item ->
                if (item is LocationFarmItemModel) {
                    val farmLocation = LatLng(item.lat, item.long)
                    listfarm.add(
                        MarkerOptions()
                            .position(farmLocation)
                            .icon(icon)
                            .title(item.name.getValueOrDefaultIsEmpty())
                    )
                }
                mapboxMap?.addMarkers(listfarm)
            }
            mapboxMap?.setOnInfoWindowClickListener { marker ->

                for (position in 0 until listfarm.size) {
                    val item = listData[position] as LocationFarmItemModel
                    if (item.name.equals(marker.title)) {
                        // mPresenter.getMedicalDetail(item.medicalId)
                        mPresenter.gotoFarmDetail(item)
                        break
                    }
                }
                true
            }
            isNotDraw = false
        }
//        }
    }

    override fun getMapView(): MapView {
        return view.mapView
    }

/*    override fun loadData() {
        mapboxMap?.clear()
        view.mapView.getMapAsync(getOnMapReadyCallback())
        mPresenter.getFarmList()
    }*/

    override fun initData() {
        super.initData()
        view.mapView.getMapAsync(getOnMapReadyCallback())
        /*if (ConfigUtil.listFarm!=null){
            listData.addAll(ConfigUtil.listFarm!!)
            listViewMvp?.setItems(listData)
            listViewMvp?.notifyDataChanged()
            drawBuldingOnMap()
            ConfigUtil.saveListFarm(null)
        }
        else{
            mPresenter.getFarmList()
        }*/
        mPresenter.getFarmList()

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



