package test.createx.dogsimulator.ui.translator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TranslatorViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TranslatorViewModel::class.java)) {
            return TranslatorViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}