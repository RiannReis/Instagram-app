package co.tiagoaguiar.course.instagram.common.model

data class UserAuth(
    val userId: String,
    val name: String,
    val email: String,
    val password: String,
    val postCount: Int = 0,
    val followingCount: Int = 0,
    val followersCount: Int = 0
)
