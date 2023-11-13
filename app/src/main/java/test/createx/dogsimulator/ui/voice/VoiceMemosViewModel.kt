package test.createx.dogsimulator.ui.voice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import test.createx.dogsimulator.data.models.AudioRecord
import test.createx.dogsimulator.record.AudioRecorder
import java.io.File

class VoiceMemosViewModel(audioRecorder: AudioRecorder, readDir: File) :
    ViewModel() {

    private val _audioRecordsList = MutableLiveData<ArrayList<AudioRecord>>()
    val audioRecordsList: LiveData<ArrayList<AudioRecord>> = _audioRecordsList

    private var _isRecording = MutableLiveData<Boolean>()
    val isRecording: LiveData<Boolean> = _isRecording

    private val _recordFile = MutableLiveData<File>()

    private var recorder: AudioRecorder
    private var audioDir: File

    init {
        audioDir = readDir
        recorder = audioRecorder
        getAudioRecordsList()
    }

    fun deleteFile(audioRecord: AudioRecord) {
        audioRecord.file.delete()
        getAudioRecordsList()
    }

    fun setRecordFile(file: File) {
        _recordFile.value = file
        _recordFile.value.also {
            recorder.start(it!!)
        }
    }

    fun toggleRecorder() {
        if (_isRecording.value == true) {
            recorder.stop()
            _isRecording.value = false
        } else {
            _isRecording.value = true
        }
    }


    private fun getAudioRecordsList() {
        viewModelScope.launch {
            val items = arrayListOf<AudioRecord>()
            if (!audioDir.mkdir() && audioDir.listFiles()?.size!! > 0) {
                for (file in audioDir.listFiles()!!) {
                    items.add(AudioRecord(file))
                }
            }
            _audioRecordsList.value = items
        }
    }
}