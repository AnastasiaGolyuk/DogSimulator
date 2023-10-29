package test.createx.dogsimulator.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.button.MaterialButton
import test.createx.dogsimulator.R

class ThirdSlideFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_third_slide, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager = view.findViewById<ViewPager2>(R.id.sliderItemViewPager)
        val buttonContinue = view.findViewById<MaterialButton>(R.id.buttonContinue)
        buttonContinue.setOnClickListener {
            viewPager?.currentItem = 3
        }
    }
}