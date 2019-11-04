package vn.silverbot99.core.base.presentation.mvp.android.model

import android.view.View
import vn.silverbot99.core.base.presentation.view.ViewAction
import vn.silverbot99.core.base.presentation.view.ViewSize
import vn.silverbot99.core.base.presentation.view.ViewSizer

class HeightViewSizer(val numRow: Int, val padding: Int = 0) : ViewSizer {
    override fun size(viewParentSize: ViewSize): ViewAction {
        return object : ViewAction {
            override fun action(view: View) {
                view.layoutParams.height = (viewParentSize.height / numRow) - (padding * 2)
            }
        }
    }
}