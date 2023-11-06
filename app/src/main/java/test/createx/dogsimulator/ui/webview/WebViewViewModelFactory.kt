package test.createx.dogsimulator.ui.webview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class WebViewViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WebViewViewModel::class.java)) {
            return WebViewViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}