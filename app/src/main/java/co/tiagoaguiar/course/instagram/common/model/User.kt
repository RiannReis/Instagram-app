package co.tiagoaguiar.course.instagram.common.model

import android.net.Uri

data class User(
    val userId: String? = null,
    val name: String? = null,
    val email: String? = null,
    val bio: String? = null,
    val photoUrl: String? = null,
    val postCount: Int = 0,
    val following: Int = 0,
    val followers: Int = 0
)
