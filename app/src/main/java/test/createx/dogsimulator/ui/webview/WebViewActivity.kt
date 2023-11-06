package test.createx.dogsimulator.ui.webview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import test.createx.dogsimulator.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityWebViewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.webView.webViewClient = WebViewClient()
        val url = intent.getStringExtra("url")
        if (url != null) {
            binding.webView.loadUrl(url)
        }
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.settings.setSupportZoom(true)
    }
}