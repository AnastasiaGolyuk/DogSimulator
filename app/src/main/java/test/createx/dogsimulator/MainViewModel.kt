package test.createx.dogsimulator

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel


class MainViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    private val currentFragmentIndex = savedStateHandle.getLiveData<Int>("currentFragmentIndex")

    fun getCurrentFragmentIndex(): LiveData<Int> {
        return currentFragmentIndex
    }

    fun setCurrentFragmentIndex(index: Int) {
        currentFragmentIndex.value = index
    }
}