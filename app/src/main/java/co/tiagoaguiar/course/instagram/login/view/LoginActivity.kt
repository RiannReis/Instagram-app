package co.tiagoaguiar.course.instagram.login.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.databinding.ActivityLoginBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {

  private lateinit var binding: ActivityLoginBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityLoginBinding.inflate(layoutInflater)

    setContentView(binding.root)

    binding.loginEditEmail.addTextChangedListener(watcher)
    binding.loginEditPassword.addTextChangedListener(watcher)

    binding.loginBtnEnter.setOnClickListener {

      binding.loginBtnEnter.showProgress(true)

      binding.loginEditEmailInput.error = "e-mail inválido!"

      binding.loginEditPasswordInput.error = "senha incorreta!"

      Handler(Looper.getMainLooper()).postDelayed({
        binding.loginBtnEnter.showProgress(false)
      }, 2000)
    }

  }

  private val watcher = object: TextWatcher{
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
      binding.loginBtnEnter.isEnabled = s.toString().isNotEmpty()
    }

    override fun afterTextChanged(s: Editable?) {

    }

  }
}