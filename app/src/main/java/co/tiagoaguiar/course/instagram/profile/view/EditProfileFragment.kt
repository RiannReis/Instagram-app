package co.tiagoaguiar.course.instagram.profile.view

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.common.base.DependencyInjector
import co.tiagoaguiar.course.instagram.common.base.RequestCallback
import co.tiagoaguiar.course.instagram.common.model.User
import co.tiagoaguiar.course.instagram.common.util.TxtWatcher
import co.tiagoaguiar.course.instagram.common.view.CropperImageFragment
import co.tiagoaguiar.course.instagram.common.view.CustomDialog
import co.tiagoaguiar.course.instagram.databinding.FragmentEditProfileBinding
import co.tiagoaguiar.course.instagram.profile.EditProfile
import co.tiagoaguiar.course.instagram.profile.presentation.EditProfilePresenter
import com.bumptech.glide.Glide

class EditProfileFragment : Fragment(R.layout.fragment_edit_profile), EditProfile.View {

    companion object {
        private val REQUIRED_PERMISSION =
            arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    private var userId: String? = null
    private var binding: FragmentEditProfileBinding? = null
    private var fragmentAttachListener: FragmentAttachEditProfileListener? = null
    private var editProfileListener: EditProfileListener? = null

    override lateinit var presenter: EditProfile.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragmentResultListener("cropKey") { requestKey, bundle ->
            val uri = bundle.getParcelable<Uri>(CropperImageFragment.KEY_URI)
            onCropImageResult(uri)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentEditProfileBinding.bind(view)

        val repository = DependencyInjector.profileRepository()
        presenter = EditProfilePresenter(this, repository)

        binding?.let {
            with(it) {
                editProfileBtnCancel.setOnClickListener {
                    parentFragmentManager.popBackStack()
                }
                editProfileBtnSave.setOnClickListener {
                    presenter.editUserInfos(userId, editProfileTxtUsername.text.toString(), editProfileTxtBio.text.toString())
                }
                photoUpdateBtn.setOnClickListener {
                    openDialog()
                }

                editProfileTxtUsername.addTextChangedListener(watcher)
                editProfileTxtUsername.addTextChangedListener(TxtWatcher{
                    displayEditProfileUserFailure(null)
                })

            }
        }

        presenter.fetchUserInfos(userId)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentAttachEditProfileListener) {
            fragmentAttachListener = context
        }
        if (context is EditProfileListener) {
            editProfileListener = context
        }
    }

    override fun showProgress(enabled: Boolean) {
        binding?.editProfileProgress?.visibility = if (enabled) View.VISIBLE else View.GONE
    }

    override fun onUpdateFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun onUpdateSuccess(userPic: Pair<User, Boolean?>) {
        val (user, bool) = userPic

        Toast.makeText(requireContext(), "Foto atualizada com sucesso", Toast.LENGTH_SHORT).show()
        binding?.let {
            Glide.with(requireContext()).load(user.photoUrl).into(it.profileImgIcon)
        }

    }

    override fun goToMainScreen() {
        fragmentAttachListener?.goToMainScreen()
    }

    override fun displayEditProfileUserFailure(editUserError: Int?) {
        binding?.editProfileTxtUsername?.error = editUserError?.let { getString(it) }
    }

    override fun onEditProfileUserFailure(message: String) {
        binding?.editProfileTxtUsername?.error = message
    }

    override fun displayEmptyScreen() {
        binding?.editProfileTxtEmpty?.visibility = View.VISIBLE
        binding?.editContainer?.visibility = View.GONE
    }

    override fun displayFullScreen(usr: Pair<User, Boolean?>) {
        val (user, bool) = usr
        binding?.editProfileTxtEmpty?.visibility = View.GONE
        binding?.editContainer?.visibility = View.VISIBLE
        binding?.editProfileTxtUsername?.setText(user.name)
        binding?.editProfileTxtBio?.setText(user.bio)

        binding?.let {
            Glide.with(requireContext()).load(user.photoUrl).into(it.profileImgIcon)
        }
    }

    override fun editProfileUpdated() {
        editProfileListener?.editProfileUpdated()
    }

    override fun onDestroy() {
        binding = null
        fragmentAttachListener = null
        presenter.onDestroy()
        super.onDestroy()
    }

    private fun openDialog() {
        val customDialog = CustomDialog(requireContext())

        customDialog.addButton(R.string.photo, R.string.gallery) {
            when (it.id) {
                R.string.photo -> {
                    if (allPermissionsGranted()) {
                        fragmentAttachListener?.goToCameraScreen()
                    } else {
                        getPermission.launch(REQUIRED_PERMISSION)
                    }
                }
                R.string.gallery -> {
                    fragmentAttachListener?.goToGalleryScreen()
                }
            }
        }
        customDialog.show()
    }

    private fun allPermissionsGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            REQUIRED_PERMISSION[0]
        ) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
            requireContext(),
            REQUIRED_PERMISSION[1]
        ) == PackageManager.PERMISSION_GRANTED
    }

    private val getPermission =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { granted ->
            if (allPermissionsGranted()) {
                fragmentAttachListener?.goToCameraScreen()
            } else {
                Toast.makeText(
                    requireContext(),
                    R.string.permission_camera_denied,
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    private fun onCropImageResult(uri: Uri?) {
        if (uri != null) {
            val bitmap = if (Build.VERSION.SDK_INT >= 28) {
                val source = ImageDecoder.createSource(requireContext().contentResolver, uri)
                ImageDecoder.decodeBitmap(source)
            } else {
                MediaStore.Images.Media.getBitmap(requireContext().contentResolver, uri)
            }
            binding?.profileImgIcon?.setImageBitmap(bitmap)

            presenter.changePhoto(uri)
        }
    }

    private val watcher = TxtWatcher {
        binding?.editProfileBtnSave?.isEnabled = binding?.editProfileTxtUsername?.text.toString().isNotEmpty()
    }

    interface EditProfileListener {
        fun editProfileUpdated()
    }
}