package kotlinex.context

import android.content.Context
import android.support.annotation.PluralsRes
import org.jetbrains.anko.alert
import org.jetbrains.anko.appcompat.v7.Appcompat
import vn.minerva.core.base.domain.listener.OnActionNotify
import vn.minerva.travinh.R

fun Context.showAlert(msg: String, title: String, onActionPositive: OnActionNotify?, onActionNegative: OnActionNotify?, onActionNeutral: OnActionNotify?) {
    this.alert(Appcompat, msg, title)
    {
        positiveButton(buttonText = getString(R.string.ACTION_RETRY), onClicked = {
            onActionPositive?.onActionNotify()
        })
        negativeButton(buttonText = getString(R.string.ACTION_CANCEL), onClicked = {
            onActionNegative?.onActionNotify()
        })
        neutralPressed(buttonText = "", onClicked = {
            onActionNeutral?.onActionNotify()
        })
    }
            .show()
            .setCancelable(false)
}

fun Context.showAlert(msg: String, title: String, onActionPositive: OnActionNotify?, onActionNegative: OnActionNotify?) {
    this.alert(Appcompat, msg, title)
    {
        positiveButton(buttonText = getString(R.string.ACTION_YES), onClicked = {
            onActionPositive?.onActionNotify()
        })
        negativeButton(buttonText = getString(R.string.ACTION_CANCEL), onClicked = {
            onActionNegative?.onActionNotify()
        })
    }
            .show()
            .setCancelable(false)
}

fun Context.showAlert(msg: String, title: String, onActionPositive: OnActionNotify?) {
    this.alert(Appcompat, msg, title)
    {
        positiveButton(buttonText = getString(R.string.ACTION_OK), onClicked = {
            onActionPositive?.onActionNotify()
        })

    }
            .show()
            .setCancelable(false)
}

fun Context.showAlert(msg: String, title: String, positiveStr: String, negative: String, onActionPositive: OnActionNotify?, onActionNegative: OnActionNotify?) {
    this.alert(Appcompat, msg, title)
    {
        positiveButton(buttonText = positiveStr, onClicked = {
            onActionPositive?.onActionNotify()
        })
        negativeButton(buttonText = negative, onClicked = {
            onActionNegative?.onActionNotify()
        })
    }
            .show()
            .setCancelable(false)
}

fun Context.showAlert(msg: String, title: String, positiveStr: String, negative: String, neutral: String, onActionPositive: OnActionNotify?, onActionNegative: OnActionNotify?, onActionNeutral: OnActionNotify?) {
    this.alert(Appcompat, msg, title)
    {
        positiveButton(buttonText = positiveStr, onClicked = {
            onActionPositive?.onActionNotify()
        })
        negativeButton(buttonText = negative, onClicked = {
            onActionNegative?.onActionNotify()
        })
        neutralPressed(buttonText = neutral, onClicked = {
            onActionNeutral?.onActionNotify()
        })
    }
            .show()
            .setCancelable(false)
}

fun Context.getQuantityString(@PluralsRes idStr: Int, quantity: Int): String {
    return this.resources.getQuantityString(idStr, quantity)
}