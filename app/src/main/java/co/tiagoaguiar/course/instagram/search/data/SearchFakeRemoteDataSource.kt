package co.tiagoaguiar.course.instagram.search.data

import android.os.Handler
import android.os.Looper
import co.tiagoaguiar.course.instagram.common.base.RequestCallback
import co.tiagoaguiar.course.instagram.common.model.Database
import co.tiagoaguiar.course.instagram.common.model.UserAuth

class SearchFakeRemoteDataSource : SearchDataSource {

    override fun fetchUsers(name: String, callback: RequestCallback<List<UserAuth>>) {
        Handler(Looper.getMainLooper()).postDelayed({

            val users = Database.usersAuth.filter {
                it.name.lowercase()
                    .startsWith(name.lowercase()) && it.userId != Database.sessionAuth!!.userId
            }

            callback.onSuccess(users.toList())

            callback.onComplete()
        }, 2000)
    }
}