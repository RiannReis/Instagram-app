package co.tiagoaguiar.course.instagram.profile.model

import co.tiagoaguiar.course.instagram.common.base.RequestCallback
import co.tiagoaguiar.course.instagram.common.model.Post
import co.tiagoaguiar.course.instagram.common.model.UserAuth

class ProfileRepository(private val dataSourceFactory: ProfileDataSourceFactory) {

    fun fetchUserProfile(userId: String?, callback: RequestCallback<Pair<UserAuth, Boolean?>>) {
        val localDataSource = dataSourceFactory.createLocalDataSource()
        val uId = userId ?: localDataSource.fetchSession().userId

        val dataSource = dataSourceFactory.createFromUser(userId)
        dataSource.fetchUserProfile(uId, object : RequestCallback<Pair<UserAuth, Boolean?>> {
            override fun onSuccess(data: Pair<UserAuth, Boolean?>) {
                if (uId == null) {
                    localDataSource.putUser(data)
                }
                callback.onSuccess(data)
            }

            override fun onFailure(message: String) {
                callback.onFailure(message)
            }

            override fun onComplete() {
                callback.onComplete()
            }
        })
    }

    fun fetchUserPosts(userId: String?, callback: RequestCallback<List<Post>>) {
        val localDataSource = dataSourceFactory.createLocalDataSource()
        val uId = userId ?: localDataSource.fetchSession().userId

        val dataSource = dataSourceFactory.createFromPost(userId)
        dataSource.fetchUserPosts(uId, object : RequestCallback<List<Post>> {
            override fun onSuccess(data: List<Post>) {
                if (uId == null) {
                    localDataSource.putPosts(data)
                }
                callback.onSuccess(data)
            }

            override fun onFailure(message: String) {
                callback.onFailure(message)
            }

            override fun onComplete() {
                callback.onComplete()
            }
        })
    }

    fun clearCache() {
        val localDataSource = dataSourceFactory.createLocalDataSource()
        localDataSource.putPosts(null)
    }

    fun followUser(userUUID: String, isFollow: Boolean, callback: RequestCallback<Boolean>) {
        val dataSource = dataSourceFactory.createRemoteDataSource()
        val localDataSource = dataSourceFactory.createLocalDataSource()
        val uId = userUUID ?: localDataSource.fetchSession().userId

        dataSource.followUser(uId, isFollow, object : RequestCallback<Boolean> {
            override fun onSuccess(data: Boolean) {
                callback.onSuccess(data)
            }

            override fun onFailure(message: String) {
                callback.onFailure(message)
            }

            override fun onComplete() {
                callback.onComplete()
            }
        })

    }
}