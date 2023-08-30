package co.tiagoaguiar.course.instagram.profile.model

import co.tiagoaguiar.course.instagram.common.model.UserAuth

object ProfileMemoryCache : ProfileCache<UserAuth>{

    private var userAuth: UserAuth? = null

    override fun isCached(): Boolean {
        return userAuth != null
    }

    override fun get(key: String): UserAuth? {
        if (userAuth?.userId == key) {
            return userAuth
        }
        return null
    }

    override fun put(data: UserAuth) {
        userAuth = data
    }

}