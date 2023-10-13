package test.createx.dogsimulator.ui.views.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import test.createx.dogsimulator.apadters.ListArrayAdapter
import test.createx.dogsimulator.data.models.ListItem
import test.createx.dogsimulator.R
import test.createx.dogsimulator.databinding.FragmentFourthSlideBinding
import test.createx.dogsimulator.utils.FragmentUtils


class FourthSlideFragment(activityFragmentManager: FragmentManager) : Fragment() {
    private var _binding: FragmentFourthSlideBinding? = null
    private val binding get() = _binding!!

    private lateinit var preferences: SharedPreferences

    private val preferenceShowSlider = "Slider"

    private val frManager = activityFragmentManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFourthSlideBinding.inflate(inflater, container, false)
        val view = binding.root

        val items = listOf(
            ListItem("A lot ways of impression", R.drawable.intro_smile),
            ListItem("Access to all features", R.drawable.intro_phone),
            ListItem("No annoying Ads", R.drawable.intro_ad)
        )

        val mListView = view.findViewById<ListView>(R.id.sliderSubscriptionList)

        mListView.adapter = ListArrayAdapter(context, items)

        preferences = requireActivity().getSharedPreferences("IntroSlider", Context.MODE_PRIVATE)

        binding.buttonContinue.setOnClickListener {
            val navBar = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavView)
            navBar.visibility=View.VISIBLE
            FragmentUtils.replaceFragment(frManager, TranslatorFragment())
            val editor = preferences.edit()
            editor.putBoolean(preferenceShowSlider, false)
            editor.apply()
        }

        binding.buttonLater.setOnClickListener {
            val navBar = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavView)
            navBar.visibility=View.VISIBLE
            FragmentUtils.replaceFragment(frManager, TranslatorFragment())
            val editor = preferences.edit()
            editor.putBoolean(preferenceShowSlider, false)
            editor.apply()
        }

        return view
    }
}