package co.tiagoaguiar.course.instagram.profile.presentation

import co.tiagoaguiar.course.instagram.common.base.RequestCallback
import co.tiagoaguiar.course.instagram.common.model.Post
import co.tiagoaguiar.course.instagram.common.model.User
import co.tiagoaguiar.course.instagram.profile.Profile
import co.tiagoaguiar.course.instagram.profile.model.ProfileRepository

class ProfilePresenter(
    private var view: Profile.View?,
    private val repository: ProfileRepository
) : Profile.Presenter {


    override fun fetchUserProfile(userId: String?) {
        view?.showProgress(true)
        repository.fetchUserProfile(userId, object : RequestCallback<Pair<User, Boolean?>> {
            override fun onSuccess(data: Pair<User, Boolean?>) {
                view?.displayUserProfile(data)
            }

            override fun onFailure(message: String) {
                view?.displayRequestFailure(message)
            }

            override fun onComplete() {
            }
        })
    }

    override fun fetchUserPosts(userId: String?) {
        repository.fetchUserPosts(userId, object : RequestCallback<List<Post>> {
            override fun onSuccess(data: List<Post>) {
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

    override fun followUser(userUUID: String?, follow: Boolean) {
        repository.followUser(userUUID, follow, object : RequestCallback<Boolean> {
            override fun onSuccess(data: Boolean) {
                fetchUserProfile(userUUID)

                if (data) {
                    view?.followUpdated()
                }
            }

            override fun onFailure(message: String) {}

            override fun onComplete() {}

        })
    }

    override fun onDestroy() {
        view = null
    }

    override fun clear() {
        repository.clearCache()
    }
}