package test.createx.dogsimulator.playback

import android.content.Context
import android.media.MediaPlayer
import androidx.core.net.toUri
import java.io.File

class AudioPlayer(
    private val context: Context
) {

    private var player: MediaPlayer? = null

    public fun playFile(file: File) {
        MediaPlayer.create(context, file.toUri()).apply {
            player = this
            start()
        }
    }

    public fun stop() {
        player?.stop()
        player?.release()
        player = null
    }

    public fun pause() {
        if(player?.isPlaying == true){
            player?.pause()
        } else {
            player?.start()
        }
    }
}