package test.createx.dogsimulator

import android.app.Activity
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import test.createx.dogsimulator.data.models.SubscriptionListItem
import test.createx.dogsimulator.data.repository.BillingRepository


class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: BillingRepository

    private val _currentIndex = MutableLiveData<Int>()
    val currentIndex: LiveData<Int> = _currentIndex

    init {
        repository = BillingRepository.getInstance(application)
    }

    fun connectClient(activity: Activity){
        repository.connectClient(activity)
    }


    fun setIndex(index: Int){
        _currentIndex.value=index
    }
}