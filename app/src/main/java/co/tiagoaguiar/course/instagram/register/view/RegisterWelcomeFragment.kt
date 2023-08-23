package co.tiagoaguiar.course.instagram.register.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.common.base.DependencyInjector
import co.tiagoaguiar.course.instagram.common.util.TxtWatcher
import co.tiagoaguiar.course.instagram.databinding.FragmentRegisterNamePasswordBinding
import co.tiagoaguiar.course.instagram.databinding.FragmentRegisterWelcomeBinding
import co.tiagoaguiar.course.instagram.register.RegisterNameAndPassword
import co.tiagoaguiar.course.instagram.register.presentation.RegisterNameAndPasswordPresenter

class RegisterWelcomeFragment : Fragment(R.layout.fragment_register_welcome) {

        private var binding: FragmentRegisterWelcomeBinding? = null
        private var fragmentAttachListener: FragmentAttachListener? = null

        companion object {
            const val KEY_NAME = "key_name"
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            binding = FragmentRegisterWelcomeBinding.bind(view)

            val name =
                arguments?.getString(KEY_NAME) ?: throw IllegalArgumentException("name not found")

            binding?.let {
                with(it) {
                    registerTxtWelcome.text = getString(R.string.welcome_to_instagram, name)

                    registerWelcomeBtnNext.isEnabled = true
                    registerWelcomeBtnNext.setOnClickListener {
                        fragmentAttachListener?.goToPhotoScreen()
                    }
                }
            }
        }

        override fun onAttach(context: Context) {
            super.onAttach(context)
            if(context is FragmentAttachListener){
                fragmentAttachListener = context
            }
        }

        override fun onDestroy() {
            binding = null
            super.onDestroy()
        }
}