package test.createx.dogsimulator.ui.whistle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class WhistleViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WhistleViewModel::class.java)) {
            return WhistleViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}