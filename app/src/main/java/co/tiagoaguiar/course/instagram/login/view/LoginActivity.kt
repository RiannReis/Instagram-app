package co.tiagoaguiar.course.instagram.login.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import co.tiagoaguiar.course.instagram.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_login)

    val editTextEmail = findViewById<TextInputEditText>(R.id.login_edit_email)
    val editTextPassword = findViewById<TextInputEditText>(R.id.login_edit_password)

    editTextEmail.addTextChangedListener(watcher)
    editTextPassword.addTextChangedListener(watcher)

    findViewById<Button>(R.id.login_btn_enter).setOnClickListener {

      findViewById<TextInputLayout>(R.id.login_edit_email_input)
        .error = "e-mail inválido!"

      findViewById<TextInputLayout>(R.id.login_edit_password_input)
        .error = "senha incorreta!"
    }

  }

  private val watcher = object: TextWatcher{
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
      findViewById<Button>(R.id.login_btn_enter).isEnabled = s.toString().isNotEmpty()
    }

    override fun afterTextChanged(s: Editable?) {

    }

  }
}