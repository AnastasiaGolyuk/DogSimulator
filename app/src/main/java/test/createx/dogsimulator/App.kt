package test.createx.dogsimulator

import android.app.Application
import com.appsflyer.AppsFlyerLib
import com.google.firebase.FirebaseApp
import io.appmetrica.analytics.AppMetrica
import io.appmetrica.analytics.AppMetricaConfig

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)

        val config = AppMetricaConfig.newConfigBuilder(BuildConfig.APPMETRICA_API_KEY).build()
        AppMetrica.activate(this, config)

        AppsFlyerLib.getInstance().init(BuildConfig.APPSFLYER_API_KEY, null, this)
        AppsFlyerLib.getInstance().start(this)

        //facebook sdk initialization is automatically
    }
}