package test.createx.dogsimulator.ui.whistle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class HowToUseFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HowToUseViewModel::class.java)) {
            return HowToUseViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}