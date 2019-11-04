package vn.minerva.travinh.func.news.presentation.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import com.google.gson.annotations.SerializedName

class NewsViewModel(
    var categoryId: Int,
    var categoryName: String,
    var newsId: Int,
    var name: String,
    var desc: String,
    var thumbnail: String,
    var createdAt: String? = null,
    var detailUrl: String

):ViewModel