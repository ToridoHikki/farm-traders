package vn.silverbot99.farm_traders.func.farm_detail.presentation

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import vn.silverbot99.core.base.presentation.mvp.base.MvpPresenter
import vn.silverbot99.core.base.presentation.mvp.base.MvpView
import vn.silverbot99.farm_traders.app.data.network.response.FarmerResponse
import vn.silverbot99.farm_traders.func.farm_detail.presentation.model.FarmDetailProductListItemModel
import vn.silverbot99.farm_traders.func.farm_detail.presentation.model.FarmerItemModel
import vn.silverbot99.farm_traders.func.product_list.presentation.model.ProductListItemModel

interface FarmDetailContract {
    interface View: MvpView {
        fun showLoading()

        fun hideLoading()

        fun showToast(message: String)

        fun showError(message: String)

        fun showDetailInfo(data: List<ViewModel>)

        fun showFarmerDetail(data: FarmerResponse)

        fun loadProductList()
    }
    abstract class Presenter: MvpPresenter<View>() {

        abstract fun getProductListOfFarm(farmId: String)

        abstract fun getFarmerbyFarmId(farmId: String)

        abstract fun gotoProductDetail(productListItemModel: ProductListItemModel)

    }
}