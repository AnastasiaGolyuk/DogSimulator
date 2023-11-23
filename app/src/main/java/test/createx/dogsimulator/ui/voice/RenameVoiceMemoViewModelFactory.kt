package test.createx.dogsimulator.ui.voice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class RenameVoiceMemoViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RenameVoiceMemoViewModel::class.java)) {
            return RenameVoiceMemoViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}