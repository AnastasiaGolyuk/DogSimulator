package test.createx.dogsimulator

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import test.createx.dogsimulator.apadters.ViewPagerAdapter
import test.createx.dogsimulator.databinding.ActivityMainBinding
import test.createx.dogsimulator.ui.onboarding.FirstSlideFragment
import test.createx.dogsimulator.ui.onboarding.FourthSlideFragment
import test.createx.dogsimulator.ui.onboarding.SecondSlideFragment
import test.createx.dogsimulator.ui.onboarding.ThirdSlideFragment
import test.createx.dogsimulator.ui.settings.SettingsActivity
import test.createx.dogsimulator.ui.simulator.SimulatorFragment
import test.createx.dogsimulator.ui.translator.TranslatorFragment
import test.createx.dogsimulator.ui.voice.VoiceMemosFragment
import test.createx.dogsimulator.ui.whistle.WhistleFragment
import test.createx.dogsimulator.utils.FragmentUtils


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var preferences: SharedPreferences

    private val preferenceShowSlider = "Slider"

    private val fragmentManager = supportFragmentManager

    private lateinit var viewModel: MainViewModel

    private val TRANSLATOR_MENU_ITEM_ID = R.id.translatorMenuItem


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(
            this, SavedStateViewModelFactory(application, this)
        ).get(MainViewModel::class.java)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        preferences = getSharedPreferences("IntroSlider", Context.MODE_PRIVATE)

        if (!preferences.getBoolean(preferenceShowSlider, true)) {
            showTranslatorFragment()
            replaceBottomNavFragments(TRANSLATOR_MENU_ITEM_ID)
        } else {
            val fragmentList = arrayListOf(
                FirstSlideFragment(),
                SecondSlideFragment(),
                ThirdSlideFragment(),
                FourthSlideFragment()
            )

            val adapter = ViewPagerAdapter(
                fragmentList, supportFragmentManager, lifecycle
            )

            binding.sliderItemViewPager.adapter = adapter

            binding.sliderItemViewPager.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    if (position < 3) {
                        binding.buttonLater.visibility = View.INVISIBLE
                    } else {
                        binding.buttonLater.visibility = View.VISIBLE
                    }
                    super.onPageSelected(position)
                }
            })

            binding.buttonContinue.setOnClickListener {
                if (binding.sliderItemViewPager.currentItem < 3) {
                    binding.sliderItemViewPager.currentItem++
                } else {
                    showTranslatorFragment()
                    replaceBottomNavFragments(TRANSLATOR_MENU_ITEM_ID)
                    val editor = preferences.edit()
                    editor.putBoolean(preferenceShowSlider, false)
                    editor.apply()
                }
            }

            binding.buttonLater.setOnClickListener {
                showTranslatorFragment()
                replaceBottomNavFragments(TRANSLATOR_MENU_ITEM_ID)
                val editor = preferences.edit()
                editor.putBoolean(preferenceShowSlider, false)
                editor.apply()
            }
        }

        binding.settingsButton?.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        viewModel.getCurrentFragmentIndex().observe(this) { fragmentIndex ->
            replaceBottomNavFragments(fragmentIndex)
        }

        binding.bottomNavView.setOnItemSelectedListener {
            viewModel.setCurrentFragmentIndex(it.itemId)
            true
        }
    }

    private fun showTranslatorFragment() {
        binding.toolbar.visibility = View.VISIBLE
        binding.bottomNavView.visibility = View.VISIBLE
        binding.buttonContinue.visibility = View.INVISIBLE
        binding.sliderItemViewPager.visibility = View.GONE
    }

    private fun replaceBottomNavFragments(id: Int) {
        when (id) {
            R.id.translatorMenuItem -> {
                binding.toolbarTitle.text = getString(R.string.menu_item_translator)
                FragmentUtils.replaceFragment(
                    fragmentManager, TranslatorFragment()
                )
            }

            R.id.simulatorMenuItem -> {
                binding.toolbarTitle.text = getString(R.string.dog_simulator)
                FragmentUtils.replaceFragment(
                    fragmentManager, SimulatorFragment()
                )
            }

            R.id.voiceMenuItem -> {
                binding.toolbarTitle.text = getString(R.string.menu_item_voice_memos)
                FragmentUtils.replaceFragment(
                    fragmentManager, VoiceMemosFragment()
                )
            }

            R.id.whistleMenuItem -> {
                binding.toolbarTitle.text = getString(R.string.menu_item_whistle)
                FragmentUtils.replaceFragment(
                    fragmentManager, WhistleFragment()
                )
            }
        }
    }
}