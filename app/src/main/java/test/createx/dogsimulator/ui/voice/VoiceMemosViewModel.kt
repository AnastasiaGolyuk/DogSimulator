package test.createx.dogsimulator.ui.voice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import test.createx.dogsimulator.data.models.AudioRecord
import java.io.File

class VoiceMemosViewModel : ViewModel() {

    private val _audioRecordsList = MutableLiveData<List<AudioRecord>>()
    val audioRecordsList: LiveData<List<AudioRecord>> = _audioRecordsList

    //    private val recorder: AudioRecorder = AudioRecorder(context)
    private var audioFile: File? = null
    private var isRecording = false


    private fun getAudioRecordsList() {
//        val dir = File(requireActivity().cacheDir, "voice_memos")
//        dir.mkdir()
//        val items = arrayListOf<AudioRecord>()
//        if (dir.listFiles()?.size!! > 0) {
//            for (file in dir.listFiles()!!) {
//                items.add(AudioRecord(file))
//            }
//        }
    }
}