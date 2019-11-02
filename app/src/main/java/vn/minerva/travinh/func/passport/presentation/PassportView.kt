package vn.minerva.travinh.func.passport.presentation

import android.content.Context
import android.os.Handler
import android.support.design.widget.Snackbar
import android.text.Editable
import android.text.TextUtils
import android.view.KeyEvent
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import kotlinex.context.showAlert
import kotlinex.string.getValueOrDefaultIsEmpty
import kotlinx.android.synthetic.main.layout_passport.view.*
import vn.minerva.core.app.util.KeyboardUtils
import vn.minerva.core.app.util.Utils
import vn.minerva.core.base.domain.listener.OnActionNotify
import vn.minerva.core.base.presentation.mvp.android.AndroidMvpView
import vn.minerva.core.base.presentation.mvp.android.MvpActivity
import vn.minerva.travinh.R
import vn.minerva.travinh.app.common.AppConstants
import vn.minerva.travinh.app.config.ConfigUtil
import vn.minerva.travinh.app.data.network.request.PassportRequest
import vn.minerva.travinh.app.data.network.response.PassportResponse
import vn.minerva.travinh.app.presentation.navigater.AndroidScreenNavigator
import java.util.*

//cai nay la 1 view. chinh xác là 1 layout luôn đấy
class PassportView(mvpActivity: MvpActivity, viewCreator: ViewCreator, private val forceLogin: Int) : AndroidMvpView(mvpActivity, viewCreator),
        PassportContract.View {
    //khoi tạo presenter
    private val passportPresenter: PassportPresenter =
            PassportPresenter(screenNavigator = AndroidScreenNavigator(mvpActivity))
    private val mHandler = Handler()
    private val resourceProvider = PassportResourceProvider()

    //lúc tạo presenter nhớ acttactview và detact nó, tại vì để nó nhận view và xóa dữ liệu nếu view bị xóa thôi.
    //Cái view thì xử dụng để init dữ liệu bình thường thôi. như login
    //Attach view
    override fun startMvpView() {
        passportPresenter.attachView(this)
        super.startMvpView()
    }

    override fun initCreateView() {
        Utils.setupInput(view.input_passport_username)
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
        view.button_passport_login.setOnClickListener { login() }
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
        val user = view.input_passport_username.editText!!.text
        val pass = view.input_passport_password.editText!!.text
        KeyboardUtils.hideSoftInput(mvpActivity)

        if (validated(user, pass)) {
            view.input_passport_username.isEnabled = false
            view.input_passport_password.isEnabled = false
            val passportRequest = PassportRequest(
                    user = user.toString().getValueOrDefaultIsEmpty(),
                    pass = pass.toString().getValueOrDefaultIsEmpty()
            )
            //máy cái này xử lý xong sẽ gọi pressneter.
            passportPresenter.login(passportRequest)
        }
    }

    private fun validated(user: Editable, pass: Editable): Boolean {
        if (TextUtils.isEmpty(user)) {
            view.input_passport_username.error = resourceProvider.getErrorTextNotEmpty()
            view.input_passport_username.requestFocus()
            return false
        }
        if (TextUtils.isEmpty(pass)) {
            view.input_passport_password.error =resourceProvider.getErrorTextNotEmpty()
            view.input_passport_password.requestFocus()
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

    override fun handleAfterLogin(data: PassportResponse) {
        val userToken = data.key.getValueOrDefaultIsEmpty()
        if (userToken.isEmpty()) {
            showError(mvpActivity.getString(R.string.title_error_login))
        } else {
            ConfigUtil.saveDateSelected(Calendar.getInstance())
            ConfigUtil.savePassport(data)
            ConfigUtil.saveIsFirstLoginApp(true)
            loginSuccessful()
        }
    }

    private fun loginSuccessful() {
        passportPresenter.gotoMainActivity()
    }

    override fun showError(errorMsg: String) {
        mHandler.postDelayed({
            view.button_passport_login?.let {
                it.revertAnimation()
            }
        }, 1000)

        Snackbar.make(view, errorMsg.getValueOrDefaultIsEmpty(), Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.ACTION_CLOSE) {
                    view.input_passport_username.isEnabled = true
                    view.input_passport_password.isEnabled = true
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