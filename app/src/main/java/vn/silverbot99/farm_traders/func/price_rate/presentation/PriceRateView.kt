package vn.silverbot99.farm_traders.func.price_rate.presentation

import android.content.Context
import android.view.ViewGroup
import vn.silverbot99.core.app.view.loading.Loadinger
import vn.silverbot99.core.base.presentation.mvp.android.AndroidMvpView
import vn.silverbot99.core.base.presentation.mvp.android.MvpActivity
import vn.silverbot99.farm_traders.R
import vn.silverbot99.farm_traders.app.presentation.navigater.AndroidScreenNavigator
class PriceRateView(mvpActivity: MvpActivity, viewCreator: ViewCreator) : AndroidMvpView(mvpActivity,viewCreator),
    PriceRateContract.View {

    private val mPresenter =PriceRatePresenter()
    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    override fun initCreateView() {

    }
    override fun showLoading() {
        loadingView.show()
    }

    override fun hideLoading() {
        loadingView.hide()
    }
    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_price_rate, context, viewGroup)
}