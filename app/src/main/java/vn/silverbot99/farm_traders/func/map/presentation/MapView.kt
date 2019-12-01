package vn.silverbot99.farm_traders.func.map.presentation

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.BitmapFactory
import android.support.annotation.NonNull
import android.support.design.widget.BottomSheetBehavior
import android.util.Log
import android.view.View
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
import kotlinx.android.synthetic.main.layout_map.view.*
import vn.silverbot99.core.app.view.SimpleDividerItemDecoration
import vn.silverbot99.core.app.view.loading.Loadinger
import vn.silverbot99.core.base.domain.listener.OnActionNotify
import vn.silverbot99.core.base.presentation.mvp.android.AndroidMvpView
import vn.silverbot99.core.base.presentation.mvp.android.MvpActivity
import vn.silverbot99.farm_traders.R
import vn.silverbot99.farm_traders.app.presentation.navigater.AndroidScreenNavigator
import vn.silverbot99.farm_traders.func.farm_detail.presentation.model.FarmDetailProductListItemModel
import vn.silverbot99.farm_traders.func.nearest_farm.presentation.model.LocationFarmItemModel
import com.mapbox.geojson.Feature
import com.mapbox.mapboxsdk.style.layers.SymbolLayer
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource
import com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconAllowOverlap
import com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconIgnorePlacement
import com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconImage

// classes to calculate a route
import com.mapbox.services.android.navigation.ui.v5.NavigationLauncherOptions
import com.mapbox.services.android.navigation.ui.v5.route.NavigationMapRoute
import com.mapbox.services.android.navigation.v5.navigation.NavigationRoute
import com.mapbox.api.directions.v5.models.DirectionsResponse
import com.mapbox.api.directions.v5.models.DirectionsRoute
import com.mapbox.geojson.Point
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.style.sources.Source
import com.mapbox.services.android.navigation.ui.v5.NavigationLauncher
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import vn.silverbot99.farm_traders.func.main.MainActivity

class MapView(mvpActivity: MvpActivity, viewCreator: ViewCreator, val location: LocationFarmItemModel) : AndroidMvpView(mvpActivity,viewCreator),
    MapContract.View/*, MapboxMap.OnMapClickListener*/ {

    private val mPresenter = MapPresenter()
    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)

    private var mapboxMap: MapboxMap? = null
    private val listfarm = mutableListOf<MarkerOptions>()
    //private var locationComponent = mapboxMap?.locationComponent


    private var isNotDraw = true
    private val resourceProvider = MapResourceProvider()
    private var selectedMarker: Marker? = null
    //private  var originPoint: Point? = null

    private var currentRoute: DirectionsRoute? = null
    private var navigationMapRoute: NavigationMapRoute? = null
    private val destinationPoint = Point.fromLngLat(location.long, location.lat)



    override fun initCreateView() {

    }


    override fun getOnMapReadyCallback(): OnMapReadyCallback = OnMapReadyCallback { mapBox ->
        mapboxMap = mapBox
        mapboxMap?.setOnFpsChangedListener {
            selectedMarker?.infoWindow?.update()
        }
        mapboxMap?.uiSettings?.isRotateGesturesEnabled = false
        mapBox.setStyle(Style.Builder().fromUrl(resourceProvider.getMapViewStyle())) { it ->
            enableLocationComponent(it)
            addDestinationIconSymbolLayer(it)
            //var point: Point = Point.fromLngLat(location.long,location.lat)
            //mapboxMap!!.addOnMapClickListener(this)
            //var originPoint: Point = Point.fromLngLat(0.0,0.0)
            /*locationComponent?.lastKnownLocation.let {location ->
                if (location != null) {
                    originPoint = Point.fromLngLat(location.longitude,location.latitude)
                }
                *//*if(it?.lastKnownLocation!= null){
                    originPoint = Point.fromLngLat(it.lastKnownLocation!!.longitude,it.lastKnownLocation!!.latitude)
                }
            }*/
            val source: GeoJsonSource? =   mapboxMap?.style!!.getSourceAs("destination-source-id")
            source?.setGeoJson(Feature.fromGeometry(destinationPoint))
            view.startButton.isEnabled = true
            view.startButton.setBackgroundResource(R.color.mapboxBlue)

            val button = view.startButton
            button.setOnClickListener {
                val simulateRoute = true;
                val options = NavigationLauncherOptions.builder()
                    .directionsRoute(currentRoute)
                    .shouldSimulateRoute(simulateRoute)
                    .build();
                // Call this method with Context from within an Activity
                NavigationLauncher.startNavigation(mvpActivity, options);
            }
        }
/*        if (isNotDraw) {
            drawBuldingOnMap()
        }*/
    }
/*    override fun onMapClick(@NonNull point:LatLng): Boolean{
       val destinationPoint = Point.fromLngLat(location.long, location.lat)
       var originPoint: Point? = null
        locationComponent.let {
//            originPoint = Point.fromLngLat(it?.lastKnownLocation.longitude,it.lastKnownLocation.latitude)
            if(it?.lastKnownLocation!= null){
                originPoint = Point.fromLngLat(it.lastKnownLocation!!.longitude,it.lastKnownLocation!!.latitude)
            }
        }
        val source: GeoJsonSource? =   mapboxMap?.style!!.getSourceAs("destination-source-id")
        source?.setGeoJson(Feature.fromGeometry(destinationPoint))

        getRoute(originPoint!!, destinationPoint);
        view.startButton.setEnabled(true)
        view.startButton.setBackgroundResource(R.color.mapboxBlue);
        return true
    }*/
    private fun getRoute(origin: Point, destination: Point) {
        NavigationRoute.builder(mvpActivity)
            .accessToken(Mapbox.getAccessToken().toString())
            .origin(origin)
            .destination(destination)
            .build()
            .getRoute(object : Callback<DirectionsResponse> {
                override fun onFailure(call: Call<DirectionsResponse>, t: Throwable) {
                    Log.e("map", "Error: " + t.message)
                }
                override fun onResponse(call: Call<DirectionsResponse>, response: Response<DirectionsResponse>) {
                    Log.d("map", "Response code: " + response.code())
                    if (response.body() == null) {
                        Log.e("map", "No routes found, make sure you set the right user and access token.")
                        return
                    } else if (response.body()!!.routes().size < 1) {
                        Log.e(TAG, "No routes found")
                        return
                    }
                    currentRoute = response.body()!!.routes()[0]
                    // Draw the route on the map
                    if (navigationMapRoute != null) {
                        navigationMapRoute!!.removeRoute()
                    } else {
                        navigationMapRoute =
                            NavigationMapRoute(null, view.mapView, mapboxMap!!, R.style.NavigationMapRoute)
                    }
                    navigationMapRoute!!.addRoute(currentRoute)
                }
/*                fun onResponse(
                    call: Call<DirectionsResponse>,
                    response: Response<DirectionsResponse>
                ) {
                    // You can get the generic HTTP info about the response
                    Log.d(TAG, "Response code: " + response.code())
                    if (response.body() == null) {
                        Log.e(
                            TAG,
                            "No routes found, make sure you set the right user and access token."
                        )
                        return
                    } else if (response.body().routes().size() < 1) {
                        Log.e(TAG, "No routes found")
                        return
                    }

                    currentRoute = response.body().routes().get(0)

                    // Draw the route on the map
                    if (navigationMapRoute != null) {
                        navigationMapRoute.removeRoute()
                    } else {
                        navigationMapRoute =
                            NavigationMapRoute(null, mapView, mapboxMap, R.style.NavigationMapRoute)
                    }
                    navigationMapRoute.addRoute(currentRoute)
                }

                fun onFailure(call: Call<DirectionsResponse>, throwable: Throwable) {
                    Log.e(TAG, "Error: " + throwable.message)
                }*/
            })
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

            val originPoint: Point = Point.fromLngLat(locationComponent?.lastKnownLocation!!.longitude, locationComponent.lastKnownLocation!!.latitude)
            getRoute(originPoint, destinationPoint)

        }
    }
    private fun addDestinationIconSymbolLayer(@NonNull loadedMapStyle: Style?) {
        loadedMapStyle?.addImage("destination-icon-id", BitmapFactory.decodeResource(mvpActivity.resources, R.drawable.mapbox_marker_icon_default))
        val geoJsonSource: GeoJsonSource =  GeoJsonSource("destination-source-id")
        loadedMapStyle?.addSource(geoJsonSource)
        val destinationSymbolLayer = SymbolLayer("destination-symbol-layer-id", "destination-source-id");
        destinationSymbolLayer.withProperties(
            iconImage("destination-icon-id"),
            iconAllowOverlap(true),
            iconIgnorePlacement(true)
        )
        loadedMapStyle?.addLayer(destinationSymbolLayer);
    }

    /*private fun drawBuldingOnMap() {
        if (mapboxMap == null) {
            isNotDraw = true
        }
        val icon = IconFactory.getInstance(mvpActivity).fromResource(R.drawable.ic_farm_24)
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
                        break
                    }
                }
                true
            }
            isNotDraw = false
        }
    }*/
/*    private fun selectMedical(data: LocationFarmItemModel) {
        runWithCheckMultiTouch("onActionSearch_click_item", object : OnActionNotify {
            override fun onActionNotify() {
                val vehiclePosition = CameraPosition.Builder()
                    .target(LatLng(data.lat, data.long))
                    .zoom(15.0) // Khoang cach zoom gan hay xa, muon gan thi tang gia tri len
                    .build()
                selectedMarker?.hideInfoWindow()
                mapboxMap?.let {
                    selectedMarker?.showInfoWindow(it, getMapView())
                    it.animateCamera(CameraUpdateFactory.newCameraPosition(vehiclePosition), 2000)
                }
            }
        })
    }*/
    override fun getMapView(): MapView {
        return view.mapView
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


    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_map, context, viewGroup)
}



