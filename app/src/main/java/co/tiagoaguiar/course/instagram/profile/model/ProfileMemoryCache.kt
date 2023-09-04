package co.tiagoaguiar.course.instagram.profile.model

import co.tiagoaguiar.course.instagram.common.base.Cache
import co.tiagoaguiar.course.instagram.common.model.UserAuth

object ProfileMemoryCache : Cache<Pair<UserAuth, Boolean?>> {

    private var userAuth: Pair<UserAuth, Boolean?>? = null

    override fun isCached(): Boolean {
        return userAuth != null
    }

    override fun get(key: String): Pair<UserAuth, Boolean?>? {
        if (userAuth?.first?.userId == key) {
            return userAuth
        }
        return null
    }

    override fun put(data: Pair<UserAuth, Boolean?>?) {
        userAuth = data
    }

}