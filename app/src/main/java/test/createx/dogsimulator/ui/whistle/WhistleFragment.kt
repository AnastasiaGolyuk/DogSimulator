package test.createx.dogsimulator.ui.whistle

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.slider.Slider
import test.createx.dogsimulator.databinding.FragmentWhistleBinding


class WhistleFragment : Fragment() {

    private lateinit var viewModelFactory: WhistleViewModelFactory
    private lateinit var viewModel: WhistleViewModel
    private lateinit var binding: FragmentWhistleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWhistleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelFactory = WhistleViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(WhistleViewModel::class.java)

        binding.howToUseButton.setOnClickListener {
            val intent = Intent(activity, HowToUseActivity::class.java)
            startActivity(intent)
        }

        val valueHz = binding.sliderWhistle.value.toInt().toString() + " Hz"
        binding.sliderWhistleValue.text = valueHz

        binding.sliderWhistle.addOnChangeListener(Slider.OnChangeListener { slider, value, fromUser ->
            val stringValue = value.toInt().toString() + " Hz"
            binding.sliderWhistleValue.text = stringValue
        })

        binding.whistleButton.setOnTouchListener { v, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    val frequency = binding.sliderWhistle.value.toDouble()
                    viewModel.onWhistleButtonPressed(frequency)
                    v.performClick()
                }

                MotionEvent.ACTION_UP -> {
                    viewModel.onWhistleButtonReleased()
                }
            }
            true
        }
    }
}