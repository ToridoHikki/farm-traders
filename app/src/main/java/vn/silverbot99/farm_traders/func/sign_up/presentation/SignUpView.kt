package vn.silverbot99.farm_traders.func.passport.presentation

import android.content.Context
import android.os.Handler
import android.support.design.widget.Snackbar
import android.text.Editable
import android.text.TextUtils
import android.view.ViewGroup
import android.widget.Toast
import kotlinex.context.showAlert
import kotlinex.string.getValueOrDefaultIsEmpty
import kotlinx.android.synthetic.main.layout_passport.view.*
import kotlinx.android.synthetic.main.layout_sign_up.view.*
import vn.silverbot99.core.app.util.KeyboardUtils
import vn.silverbot99.core.base.domain.listener.OnActionNotify
import vn.silverbot99.core.base.presentation.mvp.android.AndroidMvpView
import vn.silverbot99.core.base.presentation.mvp.android.MvpActivity
import vn.silverbot99.farm_traders.R
import vn.silverbot99.farm_traders.app.common.AppConstants
import vn.silverbot99.farm_traders.app.presentation.navigater.AndroidScreenNavigator


import vn.silverbot99.farm_traders.func.passport.presentation.model.UserItemModel
import vn.silverbot99.farm_traders.func.sign_up.presentation.SignUpContract
import vn.silverbot99.farm_traders.func.sign_up.presentation.model.UserFirebaseModel

class SignUpView(mvpActivity: MvpActivity, viewCreator: ViewCreator) : AndroidMvpView(mvpActivity, viewCreator),
        SignUpContract.View {
    override fun signUpSuccess(phone: String) {
        signUpPresenter.gotoAuthencationPhoneView(phone)
    }


    private val signUpPresenter: SignUpPresenter =
            SignUpPresenter(screenNavigator = AndroidScreenNavigator(mvpActivity))
    private val mHandler = Handler()
    private val resourceProvider = SignUpResourceProvider()

    override fun startMvpView() {
        signUpPresenter.attachView(this)
        super.startMvpView()
    }

    override fun initCreateView() {
        val user = view.etPhoneNumberSignUp.text
        val pass = view.etPasswordSignUp.text
        KeyboardUtils.hideSoftInput(mvpActivity)

        if (validated(user, pass)) {
            view.etPhoneNumberSignUp.isEnabled = false
            view.etPasswordSignUp.isEnabled = false
            val userFirebaseModel = UserFirebaseModel(
                email = "${user.toString().getValueOrDefaultIsEmpty()}@gmail.com",
                password = pass.toString().getValueOrDefaultIsEmpty()
            )
            /**/
            view.btnCreateAccount.setOnClickListener { signUpPresenter.createAccount(userFirebaseModel) }

        }
        else{
            view.etPasswordSignUp
        }
    }


    private fun signUp() {

    }

    private fun validated(user: Editable, pass: Editable): Boolean {
        if (TextUtils.isEmpty(user)) {
            view.etPhoneNumberSignUp.error = resourceProvider.getErrorTextNotEmpty()
            view.etPhoneNumberSignUp.requestFocus()
            return false
        }
        if (TextUtils.isEmpty(pass)) {
            view.etPasswordSignUp.error =resourceProvider.getErrorTextNotEmpty()
            view.etPasswordSignUp.requestFocus()
            return false
        }
        if(!pass.equals(view.etRePasswordSignUp.text)){
            view.etRePasswordSignUp.error =resourceProvider.getErrorTextNotEmpty()
            view.etRePasswordSignUp.requestFocus()
            return false
        }
        return true
    }

    override fun showLoading() {
        view.button_sign_up?.startAnimation()
    }

    override fun hideLoading() {
        view.button_sign_up?.revertAnimation()
    }
    override fun showToast(message: String) {
        Toast.makeText(mvpActivity, message, Toast.LENGTH_LONG).show()
    }

/*    override fun handleAfterLogin(data: PassportResponse) {
        val userToken = data.key.getValueOrDefaultIsEmpty()
        if (userToken.isEmpty()) {
            showError(mvpActivity.getString(R.string.title_error_login))
        } else {
            ConfigUtil.saveDateSelected(Calendar.getInstance())
            ConfigUtil.savePassport(data)
            ConfigUtil.saveIsFirstLoginApp(true)
            loginSuccessful()
        }
    }*/


    override fun showError(errorMsg: String) {
        mHandler.postDelayed({
            view.button_passport_login?.let {
                it.revertAnimation()
            }
        }, 1000)

        Snackbar.make(view, errorMsg.getValueOrDefaultIsEmpty(), Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.ACTION_CLOSE) {
                    view.etPhoneNumber.isEnabled = true
                    view.etPassword.isEnabled = true
                }.show()
    }

    //Stop view
    override fun stopMvpView() {
        signUpPresenter.detachView()
        super.stopMvpView()
    }

    //Create view layout
    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
            AndroidMvpView.LayoutViewCreator(R.layout.layout_sign_up, context, viewGroup)


}