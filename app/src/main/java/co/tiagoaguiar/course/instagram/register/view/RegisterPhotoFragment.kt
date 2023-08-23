package co.tiagoaguiar.course.instagram.register.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.common.view.CustomDialog
import co.tiagoaguiar.course.instagram.databinding.FragmentRegisterPhotoBinding

class RegisterPhotoFragment : Fragment(R.layout.fragment_register_photo) {

    private var binding: FragmentRegisterPhotoBinding? = null
    private var fragmentAttachListener: FragmentAttachListener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegisterPhotoBinding.bind(view)

        binding?.let {
            with(it) {
                registerBtnJump.setOnClickListener {
                    fragmentAttachListener?.goToMainScreen()
                }

                    registerBtnPhoto.isEnabled = true
                    registerBtnPhoto.setOnClickListener {

                        val customDialog = CustomDialog(requireContext())

                        customDialog.addButton(R.string.photo, R.string.gallery) {
                            when (it.id) {
                                R.string.photo -> {
                                    Log.i("teste", "foto")
                                }
                                R.string.gallery -> {
                                    Log.i("teste", "galeria")
                                }
                            }
                        }
                        customDialog.show()

                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentAttachListener) {
            fragmentAttachListener = context
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

}