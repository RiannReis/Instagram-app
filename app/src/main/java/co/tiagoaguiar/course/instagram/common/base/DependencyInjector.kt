package co.tiagoaguiar.course.instagram.common.base

import co.tiagoaguiar.course.instagram.login.data.FakeDataSource
import co.tiagoaguiar.course.instagram.login.data.LoginRepository
import co.tiagoaguiar.course.instagram.register.data.FakeRegisterDataSource
import co.tiagoaguiar.course.instagram.register.data.RegisterRepository
import co.tiagoaguiar.course.instagram.splash.data.FakeLocalDataSource
import co.tiagoaguiar.course.instagram.splash.data.SplashRepository

object DependencyInjector {
    fun loginRepository(): LoginRepository {
        return LoginRepository(FakeDataSource())
    }

    fun registerEmailRepository(): RegisterRepository{
        return RegisterRepository(FakeRegisterDataSource())
    }

    fun splashRepository(): SplashRepository {
        return SplashRepository(FakeLocalDataSource())
    }
}