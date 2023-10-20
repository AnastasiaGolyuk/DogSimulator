package test.createx.dogsimulator.ui.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.button.MaterialButton
import test.createx.dogsimulator.R

class HowToUseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_how_to_use)
        val closeButton = findViewById<MaterialButton>(R.id.howToUseBackButton)
        closeButton.setOnClickListener {
            this.finish()
        }
    }
}