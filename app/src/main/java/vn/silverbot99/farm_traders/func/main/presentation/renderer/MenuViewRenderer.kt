package vn.silverbot99.farm_traders.func.main.presentation.renderer

import android.content.Context
import android.view.View
import kotlinex.view.visible
import kotlinx.android.synthetic.main.item_layout_menu_vholder.view.*
import vn.silverbot99.core.base.presentation.mvp.android.model.ViewRenderer
import vn.silverbot99.farm_traders.R
import vn.silverbot99.farm_traders.func.main.presentation.model.MenuViewModel

class MenuViewRenderer(context: Context) : ViewRenderer<MenuViewModel>(context) {
    override fun getLayoutId(): Int = R.layout.item_layout_menu_vholder

    override fun getModelClass(): Class<MenuViewModel> = MenuViewModel::class.java

    override fun bindView(model: MenuViewModel, viewRoot: View) {
        viewRoot.isSelected = model.isSelected
        viewRoot.ivIcon.isSelected = model.isSelected
        viewRoot.ivIcon.visible()
        viewRoot.ivIcon.setImageResource(model.icon)
        viewRoot.tvContent.text = model.name
    }
}