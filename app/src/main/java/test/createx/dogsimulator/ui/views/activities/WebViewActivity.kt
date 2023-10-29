package test.createx.dogsimulator.ui.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import test.createx.dogsimulator.R

class WebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        val webView = findViewById<WebView>(R.id.webView)
        webView.webViewClient = WebViewClient()
        val url = intent.getStringExtra("url")
        if (url != null) {
            webView.loadUrl(url)
        }
        webView.settings.javaScriptEnabled = true
        webView.settings.setSupportZoom(true)
    }
}