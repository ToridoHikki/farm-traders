package vn.silverbot99.core.base.presentation.mvp.android.lifecycle

import vn.silverbot99.core.base.presentation.mvp.base.lifecycle.LifeCycleMvpView

interface LifeCycleAndroidMvpView : LifeCycleMvpView {
    fun onViewResult(viewResult: ViewResult)
    fun onRequestPermissionsResult(permissionsResult: PermissionsResult)
}