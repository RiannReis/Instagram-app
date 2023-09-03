package co.tiagoaguiar.course.instagram.search

import co.tiagoaguiar.course.instagram.common.base.BasePresenter
import co.tiagoaguiar.course.instagram.common.base.BaseView

interface Search {

    interface Presenter : BasePresenter {

    }

    interface  View : BaseView<Presenter> {

    }

}