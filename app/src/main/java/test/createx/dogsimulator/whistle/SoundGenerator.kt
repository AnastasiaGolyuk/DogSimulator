package test.createx.dogsimulator.whistle

import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioTrack
import kotlin.math.PI
import kotlin.math.sin


class SoundGenerator {

    private var audioTrack: AudioTrack? = null
    private val sampleRate = 44100
    private val duration = 1.0

    fun playSound(frequency: Double) {
        val numSamples = (duration * sampleRate).toInt()

        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            .build()

        val audioFormat = AudioFormat.Builder()
            .setSampleRate(sampleRate)
            .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
            .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
            .build()

        audioTrack = AudioTrack(
            audioAttributes,
            audioFormat,
            numSamples * 2,
            AudioTrack.MODE_STREAM,
            0
        )

        val buffer = ShortArray(numSamples)
        val angularFrequency = 2.0 * PI * frequency

        for (i in 0 until numSamples) {
            val sample =
                (Short.MAX_VALUE * sin(angularFrequency * i / sampleRate)).toInt().toShort()
            buffer[i] = sample
        }

        audioTrack?.write(buffer, 0, buffer.size)
        audioTrack?.play()
    }

    fun stopSound() {
        audioTrack?.stop()
        audioTrack?.release()
        audioTrack = null
    }

}