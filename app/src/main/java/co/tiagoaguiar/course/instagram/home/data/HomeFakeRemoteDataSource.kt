package co.tiagoaguiar.course.instagram.home.data

import android.os.Handler
import android.os.Looper
import co.tiagoaguiar.course.instagram.common.base.RequestCallback
import co.tiagoaguiar.course.instagram.common.model.Database
import co.tiagoaguiar.course.instagram.common.model.Post
import co.tiagoaguiar.course.instagram.common.model.UserAuth

class HomeFakeRemoteDataSource : HomeDataSource {
    override fun fetchFeed(userUUID: String, callback: RequestCallback<List<Post>>) {
        Handler(Looper.getMainLooper()).postDelayed({

            val feed = Database.feed[userUUID]

            callback.onSuccess(feed?.toList() ?: emptyList())

            callback.onComplete()
        }, 2000)
    }
}