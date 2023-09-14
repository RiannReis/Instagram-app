package co.tiagoaguiar.course.instagram.profile.presentation

import android.net.Uri
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.common.base.RequestCallback
import co.tiagoaguiar.course.instagram.common.model.User
import co.tiagoaguiar.course.instagram.profile.EditProfile
import co.tiagoaguiar.course.instagram.profile.model.ProfileRepository

class EditProfilePresenter(
    private var view: EditProfile.View?,
    private val repository: ProfileRepository
) : EditProfile.Presenter {


    override fun fetchUserInfos(userId: String?) {
        view?.showProgress(true)
        repository.fetchUserProfile(userId, object : RequestCallback<Pair<User, Boolean?>> {
            override fun onSuccess(data: Pair<User, Boolean?>) {
                view?.displayFullScreen(data)
            }

            override fun onFailure(message: String) {
                view?.displayEmptyScreen()
                view?.onUpdateFailure(message)
            }

            override fun onComplete() {
                view?.showProgress(false)
            }
        })
    }

    override fun editUserInfos(userId: String?, name: String, bio: String?) {
        val isNameValid = name.length >= 3

        if (!isNameValid) {
            view?.displayEditProfileUserFailure(R.string.invalid_name)
        } else {
            view?.displayEditProfileUserFailure(null)
        }

        if (isNameValid) {
            view?.showProgress(true)

            repository.editUserInfos(userId, name, bio, object : RequestCallback<Boolean> {
                override fun onSuccess(data: Boolean) {
                    view?.goToMainScreen()
                    if (data) {
                        view?.editProfileUpdated()
                    }
                }

                override fun onFailure(message: String) {
                    view?.onEditProfileUserFailure(message)
                }

                override fun onComplete() {
                    view?.showProgress(false)
                }
            })
        }
    }

    override fun changePhoto(photoUri: Uri) {
        view?.showProgress(true)
        repository.changePhoto(photoUri, object : RequestCallback<Pair<User, Boolean?>> {
            override fun onSuccess(data:Pair<User, Boolean?>) {
                view?.onUpdateSuccess(data)
            }

            override fun onFailure(message: String) {
                view?.onUpdateFailure(message)
            }

            override fun onComplete() {
                view?.showProgress(false)
            }
        })
    }

    override fun clear() {
        repository.clearCache()
    }

    override fun onDestroy() {
        view = null

    }
}