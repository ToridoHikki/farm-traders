package vn.minerva.travinh.func.main.presentation

import kotlinx.android.synthetic.main.layout_toolbar.view.*
import vn.minerva.core.base.presentation.mvp.android.MvpActivity
import vn.minerva.travinh.func.main.data.ActionMenu
import vn.minerva.travinh.func.medical.presentation.MedicalView
import vn.minerva.travinh.func.gasoline_store.presentation.GasolineStoreView
import vn.minerva.travinh.func.location_gasoline_medical.presentation.LocationGasolineMedicalView


class TraVinhMainView(mvpActivity: MvpActivity, viewCreator: ViewCreator) : MainView(mvpActivity, viewCreator) {
    private var locationGasolineMedicalView: LocationGasolineMedicalView? = null

    override fun showHomeView() {
        showLocationGasolineMedicalView()
    }

    private fun showGasStoreList() {
        mainPresenter.gotoGasStoreActivity()
    }
    private fun showMedicalList() {
        mainPresenter.gotoMedicalActivity()
    }
    private fun showNewsList(){
        mainPresenter.gotoNewsActivity()
    }
    override fun handlerClickMenu(action: ActionMenu): Boolean {
         when (action) {
             ActionMenu.ACTION_MENU_MANAGER_CLINICS ->{
                 showMedicalList()
                 return true
             }
             ActionMenu.ACTION_MENU_MANAGER_COMPANY ->{
                 showLocationGasolineMedicalView()
                 return true
             }
             ActionMenu.ACTION_MENU_MANAGER_GAS_STATION ->{
                 showGasStoreList()
                 return true
             }
             ActionMenu.ACTION_MENU_NEWS  ->{
                 showNewsList()
                 return true
             }
            else ->return false
        }
    }

    private fun showLocationGasolineMedicalView() {
        addMainContentWithView(locationGasolineMedicalView)
        locationGasolineMedicalView!!.loadData()
    }

    private fun initLocationGasolineMedicalView() {
        locationGasolineMedicalView = LocationGasolineMedicalView(mvpActivity, LocationGasolineMedicalView.ViewCreator(mvpActivity, null))
        locationGasolineMedicalView?.let {
            addLifeCycle(it)
        }
    }

    override fun initLayoutView() {
        initLocationGasolineMedicalView()

    }



}