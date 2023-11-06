package test.createx.dogsimulator.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FourthSlideViewModelFactory: ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FourthSlideViewModel::class.java)) {
            return FourthSlideViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}