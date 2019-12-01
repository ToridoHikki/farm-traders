package vn.silverbot99.farm_traders.func.product_detail.presentation

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import vn.silverbot99.core.base.presentation.mvp.base.MvpPresenter
import vn.silverbot99.core.base.presentation.mvp.base.MvpView
import vn.silverbot99.farm_traders.app.data.network.response.FarmResponse
import vn.silverbot99.farm_traders.app.data.network.response.FarmerResponse
import vn.silverbot99.farm_traders.app.data.network.response.LocationFarmNearestResponse
import vn.silverbot99.farm_traders.func.nearest_farm.presentation.model.LocationFarmItemModel

interface ProductDetailContract {
    interface View: MvpView {
        fun showLoading()

        fun hideLoading()

        fun showToast(message: String)

        fun showError(message: String)

        fun showFarmerDetail(data: FarmerResponse)

        fun showFarmDetail(data: MutableList<ViewModel>)

    }
    abstract class Presenter: MvpPresenter<View>() {

        abstract fun calltoFarmer(farmerNumber: String)

        abstract fun messToFarmer(farmerNumber: String)

        abstract fun getFarmerInfo(farmId: String)

        abstract fun getFarmInfo(farmerId: String)

        abstract fun gotoMap(locationFarmItemModel: LocationFarmItemModel)

        //abstract fun goToFarm()

    }
}