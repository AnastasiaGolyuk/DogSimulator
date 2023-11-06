package test.createx.dogsimulator.ui.whistle

import androidx.lifecycle.ViewModel
import test.createx.dogsimulator.whistle.SoundGenerator

class WhistleViewModel : ViewModel() {
    private var isPlaying = false
    private val soundGenerator = SoundGenerator()

    fun onWhistleButtonPressed(frequency: Double) {
        if (!isPlaying) {
            soundGenerator.playSound(frequency)
            isPlaying = true
        }
    }

    fun onWhistleButtonReleased() {
        if (isPlaying) {
            soundGenerator.stopSound()
            isPlaying = false
        }
    }
}