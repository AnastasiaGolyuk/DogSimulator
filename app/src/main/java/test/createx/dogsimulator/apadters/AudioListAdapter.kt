package test.createx.dogsimulator.apadters

import android.media.MediaMetadataRetriever
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import test.createx.dogsimulator.data.models.AudioRecord
import test.createx.dogsimulator.databinding.AudioListItemBinding
import test.createx.dogsimulator.utils.FormatTimeUtils
import java.nio.file.Files
import java.nio.file.attribute.BasicFileAttributes
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter


class AudioListAdapter(
    private val onDeleteClick: (AudioRecord) -> Unit,
    private val onItemClick: (AudioRecord) -> Unit,
    private val onEditClick: (AudioRecord) -> Unit,
    private val onShareClick: (AudioRecord) -> Unit,
) :
    ListAdapter<AudioRecord, AudioListAdapter.AudioRecordViewHolder>(AudioRecordComparator()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AudioRecordViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AudioListItemBinding.inflate(inflater, parent, false)
        return AudioRecordViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: AudioRecordViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)

        holder.itemView.setOnClickListener {
            onItemClick(item)
        }

        holder.binding.deleteAudio.setOnClickListener {
            onDeleteClick(item)
        }

        holder.binding.editNameButton.setOnClickListener {
            onEditClick(item)
        }

        holder.binding.shareAudioButton.setOnClickListener {
            onShareClick(item)
        }

        holder.itemView.scrollTo(0, 0)
    }

    inner class AudioRecordViewHolder(val binding: AudioListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(item: AudioRecord) {
            val attributes: BasicFileAttributes =
                Files.readAttributes(item.file.toPath(), BasicFileAttributes::class.java)
            val creationTime = attributes.creationTime()
            val zonedTime = ZonedDateTime.parse(creationTime.toString())
                .withZoneSameInstant(ZoneId.systemDefault())
            val dateString = zonedTime.format(DateTimeFormatter.ofPattern("d MMM"))
            val timeString = zonedTime.format(DateTimeFormatter.ofPattern("HH:mm"))
            val displayedDateTime = "$dateString at $timeString"

            binding.audioFileName.text = item.file.nameWithoutExtension
            binding.audioCreationTime.text = displayedDateTime

            val mediaMetadataRetriever = MediaMetadataRetriever()
            mediaMetadataRetriever.setDataSource(item.file.absolutePath)
            val duration =
                (mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION))?.toLong()
            binding.audioDuration.text = FormatTimeUtils.formatMilliseconds(duration!!)

            binding.parentLayout.clipToOutline = true
        }
    }

    class AudioRecordComparator : DiffUtil.ItemCallback<AudioRecord>() {
        override fun areItemsTheSame(
            oldItem: AudioRecord, newItem: AudioRecord
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: AudioRecord, newItem: AudioRecord
        ): Boolean {
            return oldItem == newItem
        }
    }
}