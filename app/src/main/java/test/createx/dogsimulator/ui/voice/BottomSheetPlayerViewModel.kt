package test.createx.dogsimulator.ui.voice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import test.createx.dogsimulator.playback.AudioPlayer
import test.createx.dogsimulator.utils.FormatTimeUtils

class BottomSheetPlayerViewModel(audioPlayer: AudioPlayer) : ViewModel() {

    private val player: AudioPlayer

    private var _isPlaying = MutableLiveData<Boolean>()
    val isPlaying: LiveData<Boolean> = _isPlaying

    private var taskJob: Job? = null

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

//    fun initiateJob() {
//        cancelJob()
//
//
//        taskJob = viewModelScope.launch {
//
//            delay(80)
//            if (player.isPlaying()) {
//                updateSeekBar()
//                binding.audioPlayedTimeTextView.text =
//                    FormatTimeUtils.formatMilliseconds(player.getCurrentPosition().toLong())
//                val remainedTimeMillis = player.getDuration() - player.getCurrentPosition()
//                binding.audioRemainedTimeTextView.text =
//                    FormatTimeUtils.formatMilliseconds(remainedTimeMillis.toLong())
//            }
//        }
//    }

   fun cancelJob() {
        taskJob?.cancel()
    }
}