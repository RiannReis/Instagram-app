package co.tiagoaguiar.course.instagram.common.model

import java.util.*

object Database {

    val usersAuth = hashSetOf<UserAuth>()
    val photos = hashSetOf<Photo>()
    val posts = hashMapOf<String, Set<Post>>()
    val feed = hashMapOf<String, Set<Post>>()

    var sessionAuth: UserAuth? = null

    init {
        usersAuth.add(UserAuth(UUID.randomUUID().toString(), "userA", "userA@gmail.com", "12345678"))
        usersAuth.add(UserAuth(UUID.randomUUID().toString(), "userB", "userB@gmail.com", "87654321"))

        sessionAuth = usersAuth.first()
    }
}