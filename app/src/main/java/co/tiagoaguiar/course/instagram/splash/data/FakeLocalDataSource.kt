package co.tiagoaguiar.course.instagram.splash.data

import co.tiagoaguiar.course.instagram.common.model.Database

class FakeLocalDataSource : SplashDataSource {
    override fun session(callback: SplashCallback) {
        if (Database.sessionAuth != null) {
            callback.onSuccess()
        } else {
            callback.onFailure()
        }
    }
}