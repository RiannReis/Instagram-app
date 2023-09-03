package co.tiagoaguiar.course.instagram.search.data

import co.tiagoaguiar.course.instagram.common.base.RequestCallback
import co.tiagoaguiar.course.instagram.common.model.UserAuth

interface SearchDataSource {

    fun fetchUsers(name: String, callback: RequestCallback<List<UserAuth>>)
}