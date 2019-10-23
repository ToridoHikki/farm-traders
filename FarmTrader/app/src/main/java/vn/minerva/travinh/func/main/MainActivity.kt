package vn.minerva.travinh.func.main

import android.os.Bundle
import be.trikke.intentbuilder.BuildIntent
import com.mapbox.mapboxsdk.maps.MapView
import vn.minerva.core.base.presentation.mvp.android.AndroidMvpView
import vn.minerva.core.base.presentation.mvp.android.MvpActivity
import vn.minerva.travinh.func.main.presentation.MainView
import vn.minerva.travinh.func.main.presentation.TraVinhMainView

@BuildIntent
class MainActivity : MvpActivity() {
    //thằng main nó sẽ nhận cái view này. để lazy load để tránh trường hợp nhiều view khởi tạo quá main phải đợi mà ko chết
    private val mainView: MainView by lazy {
        TraVinhMainView(this, MainView.ViewCreator(this, null))
    }

    override fun createAndroidMvpView(): AndroidMvpView {
        return mainView
    }

    private var mapView: MapView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mapView = mainView.getMapView()
        mapView?.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        mapView?.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView?.onStop()
    }

    public override fun onPause() {
        super.onPause()
        mapView?.onPause()
    }

    override fun onResume() {
        super.onResume()
        mapView?.onResume()
        mainView.resetActionDefault()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView?.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView?.onSaveInstanceState(outState)
    }
}