package test.createx.dogsimulator.ui.simulator

import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import test.createx.dogsimulator.R
import test.createx.dogsimulator.data.models.SimulatorGridItem
import java.io.IOException

class SimulatorViewModel : ViewModel() {

    private val _dogEmotionsList = MutableLiveData<List<SimulatorGridItem>>()
    val dogEmotionsList: LiveData<List<SimulatorGridItem>> = _dogEmotionsList

    private var mediaPlayer: MediaPlayer? = MediaPlayer()

    init {
        getDogEmotionsList()
    }

    fun onItemClick(item: SimulatorGridItem) {
        viewModelScope.launch {
            playSound(item.soundUrl)
            setSelected(item)
        }
    }

    private suspend fun setSelected(simulatorGridItem: SimulatorGridItem) {
        _dogEmotionsList.value = _dogEmotionsList.value?.map {
            it.copy(isSelected = simulatorGridItem.img == it.img)
        }
    }

    private suspend fun playSound(path: String) {
        try {
            if(mediaPlayer!=null) {
                if(mediaPlayer?.isPlaying ==true) {
                    mediaPlayer?.stop()
                }
                mediaPlayer?.reset()
            }
            mediaPlayer?.setDataSource(path)
            mediaPlayer?.prepare()
            mediaPlayer?.start()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        }
    }

    fun stopPlayer() {
        if (mediaPlayer?.isPlaying==true){
            mediaPlayer?.stop()
        }
        mediaPlayer?.reset()
        mediaPlayer?.release()
        mediaPlayer = null
    }


    private fun getDogEmotionsList() {
        val items = listOf(
            SimulatorGridItem(
                R.drawable.happy_dog,
                "Happy",
                "http://104.236.9.253/cats/cat1/Assets/ui/buttons/sounds/btn-sound-1.wav"
            ), SimulatorGridItem(
                R.drawable.neutral_dog,
                "Neutral",
                "http://104.236.9.253/cats/cat1/Assets/ui/buttons/sounds/btn-sound-2.wav"
            ), SimulatorGridItem(
                R.drawable.smart_dog,
                "Smart",
                "http://104.236.9.253/cats/cat1/Assets/ui/buttons/sounds/btn-sound-3.wav"
            ), SimulatorGridItem(
                R.drawable.cry_dog,
                "Cry",
                "http://104.236.9.253/cats/cat1/Assets/ui/buttons/sounds/btn-sound-4.wav"
            ), SimulatorGridItem(
                R.drawable.cute_dog,
                "Cute",
                "http://104.236.9.253/cats/cat1/Assets/ui/buttons/sounds/btn-sound-10.wav"
            ), SimulatorGridItem(
                R.drawable.love_dog,
                "Love",
                "http://104.236.9.253/cats/cat1/Assets/ui/buttons/sounds/btn-sound-11.wav"
            ), SimulatorGridItem(
                R.drawable.grumpy_dog,
                "Grumpy",
                "http://104.236.9.253/cats/cat1/Assets/ui/buttons/sounds/btn-sound-12.wav"
            ), SimulatorGridItem(
                R.drawable.angry_dog,
                "Angry",
                "http://104.236.9.253/cats/cat1/Assets/ui/buttons/sounds/btn-sound-9.wav"
            ), SimulatorGridItem(
                R.drawable.cool_dog,
                "Cool",
                "http://104.236.9.253/cats/cat1/Assets/ui/buttons/sounds/btn-sound-7.wav"
            ), SimulatorGridItem(
                R.drawable.festive_dog,
                "Festive",
                "http://104.236.9.253/cats/cat1/Assets/ui/buttons/sounds/btn-sound-8.wav"
            ), SimulatorGridItem(
                R.drawable.mustache_dog,
                "Mustache",
                "http://104.236.9.253/cats/cat1/Assets/ui/buttons/sounds/btn-sound-6.wav"
            ), SimulatorGridItem(
                R.drawable.evil_dog,
                "Evil",
                "http://104.236.9.253/cats/cat1/Assets/ui/buttons/sounds/btn-sound-5.wav"
            ), SimulatorGridItem(
                R.drawable.scared_dog,
                "Scared",
                "http://104.236.9.253/cats/cat1/Assets/ui/buttons/sounds/btn-sound-11.wav"
            ), SimulatorGridItem(
                R.drawable.sad_dog,
                "Sad",
                "http://104.236.9.253/cats/cat1/Assets/ui/buttons/sounds/btn-sound-1.wav"
            ), SimulatorGridItem(
                R.drawable.wink_dog,
                "Wink",
                "http://104.236.9.253/cats/cat1/Assets/ui/buttons/sounds/btn-sound-10.wav"
            ), SimulatorGridItem(
                R.drawable.star_dog,
                "Star",
                "http://104.236.9.253/cats/cat1/Assets/ui/buttons/sounds/btn-sound-7.wav"
            ), SimulatorGridItem(
                R.drawable.sick_dog,
                "Sick",
                "http://104.236.9.253/cats/cat1/Assets/ui/buttons/sounds/btn-sound-3.wav"
            ), SimulatorGridItem(
                R.drawable.surprised_dog,
                "Surprised",
                "http://104.236.9.253/cats/cat1/Assets/ui/buttons/sounds/btn-sound-4.wav"
            ), SimulatorGridItem(
                R.drawable.hupnotized_dog,
                "Hypnotized",
                "http://104.236.9.253/cats/cat1/Assets/ui/buttons/sounds/btn-sound-12.wav"
            ), SimulatorGridItem(
                R.drawable.tongue_dog,
                "Tongue",
                "http://104.236.9.253/cats/cat1/Assets/ui/buttons/sounds/btn-sound-9.wav"
            ), SimulatorGridItem(
                R.drawable.fancy_dog,
                "Fancy",
                "http://104.236.9.253/cats/cat1/Assets/ui/buttons/sounds/btn-sound-2.wav"
            )
        )
        _dogEmotionsList.value = items
    }
}