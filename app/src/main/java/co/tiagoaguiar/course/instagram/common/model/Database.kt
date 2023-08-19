package co.tiagoaguiar.course.instagram.common.model

import java.util.*

object Database {

    val usersAuth = hashSetOf<UserAuth>()

    var sessionAuth: UserAuth? = null

    init {
        usersAuth.add(UserAuth(UUID.randomUUID().toString(), "userA@gmail.com", "12345678"))
        usersAuth.add(UserAuth(UUID.randomUUID().toString(), "userB@gmail.com", "87654321"))
    }
}