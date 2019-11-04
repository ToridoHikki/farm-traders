package vn.minerva.travinh.func.main.presentation

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import vn.minerva.core.base.presentation.mvp.base.MvpPresenter
import vn.minerva.core.base.presentation.mvp.base.MvpView
import vn.minerva.travinh.func.main.domain.MainConfigUseCase

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
        abstract fun gotoGasStoreActivity()
        abstract fun gotoMedicalActivity()
        abstract fun gotoNewsActivity()

    }

    interface MenuView : MvpView {
        fun renderListMenu(menuViewModels: MutableList<ViewModel>)
    }

    abstract class MenuPresenter : MvpPresenter<MenuView>() {
        abstract fun loadListMenu()
    }
}