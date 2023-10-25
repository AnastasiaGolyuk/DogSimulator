package test.createx.dogsimulator

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import test.createx.dogsimulator.apadters.ViewPagerAdapter
import test.createx.dogsimulator.databinding.ActivityMainBinding
import test.createx.dogsimulator.ui.views.activities.HowToUseActivity
import test.createx.dogsimulator.ui.views.activities.SettingsActivity
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
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        val titleToolbar = findViewById<TextView>(R.id.toolbarTitle)
        preferences = getSharedPreferences("IntroSlider", Context.MODE_PRIVATE)
        if (!preferences.getBoolean(preferenceShowSlider, true)) {
            val navBar = findViewById<BottomNavigationView>(R.id.bottomNavView)
            toolbar.visibility=View.VISIBLE
            navBar.visibility = View.VISIBLE
            titleToolbar.setText(R.string.menu_item_translator)
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

        binding.settingsButton?.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        binding.bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.translatorMenuItem -> {
                    titleToolbar.setText(R.string.menu_item_translator)
                    FragmentUtils.replaceFragment(
                        fragmentManager,
                        TranslatorFragment()
                    )
                }

                R.id.simulatorMenuItem -> {
                    titleToolbar.setText(getString(R.string.dog_simulator))
                    FragmentUtils.replaceFragment(
                        fragmentManager,
                        SimulatorFragment()
                    )
                }

                R.id.voiceMenuItem -> {
                    titleToolbar.setText(R.string.menu_item_voice_memos)
                    FragmentUtils.replaceFragment(
                        fragmentManager,
                        VoiceMemosFragment(applicationContext,fragmentManager)
                    )
                }

                R.id.whistleMenuItem -> {
                    titleToolbar.setText(R.string.menu_item_whistle)
                    FragmentUtils.replaceFragment(
                        fragmentManager,
                        WhistleFragment()
                    )
                }

                else -> {

                }
            }
            true
        }
    }
}