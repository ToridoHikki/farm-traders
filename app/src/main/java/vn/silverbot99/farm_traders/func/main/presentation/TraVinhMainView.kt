package vn.silverbot99.farm_traders.func.main.presentation

import android.support.design.widget.TabItem
import android.widget.Adapter
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.layout_toolbar.view.*
import vn.silverbot99.core.base.presentation.mvp.android.MvpActivity
import vn.silverbot99.farm_traders.app.config.ConfigUtil
import vn.silverbot99.farm_traders.func.empty.EmptyView
import vn.silverbot99.farm_traders.func.nearest_farm.presentation.LocationFarmNearestView



class TraVinhMainView(mvpActivity: MvpActivity, viewCreator: ViewCreator) : MainView(mvpActivity, viewCreator) {
    private var locationFarmNearestView: LocationFarmNearestView? = null
    private var emptyView: EmptyView? = null
    var mAuth: FirebaseAuth = FirebaseAuth.getInstance()


    override fun showHomeView() {

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
        initEmptyView()
        view.ivIconRight.setOnClickListener {
            mAuth.signOut()
            androidScreenNavigator.gotoPassportActivity()
        }
    }






}