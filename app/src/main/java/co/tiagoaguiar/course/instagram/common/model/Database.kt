package co.tiagoaguiar.course.instagram.common.model

import java.util.*

object Database {

    val usersAuth = hashSetOf<UserAuth>()
    val photos = hashSetOf<Photo>()
    val posts = hashMapOf<String, MutableSet<Post>>()
    val feed = hashMapOf<String, MutableSet<Post>>()
    val followers = hashMapOf<String, Set<String>>()

    var sessionAuth: UserAuth? = null

    init {
        val userA = UserAuth(UUID.randomUUID().toString(), "userA", "userA@gmail.com", "12345678")
        val userB = UserAuth(UUID.randomUUID().toString(), "userB", "userB@gmail.com", "87654321")

        usersAuth.add(userA)
        usersAuth.add(userB)

        followers[userA.userId] = hashSetOf()
        posts[userA.userId] = hashSetOf()
        feed[userA.userId] = hashSetOf()

        followers[userB.userId] = hashSetOf()
        posts[userB.userId] = hashSetOf()
        feed[userB.userId] = hashSetOf()

        sessionAuth = usersAuth.first()
    }
}