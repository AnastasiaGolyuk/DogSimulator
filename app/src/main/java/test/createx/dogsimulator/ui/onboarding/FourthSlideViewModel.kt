package test.createx.dogsimulator.ui.onboarding

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingFlowParams
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.QueryProductDetailsParams
import com.android.billingclient.api.queryProductDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import test.createx.dogsimulator.R
import test.createx.dogsimulator.data.models.SubscriptionListItem
import test.createx.dogsimulator.data.repository.BillingRepository

class FourthSlideViewModel(application: Application) : AndroidViewModel(application) {

    private val _subscriptionList = MutableLiveData<List<SubscriptionListItem>>()
    val subscriptionList: LiveData<List<SubscriptionListItem>> = _subscriptionList

    private val repository: BillingRepository

    init {
        getSubscriptionList()
        repository = BillingRepository.getInstance(application)
        repository.initClient()
    }

    fun disconnectClient(){
        repository.disconnectClient()
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
