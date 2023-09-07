package co.tiagoaguiar.course.instagram.login.data

import com.google.firebase.auth.FirebaseAuth

class FireLoginDataSource : LoginDataSource {
    override fun login(email: String, password: String, callback: LoginCallback) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { response ->
                if (response != null) {
                    callback.onSuccess()
                } else {
                    callback.onFailure("Usuário não encontrado")
                }
            }
            .addOnFailureListener { exception ->
                callback.onFailure(exception.message ?: "Erro ao fazer login")
            }
            .addOnCompleteListener {
                callback.onComplete()
            }
    }
}