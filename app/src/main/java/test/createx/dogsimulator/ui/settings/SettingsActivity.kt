package test.createx.dogsimulator.ui.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import test.createx.dogsimulator.apadters.SettingsListAdapter
import test.createx.dogsimulator.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var viewModelFactory: SettingsViewModelFactory
    private lateinit var viewModel: SettingsViewModel
    private lateinit var binding: ActivitySettingsBinding
    private var adapter:SettingsListAdapter = SettingsListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModelFactory = SettingsViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(SettingsViewModel::class.java)

        val settingsListView = binding.settingsListView
        settingsListView.layoutManager = LinearLayoutManager(applicationContext)
//        val adapter = SettingsListAdapter()
        settingsListView.adapter = adapter

        viewModel.settingsList.observe(this) { items ->
            adapter.setItems(items)
        }

        binding.settingsBackButton.setOnClickListener {
            this.finish()
        }
    }
}