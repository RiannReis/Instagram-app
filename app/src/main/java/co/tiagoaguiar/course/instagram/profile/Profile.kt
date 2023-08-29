package co.tiagoaguiar.course.instagram.profile

import co.tiagoaguiar.course.instagram.common.base.BasePresenter
import co.tiagoaguiar.course.instagram.common.base.BaseView
import co.tiagoaguiar.course.instagram.common.model.Post
import co.tiagoaguiar.course.instagram.common.model.UserAuth

interface Profile {


    interface StatefulPresenter<S: State>: BasePresenter {
        fun subscribe(state: S?)
        fun getState(): S
    }

    interface State {
        fun fetchUserProfile(): UserAuth?
        fun fetchUserPosts(): List<Post>?
    }

    interface Presenter : StatefulPresenter<State> {

    }

    interface View : BaseView<Presenter> {
        fun showProgress(enabled: Boolean)
        fun displayUserProfile(userAuth: UserAuth)
        fun displayRequestFailure(message: String)
        fun displayEmptyPosts()
        fun displayFullPosts(posts: List<Post>)
    }

}