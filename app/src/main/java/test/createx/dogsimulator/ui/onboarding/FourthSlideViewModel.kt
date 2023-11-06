package test.createx.dogsimulator.ui.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import test.createx.dogsimulator.R
import test.createx.dogsimulator.data.models.SubscriptionListItem

class FourthSlideViewModel : ViewModel() {

    private val _subscriptionList = MutableLiveData<List<SubscriptionListItem>>()
    val subscriptionList: LiveData<List<SubscriptionListItem>> = _subscriptionList

    init {
        getSubscriptionList()
    }

    private fun getSubscriptionList() {
        val items = listOf(
            SubscriptionListItem("A lot ways of impression", R.drawable.intro_smile),
            SubscriptionListItem("Access to all features", R.drawable.intro_phone),
            SubscriptionListItem("No annoying Ads", R.drawable.intro_ad)
        )
        _subscriptionList.value = items
    }
}
