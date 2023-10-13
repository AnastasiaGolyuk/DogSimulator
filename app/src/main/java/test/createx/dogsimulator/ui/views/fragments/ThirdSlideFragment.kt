package test.createx.dogsimulator.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import test.createx.dogsimulator.R
import test.createx.dogsimulator.databinding.FragmentThirdSlideBinding

class ThirdSlideFragment : Fragment() {
    private var _binding: FragmentThirdSlideBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ):  View? {
        _binding = FragmentThirdSlideBinding.inflate(inflater, container, false)
        val view = binding.root
        val viewPager = activity?.findViewById<ViewPager2>(R.id.sliderItemViewPager)
        binding.buttonContinue.setOnClickListener {
            viewPager?.currentItem = 3
        }

        return view
    }
}