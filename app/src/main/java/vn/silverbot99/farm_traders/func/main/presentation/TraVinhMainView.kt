package vn.silverbot99.farm_traders.func.main.presentation

import android.support.design.widget.TabItem
import android.widget.Adapter
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.layout_toolbar.view.*
import vn.silverbot99.core.base.presentation.mvp.android.MvpActivity
import vn.silverbot99.farm_traders.func.empty.EmptyView
import vn.silverbot99.farm_traders.func.nearest_farm.presentation.LocationFarmNearestView



class TraVinhMainView(mvpActivity: MvpActivity, viewCreator: ViewCreator) : MainView(mvpActivity, viewCreator) {
    private var locationFarmNearestView: LocationFarmNearestView? = null
    private var emptyView: EmptyView? = null
    var mAuth: FirebaseAuth = FirebaseAuth.getInstance()


    override fun showHomeView() {

    }

    /*override fun handlerClickMenu(action: ActionMenu): Boolean {
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
//                 showGasStoreList()
                 return true
             }
             ActionMenu.ACTION_MENU_NEWS  ->{
//                 showNewsList()
                 return true
             }
            else ->return false
        }
    }*/

    private fun showLocationGasolineMedicalView() {
        addMainContentWithView(locationFarmNearestView)
        locationFarmNearestView!!.loadData()
    }
    private fun showEmptyView(){
//        addMainContentWithView(emptyView)
    }
    private fun initEmptyView() {
        emptyView = EmptyView(mvpActivity, EmptyView.ViewCreator(mvpActivity, null))
        emptyView?.let {
            addLifeCycle(it)
        }
    }
    private fun initLocationGasolineMedicalView() {
        locationFarmNearestView = LocationFarmNearestView(mvpActivity, LocationFarmNearestView.ViewCreator(mvpActivity, null))
        locationFarmNearestView?.let {
            addLifeCycle(it)
        }
    }

    override fun initLayoutView() {
//        initLocationGasolineMedicalView()
        initEmptyView()
        view.ivIconRight.setOnClickListener {
            mAuth.signOut()
            androidScreenNavigator.gotoPassportActivity()
        }

    }






}