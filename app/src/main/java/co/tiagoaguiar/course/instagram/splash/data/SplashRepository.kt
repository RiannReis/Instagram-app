package co.tiagoaguiar.course.instagram.splash.data

class SplashRepository(private val dataSource: SplashDataSource) {
    fun session(callback: SplashCallback){
        dataSource.session(callback)
    }
}