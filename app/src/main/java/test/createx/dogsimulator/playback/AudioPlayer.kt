package test.createx.dogsimulator.playback

import android.content.Context
import android.media.MediaPlayer
import androidx.core.net.toUri
import java.io.File

class AudioPlayer(
    private val context: Context
) {

    private var player: MediaPlayer? = null

    fun initPlayer(file: File) {
        MediaPlayer.create(context, file.toUri()).apply {
            player = this
        }
    }

    fun stop() {
        player?.stop()
        player?.release()
        player = null
    }

    fun play() {
        player?.start()
    }

    fun pause() {
        player?.pause()
    }

    fun isPlaying(): Boolean {
        return player?.isPlaying == true
    }

    fun getCurrentPosition(): Int {
        return player?.currentPosition!!
    }

    fun getDuration(): Int {
        return player?.duration!!
    }

    fun seekTo(progress: Int) {
        player?.seekTo(progress)
    }
}