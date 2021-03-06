package vn.silverbot99.farm_traders.func.product_list.presentation

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import vn.silverbot99.core.base.presentation.mvp.base.MvpPresenter
import vn.silverbot99.core.base.presentation.mvp.base.MvpView
import vn.silverbot99.farm_traders.func.product_list.presentation.model.ProductListItemModel

interface ProductListContract {
    interface View: MvpView {
        fun showLoading()

        fun hideLoading()

        fun showToast(message: String)

        fun showError(message: String)

        fun loadData()

        fun showDetailInfo(data: List<ViewModel>)
    }
    abstract class Presenter: MvpPresenter<View>() {
        abstract fun getProductList(categoryId: String)
        abstract fun gotoProductDetail(product: ProductListItemModel)

        }
    }