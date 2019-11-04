package vn.minerva.travinh.func.main.presentation.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import vn.minerva.travinh.func.main.data.ActionMenu

class MenuViewGroupsModel(var name: String, var icon: Int, var isSelected: Boolean,
                          var isGroup: Boolean, var action: ActionMenu) : ViewModel