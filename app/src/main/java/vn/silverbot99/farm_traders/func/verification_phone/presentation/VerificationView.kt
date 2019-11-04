package vn.silverbot99.farm_traders.func.verification_phone.presentation

import android.content.Context
import android.view.ViewGroup
import kotlinex.mvpactivity.showErrorAlert
import vn.silverbot99.core.app.view.loading.Loadinger
import vn.silverbot99.core.base.presentation.mvp.android.AndroidMvpView
import vn.silverbot99.core.base.presentation.mvp.android.MvpActivity
import vn.silverbot99.farm_traders.R
import vn.silverbot99.farm_traders.app.presentation.navigater.AndroidScreenNavigator

class VerificationView (mvpActivity: MvpActivity, viewCreator: VerificationView.ViewCreator, private val phone: String) : AndroidMvpView(mvpActivity, viewCreator),
    VerificationContract.View {

    protected val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val verificationPresenter: VerificationPresenter =
        VerificationPresenter(screenNavigator = AndroidScreenNavigator(mvpActivity))
    override fun initCreateView() {

    }
    override fun showLoading() {
        loadingView?.show()
    }

    override fun hideLoading() {
        loadingView?.hide()
    }

    override fun showError(message: String) {
        mvpActivity.showErrorAlert(message)
    }

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_verification_phone_number, context, viewGroup)

}