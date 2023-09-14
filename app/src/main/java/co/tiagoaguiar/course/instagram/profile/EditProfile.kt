package co.tiagoaguiar.course.instagram.profile

import android.net.Uri
import androidx.annotation.StringRes
import co.tiagoaguiar.course.instagram.common.base.BasePresenter
import co.tiagoaguiar.course.instagram.common.base.BaseView
import co.tiagoaguiar.course.instagram.common.model.User

interface EditProfile {

    interface Presenter : BasePresenter {
        fun fetchUserInfos(userId: String?)
        fun editUserInfos(userId: String?, name: String, bio: String?)
        fun changePhoto(photoUri: Uri)
        fun clear()
    }

    interface View : BaseView<Presenter> {
        fun showProgress(enabled: Boolean)
        fun onUpdateFailure(message: String)
        fun onUpdateSuccess(usr: Pair<User, Boolean?>)
        fun displayEmptyScreen()
        fun displayFullScreen(usr: Pair<User, Boolean?>)
        fun displayEditProfileUserFailure(@StringRes editUserError: Int?)
        fun onEditProfileUserFailure(message: String)
        fun goToMainScreen()
        fun editProfileUpdated()
    }

}