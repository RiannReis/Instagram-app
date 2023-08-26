package co.tiagoaguiar.course.instagram.splash

import co.tiagoaguiar.course.instagram.common.base.BasePresenter
import co.tiagoaguiar.course.instagram.common.base.BaseView

interface Splash {

    interface  Presenter : BasePresenter {
        fun aunthenticated()
    }

    interface View : BaseView<Presenter>{
        fun goToMainScreen()
        fun goToLoginScreen()
    }

}