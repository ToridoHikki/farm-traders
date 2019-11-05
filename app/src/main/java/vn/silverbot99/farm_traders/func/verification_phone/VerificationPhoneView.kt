package vn.silverbot99.farm_traders.func.verification_phone

import android.content.Context
import android.text.Editable
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import kotlinex.mvpactivity.showErrorAlert
import kotlinx.android.synthetic.main.layout_verification.view.*
import vn.silverbot99.core.base.presentation.mvp.android.AndroidMvpView
import vn.silverbot99.core.base.presentation.mvp.android.MvpActivity
import vn.silverbot99.farm_traders.R
import vn.silverbot99.farm_traders.app.presentation.navigater.AndroidScreenNavigator
import vn.silverbot99.farm_traders.func.passport.presentation.PassportPresenter

class VerificationPhoneView(mvpActivity: MvpActivity, viewCreator: VerificationPhoneView.ViewCreator) : AndroidMvpView(mvpActivity, viewCreator), VerificationPhoneContract.View {
    private var progressBar: ProgressBar = view.progressbar
    private val mPresenter: VerificationPhonePresenter =
        VerificationPhonePresenter(screenNavigator = AndroidScreenNavigator(mvpActivity))
    override fun verificateSuccess() {
        mPresenter.gotoMainActivity()
    }

    override fun getCode(code: String) {
        view.editTextCode.text = code as Editable
    }



    override fun initCreateView() {

    }

    override fun showLoading() {
        progressBar.setVisibility(View.VISIBLE);

    }

    override fun hideLoading() {
        progressBar.setVisibility(View.GONE);

    }
    override fun showToast(message: String) {
        Toast.makeText(mvpActivity, message, Toast.LENGTH_LONG).show()
    }

    override fun showError(message: String) {
        mvpActivity.showErrorAlert(message)
    }

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_passport, context, viewGroup)

}