package vn.silverbot99.farm_traders.func.main.presentation.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import vn.silverbot99.farm_traders.func.main.data.ActionMenu

open class MenuViewModel(var name: String, var icon: Int, var isSelected: Boolean,
                         var isGroup: Boolean, var action: ActionMenu) : ViewModel