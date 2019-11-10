package vn.silverbot99.farm_traders.func.verification_phone.presentation

import android.content.Context
import android.text.Editable
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import kotlinex.mvpactivity.showErrorAlert
import kotlinx.android.synthetic.main.layout_verification_phone_number.view.*
import vn.silverbot99.core.app.view.loading.Loadinger
import vn.silverbot99.core.base.presentation.mvp.android.AndroidMvpView
import vn.silverbot99.core.base.presentation.mvp.android.MvpActivity
import vn.silverbot99.farm_traders.R
import vn.silverbot99.farm_traders.app.presentation.navigater.AndroidScreenNavigator

class VerificationView (mvpActivity: MvpActivity, viewCreator: VerificationView.ViewCreator, private val phone: String) : AndroidMvpView(mvpActivity, viewCreator),
    VerificationContract.View {

    private var progressBar: ProgressBar = view.progressbar
    private val mPresenter: VerificationPresenter =
        VerificationPresenter(screenNavigator = AndroidScreenNavigator(mvpActivity))
    override fun verificateSuccess() {
        mPresenter.gotoMainActivity()
    }

    override fun getCode(code: String) {
        view.editTextCode.text = code as Editable
    }


    override fun initData() {
        super.initData()
        mPresenter.sendVerificationCode(phone)

    }
    override fun initCreateView() {
        var codeByUserType = view.editTextCode.text
        view.buttonSignIn.setOnClickListener { mPresenter.verifyCode(codeByUserType.toString()) }
        view.buttonReSend.setOnClickListener { mPresenter.resSendVerificationCode(codeByUserType.toString())}


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
        AndroidMvpView.LayoutViewCreator(R.layout.layout_verification_phone_number, context, viewGroup)

}