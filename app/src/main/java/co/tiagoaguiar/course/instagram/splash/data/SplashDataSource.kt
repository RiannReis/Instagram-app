package co.tiagoaguiar.course.instagram.splash.data

interface SplashDataSource {
    fun session(callback: SplashCallback)
}