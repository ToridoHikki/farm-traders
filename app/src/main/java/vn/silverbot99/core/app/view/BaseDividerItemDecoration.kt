package vn.silverbot99.core.app.view

import android.support.v7.widget.RecyclerView

abstract class BaseDividerItemDecoration : RecyclerView.ItemDecoration() {
    var startPositionOfNormalType: Int = -1
    abstract fun reset()
}