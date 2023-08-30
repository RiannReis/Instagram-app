package co.tiagoaguiar.course.instagram.profile.model

import co.tiagoaguiar.course.instagram.common.model.Post
import co.tiagoaguiar.course.instagram.common.model.UserAuth

class ProfileDataSourceFactory(
    private val profileCache: ProfileCache<UserAuth>,
    private val postsCache: ProfileCache<List<Post>>

) {

    fun createLocalDataSource(): ProfileDataSource {
        return ProfileLocalDataSource(profileCache, postsCache)
    }

    fun createFromUser(): ProfileDataSource {
        if (profileCache.isCached()) {
            return ProfileLocalDataSource(profileCache, postsCache)
        }
        return FakeProfileRemoteDataSource()
    }

    fun createFromPost(): ProfileDataSource {
        if (postsCache.isCached()) {
            return ProfileLocalDataSource(profileCache, postsCache)
        }
        return FakeProfileRemoteDataSource()
    }

}