package co.tiagoaguiar.course.instagram.register

import androidx.annotation.StringRes
import co.tiagoaguiar.course.instagram.common.base.BasePresenter
import co.tiagoaguiar.course.instagram.common.base.BaseView

interface RegisterEmail {

    interface Presenter : BasePresenter{

    }

    interface View : BaseView<Presenter>{
        fun displayEmailFailure(@StringRes emailError: Int?)
    }
}