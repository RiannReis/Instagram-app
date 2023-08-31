package co.tiagoaguiar.course.instagram.add.view

import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.add.Add
import co.tiagoaguiar.course.instagram.common.base.BaseFragment
import co.tiagoaguiar.course.instagram.databinding.FragmentAddBinding
import com.google.android.material.tabs.TabLayoutMediator

class AddFragment : BaseFragment<FragmentAddBinding, Add.Presenter>(
    R.layout.fragment_add,
    FragmentAddBinding::bind
), Add.View {

    override lateinit  var presenter: Add.Presenter

    override fun setupViews() {
        val tabLayout = binding?.addTab
        val viewPager = binding?.addViewpager
        val adapter = AddViewPagerAdapter(requireActivity())
        viewPager?.adapter = adapter

        if (tabLayout != null && viewPager != null) {
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = getString(adapter.tabs[position])
            }.attach()
        }

    }

    override fun setupPresenter() {
        //TODO("Not yet implemented")
    }
}