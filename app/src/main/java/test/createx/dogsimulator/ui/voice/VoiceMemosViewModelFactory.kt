package test.createx.dogsimulator.ui.voice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class VoiceMemosViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VoiceMemosViewModel::class.java)) {
            return VoiceMemosViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}