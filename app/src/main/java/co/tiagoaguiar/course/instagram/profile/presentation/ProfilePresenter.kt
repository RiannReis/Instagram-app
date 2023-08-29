package co.tiagoaguiar.course.instagram.profile.presentation

import co.tiagoaguiar.course.instagram.common.base.RequestCallback
import co.tiagoaguiar.course.instagram.common.model.Database
import co.tiagoaguiar.course.instagram.common.model.Post
import co.tiagoaguiar.course.instagram.common.model.UserAuth
import co.tiagoaguiar.course.instagram.profile.Profile
import co.tiagoaguiar.course.instagram.profile.model.ProfileRepository

class ProfilePresenter(
    private var view: Profile.View?,
    private val repository: ProfileRepository
) : Profile.Presenter {

    var user: UserAuth? = null
    var post: List<Post>? = null

    override fun subscribe(state: Profile.State?) {
        post = state?.fetchUserPosts()

        if (post != null) {
            if (post!!.isEmpty()) {
                view?.displayEmptyPosts()
            } else {
                view?.displayFullPosts(post!!)
            }
        } else {
            val userUUID = Database.sessionAuth?.userId ?: throw RuntimeException("user not found")
            repository.fetchUserPosts(userUUID, object : RequestCallback<List<Post>> {
                override fun onSuccess(data: List<Post>) {
                    post = data
                    if (data.isEmpty()) {
                        view?.displayEmptyPosts()
                    } else {
                        view?.displayFullPosts(data)
                    }
                }

                override fun onFailure(message: String) {
                    view?.displayRequestFailure(message)
                }

                override fun onComplete() {
                    view?.showProgress(false)
                }
            })
        }

        user = state?.fetchUserProfile()
        if (user != null) {
            view?.displayUserProfile(user!!)
        } else {
            view?.showProgress(true)
            val userUUID = Database.sessionAuth?.userId ?: throw RuntimeException("user not found")
            repository.fetchUserProfile(userUUID, object : RequestCallback<UserAuth> {
                override fun onSuccess(data: UserAuth) {
                    user = data
                    view?.displayUserProfile(data)
                }

                override fun onFailure(message: String) {
                    view?.displayRequestFailure(message)
                }

                override fun onComplete() {
                }
            })
        }
    }

    override fun getState(): Profile.State {
        return ProfileState(user, post)
    }

//    override fun fetchUserProfile() {
//        view?.showProgress(true)
//        val userUUID = Database.sessionAuth?.userId ?: throw RuntimeException("user not found")
//        repository.fetchUserProfile(userUUID, object : RequestCallback<UserAuth> {
//            override fun onSuccess(data: UserAuth) {
//                state = data
//                view?.displayUserProfile(data)
//            }
//
//            override fun onFailure(message: String) {
//                view?.displayRequestFailure(message)
//            }
//
//            override fun onComplete() {
//            }
//        })
//    }
//
//    override fun fetchUserPosts() {
//        val userUUID = Database.sessionAuth?.userId ?: throw RuntimeException("user not found")
//        repository.fetchUserPosts(userUUID, object : RequestCallback<List<Post>> {
//            override fun onSuccess(data: List<Post>) {
//                if (data.isEmpty()) {
//                    view?.displayEmptyPosts()
//                } else {
//                    view?.displayFullPosts(data)
//                }
//            }
//
//            override fun onFailure(message: String) {
//                view?.displayRequestFailure(message)
//            }
//
//            override fun onComplete() {
//                view?.showProgress(false)
//            }
//        })
//    }

    override fun onDestroy() {
        view = null
    }
}