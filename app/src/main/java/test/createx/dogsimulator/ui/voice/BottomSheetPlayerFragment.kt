package test.createx.dogsimulator.ui.voice

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.Job
import test.createx.dogsimulator.R
import test.createx.dogsimulator.playback.AudioPlayer
import test.createx.dogsimulator.utils.FormatTimeUtils
import java.io.File


class BottomSheetPlayerFragment(private val audioFile: File, context: Context) :
    BottomSheetDialogFragment() {

    private val player: AudioPlayer = AudioPlayer(context)

    private var seekBar: SeekBar? = null

    private val handler: Handler = Handler(Looper.getMainLooper())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bottom_sheet_player, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title = view.findViewById<TextView>(R.id.audioTitleTextView)
        val playedTime = view.findViewById<TextView>(R.id.audioPlayedTimeTextView)
        val remainedTime = view.findViewById<TextView>(R.id.audioRemainedTimeTextView)
        seekBar = view.findViewById(R.id.audioSeekBar)
        val playButton = view.findViewById<MaterialButton>(R.id.audioPlayButton)

        player.initPlayer(audioFile)

        playedTime.text = FormatTimeUtils.formatMilliseconds(0)
        remainedTime.text = FormatTimeUtils.formatMilliseconds(player.getDuration().toLong())
        title.text = audioFile.nameWithoutExtension


        seekBar?.max = player.getDuration()


        handler.postDelayed(object : Runnable {
            override fun run() {
                updateSeekBar()
                playedTime.text =
                    FormatTimeUtils.formatMilliseconds(player.getCurrentPosition().toLong())
                val remainedTimeMillis = player.getDuration() - player.getCurrentPosition()
                remainedTime.text = FormatTimeUtils.formatMilliseconds(remainedTimeMillis.toLong())
                if (!player.isPlaying()) {
                    playButton.setIconResource(R.drawable.baseline_play_arrow_24)
                }
                handler.postDelayed(this, 80)
            }
        }, 0)

        playButton.setOnClickListener {
            if (player.isPlaying()) {
                player.pause()
                playButton.setIconResource(R.drawable.baseline_play_arrow_24)
            } else {
                player.play()
                playButton.setIconResource(R.drawable.baseline_pause_24)
            }
        }

        seekBar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    player.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                player.pause()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                player.play()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeMessages(0)
        player.stop()
    }


    private fun updateSeekBar() {
        if (player.isPlaying()) {
            seekBar?.progress = player.getCurrentPosition()
        }
    }
}