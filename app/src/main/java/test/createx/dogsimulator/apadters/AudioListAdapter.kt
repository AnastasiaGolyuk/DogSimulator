package test.createx.dogsimulator.apadters

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.RecyclerView
import test.createx.dogsimulator.data.models.AudioRecord
import test.createx.dogsimulator.databinding.AudioListItemBinding
import test.createx.dogsimulator.ui.voice.BottomSheetPlayerFragment
import test.createx.dogsimulator.ui.voice.RenameVoiceMemoFragment
import test.createx.dogsimulator.utils.FormatTimeUtils
import test.createx.dogsimulator.utils.FragmentUtils
import java.nio.file.Files
import java.nio.file.attribute.BasicFileAttributes
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter


class AudioListAdapter(private val onDeleteCallback: (AudioRecord) -> Unit) :
    RecyclerView.Adapter<AudioListAdapter.AudioRecordViewHolder>() {

    var items = mutableListOf<AudioRecord>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(items: ArrayList<AudioRecord>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AudioRecordViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AudioListItemBinding.inflate(inflater, parent, false)
        return AudioRecordViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: AudioRecordViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        val context = holder.itemView.context
        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("file_path", item.file.absolutePath)
            val bottomSheetPlayerFragment = BottomSheetPlayerFragment()
            bottomSheetPlayerFragment.arguments = bundle
            val fragmentManager = (context as AppCompatActivity).supportFragmentManager
            bottomSheetPlayerFragment.show(fragmentManager, "BottomSheetDialog")
        }

        holder.binding.deleteAudio.setOnClickListener {
            items.removeAt(position)
            notifyItemRemoved(position)
            val itemChangedCount = items.size - position
            notifyItemRangeChanged(position, itemChangedCount)
            onDeleteCallback(item)
        }

        holder.binding.editNameButton.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("file_path", item.file.absolutePath)
            val renameVoiceMemoFragment = RenameVoiceMemoFragment()
            renameVoiceMemoFragment.arguments = bundle
            val fragmentManager = (context as AppCompatActivity).supportFragmentManager
            FragmentUtils.replaceFragment(
                fragmentManager, renameVoiceMemoFragment
            )
        }

        holder.binding.shareAudioButton.setOnClickListener {
            val fileURI = FileProvider.getUriForFile(
                context,
                context.applicationContext.packageName + ".provider",
                item.file
            )
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_STREAM, fileURI)
            intent.type = "audio/*"
            context?.startActivity(Intent.createChooser(intent, "Share To:"))
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
        }
    }
}