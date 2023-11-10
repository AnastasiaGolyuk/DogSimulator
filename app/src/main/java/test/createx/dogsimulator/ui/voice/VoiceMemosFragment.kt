package test.createx.dogsimulator.ui.voice

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import test.createx.dogsimulator.R
import test.createx.dogsimulator.apadters.AudioListAdapter
import test.createx.dogsimulator.data.models.AudioRecord
import test.createx.dogsimulator.databinding.FragmentVoiceMemosBinding
import test.createx.dogsimulator.record.AudioRecorder
import test.createx.dogsimulator.utils.FragmentUtils
import test.createx.dogsimulator.utils.setItemTouchHelper
import java.io.File


class VoiceMemosFragment : Fragment() {

    private lateinit var audioListAdapter: AudioListAdapter
    private lateinit var recorder: AudioRecorder
    private lateinit var audioFile: File

    private lateinit var viewModelFactory: VoiceMemosViewModelFactory
    private lateinit var viewModel: VoiceMemosViewModel
    private lateinit var binding: FragmentVoiceMemosBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentVoiceMemosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recorder = AudioRecorder(requireContext())

        ActivityCompat.requestPermissions(
            requireActivity(), arrayOf(Manifest.permission.RECORD_AUDIO), 0
        )

        val layoutManager = LinearLayoutManager(context)
        binding.audioList.layoutManager = layoutManager
        audioListAdapter = AudioListAdapter(::deleteFile)
        binding.audioList.adapter = audioListAdapter

        setItemTouchHelper(requireContext(),binding.audioList,audioListAdapter)


        val dir = File(requireActivity().getExternalFilesDir(null), "voice_memos")

        viewModelFactory = VoiceMemosViewModelFactory(recorder, dir)
        viewModel = ViewModelProvider(this, viewModelFactory).get(VoiceMemosViewModel::class.java)

        viewModel.audioRecordsList.observe(viewLifecycleOwner) { items ->
            println(items.size)
            if (items.isNotEmpty()) {
                binding.voiceMemosIntro.visibility = View.GONE
                binding.audioList.visibility = View.VISIBLE
                audioListAdapter.setData(items)
            } else {
                binding.voiceMemosIntro.visibility = View.VISIBLE
                binding.audioList.visibility = View.GONE
            }
        }

        val fragmentManager = requireActivity().supportFragmentManager

        viewModel.isRecording.observe(viewLifecycleOwner) { isRecording ->
            if (isRecording) {
                binding.startRecordingButton.setIconResource(R.drawable.outline_stop_circle_36)
                val num = dir.listFiles()?.size!! + 1
                audioFile =
                    File(requireActivity().getExternalFilesDir(null), "voice_memos/New Voice Memo ${num}.mp3")
                viewModel.setRecordFile(audioFile)
            } else {
                binding.startRecordingButton.setIconResource(R.drawable.square_micro_icon)
                val bundle = Bundle()
                bundle.putString("file_path",audioFile.absolutePath)
                val renameVoiceMemoFragment = RenameVoiceMemoFragment()
                renameVoiceMemoFragment.arguments = bundle
                FragmentUtils.replaceFragment(
                    fragmentManager, renameVoiceMemoFragment
                )
            }
        }

        binding.startRecordingButton.setOnClickListener {
            viewModel.toggleRecorder()
        }
    }

    private fun deleteFile(audioRecord: AudioRecord){
        viewModel.deleteFile(audioRecord)
    }
}