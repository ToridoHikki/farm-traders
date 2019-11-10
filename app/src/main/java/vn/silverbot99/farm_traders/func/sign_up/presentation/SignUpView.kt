package vn.silverbot99.farm_traders.func.sign_up.presentation

import android.content.Context
import android.os.Handler
import android.support.design.widget.Snackbar
import android.text.Editable
import android.text.TextUtils
import android.util.Log
import android.view.KeyEvent
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import kotlinex.context.showAlert
import kotlinex.string.getValueOrDefaultIsEmpty
import kotlinx.android.synthetic.main.layout_passport.view.*
import kotlinx.android.synthetic.main.layout_sign_up.view.*
import vn.silverbot99.core.app.util.KeyboardUtils
import vn.silverbot99.core.app.util.Utils
import vn.silverbot99.core.base.domain.listener.OnActionNotify
import vn.silverbot99.core.base.presentation.mvp.android.AndroidMvpView
import vn.silverbot99.core.base.presentation.mvp.android.MvpActivity
import vn.silverbot99.farm_traders.R
import vn.silverbot99.farm_traders.app.common.AppConstants
import vn.silverbot99.farm_traders.app.presentation.navigater.AndroidScreenNavigator


import vn.silverbot99.farm_traders.func.passport.presentation.model.UserItemModel
import vn.silverbot99.farm_traders.func.sign_up.presentation.SignUpContract
import vn.silverbot99.farm_traders.func.sign_up.presentation.SignUpResourceProvider
import vn.silverbot99.farm_traders.func.sign_up.presentation.model.UserFirebaseModel

class SignUpView(mvpActivity: MvpActivity, viewCreator: ViewCreator) : AndroidMvpView(mvpActivity, viewCreator),
        SignUpContract.View {
    override fun signUpSuccess(phone: String) {
        Log.d("Verification", phone)
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
       // view.btnCreateAccount.setOnClickListener { signUp() }
//        signUp()
        Utils.setupInput(view.etPhoneNumberSignUpLayout)
        Utils.setupInput(view.etPasswordSignUpLayout)
        Utils.setupInput(view.etRePasswordSignUpLayout)

        /*view.etPasswordSignUpLayout.editText!!.setOnEditorActionListener { _, actionId, keyEvent ->
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
        }*/
        view.button_sign_up.setOnClickListener { signUp() }



    }


    private fun signUp() {
        val user = view.etPhoneNumberSignUpLayout.editText!!.text
        val pass = view.etPasswordSignUpLayout.editText!!.text
        val rePass = view.etRePasswordSignUpLayout.editText!!.text
        KeyboardUtils.hideSoftInput(mvpActivity)
            if (validated(user, pass, rePass)) {
                view.etPhoneNumberSignUp.isEnabled = false
                view.etPasswordSignUp.isEnabled = false
                val userFirebaseModel = UserFirebaseModel(
                    email = "${user.toString().getValueOrDefaultIsEmpty()}@gmail.com",
                    password = pass.toString().getValueOrDefaultIsEmpty()
                )
                signUpPresenter.createAccount(userFirebaseModel)
//                view.btnCreateAccount.setOnClickListener {
//                    Log.d("signUp","btnCreateAccount onClick")
//                    signUpPresenter.createAccount(userFirebaseModel)
//                }
            }
        }

    private fun validated(user: Editable, pass: Editable, rePass: Editable): Boolean {
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
        if (TextUtils.isEmpty(rePass)) {
            view.etRePasswordSignUp.error =resourceProvider.getErrorTextNotEmpty()
            view.etRePasswordSignUp.requestFocus()
            return false
        }
        if(!TextUtils.equals(pass,rePass)){
            view.etRePasswordSignUpLayout.editText?.text = null
            view.etPasswordSignUpLayout.editText?.text = null
            showToast(resourceProvider.getMessageRetypePassword())
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
        //todo nhan ok crash app
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