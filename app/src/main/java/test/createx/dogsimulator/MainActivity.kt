package test.createx.dogsimulator

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import test.createx.dogsimulator.apadters.ViewPagerAdapter
import test.createx.dogsimulator.databinding.ActivityMainBinding
import test.createx.dogsimulator.ui.views.fragments.FirstSlideFragment
import test.createx.dogsimulator.ui.views.fragments.FourthSlideFragment
import test.createx.dogsimulator.ui.views.fragments.SecondSlideFragment
import test.createx.dogsimulator.ui.views.fragments.SimulatorFragment
import test.createx.dogsimulator.ui.views.fragments.ThirdSlideFragment
import test.createx.dogsimulator.ui.views.fragments.TranslatorFragment
import test.createx.dogsimulator.ui.views.fragments.VoiceMemosFragment
import test.createx.dogsimulator.ui.views.fragments.WhistleFragment
import test.createx.dogsimulator.utils.FragmentUtils

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var preferences: SharedPreferences

    private val preferenceShowSlider = "Slider"

    private val fragmentManager = supportFragmentManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        preferences = getSharedPreferences("IntroSlider", Context.MODE_PRIVATE)
        if (!preferences.getBoolean(preferenceShowSlider, true)) {
            val navBar = findViewById<BottomNavigationView>(R.id.bottomNavView)
            navBar.visibility = View.VISIBLE
            FragmentUtils.replaceFragment(fragmentManager, TranslatorFragment())
        } else {
            val fragmentList = arrayListOf<Fragment>(
                FirstSlideFragment(),
                SecondSlideFragment(),
                ThirdSlideFragment(),
                FourthSlideFragment(fragmentManager)
            )

            val adapter = ViewPagerAdapter(
                fragmentList,
                supportFragmentManager,
                lifecycle
            )

            binding.sliderItemViewPager.adapter = adapter

        }

        binding.bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.translatorMenuItem -> FragmentUtils.replaceFragment(
                    fragmentManager,
                    TranslatorFragment()
                )

                R.id.simulatorMenuItem -> FragmentUtils.replaceFragment(
                    fragmentManager,
                    SimulatorFragment()
                )

                R.id.voiceMenuItem -> FragmentUtils.replaceFragment(
                    fragmentManager,
                    VoiceMemosFragment()
                )

                R.id.whistleMenuItem -> FragmentUtils.replaceFragment(
                    fragmentManager,
                    WhistleFragment()
                )

                else -> {

                }
            }
            true
        }
    }
}