package vn.minerva.travinh.func.main.presentation.menu

import vn.minerva.core.base.domain.provider.AndroidResourceProvider
import vn.minerva.travinh.R

class ListMenuResourceProvider : AndroidResourceProvider() {

    fun getMenuManager(): String {
        return resourceManager.getString(R.string.manager)
    }

    fun getMenuCompany(): String {
        return resourceManager.getString(R.string.company)
    }

    fun getIconMenuCompany(): Int {
        return R.drawable.ic_business
    }

    fun getMenuGasoline(): String {
        return resourceManager.getString(R.string.gasoline)
    }

    fun getIconMenuGasoline():Int {
        return R.drawable.ic_gasoline_black
    }

    fun getMenuMedical(): String {
        return resourceManager.getString(R.string.medical)
    }

    fun getIconMenuMedical(): Int {
        return R.drawable.ic_medical_black
    }

    fun getMenuInspect(): String {
        return resourceManager.getString(R.string.tab_inspect)
    }

    fun getIconMenuInspect():Int {
        return R.drawable.ic_inspection_black
    }

    fun getMenuGeometry(): String {
        return resourceManager.getString(R.string.geometry)
    }

    fun getMenuStatistical(): String {
        return resourceManager.getString(R.string.statistical)
    }

    fun getIconMenuStatistical(): Int {
        return R.drawable.ic_statistical
    }

    fun getMenuDistrict(): String {
        return resourceManager.getString(R.string.district)
    }

    fun getIconMenuDistrict(): Int {
        return R.drawable.ic_region
    }

    fun getMenuWard(): String {
        return resourceManager.getString(R.string.ward)
    }

    fun getMenuNews(): String {
        return resourceManager.getString(R.string.news)
    }

    fun getIconMenuNews(): Int {
        return R.drawable.ic_newspaper
    }

    fun getMenuAbout(): String {
        return resourceManager.getString(R.string.about)
    }

    fun getIconMenuAbout():Int{
        return R.drawable.ic_about
    }

    fun getMenuSystem(): String {
        return resourceManager.getString(R.string.system)
    }

    fun getMenuLogout(): String {
        return resourceManager.getString(R.string.logout)
    }

    fun getIconMenuLogout(): Int{
        return R.drawable.ic_logout_black
    }
}