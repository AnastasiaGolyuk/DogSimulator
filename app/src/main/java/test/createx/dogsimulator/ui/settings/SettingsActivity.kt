package test.createx.dogsimulator.ui.settings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import test.createx.dogsimulator.apadters.SettingsListAdapter
import test.createx.dogsimulator.databinding.ActivitySettingsBinding
import test.createx.dogsimulator.ui.webview.WebViewActivity

class SettingsActivity : AppCompatActivity() {

    private lateinit var viewModelFactory: SettingsViewModelFactory
    private lateinit var viewModel: SettingsViewModel
    private lateinit var binding: ActivitySettingsBinding
    private lateinit var adapter: SettingsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModelFactory = SettingsViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(SettingsViewModel::class.java)

        val settingsListView = binding.settingsListView
        settingsListView.layoutManager = LinearLayoutManager(applicationContext)
        adapter = SettingsListAdapter(::openWebView, ::shareApp)
        settingsListView.adapter = adapter

        viewModel.settingsList.observe(this) { items ->
            adapter.submitList(items)
        }

        binding.settingsBackButton.setOnClickListener {
            this.finish()
        }
    }

    private fun openWebView() {
        val intent = Intent(applicationContext, WebViewActivity::class.java)
        intent.putExtra("url", "https://www.google.com/")
        startActivity(intent)
    }

    private fun shareApp() {
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.putExtra(
            Intent.EXTRA_TEXT, "Hey Check out this Great app: Dog Simulator"
        )
        intent.type = "text/plain"
        startActivity(Intent.createChooser(intent, "Share To:"))
    }
}