package test.createx.dogsimulator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel


class MainViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {


    private val currentFragmentIndex = savedStateHandle.getLiveData<Int>("currentFragmentIndex")

    fun getCurrentFragmentIndex(): LiveData<Int> {
        return currentFragmentIndex
    }

    fun setCurrentFragmentIndex(index: Int) {
        currentFragmentIndex.value = index
    }


}