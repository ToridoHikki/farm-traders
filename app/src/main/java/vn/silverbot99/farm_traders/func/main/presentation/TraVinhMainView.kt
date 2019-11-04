package vn.silverbot99.farm_traders.func.main.presentation

import vn.silverbot99.core.base.presentation.mvp.android.MvpActivity
import vn.silverbot99.farm_traders.func.empty.EmptyView
import vn.silverbot99.farm_traders.func.main.data.ActionMenu
import vn.silverbot99.farm_traders.func.location_gasoline_medical.presentation.LocationGasolineMedicalView


class TraVinhMainView(mvpActivity: MvpActivity, viewCreator: ViewCreator) : MainView(mvpActivity, viewCreator) {
    private var locationGasolineMedicalView: LocationGasolineMedicalView? = null
    private var emptyView: EmptyView? = null

    override fun showHomeView() {
        //showLocationGasolineMedicalView()
    }

    private fun showGasStoreList() {
//        mainPresenter.gotoGasStoreActivity()
    }
    private fun showMedicalList() {
//        mainPresenter.gotoMedicalActivity()
    }
    private fun showNewsList(){
//        mainPresenter.gotoNewsActivity()
    }
    override fun handlerClickMenu(action: ActionMenu): Boolean {
         when (action) {
             ActionMenu.ACTION_MENU_MANAGER_CLINICS ->{
//                 showMedicalList()
                 return true
             }
             ActionMenu.ACTION_MENU_MANAGER_COMPANY ->{
//                 showLocationGasolineMedicalView()
//                    showEmptyView()
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
    private fun showEmptyView(){
        addMainContentWithView(emptyView)
    }
    private fun initEmptyView() {
        emptyView = EmptyView(mvpActivity, EmptyView.ViewCreator(mvpActivity, null))
        emptyView?.let {
            addLifeCycle(it)
        }
    }
    private fun initLocationGasolineMedicalView() {
        locationGasolineMedicalView = LocationGasolineMedicalView(mvpActivity, LocationGasolineMedicalView.ViewCreator(mvpActivity, null))
        locationGasolineMedicalView?.let {
            addLifeCycle(it)
        }
    }

    override fun initLayoutView() {
//        initLocationGasolineMedicalView()
        initEmptyView()

    }



}