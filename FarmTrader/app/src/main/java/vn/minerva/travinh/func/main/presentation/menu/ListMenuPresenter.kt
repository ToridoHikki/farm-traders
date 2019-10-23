package vn.minerva.travinh.func.main.presentation.menu

import android.graphics.Color
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import vn.minerva.travinh.func.main.data.ActionMenu
import vn.minerva.travinh.func.main.presentation.MainContract
import vn.minerva.travinh.func.main.presentation.model.MenuViewGroupsModel
import vn.minerva.travinh.func.main.presentation.model.MenuViewModel

class ListMenuPresenter(private val menuResourceProvider: ListMenuResourceProvider) : MainContract.MenuPresenter() {
    override fun loadListMenu() {
        val lstArea = mutableListOf<ViewModel>()
        lstArea.add(MenuViewGroupsModel(menuResourceProvider.getMenuManager(), icon = 0, action = ActionMenu.ACTION_GROUP_VIEWS, isGroup = true,isSelected = false))
        lstArea.add(MenuViewModel(menuResourceProvider.getMenuCompany(), icon = menuResourceProvider.getIconMenuCompany(), action = ActionMenu.ACTION_GROUP_VIEWS, isGroup = true,isSelected = false))
        lstArea.add(MenuViewModel(menuResourceProvider.getMenuGasoline(), icon = menuResourceProvider.getIconMenuGasoline(), action = ActionMenu.ACTION_GROUP_VIEWS, isGroup = true,isSelected = false))
        lstArea.add(MenuViewModel(menuResourceProvider.getMenuMedical(), icon =  menuResourceProvider.getIconMenuMedical(), action = ActionMenu.ACTION_GROUP_VIEWS, isGroup = true,isSelected = false))
        lstArea.add(MenuViewModel(menuResourceProvider.getMenuInspect(), icon = menuResourceProvider.getIconMenuInspect(), action = ActionMenu.ACTION_GROUP_VIEWS, isGroup = true,isSelected = false))
        lstArea.add(MenuViewGroupsModel(menuResourceProvider.getMenuGeometry(), icon = 0, action = ActionMenu.ACTION_GROUP_VIEWS, isGroup = true,isSelected = false))
        lstArea.add(MenuViewModel(menuResourceProvider.getMenuManager(), icon = 0, action = ActionMenu.ACTION_GROUP_VIEWS, isGroup = true,isSelected = false))
        lstArea.add(MenuViewModel(menuResourceProvider.getMenuManager(), icon = 0, action = ActionMenu.ACTION_GROUP_VIEWS, isGroup = true,isSelected = false))
        lstArea.add(MenuViewModel(menuResourceProvider.getMenuManager(), icon = 0, action = ActionMenu.ACTION_GROUP_VIEWS, isGroup = true,isSelected = false))
        view?.renderListMenu(lstArea)
    }
}