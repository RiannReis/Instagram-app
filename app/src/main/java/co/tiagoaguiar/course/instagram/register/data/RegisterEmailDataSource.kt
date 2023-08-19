package co.tiagoaguiar.course.instagram.register.data

interface RegisterEmailDataSource {
    fun create (email: String, callback: RegisterEmailCallback)
}