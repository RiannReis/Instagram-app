package co.tiagoaguiar.course.instagram.login.presentation

import android.util.Patterns
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.login.Login

class LoginPresenter(
    private var view: Login.View?
) : Login.Presenter {

    override fun login(email: String, password: String) {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            view?.displayEmailFailure(R.string.invalid_email)
        } else {
            view?.displayEmailFailure(null)
        }
        if (password.length < 8) {
            view?.displayPasswordFailure(R.string.invalid_password)
        } else {
            view?.displayPasswordFailure(null)
        }

        // aqui o formulario ja está valido, então chama o model

    }

    override fun onDestroy() {
        view = null
    }
}