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

        val view = inflater.inflate(R.layout.fragment_whistle, container, false)

        val buttonHowToUse = view.findViewById<MaterialButton>(R.id.howToUseButton)
        buttonHowToUse.setOnClickListener {
            val intent = Intent(activity, HowToUseActivity::class.java)
            startActivity(intent)
        }

        val sliderWhistle = view.findViewById<Slider>(R.id.sliderWhistle)
        val sliderWhistleValue = view.findViewById<TextView>(R.id.sliderWhistleValue)

        val valueHz = sliderWhistle.value.toInt().toString() + " Hz"
        sliderWhistleValue.text = valueHz

        sliderWhistle.addOnChangeListener(object: Slider.OnChangeListener{
            override fun onValueChange(slider: Slider, value: Float, fromUser: Boolean) {
                val stringValue = value.toInt().toString() + " Hz"
                sliderWhistleValue.text = stringValue

            }
        })

        val whistleButton = view.findViewById<MaterialButton>(R.id.whistleButton)
        whistleButton.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        if (!isPlaying) {
                            val frequency = sliderWhistle.value.toDouble() // Set your desired frequency
                            soundGenerator.playSound(frequency)
                            isPlaying = true
                        }
                    }
                    MotionEvent.ACTION_UP -> {
                        if (isPlaying) {
                            soundGenerator.stopSound()
                            isPlaying = false
                        }
                    }
                }
                return true
            }
        })

        return view
    }

}