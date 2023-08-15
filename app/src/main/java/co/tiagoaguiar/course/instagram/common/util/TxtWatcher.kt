package co.tiagoaguiar.course.instagram.common.util

import android.text.Editable
import android.text.TextWatcher

class TxtWatcher(val onTextChanged: (String) -> Unit) : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        TODO("Not yet implemented")
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        onTextChanged.toString()
    }

    override fun afterTextChanged(s: Editable?) {
        TODO("Not yet implemented")
    }
}