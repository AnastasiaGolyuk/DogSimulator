package test.createx.dogsimulator.ui.voice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import test.createx.dogsimulator.playback.AudioPlayer

class BottomSheetPlayerViewModel(audioPlayer: AudioPlayer) : ViewModel() {

    private val player: AudioPlayer

    private var _isPlaying = MutableLiveData<Boolean>()
    val isPlaying: LiveData<Boolean> = _isPlaying

    init {
        player=audioPlayer
    }

    fun togglePlayer() {
        if (_isPlaying.value == true) {
            pausePlayer()
            _isPlaying.value = false
        } else {
            playPlayer()
            _isPlaying.value = true
        }
    }

    fun updatePlayerSeekPosition(progress: Int) {
        player.seekTo(progress)
    }

    fun pausePlayer() {
        player.pause()
    }

    fun playPlayer() {
        player.play()
    }

    fun stopPlayer() {
        player.stop()
    }

    fun getDuration(): Int {
        return player.getDuration()
    }

    fun getCurrentPosition(): Int {
        return player.getCurrentPosition()
    }

    fun isPlaying(): Boolean {
        return player.isPlaying()
    }
}