package test.createx.dogsimulator.data.repository

import android.app.Activity
import android.app.Application
import android.util.Log
import com.android.billingclient.api.AcknowledgePurchaseParams
import com.android.billingclient.api.AcknowledgePurchaseResponseListener
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingFlowParams
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.PurchasesUpdatedListener
import com.android.billingclient.api.QueryProductDetailsParams

class BillingRepository(private val application: Application) : PurchasesUpdatedListener {

    private lateinit var billingClient: BillingClient

    companion object {

        private const val LOG_TAG = "BillingRepository"

        @Volatile
        private var INSTANCE: BillingRepository? = null

        fun getInstance(application: Application): BillingRepository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: BillingRepository(application).also { INSTANCE = it }
            }
    }

    fun initClient() {
        billingClient = BillingClient.newBuilder(application.applicationContext).setListener(this)
            .enablePendingPurchases().build()
    }

    fun connectClient(activity: Activity) {
        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                    queryPurchases(activity)
                }
            }

            override fun onBillingServiceDisconnected() {
                // Try to restart the connection on the next request to
                // Google Play by calling the startConnection() method.
            }
        })
    }

    private fun queryPurchases(activity: Activity) {
        //change product id
        val productList = listOf(
            QueryProductDetailsParams.Product.newBuilder().setProductId("")
                .setProductType(BillingClient.ProductType.SUBS).build()
        )
        val params = QueryProductDetailsParams.newBuilder()
        params.setProductList(productList)
        val queryProductDetailsParams = params.build()
        billingClient.queryProductDetailsAsync(queryProductDetailsParams)

        { billingResult, productDetailsList ->
                for (productDetails in productDetailsList) {
                val selectedOfferToken = productDetails.subscriptionOfferDetails?.get(0)?.offerToken
                val productDetailsParamsList = listOf(selectedOfferToken?.let {
                    BillingFlowParams.ProductDetailsParams.newBuilder()
                        .setProductDetails(productDetails).setOfferToken(it).build()
                })
                launchPurchaseFlow(activity, productDetailsParamsList)
            }
        }
    }

    private fun launchPurchaseFlow(
        activity: Activity,
        productDetailsParamsList: List<BillingFlowParams.ProductDetailsParams?>
    ) {
        val billingFlowParams =
            BillingFlowParams.newBuilder().setProductDetailsParamsList(productDetailsParamsList)
                .build()
        billingClient.launchBillingFlow(activity, billingFlowParams)
    }

    private val acknowledgePurchaseResponseListener =
        AcknowledgePurchaseResponseListener { Log.i(LOG_TAG, "Purchase acknowledged") }

    private fun handlePurchase(purchase: Purchase) {
        if (purchase.purchaseState == Purchase.PurchaseState.PURCHASED && !purchase.isAcknowledged) {
            val acknowledgePurchaseParams = AcknowledgePurchaseParams.newBuilder()
                .setPurchaseToken(purchase.purchaseToken)
            billingClient.acknowledgePurchase(
                acknowledgePurchaseParams.build(),
                acknowledgePurchaseResponseListener
            )
        }
    }

    fun disconnectClient() {
        if (billingClient.isReady) {
            billingClient.endConnection()
        }
    }

    override fun onPurchasesUpdated(billingResult: BillingResult, purchases: List<Purchase?>?) {
        if (billingResult.responseCode == BillingClient.BillingResponseCode.OK && purchases != null) {
            for (purchase in purchases) {
                if (purchase != null) {
                    handlePurchase(purchase)
                }
            }
        } else if (billingResult.responseCode == BillingClient.BillingResponseCode.USER_CANCELED) {
            Log.i(LOG_TAG, "User cancelled purchase flow.")
        } else {
            Log.i(LOG_TAG, "onPurchaseUpdated error: ${billingResult.responseCode}")
        }
    }
}