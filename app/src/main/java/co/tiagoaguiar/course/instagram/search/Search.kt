package co.tiagoaguiar.course.instagram.search

import co.tiagoaguiar.course.instagram.common.base.BasePresenter
import co.tiagoaguiar.course.instagram.common.base.BaseView
import co.tiagoaguiar.course.instagram.common.model.User

interface Search {

    interface Presenter : BasePresenter {
        fun fetchUsers(name: String)
    }

    interface  View : BaseView<Presenter> {
        fun showProgress(enabled: Boolean)
        fun displayFullUsers(users: List<User>)
        fun displayEmptyUsers()
    }

}