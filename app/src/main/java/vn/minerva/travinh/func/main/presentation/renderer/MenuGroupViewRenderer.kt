package vn.minerva.travinh.func.main.presentation.renderer

import android.content.Context
import android.view.View
import kotlinx.android.synthetic.main.item_layout_menu_vholder.view.*
import vn.minerva.core.base.presentation.mvp.android.model.ViewRenderer
import vn.minerva.travinh.R
import vn.minerva.travinh.func.main.presentation.model.MenuViewGroupsModel

class MenuGroupViewRenderer(context: Context) : ViewRenderer<MenuViewGroupsModel>(context) {
    override fun getLayoutId(): Int = R.layout.item_layout_menu_groups_vholder

    override fun getModelClass(): Class<MenuViewGroupsModel> = MenuViewGroupsModel::class.java

    override fun bindView(model: MenuViewGroupsModel, viewRoot: View) {
        viewRoot.isSelected = model.isSelected
        viewRoot.tvContent.text = model.name
    }
}