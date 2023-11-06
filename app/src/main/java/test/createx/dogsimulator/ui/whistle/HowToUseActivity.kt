package test.createx.dogsimulator.ui.whistle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import test.createx.dogsimulator.databinding.ActivityHowToUseBinding

class HowToUseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHowToUseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHowToUseBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.howToUseBackButton.setOnClickListener {
            this.finish()
        }
    }
}