package test.createx.dogsimulator.ui.translator

import android.media.MediaPlayer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.io.IOException

class TranslatorViewModel : ViewModel() {

    private val _checkedId = MutableLiveData<Int>()
    val checkedId: LiveData<Int> = _checkedId

    private var mediaPlayer: MediaPlayer? = MediaPlayer()

    private val _isPlaying = MutableLiveData<Boolean>()
    val isPlaying: LiveData<Boolean> = _isPlaying

    fun setId(id: Int) {
        _checkedId.value = id
    }

    fun togglePlayer() {
        _isPlaying.value = _isPlaying.value != true
    }

    fun playSound(path: String, completionListener: () -> Unit) {
        try {
            if (mediaPlayer != null) {
                if (mediaPlayer?.isPlaying == true) {
                    mediaPlayer?.stop()
                }
                mediaPlayer?.reset()
            }
            mediaPlayer?.setDataSource(path)
            mediaPlayer?.prepare()
            mediaPlayer?.start()
            mediaPlayer?.setOnCompletionListener {
                completionListener()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        }
    }

    fun stopPlayer() {
        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.stop()
        }
    }

    fun releasePlayer() {
        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.stop()
        }
        mediaPlayer?.reset()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}