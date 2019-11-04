package kotlinex.mvpactivity

import kotlinex.context.showAlert
import vn.silverbot99.core.base.domain.listener.OnActionNotify
import vn.silverbot99.core.base.presentation.mvp.android.MvpActivity
import vn.silverbot99.farm_traders.R

fun MvpActivity.showErrorAlert(msgError: String) {
    val activity = this
    activity.showAlert(msgError, activity.getString(R.string.error_title), null)
}

fun MvpActivity.showErrorAlert(msgError: String, onActionNotify: OnActionNotify) {
    val activity = this
    activity.showAlert(msgError, activity.getString(R.string.error_title), onActionNotify)
}

fun MvpActivity.showAlertNotTitle(msg: String) {
    val activity = this
    activity.showAlert(msg, activity.getString(R.string.error_title), null)
}