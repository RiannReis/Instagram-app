package co.tiagoaguiar.course.instagram.register.data

import co.tiagoaguiar.course.instagram.common.model.UserAuth

interface RegisterEmailCallback {
    fun onSuccess()
    fun onFailure(message: String)
    fun onComplete()
}