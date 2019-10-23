package vn.minerva.travinh.func.main.presentation

import android.support.v4.view.GravityCompat
import kotlinx.android.synthetic.main.layout_main.view.*
import vn.minerva.core.base.presentation.mvp.android.MvpActivity
import vn.minerva.travinh.func.main.data.ActionMenu

class TraVinhMainView(mvpActivity: MvpActivity, viewCreator: ViewCreator) : MainView(mvpActivity, viewCreator) {


    override fun showHomeView() {

    }

    override fun handlerClickMenu(action: ActionMenu): Boolean {
         when (action) {
            else ->return false
        }
    }

    override fun initLayoutView() {
    }

}