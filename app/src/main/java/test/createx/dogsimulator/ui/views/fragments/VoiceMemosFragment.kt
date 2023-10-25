package test.createx.dogsimulator.ui.views.fragments

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import test.createx.dogsimulator.R
import test.createx.dogsimulator.apadters.AudioListAdapter
import test.createx.dogsimulator.data.models.AudioRecord
import test.createx.dogsimulator.record.AudioRecorder
import java.io.File


class VoiceMemosFragment(private val context: Context) : Fragment(),ActivityCompat.OnRequestPermissionsResultCallback {

    private val audioRecords = mutableListOf<AudioRecord>()
    private lateinit var audioListAdapter: AudioListAdapter
    private val recorder: AudioRecorder = AudioRecorder(context)
    private var audioFile: File? = null
    private var isRecording = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_voice_memos, container, false)
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
            introView.visibility=View.GONE
            audioRecyclerView.visibility=View.VISIBLE
            for (file in dir.listFiles()!!) {
                audioRecords.add(AudioRecord(file))
            }
            audioListAdapter = AudioListAdapter(requireContext(), audioRecords)

            audioRecyclerView.adapter = audioListAdapter
        } else {
            introView.visibility=View.VISIBLE
            audioRecyclerView.visibility=View.GONE
        }


        val startRecordingButton = view.findViewById<MaterialButton>(R.id.startRecordingButton)

        startRecordingButton.setOnClickListener {
            if (isRecording) {
                startRecordingButton.setIconResource(R.drawable.square_micro_icon)
                recorder.stop()
                isRecording = false

            } else {
                startRecordingButton.setIconResource(R.drawable.outline_stop_36)
                isRecording = true
                val num = dir.listFiles()?.size!! +1
                File(requireActivity().cacheDir, "voice_memos/audio${num}.mp3").also {
                    recorder.start(it)
                    audioFile = it
                }
            }
        }
        return view
    }

//    private fun refreshListView(dir:File, introView: LinearLayout, audioListView: ListView) {
//        if (dir.listFiles()?.size!! > 0) {
//            introView.visibility=View.GONE
//            audioListView.visibility=View.VISIBLE
//            for (file in dir.listFiles()!!) {
//                audioRecords.add(AudioRecord(file))
//            }
//            audioListAdapter = AudioListAdapter(requireContext(), audioRecords)
//
//            audioListView.adapter = audioListAdapter
//        } else {
//            introView.visibility=View.VISIBLE
//            audioListView.visibility=View.GONE
//        }
//    }


}