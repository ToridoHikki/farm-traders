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
        lstArea.add(MenuViewModel(menuResourceProvider.getMenuCompany(), icon = menuResourceProvider.getIconMenuCompany(), action = ActionMenu.ACTION_MENU_MANAGER_COMPANY, isGroup = false,isSelected = false))
        lstArea.add(MenuViewModel(menuResourceProvider.getMenuGasoline(), icon = menuResourceProvider.getIconMenuGasoline(), action = ActionMenu.ACTION_MENU_MANAGER_GAS_STATION, isGroup = false,isSelected = false))
        lstArea.add(MenuViewModel(menuResourceProvider.getMenuMedical(), icon =  menuResourceProvider.getIconMenuMedical(), action = ActionMenu.ACTION_MENU_MANAGER_CLINICS, isGroup = false,isSelected = false))
        lstArea.add(MenuViewModel(menuResourceProvider.getMenuInspect(), icon = menuResourceProvider.getIconMenuInspect(), action = ActionMenu.ACTION_GROUP_VIEWS, isGroup = true,isSelected = false))

        lstArea.add(MenuViewGroupsModel(menuResourceProvider.getMenuGeometry(), icon = 0, action = ActionMenu.ACTION_GROUP_VIEWS, isGroup = true,isSelected = false))
        lstArea.add(MenuViewModel(menuResourceProvider.getMenuStatistical(), icon = menuResourceProvider.getIconMenuStatistical(), action = ActionMenu.ACTION_MENU_GEOGRAPHY_STATISTICS, isGroup = true,isSelected = false))
        lstArea.add(MenuViewModel(menuResourceProvider.getMenuDistrict(), icon = menuResourceProvider.getIconMenuDistrict(), action = ActionMenu.ACTION_MENU_GEOGRAPHY_DISTRICT, isGroup = true,isSelected = false))
        lstArea.add(MenuViewModel(menuResourceProvider.getMenuWard(), icon = menuResourceProvider.getIconMenuDistrict(), action = ActionMenu.ACTION_MENU_GEOGRAPHY_WARDS, isGroup = true,isSelected = false))

        lstArea.add(MenuViewGroupsModel(menuResourceProvider.getMenuNews(), icon = 0, action = ActionMenu.ACTION_GROUP_VIEWS, isGroup = true,isSelected = false))
        lstArea.add(MenuViewModel(menuResourceProvider.getMenuNews(), icon = menuResourceProvider.getIconMenuNews(), action = ActionMenu.ACTION_MENU_NEWS, isGroup = false,isSelected = false))
        lstArea.add(MenuViewModel(menuResourceProvider.getMenuAbout(), icon = menuResourceProvider.getIconMenuAbout(), action = ActionMenu.ACTION_MENU_CONTACT, isGroup = true,isSelected = false))

        lstArea.add(MenuViewGroupsModel(menuResourceProvider.getMenuSystem(), icon = 0, action = ActionMenu.ACTION_GROUP_VIEWS, isGroup = true,isSelected = false))
        lstArea.add(MenuViewGroupsModel(menuResourceProvider.getMenuLogout(), icon = menuResourceProvider.getIconMenuLogout(), action = ActionMenu.ACTION_MENU_LOGOUT, isGroup = true,isSelected = false))


        view?.renderListMenu(lstArea)
    }
}