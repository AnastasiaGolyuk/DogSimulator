package test.createx.dogsimulator.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import test.createx.dogsimulator.R
import test.createx.dogsimulator.data.models.SettingsListItem

class SettingsViewModel:ViewModel() {
    
    private val _settingsList = MutableLiveData<List<SettingsListItem>>()
    val settingsList: LiveData<List<SettingsListItem>> = _settingsList

    init {
        getSettingsList()
    }

    private fun getSettingsList() {
        val items = listOf(
            SettingsListItem("Privacy Policy", R.drawable.shield_check_fill),
            SettingsListItem("Terms of Use", R.drawable.article_fill),
            SettingsListItem("Rate Us", R.drawable.rating_fill),
            SettingsListItem("Share App", R.drawable.share_fill),
        )
        _settingsList.value = items
    }
}