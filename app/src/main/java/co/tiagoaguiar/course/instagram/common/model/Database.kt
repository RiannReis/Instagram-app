package co.tiagoaguiar.course.instagram.common.model

import android.net.Uri
import java.io.File
import java.util.*

object Database {

    val usersAuth = mutableListOf<UserAuth>()
    val posts = hashMapOf<String, MutableSet<Post>>()
    val feed = hashMapOf<String, MutableSet<Post>>()
    val followers = hashMapOf<String, MutableSet<String>>()

    var sessionAuth: UserAuth? = null

    init {
        val userA = UserAuth(
            UUID.randomUUID().toString(),
            "userA",
            "userA@gmail.com",
            "12345678",
            Uri.fromFile(File("storage/emulated/0/Android/media/co.tiagoaguiar.course.instagram/Instagram/2023-09-01-18-23-18-534.jpg"))
        )
        val userB = UserAuth(
            UUID.randomUUID().toString(),
            "userB",
            "userB@gmail.com",
            "87654321",
            Uri.fromFile(File("storage/emulated/0/Android/media/co.tiagoaguiar.course.instagram/Instagram/2023-09-01-18-23-18-534.jpg"))
        )

        usersAuth.add(userA)
        usersAuth.add(userB)

        followers[userA.userId] = hashSetOf()
        posts[userA.userId] = hashSetOf()
        feed[userA.userId] = hashSetOf()

        followers[userB.userId] = hashSetOf()
        posts[userB.userId] = hashSetOf()
        feed[userB.userId] = hashSetOf()

        for (i in 0..30) {
            val user = UserAuth(UUID.randomUUID().toString(), "User$i", "user$i@gmail.com", "123123123", null)
            usersAuth.add(user)
        }

        //sessionAuth = usersAuth.first()

        //followers[sessionAuth!!.userId]?.add(usersAuth[2].userId)
    }
}