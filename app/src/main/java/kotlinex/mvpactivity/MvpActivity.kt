package kotlinex.mvpactivity

import kotlinex.context.showAlert
import vn.minerva.core.base.domain.listener.OnActionNotify
import vn.minerva.core.base.presentation.mvp.android.MvpActivity
import vn.minerva.travinh.R

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