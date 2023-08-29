package co.tiagoaguiar.course.instagram.profile.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.common.base.BaseFragment
import co.tiagoaguiar.course.instagram.common.base.DependencyInjector
import co.tiagoaguiar.course.instagram.common.model.Post
import co.tiagoaguiar.course.instagram.common.model.UserAuth
import co.tiagoaguiar.course.instagram.databinding.FragmentProfileBinding
import co.tiagoaguiar.course.instagram.profile.Profile
import co.tiagoaguiar.course.instagram.profile.presentation.ProfilePresenter
import co.tiagoaguiar.course.instagram.profile.presentation.ProfileState

class ProfileFragment : BaseFragment<FragmentProfileBinding, Profile.Presenter>(
        R.layout.fragment_profile,
        FragmentProfileBinding::bind
), Profile.View{

    override lateinit var presenter: Profile.Presenter

    private val adapter = PostAdapter()

    override fun setupViews() {
        binding?.profileRv?.layoutManager = GridLayoutManager(requireContext(), 3)
        binding?.profileRv?.adapter = adapter

//        presenter.fetchUserProfile()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.subscribe(
            if (savedInstanceState != null) {
                ProfileState(
                    savedInstanceState.getParcelable("user"),
                    (savedInstanceState.getParcelableArray("posts") as Array<Post>).toList()
                )
            } else {
                null
            }
        )
    }

//    override fun onViewStateRestored(savedInstanceState: Bundle?) {
//        if (savedInstanceState != null) {
//            val state = savedInstanceState.getParcelable<UserAuth?>("myState")
//            state?.let {
//                displayUserProfile(it)
//            }
//        }
//        super.onViewStateRestored(savedInstanceState)
//    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable("user", presenter.getState().fetchUserProfile())
        outState.putParcelableArray("posts", presenter.getState().fetchUserPosts()?.toTypedArray())
        super.onSaveInstanceState(outState)
    }

    override fun setupPresenter() {
        val repository = DependencyInjector.profileRepository()
        presenter = ProfilePresenter(this, repository)
    }

    override fun getMenu(): Int? {
        return R.menu.menu_profile
    }

    override fun showProgress(enabled: Boolean) {
        binding?.profileProgress?.visibility = if (enabled) View.VISIBLE else View.GONE
    }

    override fun displayUserProfile(userAuth: UserAuth) {
        binding?.profileTxtPostsCount?.text = userAuth.postCount.toString()
        binding?.profileTxtFollowingCount?.text = userAuth.followingCount.toString()
        binding?.profileTxtFollowersCount?.text = userAuth.followersCount.toString()
        binding?.profileTxtUsername?.text = userAuth.name
        binding?.profileTxtBio?.text = "TODO"
//        presenter.fetchUserPosts()
    }

    override fun displayRequestFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun displayEmptyPosts() {
        binding?.profileTxtEmpty?.visibility = View.VISIBLE
        binding?.profileRv?.visibility = View.GONE
    }

    override fun displayFullPosts(posts: List<Post>) {
        binding?.profileTxtEmpty?.visibility = View.GONE
        binding?.profileRv?.visibility = View.VISIBLE
        adapter.items = posts
        adapter.notifyDataSetChanged()
    }

}