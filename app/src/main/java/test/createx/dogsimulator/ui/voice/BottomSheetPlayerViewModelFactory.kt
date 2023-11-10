package test.createx.dogsimulator.ui.voice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import test.createx.dogsimulator.playback.AudioPlayer

class BottomSheetPlayerViewModelFactory(private val audioPlayer: AudioPlayer) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BottomSheetPlayerViewModel::class.java)) {
            return BottomSheetPlayerViewModel(audioPlayer) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}