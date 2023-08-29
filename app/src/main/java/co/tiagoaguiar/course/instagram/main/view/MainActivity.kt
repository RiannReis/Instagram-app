package co.tiagoaguiar.course.instagram.main.view

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.WindowInsetsController
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import co.tiagoaguiar.course.instagram.R
import co.tiagoaguiar.course.instagram.databinding.ActivityMainBinding
import co.tiagoaguiar.course.instagram.home.view.HomeFragment
import co.tiagoaguiar.course.instagram.profile.view.ProfileFragment
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    private lateinit var homeFragment: Fragment
    private lateinit var searchFragment: Fragment
    private lateinit var cameraFragment: Fragment
    private lateinit var profileFragment: Fragment
    private lateinit var currentFragment: Fragment
    private lateinit var fragmentSavedState: HashMap<String, Fragment.SavedState?>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.setSystemBarsAppearance(
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
            window.statusBarColor = ContextCompat.getColor(this, R.color.gray)
        }

        setSupportActionBar(binding.mainToolbar)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_insta_camera)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        if (savedInstanceState == null) {
            fragmentSavedState = HashMap()
        } else {
            savedInstanceState.getSerializable("fragmentState") as HashMap<String, Fragment.SavedState?>
        }

//        homeFragment = HomeFragment()
//        searchFragment = SearchFragment()
//        cameraFragment = CameraFragment()
//        profileFragment = ProfileFragment()

//        currentFragment = homeFragment

//        supportFragmentManager.beginTransaction().apply {
//            add(R.id.main_activity, profileFragment, "3").hide(profileFragment)
//            add(R.id.main_activity, cameraFragment, "2").hide(cameraFragment)
//            add(R.id.main_activity, searchFragment, "1").hide(searchFragment)
//            add(R.id.main_activity, homeFragment, "0")
//            commit()
//        }

        binding.mainBottomNav.setOnNavigationItemSelectedListener(this)
        binding.mainBottomNav.selectedItemId = R.id.menu_bottom_home

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable("fragmentState", fragmentSavedState)
        super.onSaveInstanceState(outState)
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var scrollToolbarEnabled = false

        val newFrag: Fragment? = when (item.itemId) {
            R.id.menu_bottom_home -> {
                HomeFragment()
            }
            R.id.menu_bottom_profile -> {
                ProfileFragment()
            }
            else -> null
        }

        val currFragment = supportFragmentManager.findFragmentById(R.id.main_activity)

        val fragmentTag = newFrag?.javaClass?.simpleName

        if (!currFragment?.tag.equals(fragmentTag)) {
            currFragment?.let { frag ->
                fragmentSavedState.put(
                    frag.tag!!,
                    supportFragmentManager.saveFragmentInstanceState(frag)
                )
            }
        }

        newFrag?.setInitialSavedState(fragmentSavedState[fragmentTag])
        newFrag?.let {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_activity, it, fragmentTag)
                .addToBackStack(fragmentTag)
                .commit()
        }

        //V1
//        when (item.itemId) {
//            R.id.menu_bottom_home -> {
//                if (currentFragment == homeFragment) return false
//                supportFragmentManager.beginTransaction().hide(currentFragment).show(homeFragment).commit()
//                currentFragment = homeFragment
//            }
//            R.id.menu_bottom_search -> {
//                if (currentFragment == searchFragment) return false
//                supportFragmentManager.beginTransaction().hide(currentFragment).show(searchFragment).commit()
//                currentFragment = searchFragment
//            }
//            R.id.menu_bottom_add -> {
//                if (currentFragment == cameraFragment) return false
//                supportFragmentManager.beginTransaction().hide(currentFragment).show(cameraFragment).commit()
//                currentFragment = cameraFragment
//            }
//            R.id.menu_bottom_profile -> {
//                if (currentFragment == profileFragment) return false
//                supportFragmentManager.beginTransaction().hide(currentFragment).show(profileFragment).commit()
//                currentFragment = profileFragment
//                scrollToolbarEnabled = true
//            }
//        }

        setScrollToolbarEnabled(scrollToolbarEnabled)

//        currentFragment?.let { changeFragment(R.id.main_activity, it)}

        return true
    }

    private fun setScrollToolbarEnabled(enabled: Boolean) {
        val params = binding.mainToolbar.layoutParams as AppBarLayout.LayoutParams
        val coordinatParams = binding.mainAppbar.layoutParams as CoordinatorLayout.LayoutParams

        if (enabled) {
            params.scrollFlags =
                AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
            coordinatParams.behavior = AppBarLayout.Behavior()
        } else {
            params.scrollFlags = 0
            coordinatParams.behavior = null
        }
        binding.mainAppbar.layoutParams = coordinatParams
    }
}