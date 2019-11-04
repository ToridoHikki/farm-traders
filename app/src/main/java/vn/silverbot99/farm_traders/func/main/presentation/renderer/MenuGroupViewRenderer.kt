package vn.silverbot99.farm_traders.func.main.presentation.renderer

import android.content.Context
import android.view.View
import kotlinx.android.synthetic.main.item_layout_menu_vholder.view.*
import vn.silverbot99.core.base.presentation.mvp.android.model.ViewRenderer
import vn.silverbot99.farm_traders.R
import vn.silverbot99.farm_traders.func.main.presentation.model.MenuViewGroupsModel

class MenuGroupViewRenderer(context: Context) : ViewRenderer<MenuViewGroupsModel>(context) {
    override fun getLayoutId(): Int = R.layout.item_layout_menu_groups_vholder

    override fun getModelClass(): Class<MenuViewGroupsModel> = MenuViewGroupsModel::class.java

    override fun bindView(model: MenuViewGroupsModel, viewRoot: View) {
        viewRoot.isSelected = model.isSelected
        viewRoot.tvContent.text = model.name
    }
}