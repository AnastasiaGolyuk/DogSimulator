package test.createx.dogsimulator.ui.views.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.slider.Slider
import test.createx.dogsimulator.R
import test.createx.dogsimulator.ui.views.activities.HowToUseActivity
import test.createx.dogsimulator.whistle.SoundGenerator


class WhistleFragment : Fragment() {

    private var isPlaying = false
    private val soundGenerator = SoundGenerator()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_whistle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonHowToUse = view.findViewById<MaterialButton>(R.id.howToUseButton)

        buttonHowToUse.setOnClickListener {
            val intent = Intent(activity, HowToUseActivity::class.java)
            startActivity(intent)
        }

        val sliderWhistle = view.findViewById<Slider>(R.id.sliderWhistle)
        val sliderWhistleValue = view.findViewById<TextView>(R.id.sliderWhistleValue)

        val valueHz = sliderWhistle.value.toInt().toString() + " Hz"
        sliderWhistleValue.text = valueHz

        sliderWhistle.addOnChangeListener(Slider.OnChangeListener { slider, value, fromUser ->
            val stringValue = value.toInt().toString() + " Hz"
            sliderWhistleValue.text = stringValue
        })

        val whistleButton = view.findViewById<MaterialButton>(R.id.whistleButton)
        whistleButton.setOnTouchListener { v, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    if (!isPlaying) {
                        val frequency = sliderWhistle.value.toDouble()
                        soundGenerator.playSound(frequency)
                        isPlaying = true
                        v.performClick()
                    }
                }

                MotionEvent.ACTION_UP -> {
                    if (isPlaying) {
                        soundGenerator.stopSound()
                        isPlaying = false
                    }
                }
            }
            true
        }
    }

}