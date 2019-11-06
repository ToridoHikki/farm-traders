package vn.silverbot99.farm_traders.func.passport.presentation

import android.content.Context
import android.os.Handler
import android.support.design.widget.Snackbar
import android.text.Editable
import android.text.TextUtils
import android.view.ViewGroup
import kotlinex.context.showAlert
import kotlinex.string.getValueOrDefaultIsEmpty
import kotlinx.android.synthetic.main.layout_passport.view.*
import vn.silverbot99.core.app.util.KeyboardUtils
import vn.silverbot99.core.base.domain.listener.OnActionNotify
import vn.silverbot99.core.base.presentation.mvp.android.AndroidMvpView
import vn.silverbot99.core.base.presentation.mvp.android.MvpActivity
import vn.silverbot99.farm_traders.R
import vn.silverbot99.farm_traders.app.common.AppConstants
import vn.silverbot99.farm_traders.app.presentation.navigater.AndroidScreenNavigator


import vn.silverbot99.farm_traders.func.passport.presentation.model.UserItemModel

class PassportView(mvpActivity: MvpActivity, viewCreator: ViewCreator, private val forceLogin: Int) : AndroidMvpView(mvpActivity, viewCreator),
        PassportContract.View {
    private val passportPresenter: PassportPresenter=
        PassportPresenter(screenNavigator = AndroidScreenNavigator(mvpActivity))
    private val mHandler = Handler()
    private val resourceProvider = SignUpResourceProvider()

    override fun startMvpView() {
        passportPresenter.attachView(this)
        super.startMvpView()
    }

    override fun initCreateView() {
/*        Utils.setupInput(view.etPhoneNumber.text)
        Utils.setupInput(view.input_passport_password)
        view.input_passport_password.editText!!.setOnEditorActionListener { _, actionId, keyEvent ->
            keyEvent?.let {
                if (actionId == EditorInfo.IME_ACTION_GO
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || it.action == KeyEvent.ACTION_DOWN
                        && it.keyCode == KeyEvent.KEYCODE_ENTER
                ) {
                    login()
                    return@setOnEditorActionListener true
                }
            }
            true
        }
        view.button_passport_login.setOnClickListener { login() }*/
        login()
        view.btnCreateAccount.setOnClickListener { passportPresenter.gotoLogUpView() }

    }

    override fun initData() {
        super.initData()
        when (forceLogin) {
            AppConstants.FORCE_LOGIN_USER_NOT_SUPPORTED -> {
                showAlertForceLogin(resourceProvider.getForceLoginUserNotSupport())
            }
        }
    }

    private fun showAlertForceLogin(message: String) {
        mvpActivity.showAlert(message, "", object : OnActionNotify {
            override fun onActionNotify() {

            }
        })
    }

    private fun login() {
        val user = view.etPhoneNumber.text
        val pass = view.etPassword.text
        KeyboardUtils.hideSoftInput(mvpActivity)

        if (validated(user, pass)) {
            view.etPhoneNumber.isEnabled = false
            view.etPassword.isEnabled = false
            val userItemModel = UserItemModel(
                    phone = user.toString().getValueOrDefaultIsEmpty(),
                    password = pass.toString().getValueOrDefaultIsEmpty()
            )
            //máy cái này xử lý xong sẽ gọi pressneter.
            passportPresenter.login(userItemModel)
        }
    }

    private fun validated(user: Editable, pass: Editable): Boolean {
        if (TextUtils.isEmpty(user)) {
            view.etPhoneNumber.error = resourceProvider.getErrorTextNotEmpty()
            view.etPhoneNumber.requestFocus()
            return false
        }
        if (TextUtils.isEmpty(pass)) {
            view.etPassword.error =resourceProvider.getErrorTextNotEmpty()
            view.etPassword.requestFocus()
            return false
        }
        return true
    }

    override fun showLoading() {
        view.button_passport_login?.startAnimation()
    }

    override fun hideLoading() {
        view.button_passport_login?.revertAnimation()
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

    override fun loginSuccessful() {
        passportPresenter.gotoMainActivity()
    }

    override fun showError(errorMsg: String) {
        showLoading()
        mHandler.postDelayed({
            /*view.button_passport_login?.let {
                it.revertAnimation()
            }*/
            hideLoading()
        }, 2000)

        Snackbar.make(view, errorMsg.getValueOrDefaultIsEmpty(), Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.ACTION_CLOSE) {
                    view.etPhoneNumber.isEnabled = true
                    view.etPassword.isEnabled = true
                }.show()
    }

    //Stop view
    override fun stopMvpView() {
        passportPresenter.detachView()
        super.stopMvpView()
    }

    //Create view layout
    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
            AndroidMvpView.LayoutViewCreator(R.layout.layout_passport, context, viewGroup)


}