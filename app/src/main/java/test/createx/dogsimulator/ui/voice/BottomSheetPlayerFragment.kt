package test.createx.dogsimulator.ui.voice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import test.createx.dogsimulator.R
import test.createx.dogsimulator.databinding.FragmentBottomSheetPlayerBinding
import test.createx.dogsimulator.playback.AudioPlayer
import test.createx.dogsimulator.utils.FormatTimeUtils
import java.io.File


class BottomSheetPlayerFragment :
    BottomSheetDialogFragment() {

    private lateinit var player: AudioPlayer

    private lateinit var seekBar: SeekBar

    private lateinit var audioFile: File

    private lateinit var viewModel: BottomSheetPlayerViewModel

    private lateinit var viewModelFactory: BottomSheetPlayerViewModelFactory

    private lateinit var binding: FragmentBottomSheetPlayerBinding

    private var taskJob: Job? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBottomSheetPlayerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val filePath = arguments?.getString("file_path")
        audioFile = File(filePath!!)

        player = AudioPlayer(requireContext())
        player.initPlayer(audioFile)

        viewModelFactory = BottomSheetPlayerViewModelFactory(player)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(BottomSheetPlayerViewModel::class.java)

        seekBar = binding.audioSeekBar
        binding.audioPlayedTimeTextView.text = FormatTimeUtils.formatMilliseconds(0)
        binding.audioRemainedTimeTextView.text =
            FormatTimeUtils.formatMilliseconds(viewModel.getDuration().toLong())
        binding.audioTitleTextView.text = audioFile.nameWithoutExtension

        seekBar.max = viewModel.getDuration()

        binding.audioPlayButton.setOnClickListener {
            viewModel.togglePlayer()
        }

        viewModel.isPlaying.observe(viewLifecycleOwner){isPlaying ->
            if (isPlaying){
                initiateJob()
                binding.audioPlayButton.setIconResource(R.drawable.baseline_pause_24)
            } else {
                cancelJob()
                binding.audioPlayButton.setIconResource(R.drawable.baseline_play_arrow_24)
            }
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    viewModel.updatePlayerSeekPosition(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                viewModel.pausePlayer()
                viewModel.togglePlayer()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                viewModel.playPlayer()
                viewModel.togglePlayer()
            }
        })
    }

    private fun initiateJob() {
        cancelJob()
        taskJob = lifecycleScope.launch {
            while (true) {
                delay(80)
                if (viewModel.isPlaying()) {
                    updateSeekBar()
                    binding.audioPlayedTimeTextView.text =
                        FormatTimeUtils.formatMilliseconds(viewModel.getCurrentPosition().toLong())
                    val remainedTimeMillis = viewModel.getDuration() - viewModel.getCurrentPosition()
                    binding.audioRemainedTimeTextView.text =
                        FormatTimeUtils.formatMilliseconds(remainedTimeMillis.toLong())
                }
                else {
                    binding.audioPlayButton.setIconResource(R.drawable.baseline_play_arrow_24)
                    viewModel.togglePlayer()
                }
            }
        }
    }

    private fun cancelJob() {
        taskJob?.cancel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.stopPlayer()
        cancelJob()
    }

    private fun updateSeekBar() {
        seekBar.progress = viewModel.getCurrentPosition()
    }
}