package test.createx.dogsimulator.ui.simulator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SimulatorViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SimulatorViewModel::class.java)) {
            return SimulatorViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}