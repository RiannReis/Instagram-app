package co.tiagoaguiar.course.instagram.add

import co.tiagoaguiar.course.instagram.common.base.BasePresenter
import co.tiagoaguiar.course.instagram.common.base.BaseView

interface Add {

    interface Presenter : BasePresenter {

    }

    interface View : BaseView<Presenter> {

    }
}