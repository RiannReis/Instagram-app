package co.tiagoaguiar.course.instagram.common.model

import android.net.Uri

data class Post(
    val uuid: String? = null,
    val photoUrl: String? = null,
    val caption: String? = null,
    val timestamp: Long = 0,
    val publisher: User? = null
)
