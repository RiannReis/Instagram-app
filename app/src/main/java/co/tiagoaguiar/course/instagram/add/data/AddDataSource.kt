package co.tiagoaguiar.course.instagram.add.data

import android.net.Uri
import co.tiagoaguiar.course.instagram.common.base.RequestCallback
import co.tiagoaguiar.course.instagram.common.model.UserAuth

interface AddDataSource {
    fun createPost(userUUID: String, uri: Uri, caption: String, callback: RequestCallback<Boolean>) {
        throw UnsupportedOperationException()
    }

    fun fetchSession(): UserAuth { throw UnsupportedOperationException() }
}