package test.createx.dogsimulator.ui.voice

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import test.createx.dogsimulator.R
import test.createx.dogsimulator.apadters.AudioListAdapter
import test.createx.dogsimulator.data.models.AudioRecord
import test.createx.dogsimulator.databinding.FragmentFourthSlideBinding
import test.createx.dogsimulator.databinding.FragmentVoiceMemosBinding
import test.createx.dogsimulator.record.AudioRecorder
import test.createx.dogsimulator.ui.onboarding.FourthSlideViewModel
import test.createx.dogsimulator.ui.onboarding.FourthSlideViewModelFactory
import test.createx.dogsimulator.utils.FragmentUtils
import java.io.File


class VoiceMemosFragment(
    private val context: Context,
    private val fragmentManager: FragmentManager
) : Fragment() {

    private val audioRecords = mutableListOf<AudioRecord>()
    private lateinit var audioListAdapter: AudioListAdapter
    private val recorder: AudioRecorder = AudioRecorder(context)
    private var audioFile: File? = null
    private var isRecording = false

    private lateinit var viewModelFactory: VoiceMemosViewModelFactory
    private lateinit var viewModel: VoiceMemosViewModel
    private lateinit var binding: FragmentVoiceMemosBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVoiceMemosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelFactory = VoiceMemosViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(VoiceMemosViewModel::class.java)

        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.RECORD_AUDIO),
            0
        )

        val audioRecyclerView = view.findViewById<RecyclerView>(R.id.audioList)
        val layoutManager = LinearLayoutManager(context)
        audioRecyclerView.layoutManager = layoutManager
        val introView = view.findViewById<LinearLayout>(R.id.voiceMemosIntro)

        val dir = File(requireActivity().cacheDir, "voice_memos")
        dir.mkdir()

        if (dir.listFiles()?.size!! > 0) {
            introView.visibility = View.GONE
            audioRecyclerView.visibility = View.VISIBLE
            for (file in dir.listFiles()!!) {
                audioRecords.add(AudioRecord(file))
            }
            audioListAdapter = AudioListAdapter(requireContext(), audioRecords, fragmentManager)
            audioRecyclerView.adapter = audioListAdapter
        } else {
            introView.visibility = View.VISIBLE
            audioRecyclerView.visibility = View.GONE
        }

        val startRecordingButton = view.findViewById<MaterialButton>(R.id.startRecordingButton)

        startRecordingButton.setOnClickListener {
            if (isRecording) {
                startRecordingButton.setIconResource(R.drawable.square_micro_icon)
                recorder.stop()
                FragmentUtils.replaceFragment(
                    fragmentManager,
                    RenameVoiceMemoFragment(fragmentManager, audioFile!!)
                )
                isRecording = false
            } else {
                startRecordingButton.setIconResource(R.drawable.outline_stop_circle_36)
                isRecording = true
                val num = dir.listFiles()?.size!! + 1
                File(requireActivity().cacheDir, "voice_memos/New Voice Memo ${num}.mp3").also {
                    recorder.start(it)
                    audioFile = it
                }
            }
        }
    }
}