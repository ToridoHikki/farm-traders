package vn.minerva.travinh.func.news.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import kotlinex.number.getValueOrDefaultIsZero
import kotlinex.string.getValueOrDefaultIsEmpty
import vn.minerva.core.base.domain.mapper.Mapper
import vn.minerva.travinh.app.data.network.response.NewsResponse
import vn.minerva.travinh.func.news.presentation.model.NewsViewModel

class NewsMapper : Mapper<NewsResponse, MutableList<ViewModel>> {
    override fun map(input: NewsResponse): MutableList<ViewModel> {
        val list: MutableList<ViewModel> = mutableListOf<ViewModel>()
        input.newsList.map{
            list.add(
                NewsViewModel(
                    categoryId = it.categoryId.getValueOrDefaultIsZero(),
                    categoryName = it.categoryName.getValueOrDefaultIsEmpty(),
                    newsId = it.newsId.getValueOrDefaultIsZero(),
                    name = it.name.getValueOrDefaultIsEmpty(),
                    desc = it.desc.getValueOrDefaultIsEmpty(),
                    createdAt = it.createdAt.getValueOrDefaultIsEmpty(),
                    thumbnail = it.thumbnail.getValueOrDefaultIsEmpty(),
                    detailUrl = it.detailUrl.getValueOrDefaultIsEmpty()
                )
            )
        }
        return list
    }

}