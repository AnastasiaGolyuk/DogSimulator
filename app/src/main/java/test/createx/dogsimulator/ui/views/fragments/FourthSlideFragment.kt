package test.createx.dogsimulator.ui.views.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton
import test.createx.dogsimulator.apadters.SubscriptionListArrayAdapter
import test.createx.dogsimulator.data.models.SubscriptionListItem
import test.createx.dogsimulator.R
import test.createx.dogsimulator.utils.FragmentUtils


class FourthSlideFragment(activityFragmentManager: FragmentManager) : Fragment() {

    private lateinit var preferences: SharedPreferences

    private val preferenceShowSlider = "Slider"

    private val frManager = activityFragmentManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fourth_slide, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val items = listOf(
            SubscriptionListItem("A lot ways of impression", R.drawable.intro_smile),
            SubscriptionListItem("Access to all features", R.drawable.intro_phone),
            SubscriptionListItem("No annoying Ads", R.drawable.intro_ad)
        )

        val subscriptionListView = view.findViewById<ListView>(R.id.sliderSubscriptionList)

        subscriptionListView.adapter = SubscriptionListArrayAdapter(context, items)

        preferences = requireActivity().getSharedPreferences("IntroSlider", Context.MODE_PRIVATE)

        val buttonContinue = view.findViewById<MaterialButton>(R.id.buttonContinue)
        buttonContinue.setOnClickListener {
            val navBar = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavView)
            val toolBar = requireActivity().findViewById<Toolbar>(R.id.toolbar)
            toolBar.visibility = View.VISIBLE
            navBar.visibility = View.VISIBLE
            FragmentUtils.replaceFragment(frManager, TranslatorFragment())
            val editor = preferences.edit()
            editor.putBoolean(preferenceShowSlider, false)
            editor.apply()
        }

        val buttonLater = view.findViewById<MaterialButton>(R.id.buttonLater)
        buttonLater.setOnClickListener {
            val navBar = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavView)
            val toolBar = requireActivity().findViewById<Toolbar>(R.id.toolbar)
            toolBar.visibility = View.VISIBLE
            navBar.visibility = View.VISIBLE
            FragmentUtils.replaceFragment(frManager, TranslatorFragment())
            val editor = preferences.edit()
            editor.putBoolean(preferenceShowSlider, false)
            editor.apply()
        }
    }
}