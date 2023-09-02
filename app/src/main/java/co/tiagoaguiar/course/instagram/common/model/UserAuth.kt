package co.tiagoaguiar.course.instagram.common.model

import android.net.Uri

data class UserAuth(
    val userId: String,
    val name: String,
    val email: String,
    val password: String,
    val photoUri: Uri?,
    val postCount: Int = 0,
    val followingCount: Int = 0,
    val followersCount: Int = 0
)
