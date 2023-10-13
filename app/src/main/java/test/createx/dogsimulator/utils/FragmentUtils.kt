package test.createx.dogsimulator.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import test.createx.dogsimulator.R

class FragmentUtils {
    companion object {
        fun replaceFragment(fragmentManager: FragmentManager, fragment: Fragment) {
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frameLayout, fragment)
            fragmentTransaction.commit()
        }
    }
}