package test.createx.dogsimulator.ui.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.google.android.material.button.MaterialButton
import test.createx.dogsimulator.R
import test.createx.dogsimulator.apadters.SettingsListViewAdapter
import test.createx.dogsimulator.data.models.SettingsListItem

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        val items = listOf(
            SettingsListItem("Privacy Policy", R.drawable.shield_check_fill),
            SettingsListItem("Terms of Use", R.drawable.article_fill),
            SettingsListItem("Rate Us", R.drawable.rating_fill),
            SettingsListItem("Share App", R.drawable.share_fill),
        )
        val settingsListView = findViewById<ListView>(R.id.settingsListView)
        settingsListView.adapter = SettingsListViewAdapter(this, items)
        val backButton = findViewById<MaterialButton>(R.id.settingsBackButton)
        backButton.setOnClickListener {
            this.finish()
        }
    }
}