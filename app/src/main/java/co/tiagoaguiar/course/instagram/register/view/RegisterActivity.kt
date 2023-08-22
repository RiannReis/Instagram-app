package co.tiagoaguiar.course.instagram.register.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.databinding.ActivityRegisterBinding
import co.tiagoaguiar.course.instagram.register.view.RegisterNamePasswordFragment.Companion.KEY_EMAIL

class RegisterActivity : AppCompatActivity(), FragmentAttachListener {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val fragment = RegisterEmailFragment()
        replaceFragment(fragment)

    }

    override fun goToNameAndPasswordScreen(email: String) {
        //METODO 1
//        val args = Bundle()
//        args.putString(KEY_EMAIL, email)
//        val fragment = RegisterNamePasswordFragment()
//        fragment.arguments = args

        //METODO 2
        val fragment = RegisterNamePasswordFragment().apply {
            arguments = Bundle().apply {
                putString(KEY_EMAIL, email)
            }
        }

        replaceFragment(fragment)
    }

    private fun replaceFragment(fragment: Fragment) {
        if (supportFragmentManager.findFragmentById(R.id.register_fragment) == null) {
            supportFragmentManager.beginTransaction().apply {
                add(R.id.register_fragment, fragment)
                commit()
            }
        } else {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.register_fragment, fragment)
                addToBackStack(null)
                commit()
            }
        }
    }
}