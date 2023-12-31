package co.tiagoaguiar.course.instagram.common.base

import android.content.Context
import co.tiagoaguiar.course.instagram.add.data.AddFakeRemoteDataSource
import co.tiagoaguiar.course.instagram.add.data.AddLocalDataSource
import co.tiagoaguiar.course.instagram.add.data.AddRepository
import co.tiagoaguiar.course.instagram.add.data.FireAddDataSource
import co.tiagoaguiar.course.instagram.home.data.HomeDataSourceFactory
import co.tiagoaguiar.course.instagram.home.data.HomeFeedMemoryCache
import co.tiagoaguiar.course.instagram.home.data.HomeRepository
import co.tiagoaguiar.course.instagram.login.data.FireLoginDataSource
import co.tiagoaguiar.course.instagram.login.data.LoginRepository
import co.tiagoaguiar.course.instagram.post.data.PostLocalDataSource
import co.tiagoaguiar.course.instagram.post.data.PostRepository
import co.tiagoaguiar.course.instagram.profile.model.PostListMemoryCache
import co.tiagoaguiar.course.instagram.profile.model.ProfileDataSourceFactory
import co.tiagoaguiar.course.instagram.profile.model.ProfileMemoryCache
import co.tiagoaguiar.course.instagram.profile.model.ProfileRepository
import co.tiagoaguiar.course.instagram.register.data.FireRegisterDataSource
import co.tiagoaguiar.course.instagram.register.data.RegisterRepository
import co.tiagoaguiar.course.instagram.search.data.FireSearchDataSource
import co.tiagoaguiar.course.instagram.search.data.SearchRepository
import co.tiagoaguiar.course.instagram.splash.data.FireSplashDataSource
import co.tiagoaguiar.course.instagram.splash.data.SplashRepository

object DependencyInjector {
    fun loginRepository(): LoginRepository {
        return LoginRepository(FireLoginDataSource())
    }

    fun registerEmailRepository(): RegisterRepository{
        return RegisterRepository(FireRegisterDataSource())
    }

    fun splashRepository(): SplashRepository {
        return SplashRepository(FireSplashDataSource())
    }

    fun searchRepository(): SearchRepository {
        return SearchRepository(FireSearchDataSource())
    }

    fun profileRepository(): ProfileRepository {
        return ProfileRepository(ProfileDataSourceFactory(ProfileMemoryCache, PostListMemoryCache))
    }

    fun homeRepository(): HomeRepository {
        return HomeRepository(HomeDataSourceFactory(HomeFeedMemoryCache))
    }

    fun addRepository(): AddRepository {
        return AddRepository(FireAddDataSource(), AddLocalDataSource())
    }

    fun postRepository(context: Context): PostRepository {
        return PostRepository(PostLocalDataSource(context))
    }
}