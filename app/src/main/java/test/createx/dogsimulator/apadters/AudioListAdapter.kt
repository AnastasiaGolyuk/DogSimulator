package test.createx.dogsimulator.apadters

import android.content.Context
import android.media.MediaMetadataRetriever
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import test.createx.dogsimulator.R
import test.createx.dogsimulator.data.models.AudioRecord
import test.createx.dogsimulator.ui.voice.BottomSheetPlayerFragment
import test.createx.dogsimulator.utils.FormatTimeUtils
import java.nio.file.Files
import java.nio.file.attribute.BasicFileAttributes
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter


class AudioListAdapter(
    private val context: Context,
    private val audioRecords: List<AudioRecord>,
    private val fragmentManager: FragmentManager
) :
    RecyclerView.Adapter<AudioListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val audioName: TextView = itemView.findViewById(R.id.audioFileName)
        val audioCreationTime: TextView = itemView.findViewById(R.id.audioCreationTime)
        val audioDuration: TextView = itemView.findViewById(R.id.audioDuration)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.audio_list_item, parent, false)
        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val audioRecord = audioRecords[position]
        val attributes: BasicFileAttributes =
            Files.readAttributes(audioRecord.file.toPath(), BasicFileAttributes::class.java)
        val creationTime = attributes.creationTime()
        val zonedTime = ZonedDateTime.parse(creationTime.toString())
            .withZoneSameInstant(ZoneId.systemDefault())
        val dateString = zonedTime.format(DateTimeFormatter.ofPattern("d MMM"))
        val timeString = zonedTime.format(DateTimeFormatter.ofPattern("HH:mm"))
        val displayedDateTime = "$dateString at $timeString"

        holder.audioName.text = audioRecord.file.nameWithoutExtension
        holder.audioCreationTime.text = displayedDateTime

        val mediaMetadataRetriever = MediaMetadataRetriever()
        mediaMetadataRetriever.setDataSource(audioRecord.file.absolutePath)
        val duration =
            (mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION))?.toLong()
        holder.audioDuration.text = FormatTimeUtils.formatMilliseconds(duration!!)

        holder.itemView.setOnClickListener {
            val bottomSheetPlayerFragment = BottomSheetPlayerFragment(audioRecord.file, context)
            bottomSheetPlayerFragment.show(fragmentManager, "BottomSheetDialog")
        }
    }

    override fun getItemCount(): Int {
        return audioRecords.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}