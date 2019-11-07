package vn.silverbot99.farm_traders.func.main.presentation

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import com.mapbox.mapboxsdk.maps.MapView
import vn.silverbot99.core.base.presentation.mvp.base.MvpPresenter
import vn.silverbot99.core.base.presentation.mvp.base.MvpView
import vn.silverbot99.farm_traders.func.main.domain.MainConfigUseCase

interface MainContract {
    interface MainView : MvpView {
        fun showError(msgError: String)
        fun showLoading()
        fun hideLoading()
        fun handleAfterLoadingConfig(output: MainConfigUseCase.Output)
        fun resetActionDefault()
        fun getMapView(): MapView? = null
        //fun getOnMapReadyCallback(): OnMapReadyCallback
    }

    abstract class Presenter : MvpPresenter<MainView>() {
        abstract fun loadConfigData()
        abstract fun gotoLoginActivity()
        abstract fun showProfileView()

    }

    /*interface MenuView : MvpView {
        fun renderListMenu(menuViewModels: MutableList<ViewModel>)
    }

    abstract class MenuPresenter : MvpPresenter<MenuView>() {
        abstract fun loadListMenu()
    }*/
}