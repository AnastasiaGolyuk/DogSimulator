package test.createx.dogsimulator.ui.voice

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import test.createx.dogsimulator.R
import test.createx.dogsimulator.apadters.AudioListAdapter
import test.createx.dogsimulator.data.models.AudioRecord
import test.createx.dogsimulator.databinding.FragmentVoiceMemosBinding
import test.createx.dogsimulator.record.AudioRecorder
import test.createx.dogsimulator.utils.FragmentUtils
import test.createx.dogsimulator.utils.ItemTouchHelper
import java.io.File
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


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
            requireActivity(),
            arrayOf(
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ),
            0
        )

        val layoutManager = LinearLayoutManager(context)
        binding.audioList.layoutManager = layoutManager
        audioListAdapter = AudioListAdapter(::deleteFile, ::playFile, ::editFileName, ::shareFile)
        binding.audioList.adapter = audioListAdapter

        ItemTouchHelper.setItemTouchHelper(requireContext(), binding.audioList, audioListAdapter)

        val dir = File(requireActivity().getExternalFilesDir(null), "voice_memos")

        viewModelFactory = VoiceMemosViewModelFactory(recorder, dir)
        viewModel = ViewModelProvider(this, viewModelFactory).get(VoiceMemosViewModel::class.java)

        viewModel.audioRecordsList.observe(viewLifecycleOwner) { items ->
            if (items.isNotEmpty()) {
                binding.voiceMemosIntro.visibility = View.GONE
                binding.audioList.visibility = View.VISIBLE
                audioListAdapter.submitList(items)
            } else {
                binding.voiceMemosIntro.visibility = View.VISIBLE
                binding.audioList.visibility = View.GONE
            }
        }

        val fragmentManager = requireActivity().supportFragmentManager

        viewModel.isRecording.observe(viewLifecycleOwner) { isRecording ->
            if (isRecording) {
                binding.startRecordingButton.setIconResource(R.drawable.outline_stop_circle_36)
                val time = Calendar.getInstance().time
                val formatter = SimpleDateFormat("yyyy-MM-dd HH-mm-ss", Locale.getDefault(Locale.Category.FORMAT))
                val current = formatter.format(time)
                audioFile =
                    File(
                        requireActivity().getExternalFilesDir(null),
                        "voice_memos/New Voice Memo ${current}.mp3"
                    )
                viewModel.setRecordFile(audioFile)
            } else {
                binding.startRecordingButton.setIconResource(R.drawable.square_micro_icon)
                val bundle = Bundle()
                bundle.putString("file_path", audioFile.absolutePath)
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

    private fun deleteFile(audioRecord: AudioRecord) {
        viewModel.deleteFile(audioRecord)
    }

    private fun editFileName(audioRecord: AudioRecord) {
        val bundle = Bundle()
        bundle.putString("file_path", audioRecord.file.absolutePath)
        val renameVoiceMemoFragment = RenameVoiceMemoFragment()
        renameVoiceMemoFragment.arguments = bundle
        val fragmentManager = (context as AppCompatActivity).supportFragmentManager
        FragmentUtils.replaceFragment(
            fragmentManager, renameVoiceMemoFragment
        )
    }

    private fun shareFile(audioRecord: AudioRecord) {
        val fileURI = FileProvider.getUriForFile(
            requireContext(),
            requireContext().applicationContext.packageName + ".provider",
            audioRecord.file
        )
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.putExtra(Intent.EXTRA_STREAM, fileURI)
        intent.type = "audio/*"
        context?.startActivity(Intent.createChooser(intent, "Share To:"))
    }

    private fun playFile(audioRecord: AudioRecord) {
        val bundle = Bundle()
        bundle.putString("file_path", audioRecord.file.absolutePath)
        val bottomSheetPlayerFragment = BottomSheetPlayerFragment()
        bottomSheetPlayerFragment.arguments = bundle
        val fragmentManager = (context as AppCompatActivity).supportFragmentManager
        bottomSheetPlayerFragment.show(fragmentManager, "BottomSheetDialog")
    }
}