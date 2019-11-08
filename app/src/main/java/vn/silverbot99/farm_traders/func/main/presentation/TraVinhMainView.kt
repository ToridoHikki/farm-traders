package vn.silverbot99.farm_traders.func.main.presentation

import android.support.design.widget.TabItem
import android.widget.Adapter
import com.google.firebase.auth.FirebaseAuth
import com.thisobeystudio.customviewpager.viewpager.CustomViewPager
import kotlinx.android.synthetic.main.layout_toolbar.view.*
import vn.silverbot99.core.base.presentation.mvp.android.MvpActivity
import vn.silverbot99.farm_traders.func.empty.EmptyView
import vn.silverbot99.farm_traders.func.main.data.ActionMenu
import vn.silverbot99.farm_traders.func.nearest_farm.presentation.LocationGasolineMedicalView



class TraVinhMainView(mvpActivity: MvpActivity, viewCreator: ViewCreator) : MainView(mvpActivity, viewCreator) {
    private var locationGasolineMedicalView: LocationGasolineMedicalView? = null
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
        view.ivIconRight.setOnClickListener {
            mAuth.signOut()
            androidScreenNavigator.gotoPassportActivity()
        }

    }
    companion object {
        const val HOME_FRAGMENT_ID = "home-id"
        const val EXPLORE_FRAGMENT_ID = "explore-id"
        const val EXPERIENCES_FRAGMENT_ID = "experiences-id"
        const val DIY_TOUR_FRAGMENT_ID = "diy-tour-id"
        const val UTILLITIES_FRAGMENT_ID = "utilities-id"
        private lateinit var mTabItems: MutableList<TabItem>
    }
    private lateinit var adapter: Adapter
    private lateinit var viewPager: CustomViewPager





}