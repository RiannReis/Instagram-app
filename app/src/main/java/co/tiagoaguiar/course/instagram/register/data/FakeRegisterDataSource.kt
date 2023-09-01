package co.tiagoaguiar.course.instagram.register.data

import android.net.Uri
import android.os.Handler
import android.os.Looper
import co.tiagoaguiar.course.instagram.common.model.Database
import co.tiagoaguiar.course.instagram.common.model.Photo
import co.tiagoaguiar.course.instagram.common.model.UserAuth
import java.util.*

class FakeRegisterDataSource : RegisterDataSource {
    override fun create(email: String, callback: RegisterCallback) {
        Handler(Looper.getMainLooper()).postDelayed({

            val userAuth = Database.usersAuth.firstOrNull { email == it.email }

            if (userAuth == null) {
                callback.onSuccess()
            } else {
                callback.onFailure("Usuário já cadastrado")
            }
            callback.onComplete()

        }, 2000)
    }

    override fun create(email: String, name: String, password: String, callback: RegisterCallback) {
        Handler(Looper.getMainLooper()).postDelayed({

            val userAuth = Database.usersAuth.firstOrNull { email == it.email }

            if (userAuth != null) {
                callback.onFailure("Usuário já cadastrado")
            } else {
                val newUser = UserAuth(UUID.randomUUID().toString(), name, email, password)
                val created = Database.usersAuth.add(newUser)
                if (created) {
                    Database.sessionAuth = newUser

                    Database.followers[newUser.userId] = hashSetOf()
                    Database.posts[newUser.userId] = hashSetOf()
                    Database.feed[newUser.userId] = hashSetOf()

                    callback.onSuccess()
                } else {
                    callback.onFailure("Erro interno")
                }
            }
            callback.onComplete()

        }, 2000)
    }

    override fun updateUser(photoUri: Uri, callback: RegisterCallback) {
        Handler(Looper.getMainLooper()).postDelayed({

            val userAuth = Database.sessionAuth

            if (userAuth == null) {
                callback.onFailure("Usuário não encontrado")
            } else {
                val newPhoto = Photo(userAuth.userId, photoUri)
                val created = Database.photos.add(newPhoto)
                if (created) {
                    callback.onSuccess()
                } else {
                    callback.onFailure("Erro interno")
                }
            }
            callback.onComplete()

        }, 2000)
    }
}