package vn.silverbot99.core.base.presentation.mvp.android.list

import android.view.View

interface OnItemRvClickedListener<in D> {
    fun onItemClicked(view: View, position: Int, dataItem: D)
}