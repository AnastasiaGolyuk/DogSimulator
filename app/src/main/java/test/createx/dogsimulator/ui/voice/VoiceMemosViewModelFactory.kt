package test.createx.dogsimulator.ui.voice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import test.createx.dogsimulator.record.AudioRecorder
import java.io.File

class VoiceMemosViewModelFactory(
    private val audioRecorder: AudioRecorder, private val readDir: File) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VoiceMemosViewModel::class.java)) {
            return VoiceMemosViewModel(audioRecorder, readDir) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}