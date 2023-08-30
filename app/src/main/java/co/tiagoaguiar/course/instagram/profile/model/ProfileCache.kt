package co.tiagoaguiar.course.instagram.profile.model

interface ProfileCache<T> {
    fun isCached(): Boolean
    fun get(key: String): T?
    fun put(data: T)
}